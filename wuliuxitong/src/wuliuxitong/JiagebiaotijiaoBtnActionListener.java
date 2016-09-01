package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JiagebiaotijiaoBtnActionListener implements ActionListener {

	JiaGeBiao jgb;
	
	public JiagebiaotijiaoBtnActionListener(JiaGeBiao jgb) {
		// TODO Auto-generated constructor stub
		this.jgb = jgb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.jgb.option == JiaGeBiao.JiaGeXiuGai){
			if(this.jgb.jiage.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "价格不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.jgb.sijijiage.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "司机价格不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(!this.jgb.jiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				JOptionPane.showMessageDialog(this.jgb, "价格必须为数字！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(!this.jgb.sijijiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				JOptionPane.showMessageDialog(this.jgb, "司机价格必须为数字！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				String sql_del;
				sql_del = "delete from jiagebiao where "
						+ " huozhu = " + "'" + this.jgb.huozhu_str + "'"
						+ " and chufadi = " + "'" + this.jgb.chufadi_str + "'"
						+ " and mudidi = " + "'" + this.jgb.mudidi_str + "'"
						+ " and huoming = " + "'" + this.jgb.huoming_str + "'"
						+ ";"; 
				
				try {
					DBManager.getInstance().getConnection().setAutoCommit(false);
					DBManager.getInstance().excuteUpdate(sql_del);
					updatejiagebiao();
					DBManager.getInstance().getConnection().commit();
					DBManager.getInstance().getConnection().setAutoCommit(true);
					this.jgb.dispose();
					this.jgb.jgbp.chazhaoBtn.doClick();
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
		}
		
		
		if(this.jgb.option == JiaGeBiao.JiaGeTianJia){
			if(this.jgb.huozhu.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "货主不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.jgb.chufadi.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "出发地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.jgb.mudidi.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "目的地不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.jgb.huoming.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "货名不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.jgb.jiage.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "价格不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.jgb.sijijiage.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.jgb, "司机价格不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(!this.jgb.jiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				JOptionPane.showMessageDialog(this.jgb, "价格必须为数字！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(!this.jgb.sijijiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				JOptionPane.showMessageDialog(this.jgb, "司机价格必须为数字！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!jiagebiaoExist()){
					try {
						updatejiagebiao();
						this.jgb.dispose();
						this.jgb.jgbp.chazhaoBtn.doClick();
						JOptionPane.showMessageDialog(this.jgb, "该货主已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(this.jgb, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.jgb, "该货主已存在！", "录入失败", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	private boolean jiagebiaoExist(){
		String sql;
		sql = "select * from jiagebiao where"
							  + " huozhu=" + "'" + this.jgb.huozhu.getText().trim() + "'"
							  + " and chufadi=" + "'" + this.jgb.chufadi.getText().trim() + "'"
							  + " and mudidi=" + "'" + this.jgb.mudidi.getText().trim() + "'"
							  + " and huoming=" + "'" + this.jgb.huoming.getText().trim() + "'"
							  +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updatejiagebiao() throws SQLException, FormatException{
		
		String sql;
		sql = "insert into jiagebiao set "
				+ " huozhu = " + "'" + this.jgb.huozhu.getText().trim() + "'"
				+ ", chufadi = " + "'" + this.jgb.chufadi.getText().trim() + "'"
				+ ", mudidi = " + "'" + this.jgb.mudidi.getText().trim() + "'"
				+ ", huoming = " + "'" + this.jgb.huoming.getText().trim() + "'"
				+ ", jiage = " + "'" + this.jgb.jiage.getText().trim() + "'"
				+ ", sijijiage = " + "'" + this.jgb.sijijiage.getText().trim() + "'"
				+ ";";
		System.out.println(sql);
		DBManager.getInstance().excuteUpdate(sql);
	}
}
