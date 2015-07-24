package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportHuodanExcelBtnActionListener implements ActionListener {
	
	HuodanPanel huodanPanel;
	Workbook wb;
	
	ExportHuodanExcelBtnActionListener(HuodanPanel hd){
		this.huodanPanel = hd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser();
//		 try {
//			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		 } catch (Exception ex) {
//			 ex.printStackTrace();
//		 }
//		 SwingUtilities.updateComponentTreeUI(chooser);
		 
//	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fc.setSelectedFile(new File(""));
        int returnVal = fc.showSaveDialog(this.huodanPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
            if(file != null){
            	if(verifyDuplicate(file.getAbsolutePath() + ".xls")){
            		int jop = JOptionPane.showConfirmDialog(this.huodanPanel, "���ļ��Ѵ����Ƿ񸲸ǣ�", "��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
//            	JOptionPane.showMessageDialog(this.huodanPanel, "�ļ�������Ϊ�գ�", "�ļ���Ϊ��", JOptionPane.PLAIN_MESSAGE);
            }
            
        } else {
            System.out.println("Save command cancelled by user.");
        }
        
        
        

//        if (ret != JFileChooser.APPROVE_OPTION) {
//        	return;
//        }

//         File f = chooser.getSelectedFile();
//         this.huodanPanel.setTitle(f.getName());
//         Thread saver = new FileSaver(f, editor.getDocument());
//         saver.start();
	}
	
	private boolean verifyDuplicate(String absolutepath){
		File file = new File(absolutepath);
		boolean exists = file.exists();
		int jop;
		return exists;
	}

	private void exportExcel(String absolutepath_without_postfix){
		wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("���˵�");
//		sheet.addMergedRegion(new CellRangeAddress(
//	            0, //first row (0-based)
//	            0, //last row  (0-based)
//	            1, //first column (0-based)
//	            11 //last column  (0-based)
//	    ));
//		HSSFFont f  = wb.createFont();      
//		f.setFontHeightInPoints((short) 11);//�ֺ�      
//		f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//�Ӵ�      
//
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setFont(f);      
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//���Ҿ���      
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//���¾���      
//		CreationHelper createHelper = wb.getCreationHelper();
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("���˵�");
		
//		row = sheet.createRow(1);
//		cell = row.createCell(0);
//		cell.setCellValue("���");
//		cell = row.createCell(1);
//		cell.setCellValue("���ƺ�");
//		cell = row.createCell(2);
//		cell.setCellValue("����");
//		cell = row.createCell(3);
//		cell.setCellValue("������");
//		cell = row.createCell(4);
//		cell.setCellValue("Ŀ�ĵ�");
//		cell = row.createCell(5);
//		cell.setCellValue("����");
//		cell = row.createCell(6);
//		cell.setCellValue("����");
//		cell = row.createCell(7);
//		cell.setCellValue("����");
//		cell = row.createCell(8);
//		cell.setCellValue("����");
//		cell = row.createCell(9);
//		cell.setCellValue("���׼�");
//		cell = row.createCell(10);
//		cell.setCellValue("���");
		JTable tb= this.huodanPanel.huodanTb;
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
