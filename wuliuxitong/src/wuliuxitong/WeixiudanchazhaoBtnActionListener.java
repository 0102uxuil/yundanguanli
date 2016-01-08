package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class WeixiudanchazhaoBtnActionListener implements ActionListener {

	WeixiudanPanel weixiudanPanel;
	
	WeixiudanchazhaoBtnActionListener(WeixiudanPanel wxdp){
		this.weixiudanPanel = wxdp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select chepaihao, riqi, weixiudian, weixiuxiangmu, weixiujine, beizhu from weixiudan where "
				+ "riqi >= " + "'" 
				+ this.weixiudanPanel.riqi_start.getText().trim()
				+ "'"
				+ " and " + "riqi <=" + "'" 
				+ this.weixiudanPanel.riqi_end.getText().trim()
				+ "'" ;
		if(!this.weixiudanPanel.chepaihao.getText().trim().equals("")){
			sql = sql
					+ " and"
					+ " chepaihao like " + "'%" + this.weixiudanPanel.chepaihao.getText().trim() + "%'";
		}
		if(!this.weixiudanPanel.weixiudian.getText().trim().equals("")){
			sql = sql
					+ " and"
					+ " weixiudian like " + "'%" + this.weixiudanPanel.weixiudian.getText().trim() + "%'";
		}
//		} else {
//			JOptionPane.showMessageDialog(null, "�˵���źͳ��ƺŲ���ȫΪ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
//		}
		sql = sql + " order by riqi asc;";
		
		try {
			this.generateVector(sql, this.weixiudanPanel.vector);
			this.weixiudanPanel.weixiudanModel.resetVector(this.weixiudanPanel.vector);
			this.weixiudanPanel.weixiudanModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.weixiudanPanel.weixiudanModel.fireTableStructureChanged();
	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//��ʼ����������
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float weixiujineheji;
		weixiujineheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//�ӽ������ȡ���ݷ�������rec_vector�� 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=6; i++){
				rec_vector.addElement(rs.getString(i));
			}
			weixiujineheji += rs.getFloat("weixiujine");
			vector.addElement(rec_vector);//����rec_vector��������vect�� 
			
		}
		weixiujineheji = (float)(Math.round(weixiujineheji*100))/100;
		
		Vector tail_vector=new Vector(); 
		for(int i=1; i<=4; i++){
			tail_vector.addElement(null);
		}
		tail_vector.addElement("�ϼƣ�");
		tail_vector.addElement(weixiujineheji);
		tail_vector.addElement(null);
		vector.addElement(tail_vector);
	}

}
