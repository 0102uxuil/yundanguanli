package wuliuxitong;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WeiXiuDan extends JFrame {
	
	final static int WeiXiuDanTianJia = 0;
	final static int WeiXiuDanXiuGai = 1;
	
	WeixiudanPanel weixiudanPanel;
	
	int option;
	
	JComboBox chepaihaoCB;
	JTextField weixiudian, weixiuxiangmu, weixiujine, beizhu;
	DateChooser riqi;
	
	JButton tijiaoBtn, shanchuBtn, quxiaoBtn;
	
	WeiXiuDan(WeixiudanPanel wxdp){
		this.option = WeiXiuDan.WeiXiuDanTianJia;
		this.weixiudanPanel = wxdp;
		init();
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	WeiXiuDan(String chepaihao, String date, String weixiudian, WeixiudanPanel wxd){
		this.option = WeiXiuDan.WeiXiuDanXiuGai;
		this.weixiudanPanel = wxd;
		init();
		filltext(chepaihao, date, weixiudian);
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	private void init(){
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        
        this.chepaihaoCB = new JComboBox(new CheipaihaoComboBoxModel());
        this.makeLabelAndComboBox(this, "　　　车牌号：", this.chepaihaoCB, gridbag, c, 2);
//        this.chepaihao = new JTextField(10);
//        this.makeLabelAndTextField(this, "　　　　车牌号：", this.chepaihao, gridbag, c, 2);
        this.riqi = new DateChooser(this);
        this.makeLabelAndDateChooser(this, "　　　　　日期：", this.riqi, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.weixiudian = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　维修点：", this.weixiudian, gridbag, c, 2);
        this.weixiuxiangmu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　维修项目：", this.weixiuxiangmu, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.weixiujine = new JTextField(10);
        this.makeLabelAndTextField(this, "维修金额（元）：", this.weixiujine, gridbag, c, 2);
        this.beizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　备注：", this.beizhu, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        c.gridwidth = 2;
        JPanel p0 = new JPanel();
		gridbag.setConstraints(p0, c);
		this.add(p0);
        
		JPanel p = new JPanel();
		if(this.option == WeiXiuDan.WeiXiuDanTianJia){
			this.tijiaoBtn = new JButton("提交");
		}
		if(this.option == WeiXiuDan.WeiXiuDanXiuGai){
			this.tijiaoBtn = new JButton("修改");
		}
        this.tijiaoBtn.addActionListener(new WeixiudantijiaoBtnActionListener(this));
        p.add(this.tijiaoBtn);
        
        if(this.option == WeiXiuDan.WeiXiuDanXiuGai){
        	this.shanchuBtn = new JButton("删除");
        	this.shanchuBtn.addActionListener(new WeixiudanshanchuBtnActionListener(this));
            p.add(this.shanchuBtn);
        }
        
        this.quxiaoBtn = new JButton("取消");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.quxiaoBtn);
        if(this.option == WeiXiuDan.WeiXiuDanTianJia){
        	c.gridwidth = 2;
		}
		if(this.option == WeiXiuDan.WeiXiuDanXiuGai){
			c.gridwidth = 3;
		}
        gridbag.setConstraints(p, c);
        this.add(p);
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
	
	protected void makeLabelAndDateChooser(Container parent, 
			String name,
			DateChooser datechooser,
            GridBagLayout gridbag,
            GridBagConstraints c,
            int gridwidth) {
		JPanel panel = new JPanel(new java.awt.FlowLayout());
		JLabel label= new JLabel(name);
		panel.add(label);
		panel.add(datechooser);
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
	
	private void filltext(String chepaihao, String date, String weixiudian){
		String sql_yuekaixiaodan;
		sql_yuekaixiaodan = "select chepaihao, riqi, weixiudian, weixiuxiangmu, weixiujine, beizhu from weixiudan "
				+ " where chepaihao = " + "'" + chepaihao + "'" 
				+ " and riqi = " + "'" + date + "'"
				+ " and weixiudian = " + "'" + weixiudian + "'"
				+ ";";
		
		ResultSet rs;
		try {
			rs = DBManager.getInstance().excuteQuery(sql_yuekaixiaodan);
			rs.next();
			
			this.chepaihaoCB.setSelectedItem(rs.getString("chepaihao"));
//			this.chepaihao.setText(rs.getString("chepaihao"));
			this.riqi.setText(rs.getString("riqi"));
			this.weixiudian.setText(rs.getString("weixiudian"));
			this.weixiuxiangmu.setText(rs.getString("weixiuxiangmu"));
			this.weixiujine.setText(rs.getString("weixiujine"));
			this.beizhu.setText(rs.getString("beizhu"));
			
//			this.chepaihao.setEditable(false);
			this.chepaihaoCB.setEnabled(false);
			this.riqi.setEditable(false);
			this.weixiudian.setEditable(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
