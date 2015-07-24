package wuliuxitong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GongZiMingXiDan extends JFrame {
	
	JTable gongzimingxidanTb;
	JScrollPane gongzimingxidanSP;
	
	VSTableModel gongzimingxidanModel;
	Vector vector;
	
//	GongziPanel gongziPanel;
	
	GongZiMingXiDan(String siji, String riqi){
		super();
		init();
//		this.gongziPanel = gzp;
		fillcontent(siji, riqi);
	}
	
	public void init(){
		String[] columnNames = {"序号",
				"运单编号",
				"车牌号",
				"司机",
				"出车日期",
				"出车出发地",
				"出车目的地",
				"回车日期",
				"回车出发地",
				"回车目的地",
				"总工资",
				"已付工资",
				"未付工资"};
		
		this.vector = new Vector();
		this.gongzimingxidanModel = new VSTableModel(this.vector, columnNames);
		this.gongzimingxidanTb = new JTable(this.gongzimingxidanModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(gongzimingxidanTb.getModel());
		gongzimingxidanTb.setRowSorter(rowSorter);
		this.gongzimingxidanSP = new JScrollPane(this.gongzimingxidanTb);
		this.add(this.gongzimingxidanSP, BorderLayout.CENTER);
		
//		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width*3/4, screenSize.height*3/4);
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
        this.setVisible(true);
	}
	
	public void fillcontent(String siji, String riqi){
		String[] ymd = riqi.split("/");
		String riqi_start = ymd[0];
		String riqi_end = ymd[1];
		String sql_gongzimingxi = "select yundanbianhao, chepaihao, siji"
				+ ", chucheriqi, chuchechufadi, chuchemudidi"
				+ ", huicheriqi, huichechufadi, huichemudidi"
				+ ", gongzi, yifugongzi"
				+ " from kaixiaodan where "
				+ "siji = " + "'" + siji + "'"
				+ " and chucheriqi >= " + "'" + riqi_start + "'"
				+ " and chucheriqi <= " + "'" + riqi_end + "'"
				+ " order by chucheriqi asc;";
//		System.out.println(sql_gongzimingxi);
		try {
			this.generateVector(sql_gongzimingxi, this.vector);
			this.gongzimingxidanModel.resetVector(this.vector);
			this.gongzimingxidanModel.fireTableStructureChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float zonggongzi, yifuzonggongzi, weifuzonggongzi;
		zonggongzi = 0;
		yifuzonggongzi = 0;
		weifuzonggongzi = 0;
		while(rs.next()){
			Vector rec_vector=new Vector();
			rec_vector.addElement(count++);
			rec_vector.addElement(rs.getString("yundanbianhao"));
			rec_vector.addElement(rs.getString("chepaihao"));
			rec_vector.addElement(rs.getString("siji"));
			rec_vector.addElement(rs.getString("chucheriqi"));
			rec_vector.addElement(rs.getString("chuchechufadi"));
			rec_vector.addElement(rs.getString("chuchemudidi"));
			rec_vector.addElement(rs.getString("huicheriqi"));
			rec_vector.addElement(rs.getString("huichechufadi"));
			rec_vector.addElement(rs.getString("huichemudidi"));
			rec_vector.addElement(rs.getFloat("gongzi"));
			rec_vector.addElement(rs.getFloat("yifugongzi"));
			float weifugongzi;
			weifugongzi = 0;
			weifugongzi = rs.getFloat("gongzi") - rs.getFloat("yifugongzi");
			weifugongzi = (float)(Math.round(weifugongzi*100))/100;
			zonggongzi += rs.getFloat("gongzi");
			yifuzonggongzi += rs.getFloat("yifugongzi");
			weifuzonggongzi += weifugongzi;
			zonggongzi = (float)(Math.round(zonggongzi*100))/100;
			yifuzonggongzi = (float)(Math.round(yifuzonggongzi*100))/100;
			weifuzonggongzi = (float)(Math.round(weifuzonggongzi*100))/100;
			rec_vector.addElement(weifugongzi);
			vector.addElement(rec_vector);
		}
		Vector rec_vector=new Vector();
		for(int i = 0; i < 9; i++){
			rec_vector.addElement(null);
		}
		rec_vector.addElement("合计：");
		rec_vector.addElement(zonggongzi);
		rec_vector.addElement(yifuzonggongzi);
		rec_vector.addElement(weifuzonggongzi);
		vector.addElement(rec_vector);
	}
}
