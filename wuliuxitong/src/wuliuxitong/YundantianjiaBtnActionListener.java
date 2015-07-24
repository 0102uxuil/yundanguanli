package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YundantianjiaBtnActionListener implements ActionListener {

	YundanPanel yundanPanel;
	
	YundantianjiaBtnActionListener(YundanPanel ydp){
		this.yundanPanel = ydp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new YunDan(YunDan.YunDanTianjia, null, this.yundanPanel);
	}

}
