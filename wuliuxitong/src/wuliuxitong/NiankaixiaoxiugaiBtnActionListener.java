package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class NiankaixiaoxiugaiBtnActionListener implements ActionListener {

	NiankaixiaoPanel niankaixiaoPanel;
	
	NiankaixiaoxiugaiBtnActionListener(NiankaixiaoPanel nkxp){
		this.niankaixiaoPanel = nkxp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.niankaixiaoPanel.niankaixiaoTb.getSelectedRow() == -1 || this.niankaixiaoPanel.niankaixiaoTb.getSelectedRow() == (this.niankaixiaoPanel.niankaixiaoTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要修改的年开销单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new NianKaiXiaoDan(this.niankaixiaoPanel.niankaixiaoModel.getValueAt(this.niankaixiaoPanel.niankaixiaoTb.getSelectedRow(), 1).toString()
					, this.niankaixiaoPanel.niankaixiaoModel.getValueAt(this.niankaixiaoPanel.niankaixiaoTb.getSelectedRow(), 2).toString()+"-01-01"
					, this.niankaixiaoPanel);
		}
	}

}
