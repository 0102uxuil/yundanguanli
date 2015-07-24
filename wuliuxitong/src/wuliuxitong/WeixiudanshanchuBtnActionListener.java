package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class WeixiudanshanchuBtnActionListener implements ActionListener {

	WeiXiuDan weixiudan;
	
	WeixiudanshanchuBtnActionListener(WeiXiuDan wxd){
		this.weixiudan = wxd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.weixiudan, "确定删除该月开销单？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_weixiudan;
			sql_weixiudan = "delete from weixiudan where "
//					+ " chepaihao = " + "'" + this.weixiudan.chepaihao.getText().trim() + "'" 
					+ " chepaihao = " + "'" + this.weixiudan.chepaihaoCB.getSelectedItem().toString().trim() + "'" 
					+ " and riqi = " + "'" + this.weixiudan.riqi.getText().trim() + "'" 
					+ " and weixiudian = " + "'" + this.weixiudan.weixiudian.getText().trim() + "'" 
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql_weixiudan);
				this.weixiudan.dispose();
				this.weixiudan.weixiudanPanel.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(this.weixiudan, "该维修单已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
