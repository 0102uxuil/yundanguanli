package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class JiagebiaochazhaoBtnActionListener implements ActionListener {
	
	JiagebiaoPanel jgbp;

	public JiagebiaochazhaoBtnActionListener(JiagebiaoPanel jp) {
		// TODO Auto-generated constructor stub
		this.jgbp = jp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select huozhu, chufadi, mudidi, huoming, jiage, sijijiage from jiagebiao ";
		if(this.jgbp.huozhuming_text.getText() != null && !this.jgbp.huozhuming_text.getText().trim().equals("")){
			sql += " where huozhu like " + "'%" + this.jgbp.huozhuming_text.getText().trim() + "%'";
		}
		sql = sql + " order by huozhu asc;";
		
		try {
			this.generateVector(sql, this.jgbp.vector);
			this.jgbp.jiagebiaoModel.resetVector(this.jgbp.vector);
			this.jgbp.jiagebiaoModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.jgbp.jiagebiaoModel.fireTableStructureChanged();
	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//��ʼ����������
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//�ӽ������ȡ���ݷ�������rec_vector�� 
			rec_vector.addElement(count);
			count++;
			rec_vector.addElement(rs.getString("huozhu"));
			rec_vector.addElement(rs.getString("chufadi"));
			rec_vector.addElement(rs.getString("mudidi"));
			rec_vector.addElement(rs.getString("huoming"));
			rec_vector.addElement(rs.getString("jiage"));
			rec_vector.addElement(rs.getString("sijijiage"));
			vector.addElement(rec_vector);//����rec_vector��������vect�� 
		}
	}
}
