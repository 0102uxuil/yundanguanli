package wuliuxitong;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

public class DBComboBoxModel extends DefaultComboBoxModel<String> {
	
	DBComboBoxModel(String sql, String columnname){
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			this.addElement("нч");
			while(rs.next()){
				this.addElement(rs.getString(columnname));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
