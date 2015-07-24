package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChepaihaoCBActionListener implements ActionListener {

	YunDan yundan;
	
	ChepaihaoCBActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!this.yundan.chepaihaoCB.getSelectedItem().toString().equals("")){
			String sql;
			sql = "select siji from cheliangxinxi where chepaihao = "
					+ "'" + this.yundan.chepaihaoCB.getSelectedItem().toString() + "'" 
					+ ";";
			try {
				ResultSet rs = DBManager.getInstance().excuteQuery(sql);
				if(rs.next()){
					this.yundan.siji.setText(rs.getString("siji"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			this.yundan.siji.setText("");
		}
		if(this.yundan.option == YunDan.YunDanTianjia){
			this.yundan.initYundanbianhao();
		}
	}

}
