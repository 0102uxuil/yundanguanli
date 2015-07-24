package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class HuodanquedingBtnActionListener implements ActionListener {

	HuoDan huodan;
	
	HuodanquedingBtnActionListener(HuoDan hd){
		this.huodan = hd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.huodan.option == HuoDan.JieZhang){
			if(this.huodan.shifujine.getText().trim() == null || this.huodan.shifujine.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "实付金额不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
			} else if(!this.huodan.shifujine.getText().trim().equals("") && this.huodan.shifujine.getText().trim() != null && !this.huodan.shifujine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				JOptionPane.showMessageDialog(null, "实付金额必须为正数！", "输入格式错误", JOptionPane.PLAIN_MESSAGE);
			} else {
				try {
					DBManager.getInstance().getConnection().setAutoCommit(false);
					updatehuowudan();
//					updatekaixiaodan();
					DBManager.getInstance().getConnection().commit();
					this.huodan.dispose();
					this.huodan.huodanPanel.chazhaoBtn.doClick();
					JOptionPane.showMessageDialog(null, "实付金额修改成功！", "结账", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private void updatehuowudan() throws SQLException{
		String sql_huowudan;
		sql_huowudan = "update huowudan set shifujine=" + Float.parseFloat(this.huodan.shifujine.getText().trim())
					+ ", jiezhangbeizhu = " + "'" + this.huodan.jiezhangbeizhu.getText().trim() + "'";
		if(this.huodan.shifouqingsuan.getSelectedItem().toString().equals("是")){
			sql_huowudan = sql_huowudan	+ ", shifouqingsuan = 'yes'";
		}
		if(this.huodan.shifouqingsuan.getSelectedItem().toString().equals("否")){
			sql_huowudan = sql_huowudan	+ ", shifouqingsuan = 'no'";
		}
		
		sql_huowudan = sql_huowudan	
					   + " where  yundanbianhao=" + "'" + this.huodan.yundanbianhao.getText().trim() + "'"
					   + " and huowubianhao=" + "'" + this.huodan.huowubianhao.getText().trim() + "'" + ";";
		DBManager.getInstance().excuteUpdate(sql_huowudan);
	}
	
	private void updatekaixiaodan() throws SQLException{
		String sql_huowudan;
		sql_huowudan = "select huowubianhao, shifujine from huowudan where "
					 + " yundanbianhao=" + "'" + this.huodan.yundanbianhao.getText().trim() + "'"
				     + " and huowubianhao like " + "'" + this.huodan.huowubianhao.getText().trim().charAt(0) + "%" + "'" + ";";
		ResultSet rs = DBManager.getInstance().excuteQuery(sql_huowudan);
		float shifuzonge = 0;
		while(rs.next()){
			shifuzonge += Float.parseFloat(rs.getString("shifujine"));
		}
		String sql_kaixiaodan;
		String regexc, regexh;
		regexc = "^0.+$";
		regexh = "^1.+$";
		if(this.huodan.huowubianhao.getText().trim().matches(regexc)){
			sql_kaixiaodan = "update kaixiaodan set chucheshifuzonge = " + shifuzonge
					+ " where yundanbianhao= " + "'" + this.huodan.yundanbianhao.getText().trim() + "'" + ";";
		} else {
			sql_kaixiaodan = "update kaixiaodan set huicheshifuzonge = " + shifuzonge
					+ " where yundanbianhao= " + "'" + this.huodan.yundanbianhao.getText().trim() + "'" + ";";
		}
		DBManager.getInstance().excuteUpdate(sql_kaixiaodan);
	}
}
