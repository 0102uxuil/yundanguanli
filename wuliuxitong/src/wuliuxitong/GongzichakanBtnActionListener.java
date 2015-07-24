package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GongzichakanBtnActionListener implements ActionListener {

	GongziPanel gongziPanel;
	
	GongzichakanBtnActionListener(GongziPanel gzp){
		this.gongziPanel = gzp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			this.generateVector(this.gongziPanel.vector);
			this.gongziPanel.gongziModel.resetVector(this.gongziPanel.vector);
			this.gongziPanel.gongziModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.gongziPanel.gongziModel.fireTableStructureChanged();
	}
	
	public void generateVector(Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		
		String sql_siji;
		sql_siji = "select DISTINCT siji from kaixiaodan where "
				+ "chucheriqi >= " + "'" 
				+ this.gongziPanel.yearmonth_start.getValue().toString().trim() + "-"
				+ "01"
				+ "'" 
				+ " and " + "chucheriqi <=" + "'" 
				+ this.gongziPanel.yearmonth_end.getMonthEndDate().trim()
				+ "'" 
				+ ";";
//		System.out.println(sql_siji);
		float yuegongziheji, yifugongziheji, weifugongziheji;
		yuegongziheji = 0;
		yifugongziheji = 0;
		weifugongziheji = 0;
		ResultSet rs_siji = DBManager.getInstance().excuteQuery(sql_siji);
		int count = 1;
		while(rs_siji.next()){
			Vector rec_vector=new Vector();
			rec_vector.addElement(count++);
			rec_vector.addElement(rs_siji.getString("siji"));
			rec_vector.addElement(this.gongziPanel.yearmonth_start.getValue().toString().trim() 
					+ "-" + "01" 
					+ "/"
					+ this.gongziPanel.yearmonth_end.getMonthEndDate().trim());
			float yuegongzi, yifugongzi, weifugongzi;
			yuegongzi = 0;
			yifugongzi = 0;
			
			String sql_gongzi = "select gongzi, yifugongzi from kaixiaodan where"
					+ " siji = " + "'" + rs_siji.getString("siji") + "'" 
					+ " and chucheriqi >= " + "'" 
					+ this.gongziPanel.yearmonth_start.getValue().toString().trim() + "-"
					+ "01"
					+ "'" 
					+ " and " + "chucheriqi <= " + "'" 
					+ this.gongziPanel.yearmonth_end.getMonthEndDate().trim()
					+ "'" 
					+";";
//			System.out.println(sql_gongzi);
			ResultSet rs_gongzi = DBManager.getInstance().excuteQuery(sql_gongzi);
			while(rs_gongzi.next()){
//				System.out.println(rs_gongzi.getString("chepaihao"));
				yuegongzi += rs_gongzi.getFloat("gongzi");
				yifugongzi += rs_gongzi.getFloat("yifugongzi");
			}
			weifugongzi = yuegongzi - yifugongzi;
			yuegongziheji += yuegongzi;
			yifugongziheji += yifugongzi;
			weifugongziheji += weifugongzi;
			yuegongzi = (float)(Math.round(yuegongzi*100))/100;
			yifugongzi = (float)(Math.round(yifugongzi*100))/100;
			weifugongzi = (float)(Math.round(weifugongzi*100))/100;
			rec_vector.addElement(yuegongzi);
			rec_vector.addElement(yifugongzi);
			rec_vector.addElement(weifugongzi);
			vector.addElement(rec_vector);
		}
		
		yuegongziheji = (float)(Math.round(yuegongziheji*100))/100;
		yifugongziheji = (float)(Math.round(yifugongziheji*100))/100;
		weifugongziheji = (float)(Math.round(weifugongziheji*100))/100;
		Vector rec_vector=new Vector();
		rec_vector.addElement(null);
		rec_vector.addElement(null);
		rec_vector.addElement("合计：");
		rec_vector.addElement(yuegongziheji);
		rec_vector.addElement(yifugongziheji);
		rec_vector.addElement(weifugongziheji);
		vector.addElement(rec_vector);
		
	}
}
