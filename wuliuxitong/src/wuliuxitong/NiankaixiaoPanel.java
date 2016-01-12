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

public class NiankaixiaoPanel extends JPanel {
	
	JPanel chaxuntiaojian;
	JLabel chepaihao;
	JTextField chepaihao_text;
	YearSpinner year_start, year_end;
	
	JTable niankaixiaoTb;
	JScrollPane niankaixiaoSP;
	
	VSTableModel niankaixiaoModel;
	Vector vector;
	
	JButton chazhaoBtn, tianjiaBtn, xiugaiBtn;
	
	NiankaixiaoPanel(){
		super();
		init();
	}
	
	private void init(){
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.chepaihao = new JLabel("���ƺţ�");
		this.chepaihao_text = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		this.chaxuntiaojian.add(this.chepaihao_text);
		
		this.year_start = new YearSpinner();
		this.year_end = new YearSpinner();
		
		this.chaxuntiaojian.add(new JLabel("���ڣ�"));
		this.chaxuntiaojian.add(this.year_start);
		this.chaxuntiaojian.add(new JLabel("��"));
		this.chaxuntiaojian.add(this.year_end);
		
		this.chazhaoBtn = new JButton("����");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		this.chazhaoBtn.addActionListener(new NiankaixiaochazhaoBtnActionListener(this));
		
		this.tianjiaBtn = new JButton("���");
		this.chaxuntiaojian.add(this.tianjiaBtn);
		this.tianjiaBtn.addActionListener(new NiankaixiaotianjiaBtnActionListener(this));
		
		this.xiugaiBtn = new JButton("�޸�");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		this.xiugaiBtn.addActionListener(new NiankaixiaoxiugaiBtnActionListener(this));
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"���",
				"���ƺ�",
				"����",
				"��",
				"�󳵱�ע",
				"����",
				"���ձ�ע",
				"������",
				"�����ձ�ע",
				"��Ӫ��֤",
				"GPRS",
				"��������",
				"��ע",
				"�ܿ���"};
		
		this.vector = new Vector();
		this.niankaixiaoModel = new VSTableModel(this.vector, columnNames);
		this.niankaixiaoTb = new JTable(this.niankaixiaoModel);
		this.niankaixiaoSP = new JScrollPane(this.niankaixiaoTb);
		this.add(this.niankaixiaoSP, BorderLayout.CENTER);
	}
}
