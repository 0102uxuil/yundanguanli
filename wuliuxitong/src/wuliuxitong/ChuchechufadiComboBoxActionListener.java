package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChuchechufadiComboBoxActionListener implements ActionListener {

	YunDan yundan;
	
	ChuchechufadiComboBoxActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.yundan.chuchechufadi.getSelectedItem() != null
				&& !this.yundan.chuchechufadi.getSelectedItem().toString().equals("")){
			String sql;
			sql = "select mudidi, riqi from luxianbiao where chufadi = "
					+ "'" + this.yundan.chuchechufadi.getSelectedItem().toString() +"'"
					+ "order by riqi desc "
					+ ";";
			try {
				ResultSet rs = DBManager.getInstance().excuteQuery(sql);
				this.yundan.chuchemudidiCBM.removeAllElements();
				while(rs.next()){
					this.yundan.chuchemudidiCBM.addElement(rs.getString("mudidi"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
