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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CheliangxinxiPanel extends JPanel {
	JPanel chaxuntiaojian;
	JLabel chepaihao, siji;
	JTextField chepaihao_text, siji_text;
	
	JTable cheliangxinxiTb;
	JScrollPane cheliangxinxiSP;
	
	VSTableModel cheliangxinxiModel;
	Vector vector;
	
	JButton chazhaoBtn, tianjiaBtn, xiugaiBtn;
	
	CheliangxinxiPanel(){
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
		
		this.siji = new JLabel("司机：");
		this.siji_text = new JTextField(10);
		this.chaxuntiaojian.add(this.siji);
		this.chaxuntiaojian.add(this.siji_text);
		
		this.chazhaoBtn = new JButton("查找");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new CheliangxinxichazhaoBtnActionListener(this));
		
		this.tianjiaBtn = new JButton("添加");
		this.chaxuntiaojian.add(this.tianjiaBtn);
		this.tianjiaBtn.addActionListener(new CheliangxinxitianjiaBtnActionListener(this));
		
		this.xiugaiBtn = new JButton("修改");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		this.xiugaiBtn.addActionListener(new CheliangxinxixiugaiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"车牌号",
				"司机"};
		
		this.vector = new Vector();
		this.cheliangxinxiModel = new VSTableModel(this.vector, columnNames);
		this.cheliangxinxiTb = new JTable(this.cheliangxinxiModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(cheliangxinxiTb.getModel());
		cheliangxinxiTb.setRowSorter(rowSorter);
		this.cheliangxinxiSP = new JScrollPane(this.cheliangxinxiTb);
		this.add(this.cheliangxinxiSP, BorderLayout.CENTER);
	}
}
