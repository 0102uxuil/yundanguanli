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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportLirunExcelBtnActionListener implements ActionListener {

	LirunPanel lirunPanel;
	Workbook wb;
	
	ExportLirunExcelBtnActionListener(LirunPanel lrp){
		this.lirunPanel = lrp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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

	private void exportExcel(String absolutepath_without_postfix){
		wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("利润单");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("利润单");
		
		JTable tb= this.lirunPanel.lirunTb;
		row = sheet.createRow(1);
		for(int i = 0; i < tb.getColumnCount(); i++){
			cell = row.createCell(i);
			cell.setCellValue(tb.getColumnName(i));
		}
		
		System.out.println(tb.getRowCount());
		for(int i = 0; i < tb.getRowCount(); i++){
			row = sheet.createRow(2+i);
			for(int j = 0; j < tb.getColumnCount(); j++){
				cell = row.createCell(j);
				if(!(tb.getValueAt(i, j) == null)){
					cell.setCellValue(tb.getValueAt(i, j).toString());
				}
				System.out.println(tb.getValueAt(i, j));
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
