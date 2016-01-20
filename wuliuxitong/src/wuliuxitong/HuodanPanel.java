package wuliuxitong;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class HuodanPanel extends JPanel implements ReLoad{
	
	JPanel chaxuntiaojian;
	JLabel chepaihao, chucheriqi, shifoujiezhang, chufadi, mudidi, huozhu, huoming;
	DateChooser chuche_start, chuche_end;
	JComboBox shifoujiezhang_cb;
	JTextField chepaihao_text, chufadi_text, mudidi_text, huozhu_text, huoming_text;
	JButton chazhaoBtn, xiugaiBtn, chakanyundanBtn;
	
	JTable huodanTb;
	JScrollPane huodanSP;
	
	ResultSet resultSet;
	VSTableModel huodanModel;
	Vector vector;
	
	JButton daochuexcelBtn;
	
	HuodanPanel(){
		super();
		
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		
		this.setLayout(new BorderLayout());
		
		this.chepaihao = new JLabel("车牌号：");
		this.chepaihao_text = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		this.chaxuntiaojian.add(this.chepaihao_text);
		
		this.chucheriqi = new JLabel("日期：");
		this.chuche_start = new DateChooser(this);
		this.chuche_end = new DateChooser(this);
		this.chaxuntiaojian.add(this.chucheriqi);
		this.chaxuntiaojian.add(this.chuche_start);
		this.chaxuntiaojian.add(new JLabel("—"));
		this.chaxuntiaojian.add(this.chuche_end);
		
		this.shifoujiezhang = new JLabel("是否清算：");
		String[] str = {"全　部", "未清算", "已清算"};
		this.shifoujiezhang_cb = new JComboBox(str);
		this.chaxuntiaojian.add(this.shifoujiezhang);
		this.chaxuntiaojian.add(this.shifoujiezhang_cb);
		
		this.chufadi = new JLabel("出发地：");
		this.chufadi_text = new JTextField(10);
		this.chaxuntiaojian.add(this.chufadi);
		this.chaxuntiaojian.add(this.chufadi_text);
		
		this.mudidi = new JLabel("目的地：");
		this.mudidi_text = new JTextField(10);
		this.chaxuntiaojian.add(this.mudidi);
		this.chaxuntiaojian.add(this.mudidi_text);
		
		this.huozhu = new JLabel("货主：");
		this.huozhu_text = new JTextField(10);
		this.chaxuntiaojian.add(this.huozhu);
		this.chaxuntiaojian.add(this.huozhu_text);
		
		this.huoming = new JLabel("货名：");
		this.huoming_text = new JTextField(10);
		this.chaxuntiaojian.add(this.huoming);
		this.chaxuntiaojian.add(this.huoming_text);
		
		this.chazhaoBtn = new JButton("查找");
		this.chaxuntiaojian.add(this.chazhaoBtn);
		
		this.xiugaiBtn = new JButton("结账");
		this.chaxuntiaojian.add(this.xiugaiBtn);
		
		this.chakanyundanBtn = new JButton("查看所在运单");
		this.chaxuntiaojian.add(this.chakanyundanBtn);
		
		this.daochuexcelBtn = new JButton("导出表格");
		this.chaxuntiaojian.add(this.daochuexcelBtn);
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
//		String[] columnNames = {"序号",
//				"日期",
//				"出发地",
//				"目的地",
//				"货主",
//				"货物",
//				"重量",
//				"单价",
//				"金额"};
//		
//		Object[][] data = {
//			    {"Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy","Kathy"}
//			};
//		this.huodanTb = new JTable(data, columNames);
//		this.huodanSP = new JScrollPane(this.huodanTb);
		
		String[] columnNames = {"序号",
				"运单编号",
				"货物编号",
				"车牌号",
				"日期",
				"出发地",
				"目的地",
				"货主",
				"货物",
				"重量",
				"重量2",
				"单价",
				"保底价",
//				"折损",
				"其他费用",
				"备注",
				"手续费",
				"应付金额",
				"实付金额",
				"结账备注",
				"是否清算"};

//		try {
//			resultSet = DBManager.getInstance().excuteQuery("select * from huowudan where " + "riqi > " + "'" + this.chuche_start.getText().trim() + "'" + ";");
////			resultSet = DBManager.getInstance().excuteQuery("select chepaihao, riqi, chufadi, mudidi, huozhu, huoming, zhongliang, jiage, baodijia from huowudan where riqi >= '2015-04-04' and riqi <='2015-05-04';");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		this.vector = new Vector();
		this.huodanModel = new VSTableModel(this.vector, columnNames);
//		this.huodanModel = new HuodanTableModel(resultSet, columnNames);
//		int c = this.huodanModel.getColumnCount();
//		int r = this.huodanModel.getRowCount();
//		System.out.println(this.huodanModel.getColumnCount()+"/n");
//		System.out.println(this.huodanModel.getRowCount());
//		System.out.println(this.huodanModel.getValueAt(1, 1));
//		System.out.println(this.huodanModel.getValueAt(1, 2));
//		System.out.println(this.huodanModel.getValueAt(2, 1));
//		for(int i=1; i<=r; i++){
//			for(int j=1; j<=c; j++){
//				System.out.print(this.huodanModel.getValueAt(i, j)+" ");
//			}
//			System.out.println("");
//		}
		
		
		this.huodanTb = new JTable(this.huodanModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(huodanTb.getModel());
		huodanTb.setRowSorter(rowSorter);
//		this.huodanTb.getColumnModel().getColumn(18).setCellRenderer(new HuoDanTableCellRender());
//		this.huodanTb.getColumnModel().getColumn(1).setMinWidth(30);
//		this.huodanTb.getColumnModel().getColumn(1).setMaxWidth(30);
//		this.huodanTb.getColumnModel().getColumn(1).setPreferredWidth(200);
//		this.huodanTb.getColumnModel().getColumn(1).setResizable(false);
		this.huodanSP = new JScrollPane(this.huodanTb);
//		this.huodanSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		this.huodanTb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.add(this.huodanSP, BorderLayout.CENTER);
		this.huodanTb.setRowHeight(UIManager.getInt("TableCellHeight"));
		
		this.chazhaoBtn.addActionListener(new HuodanChazhaoBtnActionListener(this));
		
//		this.xiugaiBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(huodanTb.getSelectedRow() == -1 || huodanTb.getSelectedRow() == (huodanTb.getRowCount()-1)){
//					JOptionPane.showMessageDialog(null, "请选中要结账的货单！", "错误", JOptionPane.PLAIN_MESSAGE);
//				} else {
//					new HuoDan(HuoDan.JieZhang, huodanModel.getValueAt(huodanTb.getSelectedRow(), 1).toString(), huodanModel.getValueAt(huodanTb.getSelectedRow(), 2).toString());
//				}
//			}
//		});
		
		this.xiugaiBtn.addActionListener(new HuodanxiugaiBtnActionListener(this));
		
		this.daochuexcelBtn.addActionListener(new ExportHuodanExcelBtnActionListener(this));
		
		this.chakanyundanBtn.addActionListener(new HuodansuozaiyundanchankanActionListener(this));
//		this.chakanyundanBtn.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				new YunDan(YunDan.YunDanXiugai, huodanModel.getValueAt(huodanTb.getSelectedRow(), 1).toString(), null);
//			}});
		
	}

	@Override
	public void reload(String str) {
		// TODO Auto-generated method stub
		this.chazhaoBtn.doClick();
	}
	
//	private class HuoDanTableCellRender extends DefaultTableCellRenderer {
//
//		@Override
//		public Component getTableCellRendererComponent(JTable table,
//				Object value, boolean isSelected, boolean hasFocus, int row,
//				int column) {
//			// TODO Auto-generated method stub
//			
//			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
//					row, column);
//			
//			if(value instanceof String){
//				String str = (String)value;
//				if(str.equals("是")){
//					setBackground(Color.green);
//				}
//				if(str.equals("否")){
//					setBackground(Color.red);
//				}
//			}
//			
//			return c;
//		}
//		
//	}
	
}
