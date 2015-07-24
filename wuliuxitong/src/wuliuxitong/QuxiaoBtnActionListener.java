package wuliuxitong;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class QuxiaoBtnActionListener implements ActionListener {

	Window window;
	
	QuxiaoBtnActionListener(Window c){
		this.window = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.window.dispose();
	}

}
