package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ChucheshanchuBtnActionListener implements ActionListener {

	YunDan yundan;
	
	ChucheshanchuBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
//		System.out.println(this.yundan.chuchehuodanTb.getSelectedRow());
		if(this.yundan.chuchehuodanTb.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null, "请选中要删除的货单！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			this.yundan.chuchehuodanTM.deleteRow(this.yundan.chuchehuodanTb.getSelectedRow());
			this.yundan.chuchehuodanTM.fireTableStructureChanged();
		}
	}

}
