package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NiankaixiaotianjiaBtnActionListener implements ActionListener {

	NiankaixiaoPanel niankaixiaoPanel;
	
	NiankaixiaotianjiaBtnActionListener(NiankaixiaoPanel nkxp){
		this.niankaixiaoPanel = nkxp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new NianKaiXiaoDan(this.niankaixiaoPanel);
	}

}
