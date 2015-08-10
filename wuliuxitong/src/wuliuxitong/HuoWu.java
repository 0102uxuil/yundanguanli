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
        this.makeLabelAndTextField(this, "　　　货物编号：", this.huowubianhao, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.huoming = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　货名：", this.huoming, gridbag, c, 2);
        this.zhongliang = new JTextField(10);
        this.makeLabelAndTextField(this, "　　重量（元）：", this.zhongliang, gridbag, c, 2);
        this.zhongliang2 = new JTextField(10);
        this.makeLabelAndTextField(this, "　 重量2（元）：", this.zhongliang2, gridbag, c, 2);
        this.jiage = new JTextField(10);
        this.makeLabelAndTextField(this, "　　单价（元）：", this.jiage, gridbag, c, 2);
        this.baodijia = new JTextField(10);
        this.makeLabelAndTextField(this, "　保底价（元）：", this.baodijia, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.qitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "其他费用（元）：", this.qitafeiyong, gridbag, c, 2);
        this.beizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　备注：", this.beizhu, gridbag, c, 2);
        this.shouxufei = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　手续费：", this.shouxufei, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.huozhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　货主：", this.huozhu, gridbag, c, 2);
        this.shifujine = new JTextField(10);
        this.makeLabelAndTextField(this, "实付金额（元）：", this.shifujine, gridbag, c, 2);
        this.jiezhangbeizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　结账备注：", this.jiezhangbeizhu, gridbag, c, 2);
        String[] str = {"否", "是"};
        this.shifouqingsuan = new JComboBox(str);
        this.makeLabelAndComboBox(this, "　　　是否清算：", this.shifouqingsuan, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        JPanel p = new JPanel();
		this.quedingBtn = new JButton("确定");
		p.add(this.quedingBtn);
        this.quedingBtn.addActionListener(new HuoWuquedingBtnActionListener(this));
        this.quxiaoBtn = new JButton("取消");
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
		if(tm.getValueAt(selectedRow, 13).toString().equals("是")){
			this.shifouqingsuan.setSelectedItem("是");
		}
		if(tm.getValueAt(selectedRow, 13).toString().equals("否")){
			this.shifouqingsuan.setSelectedItem("否");
		}
		
		this.huowubianhao.setEditable(false);
	}
}
