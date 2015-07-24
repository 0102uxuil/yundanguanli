package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeixiudantianjiaBtnActionListener implements ActionListener {

	WeixiudanPanel weixiudanPanel;
	
	WeixiudantianjiaBtnActionListener(WeixiudanPanel wxdp){
		this.weixiudanPanel = wxdp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new WeiXiuDan(this.weixiudanPanel);
	}

}
