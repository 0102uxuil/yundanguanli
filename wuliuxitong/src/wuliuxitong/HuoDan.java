package wuliuxitong;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HuoDan extends JFrame {
	
	final static int JieZhang = 0;
	final static int XiuGai = 0;
	
	HuodanPanel huodanPanel;
	
	int option;
	
	JTextField yundanbianhao, huowubianhao, chepaihao, chufadi, mudidi, 
			   huoming, zhongliang, zhongliang2, jiage, baodijia, 
			   qitafeiyong, beizhu, 
			   shouxufei,
			   huozhu, yingfujine, shifujine, 
			   jiezhangbeizhu;
	
	DateChooser riqi;
	
	JButton quedingBtn, quxiaoBtn;
	
	JComboBox shifouqingsuan;
	
	HuoDan(int option, String yundanbianhao, String huowubianhao, HuodanPanel hdp){
		super();
		
		this.huodanPanel = hdp;
		
		this.option = option;
		initFrame();
		
		this.yundanbianhao.setText(yundanbianhao);
		this.huowubianhao.setText(huowubianhao);
		initHuodan();
		
		if(option == HuoDan.JieZhang){
			this.yundanbianhao.setEditable(false);
			this.huowubianhao.setEditable(false);
			this.chepaihao.setEditable(false);
			this.riqi.setEditable(false);
			this.chufadi.setEditable(false);
			this.mudidi.setEditable(false);
			
			this.huoming.setEditable(false);
			this.zhongliang.setEditable(false);
			this.zhongliang2.setEditable(false);
			this.jiage.setEditable(false);
			this.baodijia.setEditable(false);
			
//			this.zhesun.setEditable(false);
			this.qitafeiyong.setEditable(false);
			this.beizhu.setEditable(false);
			this.shouxufei.setEditable(false);
			
			this.huozhu.setEditable(false);
			this.yingfujine.setEditable(false);
		}
	}
	
	private void initFrame(){
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        
        this.yundanbianhao = new JTextField(20);
        this.makeLabelAndTextField(this, "　　　运单编号：", this.yundanbianhao, gridbag, c, 2);
        this.huowubianhao = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　货物编号：", this.huowubianhao, gridbag, c, 2);
        this.chepaihao = new JTextField(10);
        this.addpanelandchangeline(gridbag, c);
        
        this.makeLabelAndTextField(this, "　　　　车牌号：", this.chepaihao, gridbag, c, 2);
        this.riqi = new DateChooser(this);
        this.makeLabelAndDateChooser(this, "　　　　　日期：", this.riqi, gridbag, c, 2);
        this.chufadi = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　出发地：", this.chufadi, gridbag, c, 2);
        this.mudidi = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　目的地：", this.mudidi, gridbag, c, 2);
        
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
        
//        this.zhesun = new JTextField(10);
//        this.makeLabelAndTextField(this, "　　折损：", this.zhesun, gridbag, c, 2);
        this.qitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "其他费用（元）：", this.qitafeiyong, gridbag, c, 2);
        this.beizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　　备注：", this.beizhu, gridbag, c, 2);
        this.shouxufei = new JTextField(10);
        this.makeLabelAndTextField(this, "　　　　手续费：", this.shouxufei, gridbag, c, 2);
        this.huozhu = new JTextField(10);
        this.addpanelandchangeline(gridbag, c);
        
        this.makeLabelAndTextField(this, "　　　　　货主：", this.huozhu, gridbag, c, 2);
        this.yingfujine = new JTextField(10);
        this.makeLabelAndTextField(this, "应付金额（元）：", this.yingfujine, gridbag, c, 2);
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
        this.quedingBtn.addActionListener(new HuodanquedingBtnActionListener(this));
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
	
	private void initHuodan(){
		String sql_huowudan;
		sql_huowudan = "select yundanbianhao, huowubianhao,"
					   + " chepaihao, riqi, chufadi, mudidi,"
					   + " huoming, zhongliang, zhongliang2, jiage, baodijia, qitafeiyong, beizhu, shouxufei,"
					   + " huozhu, yingfujine, shifujine, jiezhangbeizhu"
					   + " from huowudan"
					   + " where yundanbianhao = " + "'" + this.yundanbianhao.getText().trim() + "'"
					   + " and huowubianhao = " + "'" + this.huowubianhao.getText().trim() + "'" + ";";
		
		ResultSet rs;
		try {
			rs = DBManager.getInstance().excuteQuery(sql_huowudan);
			
			rs.next();
			
			this.chepaihao.setText(rs.getString("chepaihao"));
			this.riqi.setText(rs.getString("riqi"));
			this.chufadi.setText(rs.getString("chufadi"));
			this.mudidi.setText(rs.getString("mudidi"));
			
			this.huoming.setText(rs.getString("huoming"));
			this.zhongliang.setText(rs.getString("zhongliang"));
			this.zhongliang2.setText(rs.getString("zhongliang2"));
			this.jiage.setText(rs.getString("jiage"));
			this.baodijia.setText(rs.getString("baodijia"));
			
//			this.zhesun.setText(rs.getString("zhesun"));
			this.qitafeiyong.setText(rs.getString("qitafeiyong"));
			this.beizhu.setText(rs.getString("beizhu"));
			this.shouxufei.setText(rs.getString("shouxufei"));
			
			this.huozhu.setText(rs.getString("huozhu"));
			this.yingfujine.setText(rs.getString("yingfujine"));
			this.shifujine.setText(rs.getString("shifujine"));
			this.jiezhangbeizhu.setText(rs.getString("jiezhangbeizhu"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
