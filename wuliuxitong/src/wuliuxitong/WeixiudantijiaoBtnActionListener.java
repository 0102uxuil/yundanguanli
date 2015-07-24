package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class WeixiudantijiaoBtnActionListener implements ActionListener {

	WeiXiuDan weixiudan;
	
	WeixiudantijiaoBtnActionListener(WeiXiuDan wxd){
		this.weixiudan = wxd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.weixiudan.option == WeiXiuDan.WeiXiuDanXiuGai){
			String sql_yuekaixiaodan;
			sql_yuekaixiaodan = "delete from weixiudan where "
//					+ " chepaihao = " + "'" + this.weixiudan.chepaihao.getText().trim() + "'" 
					+ " chepaihao = " + "'" + this.weixiudan.chepaihaoCB.getSelectedItem().toString().trim() + "'" 
					+ " and riqi = " + "'" + this.weixiudan.riqi.getText().trim() + "'" 
					+ " and weixiudian = " + "'" + this.weixiudan.weixiudian.getText().trim() + "'" 
					+ ";"; 
			
			try {
				DBManager.getInstance().getConnection().setAutoCommit(false);
				DBManager.getInstance().excuteUpdate(sql_yuekaixiaodan);
				updateweixiudan();
				DBManager.getInstance().getConnection().commit();
				this.weixiudan.dispose();
				this.weixiudan.weixiudanPanel.chazhaoBtn.doClick();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "�޸�", JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FormatException e2){
				JOptionPane.showMessageDialog(null, e2.getMessage(), "�����ʽ����", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		if(this.weixiudan.option == WeiXiuDan.WeiXiuDanTianJia){
//			if(this.weixiudan.chepaihao.getText().trim().equals("")){
			if(this.weixiudan.chepaihaoCB.getSelectedItem().toString().trim().equals("")){
				JOptionPane.showMessageDialog(this.weixiudan, "���ƺŲ���Ϊ�գ�", "�������", JOptionPane.PLAIN_MESSAGE);
			} else if(this.weixiudan.riqi.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.weixiudan, "���ڲ���Ϊ�գ�", "�������", JOptionPane.PLAIN_MESSAGE);
			} else if(this.weixiudan.weixiudian.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this.weixiudan, "ά�޵㲻��Ϊ�գ�", "�������", JOptionPane.PLAIN_MESSAGE);
			} else {
				if(!weixiudanExist()){
					try {
						updateweixiudan();
						this.weixiudan.dispose();
						this.weixiudan.weixiudanPanel.chazhaoBtn.doClick();
						JOptionPane.showMessageDialog(this.weixiudan, "��ά�޵��ѳɹ�¼�룡", "¼��ɹ�", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FormatException e2){
						JOptionPane.showMessageDialog(null, e2.getMessage(), "�����ʽ����", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.weixiudan, "��ά�޵��Ѵ��ڣ�", "¼��ʧ��", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	private boolean weixiudanExist(){
		String sql_weixiudanjiancha;
		sql_weixiudanjiancha = "select * from weixiudan where"
//							  + " chepaihao=" + "'" + this.weixiudan.chepaihao.getText().trim() + "'"
							  + " chepaihao=" + "'" + this.weixiudan.chepaihaoCB.getSelectedItem().toString().trim() + "'"
							  + "and riqi= " + "'" + this.weixiudan.riqi.getText().trim() + "'"
							  + "and weixiudian= " + "'" + this.weixiudan.weixiudian.getText().trim() + "'"
							  +";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql_weixiudanjiancha);
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void updateweixiudan() throws SQLException, FormatException{
		
		String sql_weixiudan;
		sql_weixiudan = "insert into weixiudan set "
//				+ " chepaihao = " + "'" + this.weixiudan.chepaihao.getText().trim() + "'" + ","
				+ " chepaihao = " + "'" + this.weixiudan.chepaihaoCB.getSelectedItem().toString().trim() + "'" + ","
				+ " riqi = " + "'" + this.weixiudan.riqi.getText().trim() + "'" + ","
				+ " weixiudian = " + "'" + this.weixiudan.weixiudian.getText().trim() + "'";
		if(!this.weixiudan.weixiuxiangmu.getText().trim().equals("")){
			sql_weixiudan = sql_weixiudan
					+ ", weixiuxiangmu = " + "'" + this.weixiudan.weixiuxiangmu.getText().trim() + "'";
		}
		
		if(!this.weixiudan.weixiujine.getText().trim().equals("")){
			if(!this.weixiudan.weixiujine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
				throw new FormatException("ά�޽�������������");
			}
			sql_weixiudan = sql_weixiudan
					+ ", weixiujine = " + "'" + this.weixiudan.weixiujine.getText().trim() + "'";
		}
		
		if(!this.weixiudan.beizhu.getText().trim().equals("")){
			sql_weixiudan = sql_weixiudan
					+ ", beizhu = " + "'" + this.weixiudan.beizhu.getText().trim() + "'";
		}

		sql_weixiudan = sql_weixiudan + ";";
		
		DBManager.getInstance().excuteUpdate(sql_weixiudan);
	}

}
