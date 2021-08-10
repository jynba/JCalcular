import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.math.BigDecimal;

public class Calcular {
	String str1 = "0";
	
	String str2 = "0";
	
	String signal = "+";
	
	String result = "";
	
	int choose = 1;
	
	int record = 1;
	
	int clear1 = 1;
	
	int clear2 = 1;
	
	int ifdecimal = 1;//判断小数
	
	JButton store;
	
	@SuppressWarnings("rawtypes")//leave out warnings;
	Vector vt = new Vector(20,10);
	
	JFrame frame = new JFrame("Calcular");
	JTextField result_TextField = new JTextField(result,20);
	JButton clear_Button = new JButton("clear");
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton button_dol = new JButton(".");
	JButton button_add = new JButton("+");
	JButton button_decline = new JButton("-");
	JButton button_mul = new JButton("*");
	JButton button_devide = new JButton("/");
	JButton button_equal = new JButton("=");	

	//实现热键的设置
	public Calcular(){
		button0.setMnemonic(KeyEvent.VK_0);
		button1.setMnemonic(KeyEvent.VK_1);
		button2.setMnemonic(KeyEvent.VK_2);
		button3.setMnemonic(KeyEvent.VK_3);
		button4.setMnemonic(KeyEvent.VK_4);
		button5.setMnemonic(KeyEvent.VK_5);
		button6.setMnemonic(KeyEvent.VK_6);
		button7.setMnemonic(KeyEvent.VK_7);
		button8.setMnemonic(KeyEvent.VK_8);
		button9.setMnemonic(KeyEvent.VK_9);
		button_dol.setMnemonic(KeyEvent.VK_COLON);//?
		button_add.setMnemonic(KeyEvent.VK_ADD);
		button_decline.setMnemonic(KeyEvent.VK_SUBTRACT);
		button_mul.setMnemonic(KeyEvent.VK_MULTIPLY);
		button_devide.setMnemonic(KeyEvent.VK_DIVIDE);
		button_equal.setMnemonic(KeyEvent.VK_EQUALS);
		
	//右对齐
		result_TextField.setHorizontalAlignment(JTextField.RIGHT);
		
	//添加ui元素
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(4,4,5,5));
		pan.add(button7);
		pan.add(button8);
		pan.add(button9);
		pan.add(button_devide);
		pan.add(button4);
		pan.add(button5);
		pan.add(button6);
		pan.add(button_mul);
		pan.add(button1);
		pan.add(button2);
		pan.add(button3);
		pan.add(button_decline);
		pan.add(button0);
		pan.add(button_dol);
		pan.add(button_equal);
		pan.add(button_add);
		pan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		//新开一个JPanel显示clear 和 result
		JPanel pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		pan2.add(result_TextField,BorderLayout.NORTH);
		pan2.add(clear_Button,BorderLayout.EAST);
		
