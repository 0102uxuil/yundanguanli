package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class HuichexiugaiBtnActionListener implements ActionListener {

	YunDan yundan;
	
	HuichexiugaiBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.yundan.huichehuodanTb.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null, "请选中要修改的货单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new HuoWu(this.yundan.huichehuodanTM, this.yundan.huichehuodanTb.getSelectedRow());
//			this.yundan.huichehuodanTM.fireTableStructureChanged();
		}
	}

}
