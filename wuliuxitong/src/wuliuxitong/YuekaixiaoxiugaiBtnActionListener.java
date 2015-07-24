package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class YuekaixiaoxiugaiBtnActionListener implements ActionListener {

	YuekaixiaoPanel yuekaixiaoPanel;
	
	YuekaixiaoxiugaiBtnActionListener(YuekaixiaoPanel ykxp){
		this.yuekaixiaoPanel = ykxp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.yuekaixiaoPanel.yuekaixiaoTb.getSelectedRow() == -1 || this.yuekaixiaoPanel.yuekaixiaoTb.convertRowIndexToModel(this.yuekaixiaoPanel.yuekaixiaoTb.getSelectedRow()) == (this.yuekaixiaoPanel.yuekaixiaoTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要修改的月开销单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new YueKaiXiaoDan(this.yuekaixiaoPanel.yuekaixiaoModel.getValueAt(this.yuekaixiaoPanel.yuekaixiaoTb.convertRowIndexToModel(this.yuekaixiaoPanel.yuekaixiaoTb.getSelectedRow()), 1).toString()
					, this.yuekaixiaoPanel.yuekaixiaoModel.getValueAt(this.yuekaixiaoPanel.yuekaixiaoTb.convertRowIndexToModel(this.yuekaixiaoPanel.yuekaixiaoTb.getSelectedRow()), 2).toString()+"-01"
					, this.yuekaixiaoPanel);
		}
	}

}
