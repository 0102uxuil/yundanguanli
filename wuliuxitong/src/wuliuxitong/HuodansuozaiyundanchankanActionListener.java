package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class HuodansuozaiyundanchankanActionListener implements ActionListener {

	HuodanPanel huodanPanel;
	
	HuodansuozaiyundanchankanActionListener(HuodanPanel hdp){
		this.huodanPanel = hdp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.huodanPanel.huodanTb.getSelectedRow() == -1 || this.huodanPanel.huodanTb.convertRowIndexToModel(this.huodanPanel.huodanTb.getSelectedRow()) == (this.huodanPanel.huodanTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中货单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new YunDan(YunDan.YunDanXiugai, 
					this.huodanPanel.huodanModel.getValueAt(this.huodanPanel.huodanTb.convertRowIndexToModel(this.huodanPanel.huodanTb.getSelectedRow()), 1).toString(),
					this.huodanPanel);
		}
	}

}
