package wuliuxitong;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

public class CheipaihaoComboBoxModel extends DefaultComboBoxModel<String> {

	private String sql;
	
	CheipaihaoComboBoxModel(){
		sql = "select chepaihao from cheliangxinxi;";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			this.addElement("");
			while(rs.next()){
				this.addElement(rs.getString("chepaihao"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
