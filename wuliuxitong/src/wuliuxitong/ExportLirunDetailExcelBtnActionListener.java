package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportLirunDetailExcelBtnActionListener implements ActionListener {

	LirunPanel lirunPanel;
	Workbook wb;
	String startDate, endDate, chepaihao;
	
	float zongmaoli, zongyuekaixiao, zongniankaixiao, zongweixiukaixiao;
	
	ExportLirunDetailExcelBtnActionListener(LirunPanel lrp){
		this.lirunPanel = lrp;
		this.startDate = lrp.startDate;
		this.endDate = lrp.endDate;
		this.chepaihao = lrp.chepaihao_str;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser();
	    fc.setSelectedFile(new File(""));
       int returnVal = fc.showSaveDialog(this.lirunPanel);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File file = fc.getSelectedFile();
           //This is where a real application would save the file.
           if(file != null){
           	if(verifyDuplicate(file.getAbsolutePath() + ".xls")){
           		int jop = JOptionPane.showConfirmDialog(this.lirunPanel, "该文件已存在是否覆盖？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
           		if(jop == 0){
           			exportExcel(file.getAbsolutePath());
           			System.out.println("Saving: " + file.getAbsolutePath() + ".");
           		} else {
           			System.out.println("Not Saving: " + file.getAbsolutePath() + ".");
           		}
           	} else {
           		exportExcel(file.getAbsolutePath());
           		System.out.println("Saving: " + file.getAbsolutePath() + ".");
           	}
           } else {
//           	JOptionPane.showMessageDialog(this.huodanPanel, "文件名不能为空！", "文件名为空", JOptionPane.PLAIN_MESSAGE);
           }
           
       } else {
           System.out.println("Save command cancelled by user.");
       }
	}

	private boolean verifyDuplicate(String absolutepath){
		File file = new File(absolutepath);
		boolean exists = file.exists();
		int jop;
		return exists;
	}

//	private void exportExcel(String absolutepath_without_postfix){
//		wb = new HSSFWorkbook();
//		Sheet sheet = wb.createSheet("利润单");
//		
//		Row row = sheet.createRow(0);
//		Cell cell = row.createCell(0);
//		cell.setCellValue("利润单");
//		
//		JTable tb= this.lirunPanel.lirunDetailTb;
//		row = sheet.createRow(1);
//		for(int i = 0; i < tb.getColumnCount(); i++){
//			cell = row.createCell(i);
//			cell.setCellValue(tb.getColumnName(i));
//		}
//		
//		System.out.println(tb.getRowCount());
//		for(int i = 0; i < tb.getRowCount(); i++){
//			row = sheet.createRow(2+i);
//			for(int j = 0; j < tb.getColumnCount(); j++){
//				cell = row.createCell(j);
//				if(!(tb.getValueAt(i, j) == null)){
//					cell.setCellValue(tb.getValueAt(i, j).toString());
//				}
//				System.out.println(tb.getValueAt(i, j));
//			}
//		}
//		
//		outputExcel(absolutepath_without_postfix + ".xls");
//	}
	
	private void exportExcel(String absolutepath_without_postfix){
		wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("利润单");
		this.zongmaoli = exceladdyundantable(sheet, this.lirunPanel.startDate, this.lirunPanel.endDate, this.lirunPanel.chepaihao_str);
		this.zongyuekaixiao = exceladdyuekaixiao(sheet, this.lirunPanel.startDate, this.lirunPanel.endDate, this.lirunPanel.chepaihao_str);
		this.zongniankaixiao = exceladdniankaixiao(sheet, this.lirunPanel.riqi_start.getYear() + "-01-01", this.lirunPanel.riqi_end.getYear() + "-12-31", this.lirunPanel.chepaihao_str);
		this.zongweixiukaixiao = exceladdweixiukaixiao(sheet, this.lirunPanel.startDate, this.lirunPanel.endDate, this.lirunPanel.chepaihao_str);
		outputExcel(absolutepath_without_postfix + ".xls");
	}
	
	private void outputExcel(String absolutepath){
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(absolutepath);
			try {
				wb.write(fileOut);
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void exceladdaline(Sheet sheet, String[] strs){
		Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
		Cell cell;
		for(int i=0; i<strs.length; i++){
			cell = row.createCell(i);
			cell.setCellValue(strs[i]);
		}
	}
	
	private float exceladdayundan(Sheet sheet, String yundanbianhao, int count){
//		float zongmaoli = 0;
		String yundan_sql = "select "
				+ "chepaihao, "
				+ "chucheriqi, "
				+ "chuchechufadi, "
				+ "chuchemudidi, "
				+ "huicheriqi, "
				+ "huichechufadi, "
				+ "huichemudidi, "
				+ "jiayouzhanjiayou, "
				+ "tingchechangjiayou, "
				+ "tingchechangyoujia, "
				+ "gongzi, "
				+ "zongkaixiao "
				+ "from kaixiaodan where "
				+ "yundanbianhao = " + "'" + yundanbianhao + "'" +";";
//				+ "chucheriqi >= " + "'" + this.startDate + "'" 
//				+ " and " + "chucheriqi <=" + "'" + this.endDate + "'";
//		if(!this.chepaihao.equals("")){
//			yundan_sql = yundan_sql + " and " + "chepaihao like " + "'%" + this.chepaihao + "%'";
//		}
//		yundan_sql = yundan_sql + " order by chucheriqi asc;";
		String chuchehuodan_sql = "select "
				+ "zhongliang, "
				+ "qitafeiyong, "
				+ "shouxufei, "
				+ "sijijiage, "
				+ "sijijiagejine "
				+ "from huowudan where "
				+ " yundanbianhao = " + "'" + yundanbianhao + "'"
				+ " and huowubianhao like '0%';";
		String huichehuodan_sql = "select "
				+ "zhongliang, "
				+ "qitafeiyong, "
				+ "shouxufei, "
				+ "sijijiage, "
				+ "sijijiagejine "
				+ "from huowudan where "
				+ " yundanbianhao = " + "'" + yundanbianhao + "'"
				+ " and huowubianhao like '1%';";
		try {
			ResultSet yundan_rs = DBManager.getInstance().excuteQuery(yundan_sql);
			ResultSet chuchehuodan_rs = DBManager.getInstance().excuteQuery(chuchehuodan_sql);
			ResultSet huichehuodan_rs = DBManager.getInstance().excuteQuery(huichehuodan_sql);
			Row row;
			Cell cell;
			int colNum = 0;
			while(yundan_rs.next()){
				float lirun=0;
				row = sheet.createRow(sheet.getPhysicalNumberOfRows());
				cell = row.createCell(colNum++);
				cell.setCellValue(count);
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("chepaihao"));
//				System.out.println(yundan_rs.getString("chepaihao"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("chucheriqi"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("chuchechufadi"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("chuchemudidi"));
				
				if(chuchehuodan_rs.next() == false){
					cell = row.createCell(colNum++);//zhongliang
					cell = row.createCell(colNum++);//qitafeiyong
					cell = row.createCell(colNum++);//shouxufei
					cell = row.createCell(colNum++);//sijijiage
					cell = row.createCell(colNum++);//sijijiagejine
				} else {
					cell = row.createCell(colNum++);//zhongliang
					cell.setCellValue(chuchehuodan_rs.getString("zhongliang"));
					cell = row.createCell(colNum++);//sijijiage
					cell.setCellValue(chuchehuodan_rs.getString("sijijiage"));
					cell = row.createCell(colNum++);//qitafeiyong
					cell.setCellValue(chuchehuodan_rs.getString("qitafeiyong"));
					cell = row.createCell(colNum++);//shouxufei
					cell.setCellValue(chuchehuodan_rs.getString("shouxufei"));
					cell = row.createCell(colNum++);//sijijiagejine
					cell.setCellValue(chuchehuodan_rs.getString("sijijiagejine"));
					lirun += Float.parseFloat(chuchehuodan_rs.getString("sijijiagejine"));
					Row hdrow;
					Cell hdcell;
					int i=0;
					while(chuchehuodan_rs.next()){
						hdrow = sheet.getRow(sheet.getPhysicalNumberOfRows());
						if(hdrow == null){
							hdrow = sheet.createRow(sheet.getPhysicalNumberOfRows());
						}
						hdcell = hdrow.createCell(5, Cell.CELL_TYPE_NUMERIC);//zhongliang
						hdcell.setCellValue(chuchehuodan_rs.getString("zhongliang"));
						hdcell = hdrow.createCell(6, Cell.CELL_TYPE_NUMERIC);//sijijiage
						hdcell.setCellValue(chuchehuodan_rs.getString("sijijiage"));
						hdcell = hdrow.createCell(7, Cell.CELL_TYPE_NUMERIC);//qitafeiyong
						hdcell.setCellValue(chuchehuodan_rs.getString("qitafeiyong"));
						hdcell = hdrow.createCell(8, Cell.CELL_TYPE_NUMERIC);//shouxufei
						hdcell.setCellValue(chuchehuodan_rs.getString("shouxufei"));
						hdcell = hdrow.createCell(9, Cell.CELL_TYPE_NUMERIC);//sijijiagejine
						hdcell.setCellValue(chuchehuodan_rs.getString("sijijiagejine"));
						lirun += Float.parseFloat(chuchehuodan_rs.getString("sijijiagejine"));
					}
				}
				
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("huicheriqi"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("huichechufadi"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("huichemudidi"));
				
				if(huichehuodan_rs.next() == false){
					cell = row.createCell(colNum++);//zhongliang
					cell = row.createCell(colNum++);//qitafeiyong
					cell = row.createCell(colNum++);//shouxufei
					cell = row.createCell(colNum++);//sijijiage
					cell = row.createCell(colNum++);//sijijiagejine
				} else {
					cell = row.createCell(colNum++);//zhongliang
					cell.setCellValue(huichehuodan_rs.getString("zhongliang"));
					cell = row.createCell(colNum++);//sijijiage
					cell.setCellValue(huichehuodan_rs.getString("sijijiage"));
					cell = row.createCell(colNum++);//qitafeiyong
					cell.setCellValue(huichehuodan_rs.getString("qitafeiyong"));
					cell = row.createCell(colNum++);//shouxufei
					cell.setCellValue(huichehuodan_rs.getString("shouxufei"));
					cell = row.createCell(colNum++);//sijijiagejine
					cell.setCellValue(huichehuodan_rs.getString("sijijiagejine"));
					lirun += Float.parseFloat(huichehuodan_rs.getString("sijijiagejine"));
					Row hdrow;
					Cell hdcell;
					int i=0;
					while(huichehuodan_rs.next()){
						hdrow = sheet.getRow(sheet.getPhysicalNumberOfRows());
						if(hdrow == null){
							hdrow = sheet.createRow(sheet.getPhysicalNumberOfRows());
						}
						hdcell = hdrow.createCell(13, Cell.CELL_TYPE_NUMERIC);//zhongliang
						hdcell.setCellValue(huichehuodan_rs.getString("zhongliang"));
						hdcell = hdrow.createCell(14, Cell.CELL_TYPE_NUMERIC);//sijijiage
						hdcell.setCellValue(huichehuodan_rs.getString("sijijiage"));
						hdcell = hdrow.createCell(15, Cell.CELL_TYPE_NUMERIC);//qitafeiyong
						hdcell.setCellValue(huichehuodan_rs.getString("qitafeiyong"));
						hdcell = hdrow.createCell(16, Cell.CELL_TYPE_NUMERIC);//shouxufei
						hdcell.setCellValue(huichehuodan_rs.getString("shouxufei"));
						hdcell = hdrow.createCell(17, Cell.CELL_TYPE_NUMERIC);//sijijiagejine
						hdcell.setCellValue(huichehuodan_rs.getString("sijijiagejine"));
						lirun += Float.parseFloat(huichehuodan_rs.getString("sijijiagejine"));
					}
				}
				
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("jiayouzhanjiayou"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("tingchechangjiayou"));
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("tingchechangyoujia"));
				cell = row.createCell(colNum++);//油合计
				float youheji = 0;
				if(yundan_rs.getString("jiayouzhanjiayou") != null && !yundan_rs.getString("jiayouzhanjiayou").trim().equals("")){
					youheji += Float.parseFloat(yundan_rs.getString("jiayouzhanjiayou"));
				}
				if(yundan_rs.getString("tingchechangjiayou") != null && !yundan_rs.getString("tingchechangjiayou").trim().equals("")){
					youheji += Float.parseFloat(yundan_rs.getString("tingchechangjiayou"))*Float.parseFloat(yundan_rs.getString("tingchechangyoujia"));
				}
				cell.setCellValue(youheji);
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("gongzi"));
				cell = row.createCell(colNum++);//其他开支
				cell.setCellValue(Float.parseFloat(yundan_rs.getString("zongkaixiao")) - Float.parseFloat(yundan_rs.getString("gongzi")) - youheji);
				cell = row.createCell(colNum++);
				cell.setCellValue(yundan_rs.getString("zongkaixiao"));
				cell = row.createCell(colNum++);//利润
				lirun -= Float.parseFloat(yundan_rs.getString("zongkaixiao"));
				cell.setCellValue(lirun);
				zongmaoli += lirun;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zongmaoli;
	}
	
	private float exceladdyundantable(Sheet sheet, String startDate, String endDate, String chepaihao){
		float zongmaoli = 0;
		String[] yundantablecolumnnames = {
				"序号",
				"车牌号",
				"出车日期",
				"出发地",
				"目的地",
				"吨位",
				"单价",
				"其他费用",
				"手续费",
				"运费金额",
				"回车日期",
				"出发地",
				"目的地",
				"吨位",
				"单价",
				"其他费用",
				"手续费",
				"运费金额",
				"现金加油",
				"停车场加油",
				"油价",
				"油合计",
				"工资",
				"其他开支",
				"总开支",
				"利润"
		};
		exceladdaline(sheet, yundantablecolumnnames);
		String sql = "select yundanbianhao from kaixiaodan where "
				+ "chucheriqi >= " + "'" + startDate + "'" 
				+ " and " + "chucheriqi <=" + "'" + endDate + "'";
		if(this.chepaihao !=null && !this.chepaihao.equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + chepaihao + "%'";
		}
		sql = sql + " order by chucheriqi asc;";
//		System.out.println(sql);
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			int count =1;
			while(rs.next()){
				zongmaoli = exceladdayundan(sheet, rs.getString("yundanbianhao"), count++);
			}
			Row row;
			Cell cell;
			row = sheet.createRow(sheet.getPhysicalNumberOfRows());
			cell = row.createCell(24);
			cell.setCellValue("合计：");
			cell = row.createCell(25);
			cell.setCellValue(zongmaoli);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zongmaoli;
	}
	
	private float exceladdyuekaixiao(Sheet sheet, String startDate, String endDate, String chepaihao){
		float zongyuekaixiao = 0;
		String[] title = {"月开销"};
		exceladdaline(sheet, title);
		String[] head = {
				"序号",
				"车牌号",
				"日期",
				"粤通卡",
				"电话费",
				"其他费用",
				"备注",
				"总开销",
		};
		exceladdaline(sheet, head);
		
		String sql = "select chepaihao, riqi, yuetongka, dianhuafei, qitafeiyong, beizhu, zongkaixiao from yuekaixiaodan where "
				+ "riqi >= " + "'" + startDate + "'" 
				+ " and " + "riqi <=" + "'" + endDate + "'";
		if(this.chepaihao !=null && !this.chepaihao.equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + chepaihao + "%'";
		}
		sql = sql + " order by riqi asc;";
		
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			Row row;
			Cell cell;
			int count = 1;
			while(rs.next()){
				int i = 0;
				row = sheet.createRow(sheet.getPhysicalNumberOfRows());
				cell = row.createCell(i++);
				cell.setCellValue(count++);
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("chepaihao"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("riqi"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("yuetongka"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("dianhuafei"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("qitafeiyong"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("beizhu"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("zongkaixiao"));
				zongyuekaixiao += Float.parseFloat(rs.getString("zongkaixiao"));
			}
			row = sheet.createRow(sheet.getPhysicalNumberOfRows());
			cell = row.createCell(6);
			cell.setCellValue("合计：");
			cell = row.createCell(7);
			cell.setCellValue(zongyuekaixiao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zongyuekaixiao;
	}

	private float exceladdniankaixiao(Sheet sheet, String startDate, String endDate, String chepaihao){
		float zongniankaixiao = 0;
		String[] title = {"年开销"};
		exceladdaline(sheet, title);
		String[] head = {
				"序号",
				"车牌号",
				"日期",
				"审车","审车备注",
				"车险","车险备注",
				"个人险","个人险备注",
				"审营运证",
				"GPRS",
				"其他费用",
				"备注",
				"总开销",
		};
		exceladdaline(sheet, head);
		
		String sql = "select "
				+ "chepaihao, "
				+ "riqi, "
				+ "shenche, shenchebz, "
				+ "baoxian, baoxianbz, "
				+ "gerenxian, gerenxianbz, "
				+ "shenyingyunzheng, gprs, "
				+ "qitafeiyong, beizhu, "
				+ "zongkaixiao "
				+ "from niankaixiaodan where "
				+ "riqi >= " + "'" + startDate + "'" 
				+ " and " + "riqi <=" + "'" + endDate + "'";
		if(this.chepaihao !=null && !this.chepaihao.equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + chepaihao + "%'";
		}
		sql = sql + " order by riqi asc;";
		
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			Row row;
			Cell cell;
			int count = 1;
			while(rs.next()){
				int i = 0;
				row = sheet.createRow(sheet.getPhysicalNumberOfRows());
				cell = row.createCell(i++);
				cell.setCellValue(count++);
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("chepaihao"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("riqi"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("shenche"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("shenchebz"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("baoxian"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("baoxianbz"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("gerenxian"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("gerenxianbz"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("shenyingyunzheng"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("gprs"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("qitafeiyong"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("beizhu"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("zongkaixiao"));
				zongniankaixiao += Float.parseFloat(rs.getString("zongkaixiao"));
			}
			row = sheet.createRow(sheet.getPhysicalNumberOfRows());
			cell = row.createCell(12);
			cell.setCellValue("合计：");
			cell = row.createCell(13);
			cell.setCellValue(zongniankaixiao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zongniankaixiao;
	}
	
	private float exceladdweixiukaixiao(Sheet sheet, String startDate, String endDate, String chepaihao){
		float zongweixiukaixiao = 0;
		String[] title = {"维修开销"};
		exceladdaline(sheet, title);
		String[] head = {
				"序号",
				"车牌号",
				"日期",
				"维修点",
				"维修项目",
				"维修金额",
				"备注",
		};
		exceladdaline(sheet, head);
		
		String sql = "select chepaihao, riqi, weixiudian, weixiuxiangmu, weixiujine, beizhu from weixiudan where "
				+ "riqi >= " + "'" + startDate + "'" 
				+ " and " + "riqi <=" + "'" + endDate + "'";
		if(this.chepaihao !=null && !this.chepaihao.equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + chepaihao + "%'";
		}
		sql = sql + " order by riqi asc;";
		
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			Row row;
			Cell cell;
			int count = 1;
			while(rs.next()){
				int i = 0;
				row = sheet.createRow(sheet.getPhysicalNumberOfRows());
				cell = row.createCell(i++);
				cell.setCellValue(count++);
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("chepaihao"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("riqi"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("weixiudian"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("weixiuxiangmu"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("weixiujine"));
				cell = row.createCell(i++);
				cell.setCellValue(rs.getString("beizhu"));
				zongweixiukaixiao += Float.parseFloat(rs.getString("weixiujine"));
			}
			row = sheet.createRow(sheet.getPhysicalNumberOfRows());
			cell = row.createCell(4);
			cell.setCellValue("合计：");
			cell = row.createCell(5);
			cell.setCellValue(zongweixiukaixiao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zongweixiukaixiao;
	}
}
