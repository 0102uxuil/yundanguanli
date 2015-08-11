package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YunDanxiaohejiBtnActionListener implements ActionListener {

	YunDan yundan;
	
	YunDanxiaohejiBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		float xiaoheji;
		xiaoheji = 0;
		if(this.yundan.guolufei.getText() != null && !this.yundan.guolufei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.guolufei.getText());
		}
//		if(this.yundan.yuetongka.getText() != null && !this.yundan.yuetongka.getText().equals("")){
//			xiaoheji += Float.parseFloat(this.yundan.yuetongka.getText());
//		}
		if(this.yundan.gongzi.getText() != null && !this.yundan.gongzi.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.gongzi.getText());
		}
		if(this.yundan.chifan.getText() != null && !this.yundan.chifan.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.chifan.getText());
		}
		if(this.yundan.zhusu.getText() != null && !this.yundan.zhusu.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.zhusu.getText());
		}
		if(this.yundan.jiashui.getText() != null && !this.yundan.jiashui.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.jiashui.getText());
		}
		if(this.yundan.zuochefei.getText() != null && !this.yundan.zuochefei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.zuochefei.getText());
		}
		if(this.yundan.cailiaofei.getText() != null && !this.yundan.cailiaofei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.cailiaofei.getText());
		}
		if(this.yundan.tingchefei.getText() != null && !this.yundan.tingchefei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.tingchefei.getText());
		}
		if(this.yundan.guobangfei.getText() != null && !this.yundan.guobangfei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.guobangfei.getText());
		}
		if(this.yundan.zhuangchefei.getText() != null && !this.yundan.zhuangchefei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.zhuangchefei.getText());
		}
		if(this.yundan.xiechefei.getText() != null && !this.yundan.xiechefei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.xiechefei.getText());
		}
		if(this.yundan.luntai.getText() != null && !this.yundan.luntai.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.luntai.getText());
		}
		if(this.yundan.fakuan.getText() != null && !this.yundan.fakuan.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.fakuan.getText());
		}
		if(this.yundan.xiaofei.getText() != null && !this.yundan.xiaofei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.xiaofei.getText());
		}
		if(this.yundan.xiulifei.getText() != null && !this.yundan.xiulifei.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.xiulifei.getText());
		}
		if(this.yundan.qitafeiyong.getText() != null && !this.yundan.qitafeiyong.getText().equals("")){
			xiaoheji += Float.parseFloat(this.yundan.qitafeiyong.getText());
		}
		this.yundan.xiaoheji.setText("" + xiaoheji);
	}

}
