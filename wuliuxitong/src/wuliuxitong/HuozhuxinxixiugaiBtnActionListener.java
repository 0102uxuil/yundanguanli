package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class HuozhuxinxixiugaiBtnActionListener implements ActionListener {

	HuozhuxinxiPanel hzxxp;
	
	HuozhuxinxixiugaiBtnActionListener(HuozhuxinxiPanel hp){
		this.hzxxp = hp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.hzxxp.huozhuxinxiTb.getSelectedRow() == -1
				|| this.hzxxp.huozhuxinxiTb.convertRowIndexToModel(this.hzxxp.huozhuxinxiTb.getSelectedRow()) == (this.hzxxp.huozhuxinxiTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要修改的货主信息！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new HuoZhuXinXiDan(this.hzxxp.huozhuxinxiModel.getValueAt(this.hzxxp.huozhuxinxiTb.convertRowIndexToModel(this.hzxxp.huozhuxinxiTb.getSelectedRow()), 1).toString()
					, this.hzxxp);
		}
	}

}
