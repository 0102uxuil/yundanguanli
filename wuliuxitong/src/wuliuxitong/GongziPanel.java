package wuliuxitong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GongziPanel extends JPanel {
	
//	int startYear = 1970; // 默认【最小】显示年份
//	int lastYear = 2050; // 默认【最大】显示年份
	
	JPanel chaxuntiaojian;
//	JLabel riqi;
//	JSpinner year_start, month_start, year_end, month_end;
	YearMonthSpinner yearmonth_start, yearmonth_end;
	
	JTable gongziTb;
	JScrollPane gongziSP;
	
	VSTableModel gongziModel;
	Vector vector;
	
	JButton chakanBtn, chakanmingxiBtn;
	
	GongziPanel(){
		super();
		init();
	}

	
	private void init(){
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.yearmonth_start = new YearMonthSpinner();
		this.yearmonth_end = new YearMonthSpinner();
		
		this.chaxuntiaojian.add(new JLabel("日期："));
		this.chaxuntiaojian.add(this.yearmonth_start);
		this.chaxuntiaojian.add(new JLabel("—"));
		this.chaxuntiaojian.add(this.yearmonth_end);
		
		this.chakanBtn = new JButton("查看");
		this.chaxuntiaojian.add(this.chakanBtn);
		this.chakanBtn.addActionListener(new GongzichakanBtnActionListener(this));
		
		this.chakanmingxiBtn = new JButton("查看明细");
		this.chaxuntiaojian.add(this.chakanmingxiBtn);
		this.chakanmingxiBtn.addActionListener(new GongzichakanmingxiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"司机",
				"日期",
				"总工资",
				"已付工资",
				"未付工资"};
		
		this.vector = new Vector();
		this.gongziModel = new VSTableModel(this.vector, columnNames);
		this.gongziTb = new JTable(this.gongziModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(gongziTb.getModel());
		gongziTb.setRowSorter(rowSorter);
		this.gongziSP = new JScrollPane(this.gongziTb);
		this.add(this.gongziSP, BorderLayout.CENTER);
		this.gongziTb.setRowHeight(UIManager.getInt("TableCellHeight"));
	}
	
}
