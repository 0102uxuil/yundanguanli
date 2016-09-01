package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JiagebiaoshanchuBtnActionListener implements ActionListener {

	JiaGeBiao jgb;
	
	public JiagebiaoshanchuBtnActionListener(JiaGeBiao jgb) {
		// TODO Auto-generated constructor stub
		this.jgb = jgb;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.jgb, "确定删除该货主信息？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql;
			sql = "delete from jiagebiao where "
					+ " huozhu = " + "'" + this.jgb.huozhu.getText().trim() + "'"
					+ " and chufadi = " + "'" + this.jgb.chufadi.getText().trim() + "'"
					+ " and mudidi = " + "'" + this.jgb.mudidi.getText().trim() + "'"
					+ " and huoming = " + "'" + this.jgb.huoming.getText().trim() + "'"
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql);
				this.jgb.dispose();
				this.jgb.jgbp.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "该货主信息已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
