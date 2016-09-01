package wuliuxitong;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class Zhujiemian extends JFrame {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Zhujiemian();
	}
	JSplitPane splitpane;
	Dimension frameSize;
	JTree tree;
	JPanel panel;
	CardLayout card;
	JTable table;
	
	public Zhujiemian(){
		
//		System.out.println(YunDanBianHao.previous("201507090512504"));
//		System.out.println(YunDanBianHao.next("201507090512504"));
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Font font = new Font("Serif",Font.BOLD,18);
		UIManager.put("TableHeader.font", font);
		UIManager.put("Table.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TableCellHeight", 20);
		
		//设置主界面的大小 初始化frameSize
		setFrameSize(1000, 500);
		this.splitpane = new JSplitPane();

		this.add(this.splitpane, java.awt.BorderLayout.CENTER);
		//界面左侧 初始化 tree并添加Listener
		createTree();
		this.splitpane.add(this.tree, this.splitpane.LEFT);
		//界面右侧 初始化右侧CardPanel布局
		createCardPanel();
		this.splitpane.add(this.panel, this.splitpane.RIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.splitpane.setDividerLocation(0.1);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e)
	        {
				File file =new File("e:\\mysqlbackupdir\\"); 
				//如果文件夹不存在则创建  
				if  (!file .exists()  && !file .isDirectory())    
				{     
				    System.out.println("//不存在");
				    file .mkdir();
				    System.out.println("创建文件夹");
				} else 
				{
				    System.out.println("//目录存在");
				}
				Date now = new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(now);
		        Backup backup=new Backup("root","aa112233","yundanguanli",null,"utf8","e:\\mysqlbackupdir\\" + date + ".sql");
		        boolean result=backup.backup_run();
		        if(result){
		            System.out.println("备份成功");
	        	}
	        	System.exit(0);
	        }
		});
	}
	
	private void setFrameSize(int width, int height){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = new Dimension();
//		frameSize.setSize(width, height);
//		frameSize.setSize(screenSize.getWidth()*4/5, screenSize.getHeight()*4/5);
		frameSize.setSize(screenSize.getWidth(), screenSize.getHeight()*14/15);
		this.setSize(frameSize);
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
//		this.setLayout(null);
//		this.setBounds((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2, frameSize.width, frameSize.height);
	}
	
	private void createTree(){
		DefaultMutableTreeNode yundanguanli;
		yundanguanli = new DefaultMutableTreeNode("运单结算系统");
		yundanguanli.add(new DefaultMutableTreeNode("运单管理"));
//		yundanguanli.add(new DefaultMutableTreeNode("运单录入"));
		yundanguanli.add(new DefaultMutableTreeNode("货单结算"));
		yundanguanli.add(new DefaultMutableTreeNode("月开销管理"));
		yundanguanli.add(new DefaultMutableTreeNode("年开销管理"));
		yundanguanli.add(new DefaultMutableTreeNode("维修单管理"));
		yundanguanli.add(new DefaultMutableTreeNode("利润计算"));
		yundanguanli.add(new DefaultMutableTreeNode("车辆信息"));
		yundanguanli.add(new DefaultMutableTreeNode("工资结算"));
		yundanguanli.add(new DefaultMutableTreeNode("货主信息"));
		yundanguanli.add(new DefaultMutableTreeNode("价格表"));
		this.tree = new JTree(yundanguanli);
//		this.tree.setBounds(0, 0, 100, (int)this.frameSize.getHeight());
//		this.add(tree);
		
		this.tree.addTreeSelectionListener(new TreeListener(this));
	}
	
	private void createCardPanel(){
		this.panel = new JPanel();
//		this.panel.setBounds(100, 0, (int)this.frameSize.getWidth()-100, (int)this.frameSize.getHeight());
		this.card = new CardLayout();
		this.panel.setLayout(card);
		inityundanPanel();
		initduizhangPanel();
		inityuekaixiaoPanel();
		initniankaixiaoPanel();
		initweixiudanPanel();
		initlirunPanel();
		initcheliangxinxiPanel();
		initgongziPanel();
		inithuozhuxinxiPanel();
		initjiagebiaoPanel();
//		this.add(panel);
	}
	
	private void inityundanPanel(){
		this.panel.add("yundan", new YundanPanel());
	}
	
//	private void initluruPanel(){
//		this.panel.add("luru", new LuruPanel());
//	}
	
	private void initduizhangPanel(){
		this.panel.add("duizhang", new HuodanPanel());
	}
	
	private void inityuekaixiaoPanel(){
		this.panel.add("yuekaixiao", new YuekaixiaoPanel());
	}
	
	private void initniankaixiaoPanel(){
		this.panel.add("niankaixiao", new NiankaixiaoPanel());
	}
	
	private void initweixiudanPanel(){
		this.panel.add("weixiudan", new WeixiudanPanel());
	}
	
	private void initlirunPanel(){
	this.panel.add("lirun", new LirunPanel());
	}
	
	private void initcheliangxinxiPanel(){
		this.panel.add("cheliangxinxi", new CheliangxinxiPanel());
	}
	
	private void initgongziPanel(){
		this.panel.add("gongzi", new GongziPanel());
	}
	
	private void inithuozhuxinxiPanel(){
		this.panel.add("huozhuxinxi", new HuozhuxinxiPanel());
	}
	
	private void initjiagebiaoPanel(){
		this.panel.add("jiagebiao", new JiagebiaoPanel());
	}
	
}
