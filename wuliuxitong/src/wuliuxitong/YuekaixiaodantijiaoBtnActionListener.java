package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class YuekaixiaodantijiaoBtnActionListener implements ActionListener {

	YueKaiXiaoDan yuekaixiaodan;
	
	YuekaixiaodantijiaoBtnActionListener(YueKaiXiaoDan ykxd){
		this.yuekaixiaodan = ykxd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.yuekaixiaodan.option == YueKaiXiaoDan.YueKaiXiaoXiuGai){
			String sql_yuekaixiaodan;
			sql_yuekaixiaodan = "delete from yuekaixiaodan where "
//					+ " chepaihao = " + "'" + this.yuekaixiaodan.chepaihao.getText().trim() + "'" 
					+ " chepaihao = " + "'" + this.yuekaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'" 
					+ " and riqi = " + "'" + this.yuekaixiaodan.date.getDate() + "'" 
					+ ";"; 
			
			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				DBManager.getInstance().excuteUpdate(sql_yuekaixiaodan);
				updateyuekaixiaodan();
				DBManager.getInstance().getConnection().commit();
				DBManager.getInstance().getConnection().setAutoCommit(true);
				this.yuekaixiaodan.dispose();
				this.yuekaixiaodan.yuekaixiaoPanel.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "修改成功！", "修改", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				try {
					DBManager.getInstance().getConnection().rollback();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			} catch (FormatException e2){
				try {
					DBManager.getInstance().getConnection().rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
		if(this.yuekaixiaodan.option == YueKaiXiaoDan.YueKaiXiaoTianJia){
//			if(this.yuekaixiaodan.chepaihao.getText().trim().equals("")){
			if(this.yuekaixiaodan.chepaihaoCB.getSelectedItem().toString().trim().equals("")){
				JOptionPane.showMessageDialog(this.yuekaixiaodan, "车牌号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.yuekaixiaodan.date.getYear().equals("") && this.yuekaixiaodan.date.getMonth().equals("")){
				JOptionPane.showMessageDialog(this.yuekaixiaodan, "日期不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!yuekaixiaodanExist()){
					try {
						updateyuekaixiaodan();
						this.yuekaixiaodan.dispose();
						this.yuekaixiaodan.yuekaixiaoPanel.chazhaoBtn.doClick();
						JOptionPane.showMessageDialog(this.yuekaixiaodan, "该开销单已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(), "录入失败", JOptionPane.PLAIN_MESSAGE);
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(null, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.yuekaixiaodan, "该开销单已存在！", "录入失败", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	private boolean yuekaixiaodanExist(){
		String sql_yuekaixiaojiancha;
		sql_yuekaixiaojiancha = "select * from yuekaixiaodan where"
//							  + " chepaihao=" + "'" + this.yuekaixiaodan.chepaihao.getText().trim() + "'"
							  + " chepaihao=" + "'" + this.yuekaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'"
							  + "and riqi= " + "'" + this.yuekaixiaodan.date.getDate() + "'"
							  +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_yuekaixiaojiancha);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updateyuekaixiaodan() throws SQLException, FormatException{
		
		float zongkaixiao;
		zongkaixiao = 0;
		String sql_yuekaixiao;
		sql_yuekaixiao = "insert into yuekaixiaodan set "
//				+ " chepaihao = " + "'" + this.yuekaixiaodan.chepaihao.getText().trim() + "'" + ","
				+ " chepaihao = " + "'" + this.yuekaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'" + ","
				+ " riqi = " + "'" + this.yuekaixiaodan.date.getDate() + "'";
		if(!this.yuekaixiaodan.yuetongka.getText().trim().equals("")){
			if(!this.yuekaixiaodan.yuetongka.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("粤通卡必须是正数！");
			}
			sql_yuekaixiao = sql_yuekaixiao
					+ ", yuetongka = " + "'" + this.yuekaixiaodan.yuetongka.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.yuekaixiaodan.yuetongka.getText().trim());
		}
		
		if(!this.yuekaixiaodan.dianhuafei.getText().trim().equals("")){
			if(!this.yuekaixiaodan.dianhuafei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("电话费必须是正数！");
			}
			sql_yuekaixiao = sql_yuekaixiao
					+ ", dianhuafei = " + "'" + this.yuekaixiaodan.dianhuafei.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.yuekaixiaodan.dianhuafei.getText().trim());
		}
		
		if(!this.yuekaixiaodan.qitafeiyong.getText().trim().equals("")){
			if(!this.yuekaixiaodan.qitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("其他费用必须是正数！");
			}
			sql_yuekaixiao = sql_yuekaixiao
					+ ", qitafeiyong = " + "'" + this.yuekaixiaodan.qitafeiyong.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.yuekaixiaodan.qitafeiyong.getText().trim());
		}
		
		if(!this.yuekaixiaodan.beizhu.getText().trim().equals("")){
			sql_yuekaixiao = sql_yuekaixiao
					+ ", beizhu = " + "'" + this.yuekaixiaodan.beizhu.getText().trim() + "'";
		}
		if(zongkaixiao != 0){
			sql_yuekaixiao = sql_yuekaixiao
					+ ", zongkaixiao = " + zongkaixiao;
		}
		sql_yuekaixiao = sql_yuekaixiao + ";";
		
		DBManager.getInstance().excuteUpdate(sql_yuekaixiao);
	}

}
