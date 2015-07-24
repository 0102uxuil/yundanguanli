package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class YundanchazhaoBtnActionListener implements ActionListener {
	
	YundanPanel yundanPanel;
	
	YundanchazhaoBtnActionListener(YundanPanel ydp){
		this.yundanPanel = ydp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sql;
		sql = "select yundanbianhao, chepaihao, chucheriqi, chuchechufadi, chuchemudidi, huicheriqi, huichechufadi, huichemudidi, jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia, zongkaixiao, gonglishu, youhao from kaixiaodan where "
				+ "chucheriqi >= " + "'" + this.yundanPanel.chuche_start.getText().trim() + "'" 
				+ " and " + "chucheriqi <=" + "'" + this.yundanPanel.chuche_end.getText().trim() + "'";
		if(!this.yundanPanel.yundanbianhao_text.getText().trim().equals("")){
			sql = "select yundanbianhao, chepaihao, chucheriqi, chuchechufadi, chuchemudidi, huicheriqi, huichechufadi, huichemudidi, jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia, zongkaixiao, gonglishu, youhao from kaixiaodan where "
					+ "yundanbianhao = " + "'" + this.yundanPanel.yundanbianhao_text.getText().trim() + "'";
		} else if(!this.yundanPanel.chepaihao_text.getText().trim().equals("")){
//			sql = sql + " and " + "chepaihao = " + "'" + this.yundanPanel.chepaihao_text.getText().trim() + "'";
			sql = sql + " and " + "chepaihao like " + "'%" + this.yundanPanel.chepaihao_text.getText().trim() + "%'";
		}
//		} else {
//			JOptionPane.showMessageDialog(null, "运单编号和车牌号不能全为空！", "错误", JOptionPane.PLAIN_MESSAGE);
//		}
		sql = sql + " order by chucheriqi asc;";
		
		try {
			this.generateVector(sql, this.yundanPanel.vector);
			this.yundanPanel.yundanModel.resetVector(this.yundanPanel.vector);
			this.yundanPanel.yundanModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.yundanPanel.yundanModel.fireTableStructureChanged();
	}
	
	public void generateVector(String sql, Vector vector) throws SQLException{
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float tingchechangjiayouzongshengshu, tingchechangjiayoujunjia, tingchechangjiayouheji, zongkaixiaoheji;
		tingchechangjiayouzongshengshu = 0;
		tingchechangjiayoujunjia = 0;
		tingchechangjiayouheji = 0;
		zongkaixiaoheji = 0;
		float yijiezhangzonge, weijiezhangzonge, yuqizonglirun, yidezonglirun;
		yijiezhangzonge = 0; 
		weijiezhangzonge = 0; 
		yuqizonglirun = 0; 
		yidezonglirun = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中
			rec_vector.addElement(count);
			count++;
//			for(int i=1; i<=10; i++){
//				rec_vector.addElement(rs.getString(i));
//			}
			
			rec_vector.addElement(rs.getString("yundanbianhao"));
			rec_vector.addElement(rs.getString("chepaihao"));
			rec_vector.addElement(rs.getString("chucheriqi"));
			rec_vector.addElement(rs.getString("chuchechufadi"));
			rec_vector.addElement(rs.getString("chuchemudidi"));
			rec_vector.addElement(rs.getString("huicheriqi"));
			rec_vector.addElement(rs.getString("huichechufadi"));
			rec_vector.addElement(rs.getString("huichemudidi"));
			rec_vector.addElement(rs.getFloat("tingchechangjiayou"));
			rec_vector.addElement(rs.getFloat("tingchechangyoujia"));
			
			float tingchechangjiayoujine;
			tingchechangjiayoujine = 0;
			if(rs.getString("tingchechangjiayou") != null && !rs.getString("tingchechangjiayou").equals("")){
				tingchechangjiayouzongshengshu += rs.getFloat("tingchechangjiayou");
				tingchechangjiayouheji += rs.getFloat("tingchechangyoujia")*rs.getFloat("tingchechangjiayou");
				tingchechangjiayoujine = rs.getFloat("tingchechangyoujia")*rs.getFloat("tingchechangjiayou");
			}
			rec_vector.addElement(tingchechangjiayoujine);
//			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			float zongkaixiao;
			zongkaixiao = rs.getFloat("zongkaixiao");
			//zongkaixiao包括出车时的加油开销
			//由于每次 出车前都加满油，所以出车时的加油量是上一趟的油耗，所以需要减去出车前的加油开销，加上下一趟出车前的加油开销
			zongkaixiao += rs.getFloat("youhao");
			if(rs.getString("jiayouzhanjiayou") != null && !rs.getString("jiayouzhanjiayou").equals("")){
				zongkaixiao -= rs.getFloat("jiayouzhanjiayou");
			}
			if(rs.getString("tingchechangjiayou") != null && !rs.getString("tingchechangjiayou").equals("")){
				zongkaixiao -= rs.getFloat("tingchechangyoujia")*rs.getFloat("tingchechangjiayou");
			}
			
			zongkaixiaoheji += zongkaixiao;
			rec_vector.addElement(zongkaixiao);
			//预期利润start
			String sql_chuchehuodan, sql_huichehuodan;
			sql_chuchehuodan = "select yingfujine, shifujine, shifouqingsuan from huowudan where "
					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '0%';";
			sql_huichehuodan = "select yingfujine, shifujine, shifouqingsuan from huowudan where "
					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '1%';";
			ResultSet rsc = DBManager.getInstance().excuteQuery(sql_chuchehuodan);
			ResultSet rsh = DBManager.getInstance().excuteQuery(sql_huichehuodan);
			float chucheyijiezhangjine, huicheyijiezhangjine, chucheweijiezhangjine, huicheweijiezhangjine;
			chucheyijiezhangjine = 0;
			huicheyijiezhangjine = 0;
			chucheweijiezhangjine = 0;
			huicheweijiezhangjine = 0;
			while(rsc.next()){
				if(rsc.getString("shifouqingsuan").equals("yes")){
					chucheyijiezhangjine += rsc.getFloat("shifujine");
				}
				if(rsc.getString("shifouqingsuan").equals("no")){
					chucheyijiezhangjine += rsc.getFloat("shifujine");
					chucheweijiezhangjine =chucheweijiezhangjine + rsc.getFloat("yingfujine") - rsc.getFloat("shifujine");
				}
			}
			while(rsh.next()){
				if(rsh.getString("shifouqingsuan").equals("yes")){
					huicheyijiezhangjine += rsh.getFloat("shifujine");
				}
				if(rsh.getString("shifouqingsuan").equals("no")){
					huicheyijiezhangjine += rsh.getFloat("shifujine");
					huicheweijiezhangjine = huicheweijiezhangjine + rsh.getFloat("yingfujine") - rsh.getFloat("shifujine");
				}
			}
			float yuqilirun, yidelirun;
			float yijiezhangjine, weijiezhangjine;
			yijiezhangjine = chucheyijiezhangjine + huicheyijiezhangjine;
			weijiezhangjine = chucheweijiezhangjine + huicheweijiezhangjine;
//			System.out.println(rs.getFloat("zongkaixiao"));
			yuqilirun = chucheyijiezhangjine + huicheyijiezhangjine + chucheweijiezhangjine + huicheweijiezhangjine
//					- rs.getFloat("zongkaixiao");
					- zongkaixiao;
			
			yidelirun = chucheyijiezhangjine + huicheyijiezhangjine
//					- rs.getFloat("zongkaixiao");
					- zongkaixiao;
			yijiezhangjine = (float)(Math.round(yijiezhangjine*100))/100;
			weijiezhangjine = (float)(Math.round(weijiezhangjine*100))/100;
			yuqilirun = (float)(Math.round(yuqilirun*100))/100;
			yidelirun = (float)(Math.round(yidelirun*100))/100;
//			rec_vector.addElement(yijiezhangjine);
			rec_vector.addElement(weijiezhangjine);
			rec_vector.addElement(yuqilirun);
//			rec_vector.addElement(yidelirun);

			yijiezhangzonge += yijiezhangjine;
			weijiezhangzonge += weijiezhangjine;
			yuqizonglirun += yuqilirun;
			yidezonglirun += yidelirun;
			//预期利润end
			
			//油耗start
			rec_vector.addElement((float)(Math.round(rs.getFloat("gonglishu")*100))/100);
			rec_vector.addElement((float)(Math.round(rs.getFloat("youhao")*100))/100);
			rec_vector.addElement((float)(Math.round(rs.getFloat("youhao")/rs.getFloat("gonglishu")*100))/100);
			//油耗end
			
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
		tingchechangjiayoujunjia = tingchechangjiayouheji/tingchechangjiayouzongshengshu;
		tingchechangjiayouzongshengshu = (float)(Math.round(tingchechangjiayouzongshengshu*100))/100;
		tingchechangjiayoujunjia = (float)(Math.round(tingchechangjiayoujunjia*100))/100;
		tingchechangjiayouheji = (float)(Math.round(tingchechangjiayouheji*100))/100;
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		
		yijiezhangzonge = (float)(Math.round(yijiezhangzonge*100))/100;
		weijiezhangzonge = (float)(Math.round(weijiezhangzonge*100))/100;
		yuqizonglirun = (float)(Math.round(yuqizonglirun*100))/100;
		yidezonglirun = (float)(Math.round(yidezonglirun*100))/100;
		
		Vector tail_vector = new Vector();
		for(int i=1; i<=8; i++){
			tail_vector.addElement(null);
		}
		tail_vector.addElement("合计：");
		tail_vector.addElement(tingchechangjiayouzongshengshu+"升");
		tail_vector.addElement(tingchechangjiayoujunjia+"元/升");
		tail_vector.addElement(tingchechangjiayouheji+"元");
		tail_vector.addElement(zongkaixiaoheji+"元");
		
//		tail_vector.addElement(yijiezhangzonge+"元");
		tail_vector.addElement(weijiezhangzonge+"元");
		tail_vector.addElement(yuqizonglirun+"元");
//		tail_vector.addElement(yidezonglirun+"元");
		
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		
		vector.addElement(tail_vector);
	}

}
