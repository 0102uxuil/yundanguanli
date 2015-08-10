package wuliuxitong;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HuoWu extends JFrame {
	
	VSTableModel tm;
	int selectedRow;
	YunDan yundan;
	
	JTextField huowubianhao,
			   huoming, zhongliang, zhongliang2, jiage, baodijia, 
			   qitafeiyong, beizhu, shouxufei,
			   huozhu, shifujine, jiezhangbeizhu;
	JComboBox shifouqingsuan;
	JButton quedingBtn, quxiaoBtn;
	
	HuoWu(VSTableModel tm, int selectedRow){
		this.tm = tm;
		this.selectedRow = selectedRow;
		inithuowu();
		initData(tm, selectedRow);
	}
	
	private void inithuowu(){
		
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        
        this.huowubianhao = new JTextField(10);
        this.makeLabelAndTextField(this, "�����������ţ�", this.huowubianhao, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.huoming = new JTextField(10);
        this.makeLabelAndTextField(this, "����������������", this.huoming, gridbag, c, 2);
        this.zhongliang = new JTextField(10);
        this.makeLabelAndTextField(this, "����������Ԫ����", this.zhongliang, gridbag, c, 2);
        this.zhongliang2 = new JTextField(10);
        this.makeLabelAndTextField(this, "�� ����2��Ԫ����", this.zhongliang2, gridbag, c, 2);
        this.jiage = new JTextField(10);
        this.makeLabelAndTextField(this, "�������ۣ�Ԫ����", this.jiage, gridbag, c, 2);
        this.baodijia = new JTextField(10);
        this.makeLabelAndTextField(this, "�����׼ۣ�Ԫ����", this.baodijia, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.qitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "�������ã�Ԫ����", this.qitafeiyong, gridbag, c, 2);
        this.beizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "������������ע��", this.beizhu, gridbag, c, 2);
        this.shouxufei = new JTextField(10);
        this.makeLabelAndTextField(this, "�������������ѣ�", this.shouxufei, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.huozhu = new JTextField(10);
        this.makeLabelAndTextField(this, "����������������", this.huozhu, gridbag, c, 2);
        this.shifujine = new JTextField(10);
        this.makeLabelAndTextField(this, "ʵ����Ԫ����", this.shifujine, gridbag, c, 2);
        this.jiezhangbeizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "���������˱�ע��", this.jiezhangbeizhu, gridbag, c, 2);
        String[] str = {"��", "��"};
        this.shifouqingsuan = new JComboBox(str);
        this.makeLabelAndComboBox(this, "�������Ƿ����㣺", this.shifouqingsuan, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        JPanel p = new JPanel();
		this.quedingBtn = new JButton("ȷ��");
		p.add(this.quedingBtn);
        this.quedingBtn.addActionListener(new HuoWuquedingBtnActionListener(this));
        this.quxiaoBtn = new JButton("ȡ��");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.quxiaoBtn);
        gridbag.setConstraints(p, c);
        this.add(p);
        this.addpanelandchangeline(gridbag, c);
        
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
        this.setVisible(true);
	}
	
	protected void makeLabelAndTextField(Container parent, 
			String name,
			JTextField textfield,
            GridBagLayout gridbag,
            GridBagConstraints c,
            int gridwidth) {
		JPanel panel = new JPanel(new java.awt.FlowLayout());
		JLabel label= new JLabel(name);
		panel.add(label);
		panel.add(textfield);
		c.gridwidth = gridwidth;
		gridbag.setConstraints(panel, c);
		this.add(panel);
	}
	
	protected void makeLabelAndComboBox(Container parent, 
			String name,
			JComboBox combobox,
            GridBagLayout gridbag,
            GridBagConstraints c,
            int gridwidth) {
		JPanel panel = new JPanel(new java.awt.FlowLayout());
		JLabel label= new JLabel(name);
		panel.add(label);
		panel.add(combobox);
		c.gridwidth = gridwidth;
		gridbag.setConstraints(panel, c);
		this.add(panel);
	}
	
	protected void addpanelandchangeline(GridBagLayout gridbag, GridBagConstraints c){
		JPanel p = new JPanel();
	    c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(p, c);
	    this.add(p);
	}
	
	private void initData(VSTableModel tm, int selectedRow){
		this.huowubianhao.setText(tm.getValueAt(selectedRow, 0).toString());
		this.huoming.setText(tm.getValueAt(selectedRow, 1).toString());
		this.zhongliang.setText(tm.getValueAt(selectedRow, 2).toString());
		this.zhongliang2.setText(tm.getValueAt(selectedRow, 3).toString());
		this.jiage.setText(tm.getValueAt(selectedRow, 4).toString());
		this.baodijia.setText(tm.getValueAt(selectedRow, 5).toString());
		this.huozhu.setText(tm.getValueAt(selectedRow, 6).toString());
		this.qitafeiyong.setText(tm.getValueAt(selectedRow, 7).toString());
		this.beizhu.setText(tm.getValueAt(selectedRow, 8).toString());
		this.shouxufei.setText(tm.getValueAt(selectedRow, 9).toString());
		this.shifujine.setText(tm.getValueAt(selectedRow, 11).toString());
		this.jiezhangbeizhu.setText(tm.getValueAt(selectedRow, 12).toString());
		if(tm.getValueAt(selectedRow, 13).toString().equals("��")){
			this.shifouqingsuan.setSelectedItem("��");
		}
		if(tm.getValueAt(selectedRow, 13).toString().equals("��")){
			this.shifouqingsuan.setSelectedItem("��");
		}
		
		this.huowubianhao.setEditable(false);
	}
}
