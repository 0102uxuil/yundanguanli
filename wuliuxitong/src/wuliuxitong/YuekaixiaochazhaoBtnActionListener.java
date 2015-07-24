package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class YuekaixiaochazhaoBtnActionListener implements ActionListener {

	YuekaixiaoPanel yuekaixiaoPanel;
	
	YuekaixiaochazhaoBtnActionListener(YuekaixiaoPanel ykxp){
		this.yuekaixiaoPanel = ykxp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select chepaihao, DATE_FORMAT(riqi ,'%Y-%m') , yuetongka, dianhuafei, qitafeiyong, beizhu, zongkaixiao from yuekaixiaodan where "
				+ "riqi >= " + "'" 
				+ this.yuekaixiaoPanel.year_start.getValue().toString().trim() + "-"
				+ this.yuekaixiaoPanel.month_start.getValue().toString().trim() + "-"
				+ "01"
				+ "'" 
				+ " and " + "riqi <=" + "'" 
				+ this.yuekaixiaoPanel.year_end.getValue().toString().trim() + "-"
				+ this.yuekaixiaoPanel.month_end.getValue().toString().trim() + "-"
				+ "01"
				+ "'" ;
		if(!this.yuekaixiaoPanel.chepaihao_text.getText().trim().equals("")){
			sql = sql
					+ " and"
					+ " chepaihao = " + this.yuekaixiaoPanel.chepaihao_text.getText().trim();
		}
//		} else {
//			JOptionPane.showMessageDialog(null, "运单编号和车牌号不能全为空！", "错误", JOptionPane.PLAIN_MESSAGE);
//		}
		sql = sql + " order by riqi asc;";
		
		try {
			this.generateVector(sql, this.yuekaixiaoPanel.vector);
			this.yuekaixiaoPanel.yuekaixiaoModel.resetVector(this.yuekaixiaoPanel.vector);
			this.yuekaixiaoPanel.yuekaixiaoModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.yuekaixiaoPanel.yuekaixiaoModel.fireTableStructureChanged();

	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float yuetongkaheji, dianhuafeiheji, qitafeiyongheji, zongkaixiaoheji;
		yuetongkaheji = 0;
		dianhuafeiheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=7; i++){
				rec_vector.addElement(rs.getString(i));
			}
			yuetongkaheji +=rs.getFloat("yuetongka");
			dianhuafeiheji +=rs.getFloat("dianhuafei");
			qitafeiyongheji +=rs.getFloat("qitafeiyong");
			zongkaixiaoheji +=rs.getFloat("zongkaixiao");
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
		yuetongkaheji = (float)(Math.round(yuetongkaheji*100))/100;
		dianhuafeiheji = (float)(Math.round(dianhuafeiheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		
		Vector tail_vector=new Vector(); 
		for(int i=1; i<=2; i++){
			tail_vector.addElement(null);
		}
		tail_vector.addElement("合计：");
		tail_vector.addElement(yuetongkaheji);
		tail_vector.addElement(dianhuafeiheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		tail_vector.addElement(zongkaixiaoheji);
		vector.addElement(tail_vector);
	}

}
