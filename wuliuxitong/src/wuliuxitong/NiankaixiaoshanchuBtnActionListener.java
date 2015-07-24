package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class NiankaixiaoshanchuBtnActionListener implements ActionListener {

	NianKaiXiaoDan niankaixiaodan;
	
	NiankaixiaoshanchuBtnActionListener(NianKaiXiaoDan nkxd){
		this.niankaixiaodan = nkxd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.niankaixiaodan, "确定删除该年开销单？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_niankaixiaodan;
			sql_niankaixiaodan = "delete from niankaixiaodan where "
//					+ " chepaihao = " + "'" + this.niankaixiaodan.chepaihao.getText().trim() + "'" 
					+ " chepaihao = " + "'" + this.niankaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'" 
					+ " and riqi = " + "'" + this.niankaixiaodan.year.getValue().toString() + "-01-01" + "'" 
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql_niankaixiaodan);
				this.niankaixiaodan.dispose();
				this.niankaixiaodan.niankaixiaoPanel.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "该年开销单已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
