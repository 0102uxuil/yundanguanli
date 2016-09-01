package wuliuxitong;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreeListener implements TreeSelectionListener {
	
	Zhujiemian zhujiemian;

	TreeListener(Zhujiemian zjm){
		this.zhujiemian = zjm;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		TreePath treePath = e.getNewLeadSelectionPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();
//		DefaultMutableTreeNode node = (DefaultMutableTreeNode)zhujiemian.tree.getLastSelectedPathComponent();
		String name = node.toString();
		if(name.equals("运单管理")){
			zhujiemian.card.show(zhujiemian.panel, "yundan");
		} else if(name.equals("货单结算")){
			zhujiemian.card.show(zhujiemian.panel, "duizhang");
		} else if(name.equals("月开销管理")){
			zhujiemian.card.show(zhujiemian.panel, "yuekaixiao");
		} else if(name.equals("年开销管理")){
			zhujiemian.card.show(zhujiemian.panel, "niankaixiao");
		} else if(name.equals("维修单管理")){
			zhujiemian.card.show(zhujiemian.panel, "weixiudan");
		} else if(name.equals("利润计算")){
			zhujiemian.card.show(zhujiemian.panel, "lirun");
		} else if(name.equals("车辆信息")){
			zhujiemian.card.show(zhujiemian.panel, "cheliangxinxi");
		} else if(name.equals("工资结算")){
			zhujiemian.card.show(zhujiemian.panel, "gongzi");
		} else if(name.equals("货主信息")){
			zhujiemian.card.show(zhujiemian.panel, "huozhuxinxi");
		} else if(name.equals("价格表")){
			zhujiemian.card.show(zhujiemian.panel, "jiagebiao");
		}
	}

}
