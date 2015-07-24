package wuliuxitong;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class YundanPanel extends JPanel implements ReLoad {
	
	JPanel chaxuntiaojian;
	JLabel yundanbianhao, chepaihao, chucheriqi;
	JTextField yundanbianhao_text, chepaihao_text;
	DateChooser chuche_start, chuche_end;
	
	JButton ChazhaoBtn, yundantianjiaBtn, yundanxiugaiBtn;
	
	JTable yundanTb;
	JScrollPane yundanSP;
	
//	ResultSet resultSet;
	VSTableModel yundanModel;
	Vector vector;
	
	YundanPanel(){
		super();
		
		this.chaxuntiaojian = new JPanel();
		this.chaxuntiaojian.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		
		this.yundanbianhao = new JLabel("运单编号：");
		this.yundanbianhao_text = new JTextField(20);
		this.chaxuntiaojian.add(this.yundanbianhao);
		this.chaxuntiaojian.add(this.yundanbianhao_text);
		
		this.chepaihao = new JLabel("车牌号：");
		this.chepaihao_text = new JTextField(10);
		this.chaxuntiaojian.add(this.chepaihao);
		this.chaxuntiaojian.add(this.chepaihao_text);
		
		this.chucheriqi = new JLabel("出车日期：");
		this.chaxuntiaojian.add(this.chucheriqi);
		this.chuche_start = new DateChooser(this);
		this.chaxuntiaojian.add(this.chuche_start);
		this.chaxuntiaojian.add(new JLabel("—"));
		this.chuche_end = new DateChooser(this);
		this.chaxuntiaojian.add(this.chuche_end);
		
		this.ChazhaoBtn = new JButton("查找");
		this.chaxuntiaojian.add(this.ChazhaoBtn);
		this.ChazhaoBtn.addActionListener(new YundanchazhaoBtnActionListener(this));
		
		this.yundantianjiaBtn = new JButton("添加运单");
		this.chaxuntiaojian.add(this.yundantianjiaBtn);
		this.yundantianjiaBtn.addActionListener(new YundantianjiaBtnActionListener(this));
//		this.yundantianjiaBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				new YunDan(YunDan.YunDanTianjia, null, null);
//			}
//		});
		
		this.yundanxiugaiBtn = new JButton("查看运单明细");
		this.chaxuntiaojian.add(this.yundanxiugaiBtn);
//		this.yundanchakanBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(yundanTb.getSelectedRow() == -1){
//					JOptionPane.showMessageDialog(null, "请选中要查看的订单！", "错误", JOptionPane.PLAIN_MESSAGE);
//				} else {
//					new YunDan(YunDan.YunDanXiugai, yundanModel.getValueAt(yundanTb.getSelectedRow(), 1).toString());
//				}
//			}
//		});
		this.yundanxiugaiBtn.addActionListener(new YundanxiugaiBtnActionListener(this));
		
//		this.yundanxiugaiBtn = new JButton("修改运单");
//		this.chaxuntiaojian.add(this.yundanxiugaiBtn);
//		this.yundanxiugaiBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(yundanTb.getSelectedRow() == -1){
//					JOptionPane.showMessageDialog(null, "请选中要修改的订单！", "错误", JOptionPane.PLAIN_MESSAGE);
//				} else {
//					new YunDan(YunDan.YunDanXiugai, yundanModel.getValueAt(yundanTb.getSelectedRow(), 1).toString());
//				}
//			}
//		});
		
//		this.yundanshanchuBtn = new JButton("删除运单");
//		this.chaxuntiaojian.add(this.yundanshanchuBtn);
//		this.yundanshanchuBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(yundanTb.getSelectedRow() == -1){
//					JOptionPane.showMessageDialog(null, "请选中要删除的订单！", "错误", JOptionPane.PLAIN_MESSAGE);
//				} else {
//					String sql_delete;
//				}
//			}
//		});
		
		this.add(chaxuntiaojian, BorderLayout.NORTH);
		
		String[] columnNames = {"序号",
				"运单编号",
				"车牌号",
				"出车日期",
				"出车出发地",
				"出车目的地",
				"回车日期",
				"回车出发地",
				"回车目的地",
				"停车场加油",
				"停车场油价",
				"停车场加油金额",
				"总开销",
//				"已结账金额",
				"未结账金额",
				"预期利润",
//				"已得利润",
				"公里数",
				"油耗（元）",
				"公里油耗（元）"};
//				"出车收入",
//				"回车收入",
//				"利润"};
		
		this.vector = new Vector();
		this.yundanModel = new VSTableModel(this.vector, columnNames);
		this.yundanTb = new JTable(this.yundanModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(yundanTb.getModel());
		yundanTb.setRowSorter(rowSorter);
		this.yundanSP = new JScrollPane(this.yundanTb);
		this.add(this.yundanSP, BorderLayout.CENTER);
		
	}

	@Override
	public void reload(String str) {
		// TODO Auto-generated method stub
		this.yundanbianhao_text.setText(str);
		this.ChazhaoBtn.doClick();
	}

}
