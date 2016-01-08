package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class NiankaixiaochazhaoBtnActionListener implements ActionListener {

	NiankaixiaoPanel niankaixiaoPanel;
	
	NiankaixiaochazhaoBtnActionListener(NiankaixiaoPanel nkxp){
		this.niankaixiaoPanel = nkxp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select chepaihao, DATE_FORMAT(riqi ,'%Y') , shenche, baoxian, gerenxian, gprs, qitafeiyong, beizhu, zongkaixiao from niankaixiaodan where "
				+ "riqi >= " + "'" 
				+ this.niankaixiaoPanel.year_start.getValue().toString().trim()
				+ "-01-01"
				+ "'" 
				+ " and " + "riqi <=" + "'" 
				+ this.niankaixiaoPanel.year_end.getValue().toString().trim()
				+ "-01-01"
				+ "'" ;
		if(!this.niankaixiaoPanel.chepaihao_text.getText().trim().equals("")){
			sql = sql
					+ " and"
					+ " chepaihao like " + "'%" + this.niankaixiaoPanel.chepaihao_text.getText().trim() + "%'";
		}
//		} else {
//			JOptionPane.showMessageDialog(null, "运单编号和车牌号不能全为空！", "错误", JOptionPane.PLAIN_MESSAGE);
//		}
		sql = sql + " order by riqi asc;";
		
		try {
			this.generateVector(sql, this.niankaixiaoPanel.vector);
			this.niankaixiaoPanel.niankaixiaoModel.resetVector(this.niankaixiaoPanel.vector);
			this.niankaixiaoPanel.niankaixiaoModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.niankaixiaoPanel.niankaixiaoModel.fireTableStructureChanged();
	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float shencheheji, baoxianheji, gerenxianheji, gprsheji, qitafeiyongheji, zongkaixiaoheji;
		shencheheji = 0;
		baoxianheji = 0;
		gerenxianheji = 0;
		gprsheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
//			for(int i=1; i<=8; i++){
//				rec_vector.addElement(rs.getString(i));
//			}
			rec_vector.addElement(rs.getString("chepaihao"));
			rec_vector.addElement(rs.getString("DATE_FORMAT(riqi ,'%Y')"));
			rec_vector.addElement(rs.getString("shenche"));
			rec_vector.addElement(rs.getString("baoxian"));
			rec_vector.addElement(rs.getString("gerenxian"));
			rec_vector.addElement(rs.getString("gprs"));
			rec_vector.addElement(rs.getString("qitafeiyong"));
			rec_vector.addElement(rs.getString("beizhu"));
			rec_vector.addElement(rs.getString("zongkaixiao"));
			
			shencheheji +=rs.getFloat("shenche");
			baoxianheji +=rs.getFloat("baoxian");
			gerenxianheji +=rs.getFloat("gerenxian");
			gprsheji +=rs.getFloat("gprs");
			qitafeiyongheji +=rs.getFloat("qitafeiyong");
			zongkaixiaoheji +=rs.getFloat("zongkaixiao");
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
		shencheheji = (float)(Math.round(shencheheji*100))/100;
		baoxianheji = (float)(Math.round(baoxianheji*100))/100;
		gerenxianheji = (float)(Math.round(gerenxianheji*100))/100;
		gprsheji = (float)(Math.round(gprsheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		
		Vector tail_vector=new Vector(); 
		for(int i=1; i<=2; i++){
			tail_vector.addElement(null);
		}
		tail_vector.addElement("合计：");
		tail_vector.addElement(shencheheji);
		tail_vector.addElement(baoxianheji);
		tail_vector.addElement(gerenxianheji);
		tail_vector.addElement(gprsheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		tail_vector.addElement(zongkaixiaoheji);
		vector.addElement(tail_vector);
	}

}
