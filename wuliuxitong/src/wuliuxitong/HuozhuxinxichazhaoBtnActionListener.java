package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class HuozhuxinxichazhaoBtnActionListener implements ActionListener {

	HuozhuxinxiPanel hzxxp;
	
	HuozhuxinxichazhaoBtnActionListener(HuozhuxinxiPanel hp){
		this.hzxxp = hp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select huozhuming from huozhuxinxi ";
		if(this.hzxxp.huozhuming_text.getText() != null && !this.hzxxp.huozhuming_text.getText().trim().equals("")){
			sql += " where huozhuming like " + "'%" + this.hzxxp.huozhuming_text.getText().trim() + "%'";
		}
		sql = sql + " order by huozhuming asc;";
		
		try {
			this.generateVector(sql, this.hzxxp.vector);
			this.hzxxp.huozhuxinxiModel.resetVector(this.hzxxp.vector);
			this.hzxxp.huozhuxinxiModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.hzxxp.huozhuxinxiModel.fireTableStructureChanged();
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
			rec_vector.addElement(rs.getString("huozhuming"));
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
	}

}
