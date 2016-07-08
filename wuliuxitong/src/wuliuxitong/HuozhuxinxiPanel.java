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

public class HuozhuxinxiPanel extends JPanel {
	JPanel chaxuntiaojian;
	JLabel huozhuming;
	JTextField huozhuming_text;
	
	JTable huozhuxinxiTb;
	JScrollPane huozhuxinxiSP;
	
	VSTableModel huozhuxinxiModel;
	Vector vector;
	
	JButton chazhaoBtn, tianjiaBtn, xiugaiBtn;
	
	HuozhuxinxiPanel(){
		super();
		init();
	}
	
	private void init(){
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.huozhuming = new JLabel("货主名：");
		this.huozhuming_text = new JTextField(10);
		this.chaxuntiaojian.add(this.huozhuming);
		this.chaxuntiaojian.add(this.huozhuming_text);
		
		this.chazhaoBtn = new JButton("查找");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new HuozhuxinxichazhaoBtnActionListener(this));
		
		this.tianjiaBtn = new JButton("添加");
		this.chaxuntiaojian.add(this.tianjiaBtn);
		this.tianjiaBtn.addActionListener(new HuozhuxinxitianjiaBtnActionListener(this));
		
		this.xiugaiBtn = new JButton("修改");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		this.xiugaiBtn.addActionListener(new HuozhuxinxixiugaiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"货主"};
		
		this.vector = new Vector();
		this.huozhuxinxiModel = new VSTableModel(this.vector, columnNames);
		this.huozhuxinxiTb = new JTable(this.huozhuxinxiModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(huozhuxinxiTb.getModel());
		huozhuxinxiTb.setRowSorter(rowSorter);
		this.huozhuxinxiSP = new JScrollPane(this.huozhuxinxiTb);
		this.add(this.huozhuxinxiSP, BorderLayout.CENTER);
		this.huozhuxinxiTb.setRowHeight(UIManager.getInt("TableCellHeight"));
	}
}
