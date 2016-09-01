package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class JiagebiaoxiugaiBtnActionListener implements ActionListener {

	JiagebiaoPanel jgbp;
	
	public JiagebiaoxiugaiBtnActionListener(JiagebiaoPanel jp) {
		// TODO Auto-generated constructor stub
		this.jgbp = jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.jgbp.jiagebiaoTb.getSelectedRow() == -1){
//				|| this.jgbp.jiagebiaoTb.convertRowIndexToModel(this.jgbp.jiagebiaoTb.getSelectedRow()) == (this.jgbp.jiagebiaoTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要修改的价格信息！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			new JiaGeBiao(this.jgbp.jiagebiaoModel.getValueAt(this.jgbp.jiagebiaoTb.convertRowIndexToModel(this.jgbp.jiagebiaoTb.getSelectedRow()), 1).toString()
					, this.jgbp.jiagebiaoModel.getValueAt(this.jgbp.jiagebiaoTb.convertRowIndexToModel(this.jgbp.jiagebiaoTb.getSelectedRow()), 2).toString()
					, this.jgbp.jiagebiaoModel.getValueAt(this.jgbp.jiagebiaoTb.convertRowIndexToModel(this.jgbp.jiagebiaoTb.getSelectedRow()), 3).toString()
					, this.jgbp.jiagebiaoModel.getValueAt(this.jgbp.jiagebiaoTb.convertRowIndexToModel(this.jgbp.jiagebiaoTb.getSelectedRow()), 4).toString()
					, this.jgbp);
		}
	}

}
