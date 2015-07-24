package wuliuxitong;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;

import wuliuxitong.DBManager;

public class Denglu extends JFrame {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Denglu();
//	}
	
//	private String acc="用户名：";
//	private String pwd="密码：";
	private JTextField account;
	private JPasswordField password;
	private JButton login;
	private JButton exit;
	
	
	
	public JTextField getAccount() {
		return account;
	}



	public void setAccount(JTextField account) {
		this.account = account;
	}



	public JPasswordField getPassword() {
		return password;
	}



	public void setPassword(JPasswordField password) {
		this.password = password;
	}



	public  Denglu() {
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("用户名："), c);
		
		c.gridx++;
		this.account = new JTextField(15);
		this.add(account, c);
		
		c.gridx = 0;
		c.gridy++;
		this.add(new JLabel("密码："), c);
		
		c.gridx++;
		this.password = new JPasswordField(15);
		this.add(password, c);
		
		c.gridx=0;
		c.gridy++;
		c.gridwidth = 2;
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		login = new JButton("登录");
//		login.addActionListener(
//				new ActionListener(){
//					@Override
//					public void actionPerformed(ActionEvent e){
//						ResultSet resultSet = null;
//						int count = 0;
//						try {
//							resultSet = DBManager.getInstance("mysql", "root", "aa112233").excuteQuery("select ID, PWD from 用户表;");
//							count = DBManager.getInstance("mysql", "root", "aa112233").getResultSetCount("select ID, PWD from 用户表;");
//							while(count != 0){
//								resultSet.next();
//								String ag = account.getText();
//								String rg = resultSet.getObject("ID").toString();
//								String pg = password.getPassword().toString();
//								String rgg = resultSet.getObject("PWD").toString();
//								if(account.getText().equals(resultSet.getObject("ID")) && (new String(password.getPassword())).equals(resultSet.getObject("PWD"))){
//									jframe.setVisible(false);//由于此部分在new ActionListener内，不能访问this指针，但是能访问变量jframe
//									new Zhujiemian();
//								}
//								count--;
//							}
//						} catch (Exception ex) {
//							// TODO Auto-generated catch block
//							ex.printStackTrace();
//						}
//					}
//				}
//				);
		login.addActionListener(new LoginBtnActionListener(this));
		p.add(login);
		
		c.gridx++;
		exit = new JButton("退出");
		exit.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					}
				}
				);
		p.add(exit);
		this.add(p, c);
		
		pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
