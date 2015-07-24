package wuliuxitong;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//vectorÿ��������Ԫ�ظ��� ������� columnNames������
public class VSTableModel extends AbstractTableModel {

//	ResultSet resultSet;
	Vector vector;
	String[] columnNames;
	
	VSTableModel(Vector v, String[] columnNames){
		this.vector = v;
		this.columnNames = columnNames;
	}
	
//	HuodanTableModel(ResultSet rs, String[] columnNames){
////		this.resultSet = rs;
//		init(rs, columnNames);
//	}
	
//	public void init(ResultSet rs, String[] columnNames){
//		this.columnNames = columnNames;
//		this.vector = new Vector();
//		this.vector.removeAllElements();//��ʼ���������� 
//		try {
//			int count = 1;
//			while(rs.next()){ 
//				Vector rec_vector=new Vector(); 
//				//�ӽ������ȡ���ݷ�������rec_vector�� 
//				rec_vector.addElement(count);
//				count++;
//				for(int i=1; i<=columnNames.length-2; i++){
//					rec_vector.addElement(rs.getString(i)); 
//				}
//				this.vector.addElement(rec_vector);//����rec_vector��������vect��
//				
//				float jine;
//				if(rs.getString("baodijia") == null || rs.getString("baodijia") == ""){
//					jine = Float.parseFloat(rs.getString("zhongliang"))*Float.parseFloat(rs.getString("jiage"));
//					rec_vector.addElement(jine);
//				} else {
//					jine = Float.parseFloat(rs.getString("baodijia"));
//					rec_vector.addElement(jine);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void resetVector(ResultSet rs){
//		this.vector = new Vector();
//		this.vector.removeAllElements();//��ʼ����������
//		try {
//			int count = 1;
//			while(rs.next()){ 
//				Vector rec_vector=new Vector(); 
//				//�ӽ������ȡ���ݷ�������rec_vector�� 
//				rec_vector.addElement(count);
//				count++;
//				for(int i=1; i<=columnNames.length-2; i++){
//					rec_vector.addElement(rs.getString(i)); 
//				}
//				this.vector.addElement(rec_vector);//����rec_vector��������vect�� 
//				
//				float jine;
//				if(rs.getString("baodijia") == null || rs.getString("baodijia") == ""){
//					jine = Float.parseFloat(rs.getString("zhongliang"))*Float.parseFloat(rs.getString("jiage"));
//					rec_vector.addElement(jine);
//				} else {
//					jine = Float.parseFloat(rs.getString("baodijia"));
//					rec_vector.addElement(jine);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void resetVector(Vector v){
		this.vector = v;
	}
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.columnNames[column];
	}



	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.vector.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
//		try {
////			for(int i = 0; i < rowIndex; i++){
////				rs.next();
////			}
//			this.resultSet.absolute(rowIndex+1);
//			return this.resultSet.getObject(columnIndex);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		if(!this.vector.isEmpty()){
			return ((Vector)(this.vector).elementAt(rowIndex)).elementAt(columnIndex);
		} else {
			return null;
		}
	}
	
	public void deleteRow(int i){
		this.vector.remove(i);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
//		super.setValueAt(aValue, rowIndex, columnIndex);
		((Vector)(this.vector).elementAt(rowIndex)).setElementAt(aValue, columnIndex);
	}
	
//	@Override
//	public Class getColumnClass(int columnIndex){
//		Class returnValue;
//		if((columnIndex >= 0) && (columnIndex <= this.getColumnCount())){
//			returnValue = this.getValueAt(0, columnIndex).getClass();
//		} else {
//			returnValue = Object.class;
//		}
//		return returnValue;
//	}
	
}
