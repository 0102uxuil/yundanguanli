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
	
//	int startYear = 1970; // Ĭ�ϡ���С����ʾ���
//	int lastYear = 2050; // Ĭ�ϡ������ʾ���
	
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
		
		this.chaxuntiaojian.add(new JLabel("���ڣ�"));
		this.chaxuntiaojian.add(this.yearmonth_start);
		this.chaxuntiaojian.add(new JLabel("��"));
		this.chaxuntiaojian.add(this.yearmonth_end);
		
		this.chakanBtn = new JButton("�鿴");
		this.chaxuntiaojian.add(this.chakanBtn);
		this.chakanBtn.addActionListener(new GongzichakanBtnActionListener(this));
		
		this.chakanmingxiBtn = new JButton("�鿴��ϸ");
		this.chaxuntiaojian.add(this.chakanmingxiBtn);
		this.chakanmingxiBtn.addActionListener(new GongzichakanmingxiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"���",
				"˾��",
				"����",
				"�ܹ���",
				"�Ѹ�����",
				"δ������"};
		
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
