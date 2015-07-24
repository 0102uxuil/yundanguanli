package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CheliangxinxixiugaiBtnActionListener implements ActionListener {

	CheliangxinxiPanel clxxp;
	
	CheliangxinxixiugaiBtnActionListener(CheliangxinxiPanel cp){
		this.clxxp = cp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.clxxp.cheliangxinxiTb.getSelectedRow() == -1
				|| this.clxxp.cheliangxinxiTb.convertRowIndexToModel(this.clxxp.cheliangxinxiTb.getSelectedRow()) == (this.clxxp.cheliangxinxiTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要修改的车辆信息！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new CheLiangXinXiDan(this.clxxp.cheliangxinxiModel.getValueAt(this.clxxp.cheliangxinxiTb.convertRowIndexToModel(this.clxxp.cheliangxinxiTb.getSelectedRow()), 1).toString()
					, this.clxxp);
		}
	}

}
