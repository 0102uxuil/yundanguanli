package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class HuozhuxinxishanchuBtnActionListener implements ActionListener {

	HuoZhuXinXiDan hzxxd;
	
	HuozhuxinxishanchuBtnActionListener(HuoZhuXinXiDan hd){
		this.hzxxd = hd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.hzxxd, "ȷ��ɾ���û�����Ϣ��", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_huozhushanchu;
			sql_huozhushanchu = "delete from huozhuxinxi where "
					+ " huozhuming = " + "'" + this.hzxxd.huozhuming.getText().trim() + "'" 
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql_huozhushanchu);
				this.hzxxd.dispose();
				this.hzxxd.hzxxp.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "�û�����Ϣ��ɾ����", "ɾ��", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
