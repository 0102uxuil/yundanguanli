package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CheliangxinxishanchuBtnActionListener implements ActionListener {

	CheLiangXinXiDan clxxd;
	
	CheliangxinxishanchuBtnActionListener(CheLiangXinXiDan cd){
		this.clxxd = cd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int jop = JOptionPane.showConfirmDialog(this.clxxd, "确定删除该车辆信息？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(jop == 0){
			String sql_yuekaixiaodan;
			sql_yuekaixiaodan = "delete from cheliangxinxi where "
					+ " chepaihao = " + "'" + this.clxxd.chepaihao.getText().trim() + "'" 
					+ ";"; 
			try {
				DBManager.getInstance().excuteUpdate(sql_yuekaixiaodan);
				this.clxxd.dispose();
				this.clxxd.clxxp.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "该车辆信息已删除！", "删除", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
