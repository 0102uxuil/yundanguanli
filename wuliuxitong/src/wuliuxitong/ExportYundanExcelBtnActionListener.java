package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportYundanExcelBtnActionListener implements ActionListener {

	YundanPanel yundanPanel;
	HSSFWorkbook wb;
	
	ExportYundanExcelBtnActionListener(YundanPanel yd){
		this.yundanPanel = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser();
	    fc.setSelectedFile(new File(""));
       int returnVal = fc.showSaveDialog(this.yundanPanel);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File file = fc.getSelectedFile();
           //This is where a real application would save the file.
           if(file != null){
           	if(verifyDuplicate(file.getAbsolutePath() + ".xls")){
           		int jop = JOptionPane.showConfirmDialog(this.yundanPanel, "该文件已存在是否覆盖？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
           		if(jop == 0){
           			exportExcel(file.getAbsolutePath());
           			System.out.println("Saving: " + file.getAbsolutePath() + ".");
           		} else {
           			System.out.println("Not Saving: " + file.getAbsolutePath() + ".");
           		}
           	} else {
           		System.out.println("Saving1: " + file.getAbsolutePath() + ".");
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

	private void exportExcel(String absolutepath_without_postfix){
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("对账单");
//		sheet.addMergedRegion(new CellRangeAddress(
//	            0, //first row (0-based)
//	            0, //last row  (0-based)
//	            1, //first column (0-based)
//	            11 //last column  (0-based)
//	    ));
//		HSSFFont f  = wb.createFont();      
//		f.setFontHeightInPoints((short) 11);//字号      
//		f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//加粗      
//
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setFont(f);      
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中      
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中      
//		CreationHelper createHelper = wb.getCreationHelper();
		
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
        HSSFCellStyle contextstyle = wb.createCellStyle();
		cell.setCellValue("运单");
		
		JTable tb= this.yundanPanel.yundanTb;
		row = sheet.createRow(1);
		for(int i = 0; i < tb.getColumnCount(); i++){
			cell = row.createCell(i);
			cell.setCellValue(tb.getColumnName(i));
		}
		
		System.out.println(tb.getRowCount());
		for(int i = 0; i < tb.getRowCount(); i++){
			row = sheet.createRow(2+i);
			for(int j = 0; j < tb.getColumnCount(); j++){
//				cell = row.createCell(j);
				
				Object data = tb.getValueAt(i, j);
                HSSFCell contentCell = row.createCell(j);                
                Boolean isNum = false;//data是否为数值型
                Boolean isInteger=false;//data是否为整数
//                Boolean isPercent=false;//data是否为百分数
                if (data != null || "".equals(data)) {
                    //判断data是否为数值型
                    isNum = data.toString().matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data是否为整数（小数部分是否为0）
                    isInteger=data.toString().matches("^[-\\+]?[\\d]*$");
                    //判断data是否为百分数（是否包含“%”）
//                    isPercent=data.toString().contains("%");
                }

                //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                if (isNum) {
                    HSSFDataFormat df = wb.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                        contextstyle.setDataFormat(df.getBuiltinFormat("#,#0"));//数据格式只显示整数
                    }else{
                        contextstyle.setDataFormat(df.getBuiltinFormat("#,##0.00"));//保留两位小数点
                    }
                    // 设置单元格格式
                    contentCell.setCellStyle(contextstyle);
                    // 设置单元格内容为double类型
                    contentCell.setCellValue(Double.parseDouble(data.toString()));
                } else {
                	if(data != null) {
	                    contentCell.setCellStyle(contextstyle);
	                    // 设置单元格内容为字符型
	                    contentCell.setCellValue(data.toString());
                	}
                }
				
				if(!(tb.getValueAt(i, j) == null)){
					cell.setCellValue(tb.getValueAt(i, j).toString());
				}
//				System.out.println(tb.getValueAt(i, j));
			}
		}
		
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

}
