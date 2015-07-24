package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class YuekaixiaoshanchuBtnActionListener implements ActionListener {

	YueKaiXiaoDan yuekaixiaodan;
	
	YuekaixiaoshanchuBtnActionListener(YueKaiXiaoDan ykxd){
		this.yuekaixiaodan = ykxd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.yuekaixiaodan, "确定删除该月开销单？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_yuekaixiaodan;
			sql_yuekaixiaodan = "delete from yuekaixiaodan where "
					+ " chepaihao = " + "'" + this.yuekaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'" 
					+ " and riqi = " + "'" + this.yuekaixiaodan.date.getDate() + "'" 
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql_yuekaixiaodan);
				this.yuekaixiaodan.dispose();
				this.yuekaixiaodan.yuekaixiaoPanel.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "该月开销单已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
