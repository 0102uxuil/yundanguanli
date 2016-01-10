package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class NiankaixiaodantijiaoBtnActionListener implements ActionListener {

	NianKaiXiaoDan niankaixiaodan;
	
	NiankaixiaodantijiaoBtnActionListener(NianKaiXiaoDan nkxd){
		this.niankaixiaodan = nkxd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.niankaixiaodan.option == NianKaiXiaoDan.NianKaiXiaoXiuGai){
			String sql_niankaixiaodan;
			sql_niankaixiaodan = "delete from niankaixiaodan where "
//					+ " chepaihao = " + "'" + this.niankaixiaodan.chepaihao.getText().trim() + "'" 
					+ " chepaihao = " + "'" + this.niankaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'" 
					+ " and riqi = " + "'" + this.niankaixiaodan.year.getValue().toString() + "-01-01" + "'" 
					+ ";"; 
			
			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				DBManager.getInstance().excuteUpdate(sql_niankaixiaodan);
				updateniankaixiaodan();
				DBManager.getInstance().getConnection().commit();
				DBManager.getInstance().getConnection().setAutoCommit(true);
				this.niankaixiaodan.dispose();
				this.niankaixiaodan.niankaixiaoPanel.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "修改成功！", "修改", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					DBManager.getInstance().getConnection().rollback();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
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
		
		
		if(this.niankaixiaodan.option == NianKaiXiaoDan.NianKaiXiaoTianJia){
			if(this.niankaixiaodan.chepaihaoCB.getSelectedItem().toString().trim().equals("")){
				JOptionPane.showMessageDialog(this.niankaixiaodan, "车牌号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.niankaixiaodan.year.getValue().toString().equals("")){
				JOptionPane.showMessageDialog(this.niankaixiaodan, "日期不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!niankaixiaodanExist()){
					try {
						updateniankaixiaodan();
						this.niankaixiaodan.dispose();
						this.niankaixiaodan.niankaixiaoPanel.chazhaoBtn.doClick();
						JOptionPane.showMessageDialog(this.niankaixiaodan, "该开销单已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(null, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.niankaixiaodan, "该开销单已存在！", "录入失败", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	private boolean niankaixiaodanExist(){
		String sql_niankaixiaojiancha;
		sql_niankaixiaojiancha = "select * from niankaixiaodan where"
							  + " chepaihao=" + "'" + this.niankaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'"
							  + "and riqi= " + "'" + this.niankaixiaodan.year.getValue().toString() + "-01-01" + "'"
							  +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_niankaixiaojiancha);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updateniankaixiaodan() throws SQLException, FormatException{
		
		float zongkaixiao;
		zongkaixiao = 0;
		String sql_niankaixiao;
		sql_niankaixiao = "insert into niankaixiaodan set "
				+ " chepaihao = " + "'" + this.niankaixiaodan.chepaihaoCB.getSelectedItem().toString().trim() + "'" + ","
				+ " riqi = " + "'" + this.niankaixiaodan.year.getValue().toString()+ "-01-01" + "'";
		if(!this.niankaixiaodan.shenche.getText().trim().equals("")){
			if(!this.niankaixiaodan.shenche.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("审车必须是正数！");
			}
			sql_niankaixiao = sql_niankaixiao
					+ ", shenche = " + "'" + this.niankaixiaodan.shenche.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.niankaixiaodan.shenche.getText().trim());
		}
		
		if(!this.niankaixiaodan.baoxian.getText().trim().equals("")){
			if(!this.niankaixiaodan.baoxian.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("车险必须是正数！");
			}
			sql_niankaixiao = sql_niankaixiao
					+ ", baoxian = " + "'" + this.niankaixiaodan.baoxian.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.niankaixiaodan.baoxian.getText().trim());
		}
		
		if(!this.niankaixiaodan.gerenxian.getText().trim().equals("")){
			if(!this.niankaixiaodan.gerenxian.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("个人险必须是正数！");
			}
			sql_niankaixiao = sql_niankaixiao
					+ ", gerenxian = " + "'" + this.niankaixiaodan.gerenxian.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.niankaixiaodan.gerenxian.getText().trim());
		}
		
		if(!this.niankaixiaodan.gprs.getText().trim().equals("")){
			if(!this.niankaixiaodan.gprs.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("GPRS必须是正数！");
			}
			sql_niankaixiao = sql_niankaixiao
					+ ", gprs = " + "'" + this.niankaixiaodan.gprs.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.niankaixiaodan.gprs.getText().trim());
		}
		
		if(!this.niankaixiaodan.qitafeiyong.getText().trim().equals("")){
			if(!this.niankaixiaodan.qitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("其他费用必须是正数！");
			}
			sql_niankaixiao = sql_niankaixiao
					+ ", qitafeiyong = " + "'" + this.niankaixiaodan.qitafeiyong.getText().trim() + "'";
			zongkaixiao += Float.parseFloat(this.niankaixiaodan.qitafeiyong.getText().trim());
		}
		
		if(!this.niankaixiaodan.beizhu.getText().trim().equals("")){
			sql_niankaixiao = sql_niankaixiao
					+ ", beizhu = " + "'" + this.niankaixiaodan.beizhu.getText().trim() + "'";
		}
		if(zongkaixiao != 0){
			sql_niankaixiao = sql_niankaixiao
					+ ", zongkaixiao = " + zongkaixiao;
		}
		sql_niankaixiao = sql_niankaixiao + ";";
		
		DBManager.getInstance().excuteUpdate(sql_niankaixiao);
	}
}
