package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

public class YundantijiaoBtnActionListener implements ActionListener {
	
	YunDan yundan;
	
	YundantijiaoBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(this.yundan.option == YunDan.YunDanXiugai){
			if(this.yundan.yundanbianhao.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "运单编号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chepaihaoCB.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "车牌号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.siji.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "司机不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chucheriqi.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "出车日期不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.huicheriqi.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "回车日期不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chuchechufadi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "出车出发地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chuchemudidi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "出车目的地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.huichechufadi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "回车出发地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.huichemudidi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "回车目的地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				String sql_kaixiaodan;
				sql_kaixiaodan = "delete from kaixiaodan where yundanbianhao = " + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ";"; 
				
				String sql_huowudan;
				sql_huowudan = "delete from huowudan where yundanbianhao = " + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ";"; 
				try {
					DBManager.getInstance().getConnection().setAutoCommit(false);
					DBManager.getInstance().excuteUpdate(sql_kaixiaodan);
					DBManager.getInstance().excuteUpdate(sql_huowudan);
					updatekaixiaodan();
					updatehuowudan();
					DBManager.getInstance().getConnection().commit();
					DBManager.getInstance().getConnection().setAutoCommit(true);
					this.yundan.dispose();
					this.yundan.reload.reload(null);
					JOptionPane.showMessageDialog(null, "修改成功！", "修改", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FormatException e2){
					JOptionPane.showMessageDialog(null, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		if(this.yundan.option == YunDan.YunDanTianjia){
			if(this.yundan.yundanbianhao.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "运单编号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.yundanbianhao.getText().trim().length() <= 10) {
				JOptionPane.showMessageDialog(null, "运单编号格式错误！长度必须大于10！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chepaihaoCB.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "车牌号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.siji.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "司机不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chucheriqi.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "出车日期不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.huicheriqi.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "回车日期不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chuchechufadi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "出车出发地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.chuchemudidi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "出车目的地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.huichechufadi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "回车出发地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yundan.huichemudidi.getSelectedItem().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "回车目的地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!yundanbianhaoExist()){
					try {
						DBManager.getInstance().getConnection().setAutoCommit(false);
						updatekaixiaodan();
						updatehuowudan();
						updateluxianbiao();
						updatehuozhubiao();
						DBManager.getInstance().getConnection().commit();
						DBManager.getInstance().getConnection().setAutoCommit(true);
						this.yundan.dispose();
//						this.yundan.yundanPanel.yundanbianhao_text.setText(this.yundan.yundanbianhao.getText().trim());
//						this.yundan.yundanPanel.ChazhaoBtn.doClick();
						this.yundan.reload.reload(this.yundan.yundanbianhao.getText().trim());
						JOptionPane.showMessageDialog(null, "该车单已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						try {
							DBManager.getInstance().getConnection().rollback();
							JOptionPane.showMessageDialog(null, "车单录入失败！", "录入失败", JOptionPane.PLAIN_MESSAGE);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						e1.printStackTrace();
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(null, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "当前车单已存在！", "请勿重复录入", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	private boolean yundanbianhaoExist(){
		String sql_bianhaojiancha;
		sql_bianhaojiancha = "select * from kaixiaodan where yundanbianhao=" + "'" + this.yundan.yundanbianhao.getText().trim() + "'" +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_bianhaojiancha);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updatekaixiaodan() throws SQLException, FormatException{
		String sql_kaixiaodan;
		float zongkaixiao, youhao_pre;
		zongkaixiao = 0;
//		youhao_pre = 0;
//		sql_kaixiaodan = "insert into kaixiaodan set ";
		sql_kaixiaodan = "insert into kaixiaodan set "
				+ "yundanbianhao=" + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ","
				+ "chepaihao=" + "'" + this.yundan.chepaihaoCB.getSelectedItem().toString().trim() + "'" + ","
				+ "siji=" + "'" + this.yundan.siji.getText().trim() + "'" + ","
				+ "chucheriqi=" + "'" + this.yundan.chucheriqi.getText().trim() + "'" + ","
				+ "huicheriqi=" + "'" + this.yundan.huicheriqi.getText().trim() + "'";
		if(!this.yundan.chuchechufadi.getSelectedItem().toString().trim().equals("")){
			sql_kaixiaodan = sql_kaixiaodan + "," + "chuchechufadi=" + "'" + this.yundan.chuchechufadi.getSelectedItem().toString().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "chuchemudidi=" + "'" + this.yundan.chuchemudidi.getSelectedItem().toString().trim() + "'";
		}
		if(!this.yundan.huichechufadi.getSelectedItem().toString().trim().equals("")){
			sql_kaixiaodan = sql_kaixiaodan + "," + "huichechufadi=" + "'" + this.yundan.huichechufadi.getSelectedItem().toString().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "huichemudidi=" + "'" + this.yundan.huichemudidi.getSelectedItem().toString().trim() + "'";
		}
		if(!this.yundan.chuchekuan.getText().trim().equals("")){
			if(!this.yundan.chuchekuan.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("出车款必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "chuchekuan=" + Float.parseFloat(this.yundan.chuchekuan.getText().trim());
		}
		if(!this.yundan.gonglishu.getText().trim().equals("")){
			if(!this.yundan.gonglishu.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("公里数必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "gonglishu=" + Float.parseFloat(this.yundan.gonglishu.getText().trim());
		}
		if(this.yundan.yifugongzi.getText() ==null || this.yundan.yifugongzi.getText().trim().equals("")){
			throw new FormatException("已付工资不能为空！");
		}
		if(!this.yundan.yifugongzi.getText().trim().equals("")){
			if(!this.yundan.yifugongzi.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("已付工资必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "yifugongzi=" + Float.parseFloat(this.yundan.yifugongzi.getText().trim());
		}
		if(!this.yundan.jiayouzhanjiayou.getText().trim().equals("")){
			if(!this.yundan.jiayouzhanjiayou.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("加油站加油必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "jiayouzhanjiayou=" + Float.parseFloat(this.yundan.jiayouzhanjiayou.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.jiayouzhanjiayou.getText().trim());
//			youhao_pre += Float.parseFloat(this.yundan.jiayouzhanjiayou.getText().trim());
		}
		if(!this.yundan.tingchechangjiayou.getText().trim().equals("")){
			if(!this.yundan.tingchechangjiayou.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("停车场加油必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "tingchechangjiayou=" + Float.parseFloat(this.yundan.tingchechangjiayou.getText().trim());
			if(!this.yundan.tingchechangyoujia.getText().trim().equals("")){
				if(!this.yundan.tingchechangyoujia.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
					throw new FormatException("停车场油价必须是正数！");
				}
				sql_kaixiaodan = sql_kaixiaodan + "," + "tingchechangyoujia=" + Float.parseFloat(this.yundan.tingchechangyoujia.getText().trim());
				zongkaixiao += Float.parseFloat(this.yundan.tingchechangyoujia.getText().trim())*Float.parseFloat(this.yundan.tingchechangjiayou.getText().trim());
//				youhao_pre += Float.parseFloat(this.yundan.tingchechangyoujia.getText().trim())*Float.parseFloat(this.yundan.tingchechangjiayou.getText().trim());
			} else {
				throw new FormatException("停车场油价必须是正数！");
			}
		}
		if(!this.yundan.guolufei.getText().trim().equals("")){
			if(!this.yundan.guolufei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("过路费必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "guolufei=" + Float.parseFloat(this.yundan.guolufei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.guolufei.getText().trim());
		}
		if(!this.yundan.yuetongka.getText().trim().equals("")){
			if(!this.yundan.yuetongka.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("粤通卡必须是正数！");
			}
			sql_kaixiaodan = sql_kaixiaodan + "," + "yuetongka=" + Float.parseFloat(this.yundan.yuetongka.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.yuetongka.getText().trim());
		}
		if(this.yundan.gongzi.getText() ==null || this.yundan.gongzi.getText().trim().equals("")){
			throw new FormatException("工资不能为空！");
		}
		if(!this.yundan.gongzi.getText().trim().equals("")){
			if(!this.yundan.gongzi.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("工资必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "gongzi=" + "'" + this.yundan.gongzi.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "gongzi=" + Float.parseFloat(this.yundan.gongzi.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.gongzi.getText().trim());
		}
		if(!this.yundan.chifan.getText().trim().equals("")){
			if(!this.yundan.chifan.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("吃饭必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "chifan=" + "'" + this.yundan.chifan.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "chifan=" + Float.parseFloat(this.yundan.chifan.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.chifan.getText().trim());
		}
		if(!this.yundan.zhusu.getText().trim().equals("")){
			if(!this.yundan.zhusu.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("住宿必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "zhusu=" + "'" + this.yundan.zhusu.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "zhusu=" + Float.parseFloat(this.yundan.zhusu.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.zhusu.getText().trim());
		}
		if(!this.yundan.jiashui.getText().trim().equals("")){
			if(!this.yundan.jiashui.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("加水必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "jiashui=" + "'" + this.yundan.jiashui.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "jiashui=" + Float.parseFloat(this.yundan.jiashui.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.jiashui.getText().trim());
		}
		if(!this.yundan.zuochefei.getText().trim().equals("")){
			if(!this.yundan.zuochefei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("坐车费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "zuochefei=" + "'" + this.yundan.zuochefei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "zuochefei=" + Float.parseFloat(this.yundan.zuochefei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.zuochefei.getText().trim());
		}
		if(!this.yundan.cailiaofei.getText().trim().equals("")){
			if(!this.yundan.cailiaofei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("材料费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "cailiaofei=" + "'" + this.yundan.cailiaofei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "cailiaofei=" + Float.parseFloat(this.yundan.cailiaofei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.cailiaofei.getText().trim());
		}
		if(!this.yundan.tingchefei.getText().trim().equals("")){
			if(!this.yundan.tingchefei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("停车费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "tingchefei=" + "'" + this.yundan.tingchefei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "tingchefei=" + Float.parseFloat(this.yundan.tingchefei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.tingchefei.getText().trim());
		}
		if(!this.yundan.guobangfei.getText().trim().equals("")){
			if(!this.yundan.guobangfei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("过磅费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "guobangfei=" + "'" + this.yundan.guobangfei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "guobangfei=" + Float.parseFloat(this.yundan.guobangfei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.guobangfei.getText().trim());
		}
		if(!this.yundan.zhuangchefei.getText().trim().equals("")){
			if(!this.yundan.zhuangchefei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("装车费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "zhuangchefei=" + "'" + this.yundan.zhuangchefei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "zhuangchefei=" + Float.parseFloat(this.yundan.zhuangchefei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.zhuangchefei.getText().trim());
		}
		if(!this.yundan.xiechefei.getText().trim().equals("")){
			if(!this.yundan.xiechefei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("卸车费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "xiechefei=" + "'" + this.yundan.xiechefei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "xiechefei=" + Float.parseFloat(this.yundan.xiechefei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.xiechefei.getText().trim());
		}
		if(!this.yundan.luntai.getText().trim().equals("")){
			if(!this.yundan.luntai.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("轮胎必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "luntai=" + "'" + this.yundan.luntai.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "luntai=" + Float.parseFloat(this.yundan.luntai.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.luntai.getText().trim());
		}
		if(!this.yundan.fakuan.getText().trim().equals("")){
			if(!this.yundan.fakuan.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("罚款必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "fakuan=" + "'" + this.yundan.fakuan.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "fakuan=" + Float.parseFloat(this.yundan.fakuan.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.fakuan.getText().trim());
		}
		if(!this.yundan.xiaofei.getText().trim().equals("")){
			if(!this.yundan.xiaofei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("小费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "xiaofei=" + "'" + this.yundan.xiaofei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "xiaofei=" + Float.parseFloat(this.yundan.xiaofei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.xiaofei.getText().trim());
		}
		if(!this.yundan.xiulifei.getText().trim().equals("")){
			if(!this.yundan.xiulifei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("修理费必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "xiulifei=" + "'" + this.yundan.xiulifei.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "xiulifei=" + Float.parseFloat(this.yundan.xiulifei.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.xiulifei.getText().trim());
		}
		if(!this.yundan.qitafeiyong.getText().trim().equals("")){
			if(!this.yundan.qitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("其他费用必须是正数！");
			}
//			sql_kaixiaodan = sql_kaixiaodan + "," + "qitafeiyong=" + "'" + this.yundan.qitafeiyong.getText().trim() + "'";
			sql_kaixiaodan = sql_kaixiaodan + "," + "qitafeiyong=" + Float.parseFloat(this.yundan.qitafeiyong.getText().trim());
			zongkaixiao += Float.parseFloat(this.yundan.qitafeiyong.getText().trim());
		}
		if(!this.yundan.beizhu.getText().trim().equals("")){
			sql_kaixiaodan = sql_kaixiaodan + "," + "beizhu=" + "'" + this.yundan.beizhu.getText().trim() + "'";
		}
		sql_kaixiaodan = sql_kaixiaodan + "," + "zongkaixiao=" + zongkaixiao;
		
//		sql_kaixiaodan = sql_kaixiaodan + "," + "chucheyingfuzonge=" + this.yundan.chucheyingfuzonge;
//		sql_kaixiaodan = sql_kaixiaodan + "," + "chucheshifuzonge=" + this.yundan.chucheshifuzonge;
//		sql_kaixiaodan = sql_kaixiaodan + "," + "huicheyingfuzonge=" + this.yundan.huicheyingfuzonge;
//		sql_kaixiaodan = sql_kaixiaodan + "," + "huicheshifuzonge=" + this.yundan.huicheshifuzonge;
		
		sql_kaixiaodan = sql_kaixiaodan + ";";
		
		DBManager.getInstance().excuteUpdate(sql_kaixiaodan);
		
		if(YunDanBianHao.previous(this.yundan.yundanbianhao.getText().trim()) != null){
			float youhao;
			youhao = 0;
			if(this.yundan.jiayouzhanjiayou.getText() != null 
					&& !this.yundan.jiayouzhanjiayou.getText().trim().equals("")){
				youhao += Float.parseFloat(this.yundan.jiayouzhanjiayou.getText().trim());
			}
			if(this.yundan.tingchechangjiayou.getText() != null 
					&& !this.yundan.tingchechangjiayou.getText().trim().equals("")){
				youhao += Float.parseFloat(this.yundan.tingchechangjiayou.getText().trim())*Float.parseFloat(this.yundan.tingchechangyoujia.getText().trim());
			}
			String sql_previous;
			sql_previous = "update kaixiaodan set "
					+ " youhao = "
					+ youhao
					+ " where yundanbianhao = "
					+ "'" + YunDanBianHao.previous(this.yundan.yundanbianhao.getText().trim()) + "'"
					+ ";";
			DBManager.getInstance().excuteUpdate(sql_previous);
		}
		if(YunDanBianHao.next(this.yundan.yundanbianhao.getText().trim()) != null){
			float youhao;
			youhao = 0;
			String sql_next;
			sql_next = "select jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia from kaixiaodan "
					+ " where yundanbianhao = "
					+ "'" + YunDanBianHao.next(this.yundan.yundanbianhao.getText().trim()) + "'"
					+";";
			ResultSet rs = DBManager.getInstance().excuteQuery((sql_next));
			if(rs.next()){
				String jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia;
				jiayouzhanjiayou = rs.getString("jiayouzhanjiayou");
				tingchechangjiayou = rs.getString("tingchechangjiayou");
				tingchechangyoujia = rs.getString("tingchechangyoujia");
				if(jiayouzhanjiayou != null){
					youhao += Float.parseFloat(jiayouzhanjiayou);
				}
				if(tingchechangjiayou != null){
					youhao += Float.parseFloat(tingchechangjiayou)*Float.parseFloat(tingchechangyoujia);
				}
			}
			String sql;
			sql = "update kaixiaodan set "
					+ " youhao = "
					+ youhao
					+ " where yundanbianhao = "
					+ "'" + this.yundan.yundanbianhao.getText().trim() + "'"
					+";";
			DBManager.getInstance().excuteUpdate((sql));
		}
		
	}
	
	private void updatehuowudan() throws SQLException{
		if(this.yundan.chuchehuodanV.size() > 0){
			for(int i = 0; i < this.yundan.chuchehuodanV.size(); i++){
				String sql_huowudan;
				sql_huowudan = "insert into huowudan set "
//				+ "biaoji= '1', "//标记是出车还是回车1为出车0为回车
				+ "yundanbianhao=" + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ","
				+ "chepaihao=" + "'" + this.yundan.chepaihaoCB.getSelectedItem().toString().trim() + "'" + ","
						//将(this.yundan.chuchehuodanV).elementAt(i)转换为Vector类型 再用.引用elementAt函数
				+ "riqi=" + "'" + this.yundan.chucheriqi.getText().trim() + "'" + ","
				+ "chufadi=" + "'" + this.yundan.chuchechufadi.getSelectedItem().toString().trim() + "'" + ","
				+ "mudidi=" + "'" + this.yundan.chuchemudidi.getSelectedItem().toString().trim() + "'" + ","
				+ "huowubianhao=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(0) + "'" + ","
				+ "huoming=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(1) + "'" + ","
//				+ "zhongliang=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(1) + "'";
				+ "zhongliang=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(2).toString());
				
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(3).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(3) == null)){
//					sql_huowudan = sql_huowudan + "," + "zhongliang2=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(2) + "'";
					sql_huowudan = sql_huowudan + "," + "zhongliang2=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(3).toString());
				}
				if(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(5).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(5) == null){
//					sql_huowudan = sql_huowudan + "," + "jiage=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(3) + "'";
					sql_huowudan = sql_huowudan + "," + "jiage=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(4).toString());
				} else {
//					sql_huowudan = sql_huowudan + "," + "baodijia=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(4) + "'";
					sql_huowudan = sql_huowudan + "," + "baodijia=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(5).toString());
				}
//				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6).equals("") 
//						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6) == null)){
////					sql_huowudan = sql_huowudan + "," + "zhesun=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(5) + "'";
//					sql_huowudan = sql_huowudan + "," + "zhesun=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6).toString());
//				}
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6) == null)){
					sql_huowudan = sql_huowudan + "," + "huozhu=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6) + "'";
//					sql_huowudan = sql_huowudan + "," + "huozhu=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(6).toString());
				}
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(7).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(7) == null)){
//					sql_huowudan = sql_huowudan + "," + "qitafeiyong=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(7) + "'";
					sql_huowudan = sql_huowudan + "," + "qitafeiyong=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(7).toString());
				}
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(8).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(8) == null)){
					sql_huowudan = sql_huowudan + "," + "beizhu=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(8) + "'";
				}
				
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(9).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(9) == null)){
					sql_huowudan = sql_huowudan + "," + "shouxufei=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(9) + "'";
				}
				
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(10).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(10) == null)){
//					sql_huowudan = sql_huowudan + "," + "yingfujine=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(9) + "'";
					sql_huowudan = sql_huowudan + "," + "yingfujine=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(10).toString());
				}
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(11).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(11) == null)){
//					sql_huowudan = sql_huowudan + "," + "shifujine=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(10) + "'";
					sql_huowudan = sql_huowudan + "," + "shifujine=" + Float.parseFloat(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(11).toString());
//					sql_huowudan = sql_huowudan + "," + "shifouqingsuan= 'yes'";
				}
				if(!(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(12).equals("") 
						|| ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(12) == null)){
					sql_huowudan = sql_huowudan + "," + "jiezhangbeizhu=" + "'" + ((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(12).toString() + "'";
				}
				if(((Vector)(this.yundan.chuchehuodanV).elementAt(i)).elementAt(13).equals("是")){
					sql_huowudan = sql_huowudan + "," + "shifouqingsuan= 'yes'";
				}
				sql_huowudan = sql_huowudan + ";";
				
				DBManager.getInstance().excuteUpdate(sql_huowudan);
			}
		}
//		System.out.println(this.yundan.huichehuodanV.size());
		if(this.yundan.huichehuodanV.size() > 0){
			for(int i = 0; i < this.yundan.huichehuodanV.size(); i++){
				String sql_huowudan;
				sql_huowudan = "insert into huowudan set "
//				+ "biaoji= '0', "//标记是出车还是回车1为出车0为回车
				+ "yundanbianhao=" + "'" + this.yundan.yundanbianhao.getText().trim() + "'" + ","
				+ "chepaihao=" + "'" + this.yundan.chepaihaoCB.getSelectedItem().toString().trim() + "'" + ","
						//将(this.yundan.huichehuodanV).elementAt(i)转换为Vector类型 再用.引用elementAt函数
				+ "riqi=" + "'" + this.yundan.huicheriqi.getText().trim() + "'" + ","
				+ "chufadi=" + "'" + this.yundan.huichechufadi.getSelectedItem().toString().trim() + "'" + ","
				+ "mudidi=" + "'" + this.yundan.huichemudidi.getSelectedItem().toString().trim() + "'" + ","
				+ "huowubianhao=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(0) + "'" + ","
				+ "huoming=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(1) + "'" + ","
//				+ "zhongliang=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(1) + "'";
				+ "zhongliang=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(2).toString());
				
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(3).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(3) == null)){
//					sql_huowudan = sql_huowudan + "," + "zhongliang2=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(2) + "'";
					sql_huowudan = sql_huowudan + "," + "zhongliang2=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(3).toString());
				}
				if(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(5).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(5) == null){
//					sql_huowudan = sql_huowudan + "," + "jiage=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(3) + "'";
					sql_huowudan = sql_huowudan + "," + "jiage=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(4).toString());
				} else {
//					sql_huowudan = sql_huowudan + "," + "baodijia=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(4) + "'";
					sql_huowudan = sql_huowudan + "," + "baodijia=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(5).toString());
				}
//				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6).equals("") 
//						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6) == null)){
////					sql_huowudan = sql_huowudan + "," + "zhesun=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(5) + "'";
//					sql_huowudan = sql_huowudan + "," + "zhesun=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6).toString());
//				}
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6) == null)){
					sql_huowudan = sql_huowudan + "," + "huozhu=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6) + "'";
//					sql_huowudan = sql_huowudan + "," + "huozhu=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(6).toString());
				}
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(7).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(7) == null)){
//					sql_huowudan = sql_huowudan + "," + "qitafeiyong=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(7) + "'";
					sql_huowudan = sql_huowudan + "," + "qitafeiyong=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(7).toString());
				}
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(8).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(8) == null)){
//					sql_huowudan = sql_huowudan + "," + "beizhu=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(8) + "'";
					sql_huowudan = sql_huowudan + "," + "beizhu=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(8).toString() + "'";
				}
				
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(9).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(9) == null)){
					sql_huowudan = sql_huowudan + "," + "shouxufei=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(9).toString() + "'";
				}
				
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(10).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(10) == null)){
//					sql_huowudan = sql_huowudan + "," + "yingfujine=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(9) + "'";
					sql_huowudan = sql_huowudan + "," + "yingfujine=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(10).toString());
				}
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(11).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(11) == null)){
//					sql_huowudan = sql_huowudan + "," + "shifujine=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(10) + "'";
					sql_huowudan = sql_huowudan + "," + "shifujine=" + Float.parseFloat(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(11).toString());
//					sql_huowudan = sql_huowudan + "," + "shifouqingsuan= 'yes'";
				}
				if(!(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(11).equals("") 
						|| ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(12) == null)){
					sql_huowudan = sql_huowudan + "," + "jiezhangbeizhu=" + "'" + ((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(12).toString() + "'";
				}
				if(((Vector)(this.yundan.huichehuodanV).elementAt(i)).elementAt(13).equals("是")){
					sql_huowudan = sql_huowudan + "," + "shifouqingsuan= 'yes'";
				}
				
				sql_huowudan = sql_huowudan + ";";
				
				DBManager.getInstance().excuteUpdate(sql_huowudan);
			}
		}
	}
	
	private void updateluxianbiao() throws SQLException{
		if(this.yundan.chuchehuodanV.size() > 0){
			if(!chucheluxianexist()){
				String sql = "insert into luxianbiao set "
						+ " chufadi = " + "'" + this.yundan.chuchechufadi.getSelectedItem().toString().trim() + "'"
						+ ", mudidi = " + "'" + this.yundan.chuchemudidi.getSelectedItem().toString().trim() + "'"
						+ ", riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "'"
						+ ";";
				DBManager.getInstance().excuteUpdate(sql);
				luxianbiaoNumControl(10);
			} else {
				String sql = "update luxianbiao set riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "'"
						+ "where "
						+ "chufadi = " + "'" + this.yundan.chuchechufadi.getSelectedItem().toString().trim() + "'"
						+ "and mudidi = " + "'" + this.yundan.chuchemudidi.getSelectedItem().toString().trim() + "'"
						+ ";";
				DBManager.getInstance().excuteUpdate(sql);
			}
		}
		
		if(this.yundan.huichehuodanV.size() > 0){
			if(!huicheluxianexist()){
				String sql = "insert into luxianbiao set "
						+ "chufadi = " + "'" + this.yundan.huichechufadi.getSelectedItem().toString().trim() + "'"
						+ ", mudidi = " + "'" + this.yundan.huichemudidi.getSelectedItem().toString().trim() + "'"
						+ ", riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()+1000)) + "'"
						+ ";";
				DBManager.getInstance().excuteUpdate(sql);
				luxianbiaoNumControl(10);
			} else {
				String sql = "update luxianbiao set riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()+1000)) + "'"
						+ "where "
						+ "chufadi = " + "'" + this.yundan.huichechufadi.getSelectedItem().toString().trim() + "'"
						+ "and mudidi = " + "'" + this.yundan.huichemudidi.getSelectedItem().toString().trim() + "'"
						+ ";";
				DBManager.getInstance().excuteUpdate(sql);
			}
		}
	}
	
	private boolean chucheluxianexist() throws SQLException{
		String sql = "select * from luxianbiao where "
				+ "chufadi = " + "'" +this.yundan.chuchechufadi.getSelectedItem().toString().trim() + "'"
				+ "and mudidi = " + "'" +this.yundan.chuchemudidi.getSelectedItem().toString().trim() + "'"
				+ ";";
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	private boolean huicheluxianexist() throws SQLException{
		String sql = "select * from luxianbiao where "
				+ "chufadi = " + "'" +this.yundan.huichechufadi.getSelectedItem().toString().trim() + "'"
				+ "and mudidi = " + "'" +this.yundan.huichemudidi.getSelectedItem().toString().trim() + "'"
				+ ";";
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	private void luxianbiaoNumControl(int num) throws SQLException{
		String sql = "select count(*) from luxianbiao;";
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		while(rs.next()){
//			System.out.println(rs.getInt("count(*)"));
			if(rs.getInt("count(*)") > num){
//				String sql_delete = "delete from luxianbiao where chufadi in (select riqi from luxianbiao order by riqi asc limit 1) as luxian and mudidi in luxian;";
//				String sql_delete = "delete from luxianbiao where (chufadi, mudidi)  in (select lx.chufadi, lx.mudidi from (select * from luxianbiao order by riqi asc limit 1) as lx);";
				String sql_delete = "delete from luxianbiao where riqi in (select lx.riqi from (select * from luxianbiao order by riqi asc limit 1) as lx);";
//				System.out.println(sql_delete);
				DBManager.getInstance().excuteUpdate(sql_delete);
			}
		}
	}

	private void updatehuozhubiao() throws SQLException{
		if(this.yundan.chuchehuodanV.size() > 0){
//			System.out.println(this.yundan.chuchehuodanTM.getRowCount());
			for(int i = 0; i < this.yundan.chuchehuodanTM.getRowCount(); i++){
				if(!huozhuexist(this.yundan.chuchehuodanTM.getValueAt(i, 6).toString()
						, this.yundan.chuchehuodanTM.getValueAt(i, 1).toString())){
					String sql = "insert into huozhubiao set "
							+ " huozhu = " + "'" + this.yundan.chuchehuodanTM.getValueAt(i, 6).toString().trim() + "'"
							+ ", huowu = " + "'" + this.yundan.chuchehuodanTM.getValueAt(i, 1).toString().trim() + "'"
							+ ", huozhupinyin = " + "'" + Cn2Spell.converterToSpell(this.yundan.chuchehuodanTM.getValueAt(i, 6).toString().trim()) + "'"
							+ ", riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "'"
							+ ";";
//					System.out.println(sql);
					DBManager.getInstance().excuteUpdate(sql);
					huozhubiaoNumControl(30);
				} else {
					String sql = "update huozhubiao set riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "'"
							+ ","
							+ "huowu = " + "'" + this.yundan.chuchehuodanTM.getValueAt(i, 1).toString().trim() + "'"
							+ "where "
							+ "huozhu = " + "'" + this.yundan.chuchehuodanTM.getValueAt(i, 6).toString().trim() + "'"
							+ ";";
					DBManager.getInstance().excuteUpdate(sql);
				}
			}
		}
		
		if(this.yundan.huichehuodanV.size() > 0){
			for(int i = 0; i < this.yundan.huichehuodanTM.getRowCount(); i++){
				if(!huozhuexist(this.yundan.huichehuodanTM.getValueAt(i, 6).toString()
						, this.yundan.huichehuodanTM.getValueAt(i, 1).toString())){
					String sql = "insert into huozhubiao set "
							+ " huozhu = " + "'" + this.yundan.huichehuodanTM.getValueAt(i, 6).toString().trim() + "'"
							+ ", huowu = " + "'" + this.yundan.huichehuodanTM.getValueAt(i, 1).toString().trim() + "'"
							+ ", huozhupinyin = " + "'" + Cn2Spell.converterToSpell(this.yundan.huichehuodanTM.getValueAt(i, 6).toString().trim()) + "'"
							+ ", riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()+1000)) + "'"
							+ ";";
//					System.out.println(sql);
					DBManager.getInstance().excuteUpdate(sql);
					huozhubiaoNumControl(30);
				} else {
					String sql = "update huozhubiao set riqi = " + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "'"
							+ ","
							+ "huowu = " + "'" + this.yundan.huichehuodanTM.getValueAt(i, 1).toString().trim() + "'"
							+ "where "
							+ "huozhu = " + "'" + this.yundan.huichehuodanTM.getValueAt(i, 6).toString().trim() + "'"
							+ ";";
					DBManager.getInstance().excuteUpdate(sql);
				}
			}
		}
	}
	
	private boolean huozhuexist(String huozhu, String huowu) throws SQLException{
		String sql = "select * from huozhubiao where "
				+ "huozhu = " + "'" + huozhu + "'"
//				+ "and huowu = " + "'" + huowu + "'"
				+ ";";
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	private void huozhubiaoNumControl(int num) throws SQLException{
		String sql = "select count(*) from huozhubiao;";
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		while(rs.next()){
			if(rs.getInt("count(*)") > num){
				String sql_delete = "delete from huozhubiao where riqi in (select lx.riqi from (select * from huozhubiao order by riqi asc limit 1) as lx);";
				DBManager.getInstance().excuteUpdate(sql_delete);
			}
		}
	}
}
