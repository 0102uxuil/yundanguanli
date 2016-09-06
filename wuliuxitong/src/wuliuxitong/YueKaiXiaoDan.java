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
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class YueKaiXiaoDan extends JFrame {

	final static int YueKaiXiaoTianJia = 0;
	final static int YueKaiXiaoXiuGai = 1;
	
	YuekaixiaoPanel yuekaixiaoPanel;
	
	int option;
	
	JComboBox chepaihaoCB;
	JTextField yuetongka, dianhuafei, qitafeiyong, beizhu;
	DateSpinner date;
	
	JButton tijiaoBtn, shanchuBtn, quxiaoBtn;
	
	YueKaiXiaoDan(YuekaixiaoPanel ykxp){
		this.option = YueKaiXiaoDan.YueKaiXiaoTianJia;
		this.yuekaixiaoPanel = ykxp;
		init();
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	YueKaiXiaoDan(String chepaihao, String date, YuekaixiaoPanel ykxp){
		this.option = YueKaiXiaoDan.YueKaiXiaoXiuGai;
		this.yuekaixiaoPanel = ykxp;
		init();
		filltext(chepaihao, date);
		
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
        this.date = new DateSpinner();
        this.makeLabelAndDateSpinner(this, "　　　　　日期：", this.date, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.yuetongka = new JTextField(10);
        this.makeLabelAndTextField(this, "　粤通卡（元）：", this.yuetongka, gridbag, c, 2);
        this.dianhuafei = new JTextField(10);
        this.makeLabelAndTextField(this, "　电话费（元）：", this.dianhuafei, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.qitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "　停车费（元）：", this.qitafeiyong, gridbag, c, 2);//其他费用改成停车费
        this.beizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　备注：", this.beizhu, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        c.gridwidth = 2;
        JPanel p0 = new JPanel();
		gridbag.setConstraints(p0, c);
		this.add(p0);
        
		JPanel p = new JPanel();
		if(this.option == YueKaiXiaoDan.YueKaiXiaoTianJia){
			this.tijiaoBtn = new JButton("提交");
		}
		if(this.option == YueKaiXiaoDan.YueKaiXiaoXiuGai){
			this.tijiaoBtn = new JButton("修改");
		}
        this.tijiaoBtn.addActionListener(new YuekaixiaodantijiaoBtnActionListener(this));
        p.add(this.tijiaoBtn);
        
        if(this.option == YueKaiXiaoDan.YueKaiXiaoXiuGai){
        	this.shanchuBtn = new JButton("删除");
        	this.shanchuBtn.addActionListener(new YuekaixiaoshanchuBtnActionListener(this));
            p.add(this.shanchuBtn);
        }
        
        this.quxiaoBtn = new JButton("取消");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.quxiaoBtn);
        if(this.option == YueKaiXiaoDan.YueKaiXiaoTianJia){
        	c.gridwidth = 2;
		}
		if(this.option == YueKaiXiaoDan.YueKaiXiaoXiuGai){
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
	
	protected void makeLabelAndDateSpinner(Container parent, 
			String name,
			DateSpinner date,
            GridBagLayout gridbag,
            GridBagConstraints c,
            int gridwidth) {
		JPanel panel = new JPanel(new java.awt.FlowLayout());
		JLabel label= new JLabel(name);
		panel.add(label);
		panel.add(date);
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
	
	private void filltext(String chepaihao, String date){
		String sql_yuekaixiaodan;
		sql_yuekaixiaodan = "select chepaihao, riqi, yuetongka, dianhuafei, qitafeiyong, beizhu from yuekaixiaodan "
				+ " where chepaihao = " + "'" + chepaihao + "'" 
				+ " and riqi = " + "'" + date + "'"
				+ ";";
		
		ResultSet rs;
		try {
			rs = DBManager.getInstance().excuteQuery(sql_yuekaixiaodan);
			rs.next();
			
			this.chepaihaoCB.setSelectedItem(rs.getString("chepaihao"));
//			this.chepaihao.setText(rs.getString("chepaihao"));
			this.date.setDate(rs.getString("riqi"));
			this.yuetongka.setText(rs.getString("yuetongka"));
			this.dianhuafei.setText(rs.getString("dianhuafei"));
			this.qitafeiyong.setText(rs.getString("qitafeiyong"));
			this.beizhu.setText(rs.getString("beizhu"));
			
			this.chepaihaoCB.setEnabled(false);
//			this.chepaihao.setEditable(false);
			this.date.setEditable(false);
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
