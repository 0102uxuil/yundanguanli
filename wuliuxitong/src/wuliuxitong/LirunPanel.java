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
		
		this.chaxuntiaojian.add(new JLabel("���ƺţ�"));
		this.chepaihao = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		
		this.riqi_start = new DateSpinner();
		this.riqi_end = new DateSpinner();
		this.chaxuntiaojian.add(new JLabel("���ڣ�"));
		this.chaxuntiaojian.add(this.riqi_start);
		this.chaxuntiaojian.add(new JLabel("��"));
		this.chaxuntiaojian.add(this.riqi_end);
		
		this.chazhaoBtn = new JButton("�������");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new LirunjisuanBtnActionListener(this));
		
		this.exportExcelBtn = new JButton("�������");
		this.chaxuntiaojian.add(this.exportExcelBtn);
		this.exportExcelBtn.addActionListener(new ExportLirunExcelBtnActionListener(this));
		
		this.exportDetailBtn = new JButton("������ϸ��Ϣ");
		this.chaxuntiaojian.add(this.exportDetailBtn);
		this.exportDetailBtn.addActionListener(new ExportLirunDetailExcelBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"���",
				"�˵����",
				"���ƺ�",
				"��������",
				"����������",
				"����Ŀ�ĵ�",
				"�س�����",
				"�س�������",
				"�س�Ŀ�ĵ�",
				"�ܿ���",
//				"�����ѽ��˽��",
//				"����δ���˽��",
//				"�س��ѽ��˽��",
//				"�س�δ���˽��",
				"�ѽ��˽��",
				"δ���˽��",
				"Ԥ������",
				"�ѵ�����",
				"˾���۸�����"};
//				"��������",
//				"�س�����",
//				"����"};
		
		
		this.vector = new Vector();
		this.lirunModel = new VSTableModel(this.vector, columnNames);
		this.lirunTb = new JTable(this.lirunModel);
		this.lirunSP = new JScrollPane(this.lirunTb);
//		this.lirunSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		this.lirunTb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.add(this.lirunSP, BorderLayout.CENTER);
		this.lirunTb.setRowHeight(UIManager.getInt("TableCellHeight"));
		
		
		String[] detailcolumnNames = {"���",
				"�˵����",
				"���ƺ�",
				"˾��",
				"��������",
				"����������",
				"����Ŀ�ĵ�",
				"�س�����",
				"�س�������",
				"�س�Ŀ�ĵ�",
				"��·��",
				"��ͨ��",
				"����", 
				"�Ѹ�����",
				"�Է�",
				"ס��", 
				"��ˮ", 
				"������",
				"���Ϸ�",
				"ͣ����", 
				"������",
				"װ����",
				"ж����",
				"��̥",
				"����",
				"С��",
				"�����",
				"����",
				"��ע",
				"����վ����",
				"ͣ��������",
				"ͣ�����ͼ�",
				"�ܿ���",
				"�����ѽ��˽��",
				"�س��ѽ��˽��",
				"����δ���˽��",
				"�س�δ���˽��",
				"����Ԥ������",
				"�س�Ԥ������",
				"�ѽ��˽��",
				"δ���˽��",
				"Ԥ������",
				"�ѵ�����"};
		this.detailvector = new Vector();
		this.lirunDetailModel = new VSTableModel(this.detailvector, detailcolumnNames);
		this.lirunDetailTb = new JTable(this.lirunDetailModel);
	}
}
