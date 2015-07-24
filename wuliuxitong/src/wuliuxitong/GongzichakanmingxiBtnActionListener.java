package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GongzichakanmingxiBtnActionListener implements ActionListener {

	GongziPanel gongziPanel;
	
	GongzichakanmingxiBtnActionListener(GongziPanel gzp){
		this.gongziPanel = gzp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.gongziPanel.gongziTb.getSelectedRow() == -1 || this.gongziPanel.gongziTb.convertRowIndexToModel(this.gongziPanel.gongziTb.getSelectedRow()) == (this.gongziPanel.gongziTb.getRowCount()-1)){
			JOptionPane.showMessageDialog(null, "请选中要查看的项目！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
//			new YunDan(YunDan.YunDanXiugai, this.yundanPanel.yundanModel.getValueAt(this.yundanPanel.yundanTb.getSelectedRow(), 1).toString(), this.yundanPanel);
			new GongZiMingXiDan(this.gongziPanel.gongziModel.getValueAt(this.gongziPanel.gongziTb.convertRowIndexToModel(this.gongziPanel.gongziTb.getSelectedRow()), 1).toString()
					, this.gongziPanel.gongziModel.getValueAt(this.gongziPanel.gongziTb.convertRowIndexToModel(this.gongziPanel.gongziTb.getSelectedRow()), 2).toString());
		}
	}

}
