package wuliuxitong;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class YunDan extends JFrame {
	
	final static int YunDanTianjia = 0;
	final static int YunDanXiugai = 1;
	
//	YundanPanel yundanPanel;
	ReLoad reload;
	
	int option;
	
	JTextField yundanbianhao;
//	JTextField chepaihao;
	JTextField chuchekuan;
	JTextField siji;
	JTextField gonglishu;
	JTextField yifugongzi;
	
	JComboBox chepaihaoCB;
	
	DateChooser chucheriqi;
	DateChooser huicheriqi;
//	JTextField chuchechufadi, huichechufadi;
//	JTextField chuchemudidi, huichemudidi;
	
	DBComboBoxModel chuchechufadiCBM, chuchemudidiCBM;
	JComboBox chuchechufadi, chuchemudidi;
//	JComboBox chuchemudidi;
//	TextFieldWithComboBox chuchechufadi;
	DBComboBoxModel huichechufadiCBM, huichemudidiCBM;
	JComboBox huichechufadi, huichemudidi;
	
	JTextField chuchehuowubianhao,huichehuowubianhao;
	JTextField chuchehuoming, huichehuoming;
	JTextField chuchezhongliang, huichezhongliang;
	JTextField chuchezhongliang2, huichezhongliang2;
	JTextField chuchejiage, huichejiage;
	JTextField chuchebaodijia, huichebaodijia;
//	JTextField chuchezhesun, huichezhesun;
	JTextField chuchehuozhu, huichehuozhu;
	JTextField chucheqitafeiyong, huicheqitafeiyong;
	JTextField chuchebeizhu, huichebeizhu;
	JTextField chucheyingfujine, huicheyingfujine;
	JTextField chucheshifujine, huicheshifujine;
	JTextField chuchejiezhangbeizhu, huichejiezhangbeizhu;
	
	JTextField chucheshouxufei, huicheshouxufei;
	
	float chucheyingfuzonge, chucheshifuzonge, huicheyingfuzonge, huicheshifuzonge;
	
	JComboBox chucheshifouqingsuan, huicheshifouqingsuan;
	
	JButton chuchetianjiaBtn, chucheshanchuBtn, chuchexiugaiBtn;
	JTable chuchehuodanTb;
	VSTableModel chuchehuodanTM;
	Vector chuchehuodanV;//��������
	JScrollPane chuchehuodanSP;
	
	JButton huichetianjiaBtn, huicheshanchuBtn, huichexiugaiBtn;
	JTable huichehuodanTb;
	VSTableModel huichehuodanTM;
	Vector huichehuodanV;//�س�����
	JScrollPane huichehuodanSP;
	
	JTextField jiayouzhanjiayou,
			   tingchechangjiayou, tingchechangyoujia,
			   guolufei, yuetongka,
			   gongzi, 
			   chifan, 
			   zhusu, 
			   jiashui, 
			   zuochefei, 
			   cailiaofei,
			   tingchefei, 
			   guobangfei,
			   zhuangchefei,
			   xiechefei,
			   luntai,
			   fakuan,
			   xiaofei,
			   xiulifei,
			   qitafeiyong,
			   beizhu;
	
	JTextField xiaoheji;
	JButton xiaohejiBtn;
	
	JButton yundantijiaoBtn, quxiaoBtn, yundanshanchuBtn;
	
	YunDan(int option, String yundanbianhao, ReLoad rl){
		
//		this.yundanPanel = ydp;
		this.reload = rl;
		this.option = option;
		
		this.chucheyingfuzonge = 0;
		this.chucheshifuzonge = 0;
		this.huicheyingfuzonge = 0;
		this.huicheshifuzonge = 0;

		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);
        //
        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
        this.yundanbianhao = new JTextField(20);//����java����������ǲ����Ŀ�������������ں�����new��ônew�ĵ�ַ�޷����������������ں�����new
        this.makeLabelAndTextField(this, "�������˵���ţ�", this.yundanbianhao, gridbag, c, 2);
//        this.chepaihao = new JTextField(10);
//        this.makeLabelAndTextField(this, "���������ƺţ�", this.chepaihao, gridbag, c, 2);
        this.chepaihaoCB = new JComboBox(new CheipaihaoComboBoxModel());
        this.makeLabelAndComboBox(this, "���������ƺţ�", this.chepaihaoCB, gridbag, c, 2);
        this.chepaihaoCB.addActionListener(new ChepaihaoCBActionListener(this));
        this.chepaihaoCB.setEditable(true);
        this.chuchekuan = new JTextField(10);
        this.makeLabelAndTextField(this, "�����Ԫ����", this.chuchekuan, gridbag, c, 2);
        this.siji = new JTextField(10);
        this.makeLabelAndTextField(this, "��������˾����", this.siji, gridbag, c, 2);
        this.gonglishu = new JTextField(10);
        this.makeLabelAndTextField(this, "��������������", this.gonglishu, gridbag, c, 2);
//        this.yifugongzi = new JTextField(10);
//        this.makeLabelAndTextField(this, "�����Ѹ����ʣ�", this.yifugongzi, gridbag, c, 2);
        this.gongzi = new JTextField(10);
        this.makeLabelAndTextField(this, "�����������ʣ�", this.gongzi, gridbag, c, 2);
        
        this.addpanelandchangeline(gridbag, c);
        
        //��������
        this.chucheriqi = new DateChooser(this){
        	@Override
        	protected void setTextAction() throws Exception {
        		if(option == YunDan.YunDanTianjia) {
        			initYundanbianhao();
        		}
        	}
        };
        this.makeLabelAndDateChooser(this, "�������������ڣ�", this.chucheriqi, gridbag, c, 2);
        
//        this.chuchechufadi = new JTextField(10);
//        this.makeLabelAndTextField(this, "�������������أ�", this.chuchechufadi, gridbag, c, 2);
//        this.chuchemudidi = new JTextField(10);
//        this.makeLabelAndTextField(this, "��������Ŀ�ĵأ�", this.chuchemudidi, gridbag, c, 2);
        JTextField comboBoxSize = new JTextField(15);
        this.chuchechufadiCBM = new DBComboBoxModel(
        		"select distinct chufadi from luxianbiao order by riqi desc;"
        		, "chufadi");
        this.chuchechufadi = new JComboBox(this.chuchechufadiCBM){
            public Dimension getPreferredSize() {
                return new Dimension(comboBoxSize.getPreferredSize().width, comboBoxSize.getPreferredSize().height);
            }
        };
        this.chuchechufadi.addActionListener(new ChuchechufadiComboBoxActionListener(this));
        this.makeLabelAndComboBox(this, "�����������أ�", this.chuchechufadi, gridbag, c, 2);
//        this.chuchechufadi = new TextFieldWithComboBox(){
//			@Override
//			protected void updateComboBoxModel() throws Exception {
//				// TODO Auto-generated method stub
//				String sql;
//				sql = "select distinct chufadi, riqi from luxianbiao where chufadi like "
//						+ "'%" + this.getText() +"%'"
//						+ "order by riqi desc "
//						+ ";";
//				try {
//					ResultSet rs = DBManager.getInstance().excuteQuery(sql);
//					this.getComboBox().removeAllItems();
//					while(rs.next()){
//						this.getComboBox().addItem(rs.getString("chufadi"));
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//        };
//        this.makeLabelAndTextField(this, "�����������أ�", this.chuchechufadi, gridbag, c, 2);
        this.chuchechufadi.setEditable(true);
        this.chuchemudidiCBM = new DBComboBoxModel(
        		"select distinct mudidi from luxianbiao order by riqi desc;"
        		, "mudidi");
        this.chuchemudidi = new JComboBox(this.chuchemudidiCBM){
            public Dimension getPreferredSize() {
                return new Dimension(comboBoxSize.getPreferredSize().width, comboBoxSize.getPreferredSize().height);
            }
        };
        this.makeLabelAndComboBox(this, "������Ŀ�ĵأ�", this.chuchemudidi, gridbag, c, 2);
//        this.chepaihaoCB.addActionListener(new ChepaihaoCBActionListener(this));
        this.chuchemudidi.setEditable(true);
        
        this.addpanelandchangeline(gridbag, c);
        
        this.chuchehuowubianhao = new JTextField(10);
        this.makeLabelAndTextField(this, "�����������ţ�", this.chuchehuowubianhao, gridbag, c, 2);
//        this.chuchehuozhu = new JTextField(10);
//        this.makeLabelAndTextField(this, "����������������", this.chuchehuozhu, gridbag, c, 2);
        this.chuchehuoming = new JTextField(10);
        this.chuchehuozhu = new TextFieldWithComboBox(this.chuchehuoming){
			@Override
			protected void updateComboBoxModel() throws Exception {
				// TODO Auto-generated method stub
				String sql;
				sql = "select distinct huozhu, huowu from huozhubiao where huozhu like "
						+ "'%" + this.getText().replaceAll("'", "") +"%'"
						+ " or huozhupinyin like"
						+ "'%" + Cn2Spell.converterToSpell(this.getText().replaceAll("'", "")) +"%'"
						+ "order by riqi desc "
						+ ";";
//				System.out.println(sql);
				try {
					ResultSet rs = DBManager.getInstance().excuteQuery(sql);
					this.getComboBox().removeAllItems();
					while(rs.next()){
						this.getComboBox().addItem(rs.getString("huozhu"));
//						System.out.println("++"+rs.getString("huowu"));
//						this.relatedTF.setText(rs.getString("huowu"));
//						if(this.relatedTF.getText() == null 
//								|| this.relatedTF.getText().equals("")){
//							this.relatedTF.setText(rs.getString("huowu"));
//						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			protected void updaterelatedTextField() throws Exception {
				// TODO Auto-generated method stub
				String sql;
				sql = "select distinct huozhu, huowu from huozhubiao where huozhu like "
						+ "'%" + this.getComboBox().getSelectedItem().toString().trim() +"%'"
//						+ " or huozhupinyin like"
//						+ "'%" + Cn2Spell.converterToSpell(this.getComboBox().getSelectedItem().toString().trim()) +"%'"
						+ " order by riqi desc "
						+ ";";
				try {
					ResultSet rs = DBManager.getInstance().excuteQuery(sql);
					this.getComboBox().removeAllItems();
					while(rs.next()){
//						this.getComboBox().addItem(rs.getString("huozhu"));
						this.relatedTF.setText(rs.getString("huowu"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        };
        this.makeLabelAndTextField(this, "����������������", this.chuchehuozhu, gridbag, c, 2);
        
        this.makeLabelAndTextField(this, "����������������", this.chuchehuoming, gridbag, c, 2);
        this.chuchezhongliang = new JTextField(10);
        this.makeLabelAndTextField(this, "�����������֣���", this.chuchezhongliang, gridbag, c, 2);
        this.chuchezhongliang2 = new JTextField(10);
        this.makeLabelAndTextField(this, "�� ����2���֣���", this.chuchezhongliang2, gridbag, c, 2);
        this.chuchejiage = new JTextField(10);
        this.makeLabelAndTextField(this, "�������ۣ�Ԫ����", this.chuchejiage, gridbag, c, 2);
        this.chuchebaodijia = new JTextField(10);
        this.makeLabelAndTextField(this, "�����׼ۣ�Ԫ����", this.chuchebaodijia, gridbag, c, 2);
//        this.chuchezhesun = new JTextField(10);
//        this.makeLabelAndTextField(this, "��������", this.chuchezhesun, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.chucheqitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "�������ã�Ԫ����", this.chucheqitafeiyong, gridbag, c, 2);
        this.chuchebeizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "������������ע��", this.chuchebeizhu, gridbag, c, 2);
//        this.chucheyingfujine = new JTextField(10);
//        this.makeLabelAndTextField(this, "Ӧ����", this.chucheyingfujine, gridbag, c, 2);
        
        this.chucheshouxufei = new JTextField(10);
        this.makeLabelAndTextField(this, "�������������ѣ�", this.chucheshouxufei, gridbag, c, 2);
        
        this.chucheshifujine = new JTextField(10);
        this.makeLabelAndTextField(this, "ʵ����Ԫ����", this.chucheshifujine, gridbag, c, 2);
        this.chuchejiezhangbeizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "���������˱�ע��", this.chuchejiezhangbeizhu, gridbag, c, 2);
        
        String[] str = {"��", "��"};
        this.chucheshifouqingsuan = new JComboBox(str);
        this.makeLabelAndComboBox(this, "���Ƿ����㣺", this.chucheshifouqingsuan, gridbag, c, 2);
        
        JPanel cp;
        cp = new JPanel();
        c.gridwidth = 3;
//        c.gridwidth = 1;
        this.chuchetianjiaBtn = new JButton("���");
//        gridbag.setConstraints(this.chuchetianjiaBtn, c);
        cp.add(this.chuchetianjiaBtn);
        this.chuchetianjiaBtn.addActionListener(new ChuchetianjiaBtnActionListener(this));
        
        this.chuchexiugaiBtn = new JButton("�޸�");
        cp.add(this.chuchexiugaiBtn);
        this.chuchexiugaiBtn.addActionListener(new ChuchexiugaiBtnActionListener(this));
        
        this.chucheshanchuBtn = new JButton("ɾ��");
//        gridbag.setConstraints(this.chucheshanchuBtn, c);
        cp.add(this.chucheshanchuBtn);
        this.chucheshanchuBtn.addActionListener(new ChucheshanchuBtnActionListener(this));
        gridbag.setConstraints(cp, c);
        this.add(cp);
        this.addpanelandchangeline(gridbag, c);
        
        this.chuchehuodanV = new Vector();
        
        String[] columnNames = {"������",
        		"����",
				"����",
				"����2",
				"����",
				"���׼�",
//				"����",
				"����",
				"��������",
				"��ע",
				"������",
				"Ӧ�����",
				"ʵ�����",
				"���˱�ע",
				"�Ƿ�����"};
		
		this.chuchehuodanV = new Vector();
		this.chuchehuodanTM = new VSTableModel(this.chuchehuodanV, columnNames);
		this.chuchehuodanTb = new JTable(this.chuchehuodanTM);
		this.chuchehuodanSP = new JScrollPane(this.chuchehuodanTb);
		this.chuchehuodanTb.setRowHeight(UIManager.getInt("TableCellHeight"));
		
		Dimension d = new Dimension();
		d.setSize(this.getSize().getWidth(), 100);
		this.chuchehuodanSP.setPreferredSize(d);
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(this.chuchehuodanSP, c);
		this.add(this.chuchehuodanSP);
		
		//�س�����
		this.huicheriqi = new DateChooser(this);
		this.makeLabelAndDateChooser(this, "�������س����ڣ�", this.huicheriqi, gridbag, c, 2);
//		this.huichechufadi = new JTextField(10);
//        this.makeLabelAndTextField(this, "�������������أ�", this.huichechufadi, gridbag, c, 2);
//        this.huichemudidi = new JTextField(10);
//        this.makeLabelAndTextField(this, "��������Ŀ�ĵأ�", this.huichemudidi, gridbag, c,2);
		this.huichechufadiCBM = new DBComboBoxModel(
        		"select distinct chufadi from luxianbiao order by riqi desc;"
        		, "chufadi");
        this.huichechufadi = new JComboBox(this.huichechufadiCBM){
            public Dimension getPreferredSize() {
                return new Dimension(comboBoxSize.getPreferredSize().width, comboBoxSize.getPreferredSize().height);
            }
        };
        this.makeLabelAndComboBox(this, "�����������أ�", this.huichechufadi, gridbag, c, 2);
        this.huichechufadi.addActionListener(new HuichechufadiComboBoxActionListener(this));
        this.huichechufadi.setEditable(true);
        this.huichemudidiCBM = new DBComboBoxModel(
        		"select distinct mudidi from luxianbiao order by riqi desc;"
        		, "mudidi");
        this.huichemudidi = new JComboBox(this.huichemudidiCBM){
            public Dimension getPreferredSize() {
                return new Dimension(comboBoxSize.getPreferredSize().width, comboBoxSize.getPreferredSize().height);
            }
        };
        this.makeLabelAndComboBox(this, "������Ŀ�ĵأ�", this.huichemudidi, gridbag, c, 2);
//        this.chepaihaoCB.addActionListener(new ChepaihaoCBActionListener(this));
        this.huichemudidi.setEditable(true);
        
        this.addpanelandchangeline(gridbag, c);
        
        this.huichehuowubianhao = new JTextField(10);
        this.makeLabelAndTextField(this, "�����������ţ�", this.huichehuowubianhao, gridbag, c, 2);
//        this.huichehuozhu = new JTextField(10);
//        this.makeLabelAndTextField(this, "����������������", this.huichehuozhu, gridbag, c, 2);
        this.huichehuoming = new JTextField(10);
        this.huichehuozhu = new TextFieldWithComboBox(this.huichehuoming){
			@Override
			protected void updateComboBoxModel() throws Exception {
				// TODO Auto-generated method stub
				String sql;
				sql = "select distinct huozhu, huowu from huozhubiao where huozhu like "
						+ "'%" + this.getText().replaceAll("'", "") +"%'"
						+ " or huozhupinyin like"
						+ "'%" + Cn2Spell.converterToSpell(this.getText().replaceAll("'", "")) +"%'"
						+ " order by riqi desc "
						+ ";";
				try {
					ResultSet rs = DBManager.getInstance().excuteQuery(sql);
					this.getComboBox().removeAllItems();
					while(rs.next()){
						this.getComboBox().addItem(rs.getString("huozhu"));
//						if(this.relatedTF.getText() == null 
//								|| this.relatedTF.getText().equals("")){
//							this.relatedTF.setText(rs.getString("huowu"));
//						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			protected void updaterelatedTextField() throws Exception {
				String sql;
				sql = "select distinct huozhu, huowu from huozhubiao where huozhu like "
						+ "'%" + this.getComboBox().getSelectedItem().toString().trim() +"%'"
						+ "order by riqi desc "
						+ ";";
				try {
					ResultSet rs = DBManager.getInstance().excuteQuery(sql);
					this.getComboBox().removeAllItems();
					while(rs.next()){
//						this.getComboBox().addItem(rs.getString("huozhu"));
						this.relatedTF.setText(rs.getString("huowu"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        };
        this.makeLabelAndTextField(this, "����������������", this.huichehuozhu, gridbag, c, 2);
        this.makeLabelAndTextField(this, "����������������", this.huichehuoming, gridbag, c, 2);
        this.huichezhongliang = new JTextField(10);
        this.makeLabelAndTextField(this, "�����������֣���", this.huichezhongliang, gridbag, c, 2);
        this.huichezhongliang2 = new JTextField(10);
        this.makeLabelAndTextField(this, "�� ����2���֣���", this.huichezhongliang2, gridbag, c, 2);
        this.huichejiage = new JTextField(10);
        this.makeLabelAndTextField(this, "�������ۣ�Ԫ����", this.huichejiage, gridbag, c, 2);
        this.huichebaodijia = new JTextField(10);
        this.makeLabelAndTextField(this, "�����׼ۣ�Ԫ����", this.huichebaodijia, gridbag, c, 2);
//        this.huichezhesun = new JTextField(10);
//        this.makeLabelAndTextField(this, "��������", this.huichezhesun, gridbag, c, 2);
        this.addpanelandchangeline(gridbag, c);
        
        this.huicheqitafeiyong = new JTextField(10);
        this.makeLabelAndTextField(this, "�������ã�Ԫ����", this.huicheqitafeiyong, gridbag, c, 2);
        this.huichebeizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "������������ע��", this.huichebeizhu, gridbag, c, 2);
//        this.huicheyingfujine = new JTextField(10);
//        this.makeLabelAndTextField(this, "Ӧ����", this.huicheyingfujine, gridbag, c, 2);
        
        this.huicheshouxufei = new JTextField(10);
        this.makeLabelAndTextField(this, "�������������ѣ�", this.huicheshouxufei, gridbag, c, 2);
        
        this.huicheshifujine = new JTextField(10);
        this.makeLabelAndTextField(this, "ʵ����Ԫ����", this.huicheshifujine, gridbag, c, 2);
        this.huichejiezhangbeizhu = new JTextField(10);
        this.makeLabelAndTextField(this, "���������˱�ע��", this.huichejiezhangbeizhu, gridbag, c, 2);
        
//        String[] str = {"��", "��"};
        this.huicheshifouqingsuan = new JComboBox(str);
        this.makeLabelAndComboBox(this, "�����Ƿ����㣺", this.huicheshifouqingsuan, gridbag, c, 2);
        
        JPanel hp;
        hp = new JPanel();
        c.gridwidth = 3;
//        c.gridwidth = 1;
        this.huichetianjiaBtn = new JButton("���");
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        gridbag.setConstraints(this.huichetianjiaBtn, c);
//        this.add(this.huichetianjiaBtn);
        hp.add(this.huichetianjiaBtn);
        this.huichetianjiaBtn.addActionListener(new HuichetianjiaBtnActionListener(this));
        
        this.huichexiugaiBtn = new JButton("�޸�");
        hp.add(this.huichexiugaiBtn);
        this.huichexiugaiBtn.addActionListener(new HuichexiugaiBtnActionListener(this));
        
//        c.gridwidth = 1;
        this.huicheshanchuBtn = new JButton("ɾ��");
//        c.gridwidth = GridBagConstraints.REMAINDER;
//        gridbag.setConstraints(this.huicheshanchuBtn, c);
//        this.add(this.huicheshanchuBtn);
        hp.add(this.huicheshanchuBtn);
        this.huicheshanchuBtn.addActionListener(new HuicheshanchuBtnActionListener(this));
        gridbag.setConstraints(hp, c);
        this.add(hp);
        this.addpanelandchangeline(gridbag, c);
		
		this.huichehuodanV = new Vector();
		this.huichehuodanTM = new VSTableModel(this.huichehuodanV, columnNames);
		this.huichehuodanTb = new JTable(this.huichehuodanTM);
		this.huichehuodanSP = new JScrollPane(this.huichehuodanTb);
		this.huichehuodanTb.setRowHeight(UIManager.getInt("TableCellHeight"));
		d.setSize(this.getSize().getWidth(), 100);
		this.huichehuodanSP.setPreferredSize(d);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 4;
		gridbag.setConstraints(this.huichehuodanSP, c);
		this.add(this.huichehuodanSP);
        
		this.jiayouzhanjiayou = new JTextField(10);
		this.makeLabelAndTextField(this, "����վ���ͣ�Ԫ����", this.jiayouzhanjiayou, gridbag, c, 2);
		this.tingchechangjiayou = new JTextField(10);
		this.makeLabelAndTextField(this, "ͣ�������ͣ�������", this.tingchechangjiayou, gridbag, c, 2);
		this.tingchechangyoujia = new JTextField(10);
		this.makeLabelAndTextField(this, "ͣ�����ͼۣ�Ԫ����", this.tingchechangyoujia, gridbag, c, 2);
		this.tingchefei = new JTextField(10);
		this.makeLabelAndTextField(this, "����ͣ���ѣ�Ԫ����", this.tingchefei, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.guolufei = new JTextField(10);
		this.makeLabelAndTextField(this, "������·�ѣ�Ԫ����", this.guolufei, gridbag, c, 2);
		this.guobangfei = new JTextField(10);
		this.makeLabelAndTextField(this, "���������ѣ�Ԫ����", this.guobangfei, gridbag, c, 2);
		this.yuetongka = new JTextField(10);
		this.makeLabelAndTextField(this, "������ͨ����Ԫ����", this.yuetongka, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
//		this.gongzi = new JTextField(10);
//		this.makeLabelAndTextField(this, "���������ʣ�Ԫ����", this.gongzi, gridbag, c, 2);
		this.yifugongzi = new JTextField(10);
		this.makeLabelAndTextField(this, "���Ѹ����ʣ�Ԫ����", this.yifugongzi, gridbag, c, 2);
		this.zhuangchefei = new JTextField(10);
		this.makeLabelAndTextField(this, "����װ���ѣ�Ԫ����", this.zhuangchefei, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.chifan = new JTextField(10);
		this.makeLabelAndTextField(this, "�������Է���Ԫ����", this.chifan, gridbag, c, 2);
		this.xiechefei = new JTextField(10);
		this.makeLabelAndTextField(this, "����ж���ѣ�Ԫ����", this.xiechefei, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.zhusu = new JTextField(10);
		this.makeLabelAndTextField(this, "������ס�ޣ�Ԫ����", this.zhusu, gridbag, c, 2);
		this.luntai = new JTextField(10);
		this.makeLabelAndTextField(this, "��������̥��Ԫ����", this.luntai, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.jiashui = new JTextField(10);
		this.makeLabelAndTextField(this, "��������ˮ��Ԫ����", this.jiashui, gridbag, c, 2);
		this.fakuan = new JTextField(10);
		this.makeLabelAndTextField(this, "���������Ԫ����", this.fakuan, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.zuochefei = new JTextField(10);
		this.makeLabelAndTextField(this, "���������ѣ�Ԫ����", this.zuochefei, gridbag, c, 2);
		this.xiaofei = new JTextField(10);
		this.makeLabelAndTextField(this, "������С�ѣ�Ԫ����", this.xiaofei, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.cailiaofei = new JTextField(10);
		this.makeLabelAndTextField(this, "�������Ϸѣ�Ԫ����", this.cailiaofei, gridbag, c, 2);
		this.xiulifei = new JTextField(10);
		this.makeLabelAndTextField(this, "��������ѣ�Ԫ����", this.xiulifei, gridbag, c, 2);
		this.addpanelandchangeline(gridbag, c);
		
		this.qitafeiyong = new JTextField(10);
		this.makeLabelAndTextField(this, "���������ã�Ԫ����", this.qitafeiyong, gridbag, c, 2);
		this.beizhu = new JTextField(10);
		this.makeLabelAndTextField(this, "��������������ע��", this.beizhu, gridbag, c, 3);
//		this.addpanelandchangeline(gridbag, c);
		
		this.xiaohejiBtn = new JButton("С�ϼ�");
		this.xiaoheji = new JTextField(10);
		this.xiaoheji.setEditable(false);
		JPanel hejipanel = new JPanel(new java.awt.FlowLayout());
		hejipanel.add(xiaohejiBtn);
		hejipanel.add(xiaoheji);
		c.gridwidth = 2;
		gridbag.setConstraints(hejipanel, c);
		this.add(hejipanel);
		this.addpanelandchangeline(gridbag, c);
		this.xiaohejiBtn.addActionListener(new YunDanxiaohejiBtnActionListener(this));
		
		c.gridwidth = 8;
		if(option == YunDan.YunDanXiugai){
			c.gridwidth = 7;
		}
//		c.gridwidth = GridBagConstraints.RELATIVE;
		JPanel p0 = new JPanel();
		gridbag.setConstraints(p0, c);
		this.add(p0);
		c.gridwidth = 2;
		JPanel p = new JPanel();
		if(option == YunDan.YunDanXiugai){
			this.yundantijiaoBtn = new JButton("�޸�");
		}
		if(option == YunDan.YunDanTianjia){
			this.yundantijiaoBtn = new JButton("�ύ");
		}
        this.yundantijiaoBtn.addActionListener(new YundantijiaoBtnActionListener(this));
        this.quxiaoBtn = new JButton("ȡ��");
        this.quxiaoBtn.addActionListener(new QuxiaoBtnActionListener(this));
        p.add(this.yundantijiaoBtn);
        
        if(option == YunDan.YunDanXiugai){
        	c.gridwidth = 3;
			this.yundanshanchuBtn = new JButton("ɾ��");
			this.yundanshanchuBtn.addActionListener(new YundanshanchuBtnActionListener(this));
			p.add(this.yundanshanchuBtn);
		}
        
		p.add(this.quxiaoBtn);
		
		
		
        gridbag.setConstraints(p, c);
        this.add(p);
        this.addpanelandchangeline(gridbag, c);
//������޸�YunDan����Ա����г�ʼ��
        if(option == YunDan.YunDanXiugai){
        	this.yundanbianhao.setText(yundanbianhao);
        	this.yundanbianhao.setEditable(false);
//        	this.chepaihaoCB.setEditable(false);
        	this.chepaihaoCB.setEnabled(false);
        	this.siji.setEditable(false);
//        	this.chucheriqi.setEnabled(false);
        	this.chucheriqi.setEditable(false);
//        	this.chuchechufadi.setEditable(false);
//        	this.chuchemudidi.setEditable(false);
//        	this.huichechufadi.setEditable(false);
//        	this.huichemudidi.setEditable(false);
//        	initYunDan();//
        }
        
//        this.setSize(1000, 500);
//        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(screenSize.width*7/10, screenSize.height*7/10);
//		this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
        this.setVisible(true);
        this.pack();
        this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
        
        if(option == YunDan.YunDanTianjia){
        	this.initYundanbianhao();
        }
        
        if(option == YunDan.YunDanXiugai){
        	initYunDan();
        }
	}
	
	protected void makeLabelAndTextField(Container parent, 
			String name,
			JTextField textfield,
            GridBagLayout gridbag,
            GridBagConstraints c,
            int gridwidth) {
//		JLabel label= new JLabel(name);
//		gridbag.setConstraints(label, c);
//		this.add(label);
//		textfield = new JTextField(textfield_length);
//		gridbag.setConstraints(textfield, c);
//		this.add(textfield);
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
//		datechooser = new DateChooser(this);
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
	
	private void initYunDan(){
		String sql_kaixiaodan;
		sql_kaixiaodan = "select yundanbianhao,"
				+ " chepaihao, chuchekuan, siji, gonglishu, yifugongzi,"
				+ " chucheriqi, chuchechufadi, chuchemudidi,"
				+ " huicheriqi, huichechufadi, huichemudidi,"
				+ " jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia, tingchefei,"
				+ " guolufei, guobangfei, yuetongka,"
				+ " gongzi, zhuangchefei,"
				+ " chifan, xiechefei,"
				+ " zhusu, luntai,"
				+ " jiashui, fakuan,"
				+ " zuochefei, xiaofei,"
				+ " cailiaofei, xiulifei,"
				+ " qitafeiyong, beizhu"
				+ " from kaixiaodan"
				+ " where yundanbianhao = " + "'" + this.yundanbianhao.getText().trim() + "'" + ";";
		
//		String chuchechufadi, huichechufadi;
//		chuchechufadi = null;
//		huichechufadi = null;
		
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_kaixiaodan);
			
			rs.next();
			
			this.chepaihaoCB.setSelectedItem(rs.getString("chepaihao"));
			this.chuchekuan.setText(rs.getString("chuchekuan"));
			this.siji.setText(rs.getString("siji"));
			this.gonglishu.setText(rs.getString("gonglishu"));
			this.yifugongzi.setText(rs.getString("yifugongzi"));
			
			this.chucheriqi.setText(rs.getString("chucheriqi"));
			this.chuchechufadi.setSelectedItem(rs.getString("chuchechufadi"));
//			this.chuchechufadi.setText(rs.getString("chuchechufadi"));
			this.chuchemudidi.setSelectedItem(rs.getString("chuchemudidi"));
			
			this.huicheriqi.setText(rs.getString("huicheriqi"));
			this.huichechufadi.setSelectedItem(rs.getString("huichechufadi"));
			this.huichemudidi.setSelectedItem(rs.getString("huichemudidi"));
			
			this.jiayouzhanjiayou.setText(rs.getString("jiayouzhanjiayou"));
			this.tingchechangjiayou.setText(rs.getString("tingchechangjiayou"));
			this.tingchechangyoujia.setText(rs.getString("tingchechangyoujia"));
			this.tingchefei.setText(rs.getString("tingchefei"));
			
			this.guolufei.setText(rs.getString("guolufei"));
			this.guobangfei.setText(rs.getString("guobangfei"));
			this.yuetongka.setText(rs.getString("yuetongka"));
			
			this.gongzi.setText(rs.getString("gongzi"));
			this.zhuangchefei.setText(rs.getString("zhuangchefei"));
			
			this.chifan.setText(rs.getString("chifan"));
			this.xiechefei.setText(rs.getString("xiechefei"));
			
			this.zhusu.setText(rs.getString("zhusu"));
			this.luntai.setText(rs.getString("luntai"));
			
			this.jiashui.setText(rs.getString("jiashui"));
			this.fakuan.setText(rs.getString("fakuan"));
			
			this.zuochefei.setText(rs.getString("zuochefei"));
			this.xiaofei.setText(rs.getString("xiaofei"));
			
			this.cailiaofei.setText(rs.getString("cailiaofei"));
			this.xiulifei.setText(rs.getString("xiulifei"));
			
			this.qitafeiyong.setText(rs.getString("qitafeiyong"));
			this.beizhu.setText(rs.getString("beizhu"));
			
			String sql_chuchehuodan;
			sql_chuchehuodan = "select huowubianhao, huoming, zhongliang, zhongliang2, jiage, baodijia, huozhu, qitafeiyong, beizhu, shouxufei, shifujine, jiezhangbeizhu, shifouqingsuan"
					+ " from huowudan"
					+ " where yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '0%' " + ";";
			ResultSet rsc = DBManager.getInstance().excuteQuery(sql_chuchehuodan);
			
			while(rsc.next()){
				String huowubianhao;
				huowubianhao = rsc.getString("huowubianhao");
				System.out.println(huowubianhao);
				System.out.println(huowubianhao.length());
				System.out.println(huowubianhao.substring(1, huowubianhao.length()));
				this.chuchehuowubianhao.setText(huowubianhao.substring(1, huowubianhao.length()));
//				this.chuchehuowubianhao.setText(rsc.getString("huowubianhao"));
				this.chuchehuoming.setText(rsc.getString("huoming"));
				this.chuchezhongliang.setText(rsc.getString("zhongliang"));
				this.chuchezhongliang2.setText(rsc.getString("zhongliang2"));
				this.chuchejiage.setText(rsc.getString("jiage"));
				this.chuchebaodijia.setText(rsc.getString("baodijia"));
				this.chuchehuozhu.setText(rsc.getString("huozhu"));
				this.chucheqitafeiyong.setText(rsc.getString("qitafeiyong"));
				this.chuchebeizhu.setText(rsc.getString("beizhu"));
				this.chucheshouxufei.setText(rsc.getString("shouxufei"));
				this.chucheshifujine.setText(rsc.getString("shifujine"));
				this.chuchejiezhangbeizhu.setText(rsc.getString("jiezhangbeizhu"));
				if(rsc.getString("shifouqingsuan").equals("yes")){
					this.chucheshifouqingsuan.setSelectedItem("��");
				}
				this.chuchetianjiaBtn.doClick();
			}
			
			String sql_huichehuodan;
			sql_huichehuodan = "select huowubianhao, huoming, zhongliang, zhongliang2, jiage, baodijia, huozhu, qitafeiyong, beizhu, shouxufei, shifujine, jiezhangbeizhu, shifouqingsuan"
					+ " from huowudan"
					+ " where yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
//					+ " and biaoji = '0' " + ";";
					+ " and huowubianhao like '1%' " + ";";
			ResultSet rsh = DBManager.getInstance().excuteQuery(sql_huichehuodan);
			
			while(rsh.next()){
				String huowubianhao;
				huowubianhao = rsh.getString("huowubianhao");
				System.out.println(huowubianhao);
				System.out.println(huowubianhao.length());
				System.out.println(huowubianhao.substring(1, huowubianhao.length()));
				this.huichehuowubianhao.setText(huowubianhao.substring(1, huowubianhao.length()));
//				this.huichehuowubianhao.setText(rsh.getString("huowubianhao"));
				this.huichehuoming.setText(rsh.getString("huoming"));
				this.huichezhongliang.setText(rsh.getString("zhongliang"));
				this.huichezhongliang2.setText(rsh.getString("zhongliang2"));
				this.huichejiage.setText(rsh.getString("jiage"));
				this.huichebaodijia.setText(rsh.getString("baodijia"));
				this.huichehuozhu.setText(rsh.getString("huozhu"));
				this.huicheqitafeiyong.setText(rsh.getString("qitafeiyong"));
				this.huichebeizhu.setText(rsh.getString("beizhu"));
				this.huicheshouxufei.setText(rsh.getString("shouxufei"));
				this.huicheshifujine.setText(rsh.getString("shifujine"));
				this.huichejiezhangbeizhu.setText(rsh.getString("jiezhangbeizhu"));
				if(rsh.getString("shifouqingsuan").equals("yes")){
					this.huicheshifouqingsuan.setSelectedItem("��");
				}
				this.huichetianjiaBtn.doClick();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initYundanbianhao(){
		String yundanbianhao_temp, yundanbianhao_sql;
		yundanbianhao_temp = "";
		yundanbianhao_sql = "";
		if(this.chucheriqi != null && this.chucheriqi.getText() != null){
			yundanbianhao_temp += this.chucheriqi.getText().trim().replaceAll("-", "");
			yundanbianhao_sql += yundanbianhao_temp.substring(0, 6) + "__" ;
		}
		if(this.chepaihaoCB != null && this.chepaihaoCB.getEditor().getEditorComponent().toString().trim() != null){
			yundanbianhao_temp += this.chepaihaoCB.getSelectedItem().toString().trim().replaceAll("[^0-9]", "");
			yundanbianhao_sql += this.chepaihaoCB.getSelectedItem().toString().trim().replaceAll("[^0-9]", "");
		}
		
		String sql;
		sql = "select yundanbianhao from kaixiaodan "
				+ " where yundanbianhao like "
				+ " '" + yundanbianhao_sql + "%'"
				+ " order by yundanbianhao desc "
				+ ";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			if(rs.next()){
				String yundanbianhao_before;
				int num;
				yundanbianhao_before = rs.getString("yundanbianhao");
				num = Integer.parseInt(yundanbianhao_before.substring(yundanbianhao_before.length()-2, yundanbianhao_before.length()))+1;
				yundanbianhao_temp += String.format("%02d", num);
//				System.out.println(this.yundanbianhao.getText());
			} else {
				yundanbianhao_temp += "01";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.yundanbianhao.setText(yundanbianhao_temp);
	}
}
