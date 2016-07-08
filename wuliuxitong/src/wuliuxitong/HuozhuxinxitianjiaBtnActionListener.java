package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HuozhuxinxitianjiaBtnActionListener implements ActionListener {

	HuozhuxinxiPanel hzxxp;
	
	HuozhuxinxitianjiaBtnActionListener(HuozhuxinxiPanel hp){
		this.hzxxp = hp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new HuoZhuXinXiDan(this.hzxxp);
	}

}
