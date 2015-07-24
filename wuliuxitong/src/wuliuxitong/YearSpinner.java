package wuliuxitong;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class YearSpinner extends JPanel {
	int startYear = 1970; // 默认【最小】显示年份
	int lastYear = 2050; // 默认【最大】显示年份
	
	JSpinner year;
	
	YearSpinner(){
		super();
		
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		
		this.year = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentYear, startYear, lastYear, 1));
		year.setPreferredSize(new Dimension(48, 20));
		year.setName("Year");
		year.setEditor(new JSpinner.NumberEditor(year, "####"));
		
		this.setLayout(new FlowLayout());
		this.add(this.year);
		this.add(new JLabel("年"));
	}
	
	public Object getValue(){
		return this.year.getValue();
	}
	
	public void setYear(String date){
		String[] ymd = date.split("-");
		
		try {
			this.year.setValue(Integer.parseInt(ymd[0]));
			this.year.commitEdit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setEditable(boolean editable){
		this.year.setEnabled(editable);
	}
}
