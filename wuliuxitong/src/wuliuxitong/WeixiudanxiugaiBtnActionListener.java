package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class WeixiudanxiugaiBtnActionListener implements ActionListener {

	WeixiudanPanel weixiudanPanel;
	
	WeixiudanxiugaiBtnActionListener(WeixiudanPanel wxdp){
		this.weixiudanPanel = wxdp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.weixiudanPanel.weixiudanTb.getSelectedRow() == -1 || this.weixiudanPanel.weixiudanTb.convertRowIndexToModel(this.weixiudanPanel.weixiudanTb.getSelectedRow()) == (this.weixiudanPanel.weixiudanTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要修改的维修单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new WeiXiuDan(this.weixiudanPanel.weixiudanModel.getValueAt(this.weixiudanPanel.weixiudanTb.convertRowIndexToModel(this.weixiudanPanel.weixiudanTb.getSelectedRow()), 1).toString()
					, this.weixiudanPanel.weixiudanModel.getValueAt(this.weixiudanPanel.weixiudanTb.convertRowIndexToModel(this.weixiudanPanel.weixiudanTb.getSelectedRow()), 2).toString()
					, this.weixiudanPanel.weixiudanModel.getValueAt(this.weixiudanPanel.weixiudanTb.convertRowIndexToModel(this.weixiudanPanel.weixiudanTb.getSelectedRow()), 3).toString()
					, this.weixiudanPanel);
		}
	}

}
