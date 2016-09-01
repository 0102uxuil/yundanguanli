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

public class JiaGeBiao extends JFrame {
	final static int JiaGeTianJia = 0;
	final static int JiaGeXiuGai = 1;
	
	JiagebiaoPanel jgbp;
	String huozhu_str, chufadi_str, mudidi_str, huoming_str;
	
	int option;
	
	JTextField huozhu, chufadi, mudidi, huoming, jiage, sijijiage;
	
	JButton tijiaoBtn, shanchuBtn, quxiaoBtn;
	
	JiaGeBiao(JiagebiaoPanel jp){
		this.option = JiaGeBiao.JiaGeTianJia;
		this.jgbp = jp;
		init();
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	JiaGeBiao(String huozhu, String chufadi, String mudidi, String huoming, JiagebiaoPanel jp){
		this.option = JiaGeBiao.JiaGeXiuGai;
		this.jgbp = jp;
		this.huozhu_str = huozhu;
		this.chufadi_str = chufadi;
		this.mudidi_str = mudidi;
		this.huoming_str = huoming;
		init();
		filltext(huozhu, chufadi, mudidi, huoming);
		this.huozhu.setEditable(false);
		this.chufadi.setEditable(false);
		this.mudidi.setEditable(false);
		this.huoming.setEditable(false);
		
		this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)*2/5);
        this.setVisible(true);
	}
	
	private void init(){
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        
        this.huozhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　货主：", this.huozhu, gridbag, c, 2);
        this.chufadi = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　出发地：", this.chufadi, gridbag, c, 2);
        this.mudidi = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　目的地：", this.mudidi, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        this.huoming = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　货名：", this.huoming, gridbag, c, 2);
        this.jiage = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　价格：", this.jiage, gridbag, c, 2);
        this.sijijiage = new JTextField(10);
        this.makeLabelAndTextField(this, "　　司机价格：", this.sijijiage, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        c.gridwidth = 2;
        JPanel p0 = new JPanel();
		gridbag.setConstraints(p0, c);
		this.add(p0);
        
		JPanel p = new JPanel();
		if(this.option == JiaGeBiao.JiaGeTianJia){
			this.tijiaoBtn = new JButton("提交");
		}
		if(this.option == JiaGeBiao.JiaGeXiuGai){
			this.tijiaoBtn = new JButton("修改");
		}
        this.tijiaoBtn.addActionListener(new JiagebiaotijiaoBtnActionListener(this));
        p.add(this.tijiaoBtn);
        
        if(this.option == JiaGeBiao.JiaGeXiuGai){
        	this.shanchuBtn = new JButton("删除");
        	this.shanchuBtn.addActionListener(new JiagebiaoshanchuBtnActionListener(this));
            p.add(this.shanchuBtn);
        }
        
        this.quxiaoBtn = new JButton("取消");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.quxiaoBtn);
        if(this.option == JiaGeBiao.JiaGeTianJia){
        	c.gridwidth = 2;
		}
		if(this.option == JiaGeBiao.JiaGeXiuGai){
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
	
	protected void addpanelandchangeline(GridBagLayout gridbag, GridBagConstraints c){
		JPanel p = new JPanel();
	    c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(p, c);
	    this.add(p);
	}
	
	private void filltext(String huozhu, String chufadi, String mudidi, String huoming){
		String sql_cheliangxinxi;
		sql_cheliangxinxi = "select huozhu, chufadi, mudidi, huoming, jiage, sijijiage from jiagebiao "
				+ " where huozhu = " + "'" + huozhu + "'"
				+ " and chufadi = " + "'" + chufadi + "'"
				+ " and mudidi = " + "'" + mudidi + "'"
				+ " and huoming = " + "'" + huoming + "'"
				+ ";";
		
		ResultSet rs;
		try {
			rs = DBManager.getInstance().excuteQuery(sql_cheliangxinxi);
			rs.next();
			
			this.huozhu.setText(rs.getString("huozhu"));
			this.chufadi.setText(rs.getString("chufadi"));
			this.mudidi.setText(rs.getString("mudidi"));
			this.huoming.setText(rs.getString("huoming"));
			this.jiage.setText(rs.getString("jiage"));
			this.sijijiage.setText(rs.getString("sijijiage"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
