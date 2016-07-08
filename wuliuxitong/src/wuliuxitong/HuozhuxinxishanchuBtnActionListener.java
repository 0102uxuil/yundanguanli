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
		int jop = JOptionPane.showConfirmDialog(this.hzxxd, "确定删除该货主信息？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_huozhushanchu;
			sql_huozhushanchu = "delete from huozhuxinxi where "
					+ " huozhuming = " + "'" + this.hzxxd.huozhuming.getText().trim() + "'" 
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql_huozhushanchu);
				this.hzxxd.dispose();
				this.hzxxd.hzxxp.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "该货主信息已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
