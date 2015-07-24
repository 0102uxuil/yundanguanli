package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YuekaixiaotianjiaBtnActionListener implements ActionListener {

	YuekaixiaoPanel yuekaixiaoPanel;
	
	YuekaixiaotianjiaBtnActionListener(YuekaixiaoPanel ykxp){
		this.yuekaixiaoPanel = ykxp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new YueKaiXiaoDan(this.yuekaixiaoPanel);
	}

}
