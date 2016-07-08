package wuliuxitong;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HuoZhuXinXiDan extends JFrame {
	final static int HuoZhuXinxiTianJia = 0;
	final static int HuoZhuXinxiXiuGai = 1;
	
	HuozhuxinxiPanel hzxxp;
	String huozhu_str;
	
	int option;
	
	JTextField huozhuming;
	
	JButton tijiaoBtn, shanchuBtn, quxiaoBtn;
	
	HuoZhuXinXiDan(HuozhuxinxiPanel hp){
		this.option = HuoZhuXinXiDan.HuoZhuXinxiTianJia;
		this.hzxxp = hp;
		init();
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	HuoZhuXinXiDan(String huozhuming, HuozhuxinxiPanel hp){
		this.option = HuoZhuXinXiDan.HuoZhuXinxiXiuGai;
		this.hzxxp = hp;
		this.huozhu_str = huozhuming;
		init();
		filltext(huozhuming);
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	private void init(){
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        
        this.huozhuming = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　货主：", this.huozhuming, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        c.gridwidth = 2;
        JPanel p0 = new JPanel();
		gridbag.setConstraints(p0, c);
		this.add(p0);
        
		JPanel p = new JPanel();
		if(this.option == HuoZhuXinXiDan.HuoZhuXinxiTianJia){
			this.tijiaoBtn = new JButton("提交");
		}
		if(this.option == HuoZhuXinXiDan.HuoZhuXinxiXiuGai){
			this.tijiaoBtn = new JButton("修改");
		}
        this.tijiaoBtn.addActionListener(new HuozhuxinxitijiaoBtnActionListener(this));
        p.add(this.tijiaoBtn);
        
        if(this.option == HuoZhuXinXiDan.HuoZhuXinxiXiuGai){
        	this.shanchuBtn = new JButton("删除");
        	this.shanchuBtn.addActionListener(new HuozhuxinxishanchuBtnActionListener(this));
            p.add(this.shanchuBtn);
        }
        
        this.quxiaoBtn = new JButton("取消");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.quxiaoBtn);
        if(this.option == HuoZhuXinXiDan.HuoZhuXinxiTianJia){
        	c.gridwidth = 2;
		}
		if(this.option == HuoZhuXinXiDan.HuoZhuXinxiXiuGai){
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
	
//	protected void makeLabelAndDateSpinner(Container parent, 
//			String name,
//			DateSpinner date,
//            GridBagLayout gridbag,
//            GridBagConstraints c,
//            int gridwidth) {
//		JPanel panel = new JPanel(new java.awt.FlowLayout());
//		JLabel label= new JLabel(name);
//		panel.add(label);
//		panel.add(date);
//		c.gridwidth = gridwidth;
//		gridbag.setConstraints(panel, c);
//		this.add(panel);
//	}
	
	protected void addpanelandchangeline(GridBagLayout gridbag, GridBagConstraints c){
		JPanel p = new JPanel();
	    c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(p, c);
	    this.add(p);
	}
	
	private void filltext(String huozhuming){
		String sql_cheliangxinxi;
		sql_cheliangxinxi = "select huozhuming from huozhuxinxi "
				+ " where huozhuming = " + "'" + huozhuming + "'" 
				+ ";";
		
		ResultSet rs;
		try {
			rs = DBManager.getInstance().excuteQuery(sql_cheliangxinxi);
			rs.next();
			
			this.huozhuming.setText(rs.getString("huozhuming"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
