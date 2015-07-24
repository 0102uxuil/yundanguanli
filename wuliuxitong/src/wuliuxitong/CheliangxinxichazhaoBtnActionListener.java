package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CheliangxinxichazhaoBtnActionListener implements ActionListener {

	CheliangxinxiPanel clxxp;
	
	CheliangxinxichazhaoBtnActionListener(CheliangxinxiPanel cp){
		this.clxxp = cp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select chepaihao, siji from cheliangxinxi ";
		if(this.clxxp.chepaihao_text.getText() != null && !this.clxxp.chepaihao_text.getText().trim().equals("")){
			sql += " where chepaihao like " + "'%" + this.clxxp.chepaihao_text.getText().trim() + "%'";
			if(this.clxxp.siji_text.getText() != null && !this.clxxp.siji_text.getText().trim().equals("")){
				sql += " and siji like " + "'%" + this.clxxp.siji_text.getText().trim() + "%'";
			}
		} else if(this.clxxp.siji_text.getText() != null && !this.clxxp.siji_text.getText().trim().equals("")){
			sql += "where siji like " + "'%" + this.clxxp.siji_text.getText().trim() + "%'" + ";";
		}
		sql = sql + " order by chepaihao asc;";
		
		try {
			this.generateVector(sql, this.clxxp.vector);
			this.clxxp.cheliangxinxiModel.resetVector(this.clxxp.vector);
			this.clxxp.cheliangxinxiModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.clxxp.cheliangxinxiModel.fireTableStructureChanged();
	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
//			for(int i=1; i<=2; i++){
//				rec_vector.addElement(rs.getString(i));
//			}
			rec_vector.addElement(rs.getString("chepaihao"));
			rec_vector.addElement(rs.getString("siji"));
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
	}

}
