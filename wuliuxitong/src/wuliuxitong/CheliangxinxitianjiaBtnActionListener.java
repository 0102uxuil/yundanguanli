package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheliangxinxitianjiaBtnActionListener implements ActionListener {

	CheliangxinxiPanel clxxp;
	
	CheliangxinxitianjiaBtnActionListener(CheliangxinxiPanel cp){
		this.clxxp = cp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new CheLiangXinXiDan(this.clxxp);
	}

}
