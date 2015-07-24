package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ChuchexiugaiBtnActionListener implements ActionListener {

	YunDan yundan;
	
	ChuchexiugaiBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.yundan.chuchehuodanTb.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null, "请选中要修改的货单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new HuoWu(this.yundan.chuchehuodanTM, this.yundan.chuchehuodanTb.getSelectedRow());
//			this.yundan.chuchehuodanTM.deleteRow(this.yundan.chuchehuodanTb.getSelectedRow());
//			this.yundan.chuchehuodanTM.setValueAt("", 0, 1);
//			this.yundan.chuchehuodanTM.fireTableStructureChanged();
		}
	}

}
