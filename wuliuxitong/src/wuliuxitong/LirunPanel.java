package wuliuxitong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class LirunPanel extends JPanel {
	
	JPanel chaxuntiaojian;
	JTextField chepaihao;
	DateSpinner riqi_start, riqi_end;
	
	JButton chazhaoBtn, exportExcelBtn;
	JButton exportDetailBtn;
	
	JTable lirunTb;
	JScrollPane lirunSP;
	
	ResultSet resultSet;
	VSTableModel lirunModel;
	Vector vector;
	
	JTable lirunDetailTb;
	VSTableModel lirunDetailModel;
	Vector detailvector;
	
	LirunPanel(){
		super();
		init();
	}
	
	private void init(){
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.chaxuntiaojian.add(new JLabel("车牌号："));
		this.chepaihao = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		
		this.riqi_start = new DateSpinner();
		this.riqi_end = new DateSpinner();
		this.chaxuntiaojian.add(new JLabel("日期："));
		this.chaxuntiaojian.add(this.riqi_start);
		this.chaxuntiaojian.add(new JLabel("—"));
		this.chaxuntiaojian.add(this.riqi_end);
		
		this.chazhaoBtn = new JButton("利润计算");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new LirunjisuanBtnActionListener(this));
		
		this.exportExcelBtn = new JButton("导出表格");
		this.chaxuntiaojian.add(this.exportExcelBtn);
		this.exportExcelBtn.addActionListener(new ExportLirunExcelBtnActionListener(this));
		
		this.exportDetailBtn = new JButton("导出详细信息");
		this.chaxuntiaojian.add(this.exportDetailBtn);
		this.exportDetailBtn.addActionListener(new ExportLirunDetailExcelBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"运单编号",
				"车牌号",
				"出车日期",
				"出车出发地",
				"出车目的地",
				"回车日期",
				"回车出发地",
				"回车目的地",
				"总开销",
//				"出车已结账金额",
//				"出车未结账金额",
//				"回车已结账金额",
//				"回车未结账金额",
				"已结账金额",
				"未结账金额",
				"预期利润",
				"已得利润",
				"司机价格利润"};
//				"出车收入",
//				"回车收入",
//				"利润"};
		
		
		this.vector = new Vector();
		this.lirunModel = new VSTableModel(this.vector, columnNames);
		this.lirunTb = new JTable(this.lirunModel);
		this.lirunSP = new JScrollPane(this.lirunTb);
//		this.lirunSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		this.lirunTb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.add(this.lirunSP, BorderLayout.CENTER);
		this.lirunTb.setRowHeight(UIManager.getInt("TableCellHeight"));
		
		
		String[] detailcolumnNames = {"序号",
				"运单编号",
				"车牌号",
				"司机",
				"出车日期",
				"出车出发地",
				"出车目的地",
				"回车日期",
				"回车出发地",
				"回车目的地",
				"过路费",
				"粤通卡",
				"工资", 
				"已付工资",
				"吃饭",
				"住宿", 
				"加水", 
				"坐车费",
				"材料费",
				"停车费", 
				"过磅费",
				"装车费",
				"卸车费",
				"轮胎",
				"罚款",
				"小费",
				"修理费",
				"其他",
				"备注",
				"加油站加油",
				"停车场加油",
				"停车场油价",
				"总开销",
				"出车已结账金额",
				"回车已结账金额",
				"出车未结账金额",
				"回车未结账金额",
				"出车预期收益",
				"回车预期收益",
				"已结账金额",
				"未结账金额",
				"预期利润",
				"已得利润"};
		this.detailvector = new Vector();
		this.lirunDetailModel = new VSTableModel(this.detailvector, detailcolumnNames);
		this.lirunDetailTb = new JTable(this.lirunDetailModel);
	}
}
