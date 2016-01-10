package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class YundanshanchuBtnActionListener implements ActionListener {

	YunDan yundan;
	
	YundanshanchuBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.yundan, "确定删除该运单？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_kaixiaodan;
			sql_kaixiaodan = "delete from kaixiaodan where yundanbianhao = " + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ";"; 
			
			String sql_huowudan;
			sql_huowudan = "delete from huowudan where yundanbianhao = " + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ";"; 
			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				DBManager.getInstance().excuteUpdate(sql_kaixiaodan);
				DBManager.getInstance().excuteUpdate(sql_huowudan);
				DBManager.getInstance().getConnection().commit();
				DBManager.getInstance().getConnection().setAutoCommit(true);
				this.yundan.dispose();
				this.yundan.reload.reload(this.yundan.yundanbianhao.getText().trim());
				JOptionPane.showMessageDialog(null, "运单已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				try {
					DBManager.getInstance().getConnection().rollback();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
		}
	}

}
