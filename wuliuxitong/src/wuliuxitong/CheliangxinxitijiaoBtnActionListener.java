package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CheliangxinxitijiaoBtnActionListener implements ActionListener {

	CheLiangXinXiDan clxxd;
	
	CheliangxinxitijiaoBtnActionListener(CheLiangXinXiDan cd){
		this.clxxd = cd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.clxxd.option == CheLiangXinXiDan.CheLiangXinxiXiuGai){
			String sql_cheliangxinxidan;
			sql_cheliangxinxidan = "delete from cheliangxinxi where "
					+ " chepaihao = " + "'" + this.clxxd.chepaihao.getText().trim() + "'" 
					+ ";"; 
			
			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				DBManager.getInstance().excuteUpdate(sql_cheliangxinxidan);
				updatecheliangxinxidan();
				DBManager.getInstance().getConnection().commit();
				DBManager.getInstance().getConnection().setAutoCommit(true);
				this.clxxd.dispose();
				this.clxxd.clxxp.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "修改成功！", "修改", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FormatException e2){
				JOptionPane.showMessageDialog(null, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
		if(this.clxxd.option == CheLiangXinXiDan.CheLiangXinxiTianJia){
			if(this.clxxd.chepaihao.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.clxxd, "车牌号不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else if(this.clxxd.siji.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.clxxd, "司机不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!cheliangxinxidanExist()){
					try {
						updatecheliangxinxidan();
						this.clxxd.dispose();
						this.clxxd.clxxp.chazhaoBtn.doClick();
						JOptionPane.showMessageDialog(this.clxxd, "该开销单已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(this.clxxd, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.clxxd, "该开销单已存在！", "录入失败", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	private boolean cheliangxinxidanExist(){
		String sql_cheliangxinxijiancha;
		sql_cheliangxinxijiancha = "select * from cheliangxinxi where"
							  + " chepaihao=" + "'" + this.clxxd.chepaihao.getText().trim() + "'"
							  +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_cheliangxinxijiancha);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updatecheliangxinxidan() throws SQLException, FormatException{
		
		String sql_cheliangxinxi;
		sql_cheliangxinxi = "insert into cheliangxinxi set "
				+ " chepaihao = " + "'" + this.clxxd.chepaihao.getText().trim() + "'" + ","
				+ " siji = " + "'" + this.clxxd.siji.getText().trim() + "'"
				+ ";";
		
		DBManager.getInstance().excuteUpdate(sql_cheliangxinxi);
	}
	
}
