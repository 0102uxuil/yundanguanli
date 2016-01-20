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

public class YuekaixiaoPanel extends JPanel {
	
	int startYear = 1970; // 默认【最小】显示年份
	int lastYear = 2050; // 默认【最大】显示年份
	
	JPanel chaxuntiaojian;
	JLabel chepaihao, riqi;
	JTextField chepaihao_text;
	JSpinner year_start, month_start, year_end, month_end;
	
	JTable yuekaixiaoTb;
	JScrollPane yuekaixiaoSP;
	
	VSTableModel yuekaixiaoModel;
	Vector vector;
	
	JButton chazhaoBtn, tianjiaBtn, xiugaiBtn;
	
	YuekaixiaoPanel(){
		super();
		init();
	}

	
	private void init(){
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.chepaihao = new JLabel("车牌号：");
		this.chepaihao_text = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		this.chaxuntiaojian.add(this.chepaihao_text);
		
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		this.riqi = new JLabel("日期：");
		
		this.year_start = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentYear, startYear, lastYear, 1));
		year_start.setPreferredSize(new Dimension(48, 20));
		year_start.setName("Year");
		year_start.setEditor(new JSpinner.NumberEditor(year_start, "####"));
//		year.addChangeListener(this);
		
		month_start = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentMonth, 1, 12, 1));
		month_start.setPreferredSize(new Dimension(35, 20));
		month_start.setName("Month");
//		month.addChangeListener(this);
		
		this.chaxuntiaojian.add(this.riqi);
		this.chaxuntiaojian.add(this.year_start);
		this.chaxuntiaojian.add(new JLabel("年"));
		this.chaxuntiaojian.add(this.month_start);
		this.chaxuntiaojian.add(new JLabel("月"));
		
		this.year_end = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentYear, startYear, lastYear, 1));
		year_end.setPreferredSize(new Dimension(48, 20));
		year_end.setName("Year");
		year_end.setEditor(new JSpinner.NumberEditor(year_end, "####"));
//		year.addChangeListener(this);
		
		month_end = new JSpinner(new javax.swing.SpinnerNumberModel(
				currentMonth, 1, 12, 1));
		month_end.setPreferredSize(new Dimension(35, 20));
		month_end.setName("Month");
//		month.addChangeListener(this);
		
		this.chaxuntiaojian.add(new JLabel("—"));
		this.chaxuntiaojian.add(this.year_end);
		this.chaxuntiaojian.add(new JLabel("年"));
		this.chaxuntiaojian.add(this.month_end);
		this.chaxuntiaojian.add(new JLabel("月"));
		
		this.chazhaoBtn = new JButton("查找");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new YuekaixiaochazhaoBtnActionListener(this));
		
		this.tianjiaBtn = new JButton("添加");
		this.chaxuntiaojian.add(this.tianjiaBtn);
		this.tianjiaBtn.addActionListener(new YuekaixiaotianjiaBtnActionListener(this));
		
		this.xiugaiBtn = new JButton("修改");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		this.xiugaiBtn.addActionListener(new YuekaixiaoxiugaiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"车牌号",
				"日期",
				"粤通卡",
				"电话费",
				"其他费用",
				"备注",
				"总开销"};
		
		this.vector = new Vector();
		this.yuekaixiaoModel = new VSTableModel(this.vector, columnNames);
		this.yuekaixiaoTb = new JTable(this.yuekaixiaoModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(yuekaixiaoTb.getModel());
		yuekaixiaoTb.setRowSorter(rowSorter);
		this.yuekaixiaoSP = new JScrollPane(this.yuekaixiaoTb);
		this.add(this.yuekaixiaoSP, BorderLayout.CENTER);
		this.yuekaixiaoTb.setRowHeight(UIManager.getInt("TableCellHeight"));
		
	}
	
}
