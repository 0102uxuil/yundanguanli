package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class HuozhuxinxitijiaoBtnActionListener implements ActionListener {

	HuoZhuXinXiDan hzxxd;
	
	HuozhuxinxitijiaoBtnActionListener(HuoZhuXinXiDan hd){
		this.hzxxd = hd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.hzxxd.option == HuoZhuXinXiDan.HuoZhuXinxiXiuGai){
			String sql_cheliangxinxidan;
			sql_cheliangxinxidan = "delete from huozhuxinxi where "
					+ " huozhuming = " + "'" + this.hzxxd.huozhu_str + "'"
					+ ";"; 
			
			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				DBManager.getInstance().excuteUpdate(sql_cheliangxinxidan);
				updatehuozhuxinxidan();
				DBManager.getInstance().getConnection().commit();
				DBManager.getInstance().getConnection().setAutoCommit(true);
				this.hzxxd.dispose();
				this.hzxxd.hzxxp.chazhaoBtn.doClick();
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
		
		
		if(this.hzxxd.option == HuoZhuXinXiDan.HuoZhuXinxiTianJia){
			if(this.hzxxd.huozhuming.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.hzxxd, "货主不能为空！", "输入错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!huozhuxinxidanExist()){
					try {
						updatehuozhuxinxidan();
						this.hzxxd.dispose();
						this.hzxxd.hzxxp.chazhaoBtn.doClick();
						JOptionPane.showMessageDialog(this.hzxxd, "该货主已成功录入！", "录入成功", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(this.hzxxd, e2.getMessage(), "输入格式错误", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.hzxxd, "该货主已存在！", "录入失败", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	private boolean huozhuxinxidanExist(){
		String sql_huozhuxinxijiancha;
		sql_huozhuxinxijiancha = "select * from huozhuxinxi where"
							  + " huozhuming=" + "'" + this.hzxxd.huozhuming.getText().trim() + "'"
							  +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_huozhuxinxijiancha);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updatehuozhuxinxidan() throws SQLException, FormatException{
		
		String sql_huozhuxinxi;
		sql_huozhuxinxi = "insert into huozhuxinxi set "
				+ " huozhuming = " + "'" + this.hzxxd.huozhuming.getText().trim() + "'"
				+ ";";
		
		DBManager.getInstance().excuteUpdate(sql_huozhuxinxi);
	}
	
}
