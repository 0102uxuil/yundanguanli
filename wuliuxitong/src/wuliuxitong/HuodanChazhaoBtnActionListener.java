package wuliuxitong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HuodanChazhaoBtnActionListener implements ActionListener {

	HuodanPanel huodanPanel;
	
	HuodanChazhaoBtnActionListener(HuodanPanel hd){
		this.huodanPanel = hd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select yundanbianhao, huowubianhao, chepaihao, riqi, chufadi, mudidi, huozhu, huoming, zhongliang, zhongliang2, jiage, baodijia, qitafeiyong, beizhu, yingfujine, shifujine, jiezhangbeizhu, shifouqingsuan from huowudan where "
		+ "riqi >= " + "'" + this.huodanPanel.chuche_start.getText().trim() + "'" + " and " + "riqi <=" + "'" + this.huodanPanel.chuche_end.getText().trim() + "'";
		if(!this.huodanPanel.chepaihao_text.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.huodanPanel.chepaihao_text.getText().trim() + "%'";
		}
		if(!this.huodanPanel.chufadi_text.getText().trim().equals("")){
//			sql = sql + " and " + "chufadi = " + "'" + this.huodanPanel.chufadi_text.getText().trim() + "'";
			sql = sql + " and " + "chufadi like " + "'%" + this.huodanPanel.chufadi_text.getText().trim() + "%'";
		}
		if(!this.huodanPanel.mudidi_text.getText().trim().equals("")){
//			sql = sql + " and " + "mudidi = " + "'" + this.huodanPanel.mudidi_text.getText().trim() + "'";
			sql = sql + " and " + "mudidi like " + "'%" + this.huodanPanel.mudidi_text.getText().trim() + "%'";
		}
		if(!this.huodanPanel.huozhu_text.getText().trim().equals("")){
//			sql = sql + " and " + "huozhu = " + "'" + this.huodanPanel.huozhu_text.getText().trim() + "'";
			sql = sql + " and " + "huozhu like " + "'%" + this.huodanPanel.huozhu_text.getText().trim() + "%'";
		}
		if(!this.huodanPanel.huoming_text.getText().trim().equals("")){
//			sql = sql + " and " + "huoming = " + "'" + this.huodanPanel.huoming_text.getText().trim() + "'";
			sql = sql + " and " + "huoming like " + "'%" + this.huodanPanel.huoming_text.getText().trim() + "%'";
		}
		System.out.println(this.huodanPanel.shifoujiezhang_cb.getSelectedItem().toString());
		if(this.huodanPanel.shifoujiezhang_cb.getSelectedItem().toString().equals("已清算")){
			sql = sql + " and " + "shifouqingsuan = 'yes'";
		} else if(this.huodanPanel.shifoujiezhang_cb.getSelectedItem().toString().equals("未清算")){
			sql = sql + " and " + "shifouqingsuan = 'no'";
		}
		
		sql = sql + " order by riqi asc;";
//		sql = sql + " order by huozhu asc;";
		
		try {
			this.generateVector(sql, this.huodanPanel.vector);
			this.huodanPanel.huodanModel.resetVector(this.huodanPanel.vector);
//			this.huodanPanel.huodanModel.resetVector(DBManager.getInstance().excuteQuery(sql));
//			for(int i = 0; i< 19; i++){
//				this.huodanPanel.huodanTb.getColumnModel().getColumn(i).setPreferredWidth();;
//			}
//			this.huodanPanel.huodanTb.getColumnModel().getColumn(1).setMinWidth(100);
//			this.huodanPanel.huodanTb.getColumnModel().getColumn(1).setPreferredWidth(500);
			this.huodanPanel.huodanModel.fireTableStructureChanged();
//			this.huodanPanel.huodanTb.getColumnModel().getColumn(18).setCellRenderer(new HuoDanTableCellRender());
//			this.huodanPanel.huodanModel.fireTableDataChanged();
//			this.huodanPanel.huodanTb.getColumnModel().getColumn(1).setPreferredWidth(200);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.huodanPanel.huodanModel.fireTableStructureChanged();
		
		
//		String[] columnNames = {"车牌号",
//				"日期",
//				"出发地",
//				"目的地",
//				"货主",
//				"货物",
//				"重量",
//				"单价",
//				"保底价"};
////		this.huodanPanel.removeAll();
//		this.huodanPanel.remove(this.huodanPanel.huodanSP);
//		JTable jt = new JTable(new HuodanTableModel(this.huodanPanel.resultSet, columnNames));
//		JScrollPane sp = new JScrollPane(jt);
//		
//		this.huodanPanel.add(sp, BorderLayout.CENTER);
//		this.huodanPanel.repaint();

//		String[] columnNames = {"序号",
//				"日期",
//				"出发地",
//				"目的地",
//				"货主",
//				"货物",
//				"重量",
//				"单价",
//				"金额"};
//		
//		Object[][] data = {
//			    {"Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy"}
//			};
//		this.huodanPanel.data = {
//			    {"Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy"}
//			};
//		this.huodanPanel.huodanTb = new JTable(data, columnNames);
//		this.huodanPanel.huodanSP = new JScrollPane(this.huodanPanel.huodanTb);
//		this.huodanPanel.validate();
//		this.huodanPanel.repaint();
		
//		JOptionPane.showMessageDialog(null, "该车单已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
	}

	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float total_weight, total_money_yingfu, total_money_shifu;
		total_weight = 0;
		total_money_yingfu = 0;
		total_money_shifu = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
//			this.huodanPanel.huodanTb.getColumnModel().getColumn(0).setPreferredWidth(5);
			for(int i=1; i<=17; i++){
//				System.out.println(rs.getString(i));
				rec_vector.addElement(rs.getString(i));
//				if(rs.getString(i) != null){
//					System.out.println(rs.getString(i).length());
//					this.huodanPanel.huodanTb.getColumnModel().getColumn(i).setPreferredWidth(rs.getString(i).length()*2);
//				} else {
//					this.huodanPanel.huodanTb.getColumnModel().getColumn(i).setPreferredWidth(5);
//				}
			}
			if(rs.getString(18).equals("yes")){
				rec_vector.addElement("是");
//				this.huodanPanel.huodanTb.getColumnModel().getColumn(18).setPreferredWidth(5);
			} else {
				rec_vector.addElement("否");
//				this.huodanPanel.huodanTb.getColumnModel().getColumn(18).setPreferredWidth(5);
			}
//			float jine;
//			if(rs.getString("baodijia") == null || rs.getString("baodijia") == "" || rs.getString("baodijia") == "0"){
//				jine = Float.parseFloat(rs.getString("zhongliang"))*Float.parseFloat(rs.getString("jiage"));
//				rec_vector.addElement(jine);
//			} else {
//				jine = Float.parseFloat(rs.getString("baodijia"));
//				rec_vector.addElement(jine);
//			}
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
//			System.out.println(rs.getString("yingfujine"));
//			System.out.println(rs.getString("shifujine"));
			total_money_yingfu += Float.parseFloat(rs.getString("yingfujine"));
			if(!((rs.getString("shifujine") == null || rs.getString("shifujine").equals("")))){
				total_money_shifu += Float.parseFloat(rs.getString("shifujine"));
			}
			
			total_weight += Float.parseFloat(rs.getString("zhongliang"));
		}
		
		total_weight = (float)(Math.round(total_weight*100))/100;
		total_money_yingfu = (float)(Math.round(total_money_yingfu*100))/100;
		total_money_shifu = (float)(Math.round(total_money_shifu*100))/100;
		
		Vector rec_vector=new Vector();
		for(int i = 0; i < 8; i++){
			rec_vector.addElement(null);
		}
		//合计部分的vector，当vector长度改变时要跟着改变长度，不然会报错
		rec_vector.addElement("合计：");
		rec_vector.addElement(total_weight);
		rec_vector.addElement(null);
		rec_vector.addElement(null);
		rec_vector.addElement(null);
		rec_vector.addElement(null);
		rec_vector.addElement(null);
		rec_vector.addElement(total_money_yingfu);
		rec_vector.addElement(total_money_shifu);
		rec_vector.addElement(null);
		rec_vector.addElement(null);
		vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
	}
	
//	private class HuoDanTableCellRender extends DefaultTableCellRenderer {
//
//		@Override
//		public Component getTableCellRendererComponent(JTable table,
//				Object value, boolean isSelected, boolean hasFocus, int row,
//				int column) {
//			// TODO Auto-generated method stub
//			
//			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
//					row, column);
//			
//			if(value instanceof String){
//				String str = (String)value;
//				if(str.equals("是")){
//					setBackground(Color.green);
//				}
//				if(str.equals("否")){
//					setBackground(Color.red);
//				}
//			}
//			
//			return c;
//		}
//		
//	}
	
}