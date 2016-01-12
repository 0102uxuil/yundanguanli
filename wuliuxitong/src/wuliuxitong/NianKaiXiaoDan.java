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

public class NianKaiXiaoDan extends JFrame {

	final static int NianKaiXiaoTianJia = 0;
	final static int NianKaiXiaoXiuGai = 1;
	
	int option;
	
	NiankaixiaoPanel niankaixiaoPanel;
	
	JComboBox chepaihaoCB;
	JTextField shenche, shenchebz, baoxian, baoxianbz, gerenxian, gerenxianbz, shenyingyunzheng, gprs, qitafeiyong, beizhu;
	YearSpinner year;
	
	JButton tijiaoBtn, shanchuBtn, quxiaoBtn;
	
	NianKaiXiaoDan(NiankaixiaoPanel nkxp){
		this.option = NianKaiXiaoDan.NianKaiXiaoTianJia;
		this.niankaixiaoPanel = nkxp;
		init();
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	NianKaiXiaoDan(String chepaihao, String date, NiankaixiaoPanel nkxp){
		this.option = NianKaiXiaoDan.NianKaiXiaoXiuGai;
		this.niankaixiaoPanel = nkxp;
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
        this.year = new YearSpinner();
        this.makeLabelAndYearSpinner(this, "　　　　　日期：", this.year, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.shenche = new JTextField(10);
        this.makeLabelAndTextField(this, "　　审车（元）：", this.shenche, gridbag, c, 2);
        this.shenchebz = new JTextField(10);
        this.makeLabelAndTextField(this, "　　     审车备注：", this.shenchebz, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.baoxian = new JTextField(10);
        this.makeLabelAndTextField(this, "　　车险（元）：", this.baoxian, gridbag, c, 2);
        this.baoxianbz = new JTextField(10);
        this.makeLabelAndTextField(this, "　　     车险备注：", this.baoxianbz, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.gerenxian = new JTextField(10);
        this.makeLabelAndTextField(this, "　个人险（元）：", this.gerenxian, gridbag, c, 2);
        this.gerenxianbz = new JTextField(10);
        this.makeLabelAndTextField(this, "　     个人险备注：", this.gerenxianbz, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.shenyingyunzheng = new JTextField(10);
        this.makeLabelAndTextField(this, "审营运证（元）：", this.shenyingyunzheng, gridbag, c, 2);
        this.gprs = new JTextField(10);
        this.makeLabelAndTextField(this, "　　 　GPRS（元）：", this.gprs, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.qitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "其他费用（元）：", this.qitafeiyong, gridbag, c, 2);
        this.beizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　　 备注：", this.beizhu, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        c.gridwidth = 2;
        JPanel p0 = new JPanel();
		gridbag.setConstraints(p0, c);
		this.add(p0);
        
		JPanel p = new JPanel();
		if(this.option == NianKaiXiaoDan.NianKaiXiaoTianJia){
			this.tijiaoBtn = new JButton("提交");
		}
		if(this.option == NianKaiXiaoDan.NianKaiXiaoXiuGai){
			this.tijiaoBtn = new JButton("修改");
		}
        this.tijiaoBtn.addActionListener(new NiankaixiaodantijiaoBtnActionListener(this));
        p.add(this.tijiaoBtn);
        
        if(this.option == NianKaiXiaoDan.NianKaiXiaoXiuGai){
        	this.shanchuBtn = new JButton("删除");
        	this.shanchuBtn.addActionListener(new NiankaixiaoshanchuBtnActionListener(this));
            p.add(this.shanchuBtn);
        }
        
        this.quxiaoBtn = new JButton("取消");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.quxiaoBtn);
        if(this.option == NianKaiXiaoDan.NianKaiXiaoTianJia){
        	c.gridwidth = 2;
		}
		if(this.option == NianKaiXiaoDan.NianKaiXiaoXiuGai){
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
	
	protected void makeLabelAndYearSpinner(Container parent, 
			String name,
			YearSpinner year,
            GridBagLayout gridbag,
            GridBagConstraints c,
            int gridwidth) {
		JPanel panel = new JPanel(new java.awt.FlowLayout());
		JLabel label= new JLabel(name);
		panel.add(label);
		panel.add(year);
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
		String sql_niankaixiaodan;
		sql_niankaixiaodan = "select chepaihao, riqi, shenche, shenchebz, baoxian, baoxianbz, gerenxian, gerenxianbz, shenyingyunzheng, gprs, qitafeiyong, beizhu from niankaixiaodan "
				+ " where chepaihao = " + "'" + chepaihao + "'" 
				+ " and riqi = " + "'" + date + "'"
				+ ";";
		
		ResultSet rs;
		try {
			rs = DBManager.getInstance().excuteQuery(sql_niankaixiaodan);
			rs.next();
			
			this.chepaihaoCB.setSelectedItem(rs.getString("chepaihao"));
//			this.chepaihao.setText(rs.getString("chepaihao"));
			this.year.setYear(rs.getString("riqi"));
			this.shenche.setText(rs.getString("shenche"));
			this.shenchebz.setText(rs.getString("shenchebz"));
			this.baoxian.setText(rs.getString("baoxian"));
			this.baoxianbz.setText(rs.getString("baoxianbz"));
			this.gerenxian.setText(rs.getString("gerenxian"));
			this.gerenxianbz.setText(rs.getString("gerenxianbz"));
			this.shenyingyunzheng.setText(rs.getString("shenyingyunzheng"));
			this.gprs.setText(rs.getString("gprs"));
			this.qitafeiyong.setText(rs.getString("qitafeiyong"));
			this.beizhu.setText(rs.getString("beizhu"));
			
//			this.chepaihao.setEditable(false);
			this.chepaihaoCB.setEnabled(false);
			this.year.setEditable(false);
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
