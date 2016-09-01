package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JiagebiaotianjiaBtnActionListener implements ActionListener {

	JiagebiaoPanel jgbp;
	
	public JiagebiaotianjiaBtnActionListener(JiagebiaoPanel jp) {
		// TODO Auto-generated constructor stub
		this.jgbp = jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new JiaGeBiao(this.jgbp);
	}

}
