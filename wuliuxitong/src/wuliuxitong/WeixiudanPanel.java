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

public class WeixiudanPanel extends JPanel {

	JPanel chaxuntiaojian;
	
	JTextField chepaihao, weixiudian;
	DateChooser riqi_start, riqi_end;
	
	JTable weixiudanTb;
	JScrollPane weixiudanSP;
	
	VSTableModel weixiudanModel;
	Vector vector;
	
	JButton chazhaoBtn, tianjiaBtn, xiugaiBtn;
	
	WeixiudanPanel(){
		super();
		init();
	}
	
	private void init(){
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.chaxuntiaojian.add(new JLabel("���ƺţ�"));
		this.chepaihao = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		
		this.riqi_start = new DateChooser(this);
		this.riqi_end = new DateChooser(this);
		
		this.chaxuntiaojian.add(new JLabel("���ڣ�"));
		this.chaxuntiaojian.add(this.riqi_start);
		this.chaxuntiaojian.add(new JLabel("��"));
		this.chaxuntiaojian.add(this.riqi_end);
		
		this.chaxuntiaojian.add(new JLabel("ά�޵ص㣺"));
		this.weixiudian = new JTextField(10);
		this.chaxuntiaojian.add(this.weixiudian);
		
		this.chazhaoBtn = new JButton("����");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new WeixiudanchazhaoBtnActionListener(this));
		
		this.tianjiaBtn = new JButton("���");
		this.chaxuntiaojian.add(this.tianjiaBtn);
		this.tianjiaBtn.addActionListener(new WeixiudantianjiaBtnActionListener(this));
		
		this.xiugaiBtn = new JButton("�޸�");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		this.xiugaiBtn.addActionListener(new WeixiudanxiugaiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"���",
				"���ƺ�",
				"����",
				"ά�޵�",
				"ά����Ŀ",
				"ά�޽��",
				"��ע"};
		
		this.vector = new Vector();
		this.weixiudanModel = new VSTableModel(this.vector, columnNames);
		this.weixiudanTb = new JTable(this.weixiudanModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(weixiudanTb.getModel());
		weixiudanTb.setRowSorter(rowSorter);
		this.weixiudanSP = new JScrollPane(this.weixiudanTb);
		this.add(this.weixiudanSP, BorderLayout.CENTER);
		this.weixiudanTb.setRowHeight(UIManager.getInt("TableCellHeight"));
	}
	
}
