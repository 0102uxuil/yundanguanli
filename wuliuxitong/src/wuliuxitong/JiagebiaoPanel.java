package wuliuxitong;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JiagebiaoPanel extends JPanel {
	JPanel chaxuntiaojian;
	JLabel huozhuming;
	JTextField huozhuming_text;
	
	JTable jiagebiaoTb;
	JScrollPane jiagebiaoSP;
	
	VSTableModel jiagebiaoModel;
	Vector vector;
	
	JButton chazhaoBtn, tianjiaBtn, xiugaiBtn;
	
	JiagebiaoPanel(){
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
		this.chazhaoBtn.addActionListener(new JiagebiaochazhaoBtnActionListener(this));
		
		this.tianjiaBtn = new JButton("添加");
		this.chaxuntiaojian.add(this.tianjiaBtn);
		this.tianjiaBtn.addActionListener(new JiagebiaotianjiaBtnActionListener(this));
		
		this.xiugaiBtn = new JButton("修改");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		this.xiugaiBtn.addActionListener(new JiagebiaoxiugaiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"货主",
				"出发地",
				"目的地",
				"货名",
				"价格",
				"司机价格"};
		
		this.vector = new Vector();
		this.jiagebiaoModel = new VSTableModel(this.vector, columnNames);
		this.jiagebiaoTb = new JTable(this.jiagebiaoModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(jiagebiaoTb.getModel());
		jiagebiaoTb.setRowSorter(rowSorter);
		this.jiagebiaoSP = new JScrollPane(this.jiagebiaoTb);
		this.add(this.jiagebiaoSP, BorderLayout.CENTER);
		this.jiagebiaoTb.setRowHeight(UIManager.getInt("TableCellHeight"));
	}
}