		//整体框架布局
		frame.setLocation(300, 300);
		//不允许改变框架大小
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pan2, BorderLayout.NORTH);
		frame.getContentPane().add(pan, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		
		class Listener implements ActionListener{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String ss = ((JButton) e.getSource()).getText();
				vt.add(store);
				if(choose == 1) {
					if(clear1 == 1) {
						str1 = "";
						
						ifdecimal = 1;
					}
					str1 = str1 + ss;
					clear1 = clear1 +1;
					//展示结果
					result_TextField.setText(str1);
				}else if(choose == 2) {
					if(clear2 == 1) {
						str2 = "";
						
						ifdecimal = 1;
					}
					str2 = str2 + ss;
					clear2 = clear2 + 1;
					result_TextField.setText(str2);
				}
			}
		}
		
		class Listener_signal implements ActionListener{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String ss2 = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);
				
				if(record == 1) {
					choose = 2;
					ifdecimal = 1;
					signal = ss2;
					record = record +1;
				}else {
					int a = vt.size();
					JButton c =(JButton) vt.get(a-2);
					
					if(!(c.getText().equals("+")) && !(c.getText().equals("-")) && !(c.getText().equals("*")) && !(c.getText().equals("/")))
					{
						cal();
						str1 = result;
						
						choose = 2;
						ifdecimal =1;
						clear2 = 1;
						signal = ss2;
					}
					record = record + 1;
				}
			}
		}
		
		class listener_clear implements ActionListener{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				store = (JButton) e.getSource();
				vt.add(store);
				
				choose = 1;				
				record = 1;				
				clear1 = 1;			
				clear2 = 1;				
				ifdecimal = 1;
				str1 = "0";
				str2 = "0";
				signal = "";
				result = "";
				result_TextField.setText(result);
				vt.clear();
				
			}
		}
		
		class listener_equals implements ActionListener{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				store = (JButton) e.getSource();
				vt.add(store);
				cal();
				choose = 1;				
				record = 1;				
				clear1 = 1;			
				clear2 = 1;	
				
				str1 = result;
			}
		}
		
		class listener_ifdecimal implements ActionListener{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				store = (JButton) e.getSource();
				vt.add(store);
				if(ifdecimal == 1) {
					String ss2 = ((JButton) e.getSource()).getText();
					if(choose == 1) {
						if(clear1 == 1) {
							str1 = "";
							
							ifdecimal = 1;
							
						}
						str1 = str1 + ss2;
						
						clear1 =clear1 +1;
						
						result_TextField.setText(str1);
					}else if(choose == 2) {
						if( clear2 == 1){
							str2 = "";
							
							ifdecimal = 1;
						}
						str2 = str2 + ss2;
						
						clear2 = clear2 + 1;
						
						result_TextField.setText(str2);
					}
				}
				ifdecimal = ifdecimal +1;
			}
		}
		
		listener_equals jt_dy = new listener_equals();
		
		Listener jt = new Listener();
		
		Listener_signal jt_signal = new Listener_signal();
		
		listener_clear jt_c = new listener_clear();
		
		listener_ifdecimal jt_xs = new listener_ifdecimal();
		
		button7.addActionListener(jt);
		button8.addActionListener(jt);
		button9.addActionListener(jt);
		button_devide.addActionListener(jt_signal);
		button4.addActionListener(jt);
		button5.addActionListener(jt);
		button6.addActionListener(jt);
		button_mul.addActionListener(jt_signal);
		button1.addActionListener(jt);
		button2.addActionListener(jt);
		button3.addActionListener(jt);
		button_decline.addActionListener(jt_signal);
		button0.addActionListener(jt);
		button_dol.addActionListener(jt_xs);
		button_equal.addActionListener(jt_dy);
		button_add.addActionListener(jt_signal);
		clear_Button.addActionListener(jt_c);
		
		//响应windows的关闭
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void cal() {
		double a2;
		double b2;
		String c = signal;
		double result2 = 0;
		if(c.equals("")) {
			result_TextField.setText("Please input operator");
		}else {
			if( str1.equals("."))
				str1 = "0.0";
			if( str2.endsWith("."))
				str2 = "0.0";
			a2 = Double.valueOf(str1).doubleValue();
			b2 = Double.valueOf(str2).doubleValue();
			
			if(c.equals("+"))
				result2 = a2 + b2;
			if(c.equals("-"))
				result2 = a2 - b2;
			if(c.equals("*")){
				BigDecimal m1 = new BigDecimal(Double.toString(a2));
				BigDecimal m2 = new BigDecimal(Double.toString(b2));
				result2 = m1.multiply(m2).doubleValue();
			}
			if(c.equals("/")){
				if(b2 == 0) {
					result2 = 0;
				}else {
					result2 = a2/b2;
				}
			}
			result = ((new Double(result2)).toString());
			
			result_TextField.setText(result);
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calcular cal = new Calcular();
	}
}