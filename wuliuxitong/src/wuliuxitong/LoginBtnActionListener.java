package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;

public class LoginBtnActionListener implements ActionListener {

	Denglu denglu;
	
	public LoginBtnActionListener(Denglu dl){
		this.denglu = dl;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		int count = 0;
		try {
//			resultSet = DBManager.getInstance("mysql", "root", "aa112233").excuteQuery("select ID, PWD from �û���;");
//			count = DBManager.getInstance("mysql", "root", "aa112233").getResultSetCount("select ID, PWD from �û���;");
			
			resultSet = DBManager.getInstance().excuteQuery("select ID, PWD from �û���;");
			count = DBManager.getInstance().getResultSetCount("select ID, PWD from �û���;");
			
			while(count != 0){
				resultSet.next();
//���Դ���
//				String ag = account.getText();
//				String rg = resultSet.getObject("ID").toString();
//				String pg = password.getPassword().toString();
//				String rgg = resultSet.getObject("PWD").toString();
				if(this.denglu.getAccount().getText().equals(resultSet.getObject("ID")) && (new String(this.denglu.getPassword().getPassword())).equals(resultSet.getObject("PWD"))){
					this.denglu.setVisible(false);//���ڴ˲�����new ActionListener�ڣ����ܷ���thisָ�룬�����ܷ��ʱ���jframe
					new Zhujiemian();
				}
				count--;
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
