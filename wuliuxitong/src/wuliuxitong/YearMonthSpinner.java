package wuliuxitong;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class YearMonthSpinner extends JPanel {

	int startYear = 1970; // 默认【最小】显示年份
	int lastYear = 2050; // 默认【最大】显示年份
	
	JSpinner year, month;
	
	YearMonthSpinner(){
		super();
		
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		
		this.year = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentYear, startYear, lastYear, 1));
		year.setPreferredSize(new Dimension(48, 20));
		year.setName("Year");
		year.setEditor(new JSpinner.NumberEditor(year, "####"));
		
		month = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentMonth, 1, 12, 1));
		month.setPreferredSize(new Dimension(35, 20));
		month.setName("Month");
		
		this.setLayout(new FlowLayout());
		this.add(this.year);
		this.add(new JLabel("年"));
		this.add(this.month);
		this.add(new JLabel("月"));
	}
	
	public Object getValue(){
		return this.year.getValue() + "-" + this.month.getValue();
	}
	
	public void setYearMonth(String date){
		String[] ymd = date.split("-");
		
		try {
			this.year.setValue(Integer.parseInt(ymd[0]));
			this.year.commitEdit();
			this.month.setValue(Integer.parseInt(ymd[1]));
			this.month.commitEdit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setEditable(boolean editable){
		this.year.setEnabled(editable);
		this.month.setEnabled(editable);
	}
	
	public String getMonthEndDate(){
//		String[] month31 = {"1", "3", "5", "7", "8", "10", "12"};
		String date;
		String month31 = "1|3|5|7|8|10|12";
		if(this.month.getValue().toString().matches(month31)){
			date = this.year.getValue().toString().trim() + "-" + this.month.getValue().toString().trim() + "-" +"31";
		} else if(this.month.getValue().toString().trim().equals("2")){
			if(Integer.parseInt(this.year.getValue().toString().trim())%100 == 0 && Integer.parseInt(this.year.getValue().toString().trim())%400 == 0){
				date = this.year.getValue().toString().trim() + "-" + this.month.getValue().toString().trim() + "-" +"29";
			} else if(Integer.parseInt(this.year.getValue().toString().trim())%100 != 0 && Integer.parseInt(this.year.getValue().toString().trim())%4 == 0){
				date = this.year.getValue().toString().trim() + "-" + this.month.getValue().toString().trim() + "-" +"29";
			} else {
				date = this.year.getValue().toString().trim() + "-" + this.month.getValue().toString().trim() + "-" +"28";
			}
		} else {
			date = this.year.getValue().toString().trim() + "-" + this.month.getValue().toString().trim() + "-" +"30";
		}
		return date;
	}
}
