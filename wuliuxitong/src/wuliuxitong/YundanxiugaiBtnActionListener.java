package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class YundanxiugaiBtnActionListener implements ActionListener {

	YundanPanel yundanPanel;
	
	YundanxiugaiBtnActionListener(YundanPanel ydp){
		this.yundanPanel = ydp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(this.yundanPanel.yundanTb.getSelectedRow() == -1 || this.yundanPanel.yundanTb.convertRowIndexToModel(this.yundanPanel.yundanTb.getSelectedRow()) == (this.yundanPanel.yundanTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要查看的订单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
//			new YunDan(YunDan.YunDanXiugai, this.yundanPanel.yundanModel.getValueAt(this.yundanPanel.yundanTb.getSelectedRow(), 1).toString(), this.yundanPanel);
			new YunDan(YunDan.YunDanXiugai, this.yundanPanel.yundanModel.getValueAt(this.yundanPanel.yundanTb.convertRowIndexToModel(this.yundanPanel.yundanTb.getSelectedRow()), 1).toString(), this.yundanPanel);
		}
	}

}
