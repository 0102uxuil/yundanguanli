package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class LirunjisuanBtnActionListener implements ActionListener {

	LirunPanel lirunPanel;
	float lirunYuqi, lirunYide;
	
	LirunjisuanBtnActionListener(LirunPanel lrp){
		this.lirunPanel = lrp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.lirunYuqi = 0;
		this.lirunYide = 0;
//		String sql_yundan, sql_yuekaixiao, sql_niankaixiao;
//		sql_yuekaixiao = inityuekaixiaosql();
//		sql_niankaixiao = initniankaixiaosql();
		try {
			this.generateYunDanVector(this.lirunPanel.vector);
			this.appendVectorForBlank(this.lirunPanel.vector);
			this.appendVectorForYuekaixiao(this.lirunPanel.vector);
			this.appendVectorForBlank(this.lirunPanel.vector);
			this.appendVectorForNiankaixiao(this.lirunPanel.vector);
			this.appendVectorForBlank(this.lirunPanel.vector);
			this.appendVectorForWeixiukaixiao(this.lirunPanel.vector);
			this.appendVectorForBlank(this.lirunPanel.vector);
			this.appendVectorForHeji(this.lirunPanel.vector);
			this.lirunPanel.lirunModel.resetVector(this.lirunPanel.vector);
			this.lirunPanel.lirunModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.lirunPanel.lirunModel.fireTableStructureChanged();
	}
	
	public void generateYunDanVector(Vector vector) throws SQLException{
		String sql = inityundansql();
		vector.removeAllElements();//��ʼ����������
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float zongkaixiaoheji, yijiezhangzonge, weijiezhangzonge, yuqizonglirun, yidezonglirun;
		zongkaixiaoheji = 0;
		yijiezhangzonge = 0; 
		weijiezhangzonge = 0; 
		yuqizonglirun = 0; 
		yidezonglirun = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//�ӽ������ȡ���ݷ�������rec_vector��
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=9; i++){
				rec_vector.addElement(rs.getString(i));
			}
			
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
			//�����ܿ���
			float zongkaixiao;
			zongkaixiao = rs.getFloat("zongkaixiao");
			zongkaixiao += rs.getFloat("youhao");
			if(rs.getString("jiayouzhanjiayou") != null && !rs.getString("jiayouzhanjiayou").equals("")){
				zongkaixiao -= rs.getFloat("jiayouzhanjiayou");
			}
			if(rs.getString("tingchechangjiayou") != null && !rs.getString("tingchechangjiayou").equals("")){
				zongkaixiao -= rs.getFloat("tingchechangyoujia")*rs.getFloat("tingchechangjiayou");
			}
			
//			System.out.println(rs.getFloat("zongkaixiao"));
			yuqilirun = chucheyijiezhangjine + huicheyijiezhangjine + chucheweijiezhangjine + huicheweijiezhangjine
//					- rs.getFloat("zongkaixiao");
					- zongkaixiao;
			
			yidelirun = chucheyijiezhangjine + huicheyijiezhangjine
//					- rs.getFloat("zongkaixiao");
					- zongkaixiao;
			
//			rec_vector.addElement(chucheyijiezhangjine);
//			rec_vector.addElement(huicheyijiezhangjine);
//			rec_vector.addElement(chucheweijiezhangjine);
//			rec_vector.addElement(huicheweijiezhangjine);
			yijiezhangjine = (float)(Math.round(yijiezhangjine*100))/100;
			weijiezhangjine = (float)(Math.round(weijiezhangjine*100))/100;
			yuqilirun = (float)(Math.round(yuqilirun*100))/100;
			yidelirun = (float)(Math.round(yidelirun*100))/100;
			rec_vector.addElement(yijiezhangjine);
			rec_vector.addElement(weijiezhangjine);
			rec_vector.addElement(yuqilirun);
			rec_vector.addElement(yidelirun);

			yijiezhangzonge += yijiezhangjine;
			weijiezhangzonge += weijiezhangjine;
			yuqizonglirun += yuqilirun;
			yidezonglirun += yidelirun;
			
//			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			zongkaixiaoheji += zongkaixiao;
			
			vector.addElement(rec_vector);//����rec_vector��������vect�� 
		}
		
		//�ϼ���
		Vector rec_vector=new Vector();
		for(int i=1; i<=8; i++){
			rec_vector.addElement(null);
		}
		rec_vector.addElement("�ϼƣ�");
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		yijiezhangzonge = (float)(Math.round(yijiezhangzonge*100))/100;
		weijiezhangzonge = (float)(Math.round(weijiezhangzonge*100))/100;
		yuqizonglirun = (float)(Math.round(yuqizonglirun*100))/100;
		yidezonglirun = (float)(Math.round(yidezonglirun*100))/100;
		rec_vector.addElement(zongkaixiaoheji);
		rec_vector.addElement(yijiezhangzonge);
		rec_vector.addElement(weijiezhangzonge);
		rec_vector.addElement(yuqizonglirun);
		rec_vector.addElement(yidezonglirun);
		vector.addElement(rec_vector);
		
		this.lirunYuqi += yuqizonglirun;
		this.lirunYide += yidezonglirun;
	}
	
	private String inityundansql(){
		String sql;
		sql = "select yundanbianhao, chepaihao, chucheriqi, chuchechufadi, chuchemudidi, huicheriqi, huichechufadi, huichemudidi, zongkaixiao, youhao, jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia from kaixiaodan where "
				+ "chucheriqi >= " + "'" + this.lirunPanel.riqi_start.getDate() + "'" 
				+ " and " + "chucheriqi <=" + "'" + this.lirunPanel.riqi_end.getMonthEndDate() + "'";
		if(!this.lirunPanel.chepaihao.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.lirunPanel.chepaihao.getText().trim() + "%'";
		}
		sql = sql + " order by chucheriqi asc;";
		return sql;
	}
	
	private void appendVectorForYuekaixiao(Vector vector) throws SQLException{
		
		Vector head_vector=new Vector();
		head_vector.addElement("�¿���");
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("���");
		head_vector.addElement("���ƺ�");
		head_vector.addElement("����");
		head_vector.addElement("��ͨ��");
		head_vector.addElement("�绰��");
		head_vector.addElement("��������");
		head_vector.addElement("��ע");
		head_vector.addElement("�ܿ���");
		for(int i=1; i<=6; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = inityuekaixiaosql();
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float yuetongkaheji, dianhuafeiheji, qitafeiyongheji, zongkaixiaoheji;
		yuetongkaheji = 0;
		dianhuafeiheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//�ӽ������ȡ���ݷ�������rec_vector�� 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=7; i++){
				rec_vector.addElement(rs.getString(i));
			}
			for(int i=1; i<=6; i++){
				rec_vector.addElement(null);
			}
			yuetongkaheji += rs.getFloat("yuetongka");
			dianhuafeiheji += rs.getFloat("dianhuafei");
			qitafeiyongheji += rs.getFloat("qitafeiyong");
			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			
			vector.addElement(rec_vector);
		}
		
		Vector tail_vector=new Vector();
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement("�ϼƣ�");
		
		yuetongkaheji = (float)(Math.round(yuetongkaheji*100))/100;
		dianhuafeiheji = (float)(Math.round(dianhuafeiheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		tail_vector.addElement(yuetongkaheji);
		tail_vector.addElement(dianhuafeiheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		tail_vector.addElement(zongkaixiaoheji);
		for(int i=1; i<=6; i++){
			tail_vector.addElement(null);
		}
		vector.addElement(tail_vector);
		
		this.lirunYuqi -= zongkaixiaoheji;
		this.lirunYide -= zongkaixiaoheji;
	}
	
	private String inityuekaixiaosql(){
		String sql;
		sql = "select chepaihao, DATE_FORMAT(riqi ,'%Y-%m'), yuetongka, dianhuafei, qitafeiyong, beizhu, zongkaixiao from yuekaixiaodan where "
				+ "riqi >= " + "'" + this.lirunPanel.riqi_start.getDate() + "'" 
				+ " and " + "riqi <=" + "'" + this.lirunPanel.riqi_end.getMonthEndDate() + "'";
		if(!this.lirunPanel.chepaihao.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.lirunPanel.chepaihao.getText().trim() + "%'";
		}
		sql = sql + " order by riqi asc;";
		return sql;
	}
	
	private void appendVectorForNiankaixiao(Vector vector) throws SQLException{
		
		Vector head_vector=new Vector();
		head_vector.addElement("�꿪��");
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("���");
		head_vector.addElement("���ƺ�");
		head_vector.addElement("����");
		head_vector.addElement("��");
		head_vector.addElement("����");
		head_vector.addElement("������");
		head_vector.addElement("GPRS");
		head_vector.addElement("��������");
		head_vector.addElement("��ע");
		head_vector.addElement("�ܿ���");
		for(int i=1; i<=5; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = initniankaixiaosql();
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float shencheheji, baoxianheji, gerenxianheji, gprsheji, qitafeiyongheji, zongkaixiaoheji;
		shencheheji = 0;
		baoxianheji = 0;
		gerenxianheji = 0;
		gprsheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//�ӽ������ȡ���ݷ�������rec_vector�� 
			rec_vector.addElement(count);
			count++;
//			for(int i=1; i<=8; i++){
//				rec_vector.addElement(rs.getString(i));
//			}
			rec_vector.addElement(rs.getString("chepaihao"));
			rec_vector.addElement(rs.getString("DATE_FORMAT(riqi ,'%Y')"));
			rec_vector.addElement(rs.getString("shenche"));
			rec_vector.addElement(rs.getString("baoxian"));
			rec_vector.addElement(rs.getString("gerenxian"));
			rec_vector.addElement(rs.getString("gprs"));
			rec_vector.addElement(rs.getString("qitafeiyong"));
			rec_vector.addElement(rs.getString("beizhu"));
			rec_vector.addElement(rs.getString("zongkaixiao"));
			for(int i=1; i<=4; i++){
				rec_vector.addElement(null);
			}
			shencheheji += rs.getFloat("shenche");
			baoxianheji += rs.getFloat("baoxian");
			gerenxianheji += rs.getFloat("gerenxian");
			gprsheji += rs.getFloat("gprs");
			qitafeiyongheji += rs.getFloat("qitafeiyong");
			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			
			vector.addElement(rec_vector);
		}
		
		Vector tail_vector=new Vector();
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement("�ϼƣ�");
		shencheheji = (float)(Math.round(shencheheji*100))/100;
		baoxianheji = (float)(Math.round(baoxianheji*100))/100;
		gerenxianheji = (float)(Math.round(gerenxianheji*100))/100;
		gprsheji = (float)(Math.round(gprsheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		tail_vector.addElement(shencheheji);
		tail_vector.addElement(baoxianheji);
		tail_vector.addElement(gerenxianheji);
		tail_vector.addElement(gprsheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		tail_vector.addElement(zongkaixiaoheji);
		for(int i=1; i<=4; i++){
			tail_vector.addElement(null);
		}
		vector.addElement(tail_vector);
		
		this.lirunYuqi -= zongkaixiaoheji;
		this.lirunYide -= zongkaixiaoheji;
	}
	
	private String initniankaixiaosql(){
		String sql;
		sql = "select chepaihao, DATE_FORMAT(riqi ,'%Y'), shenche, baoxian, gerenxian, gprs, qitafeiyong, beizhu, zongkaixiao from niankaixiaodan where "
				+ "riqi >= " + "'" + this.lirunPanel.riqi_start.getYear() + "-01-01" + "'" 
				+ " and " + "riqi <=" + "'" + this.lirunPanel.riqi_end.getYear() + "-12-31" + "'";
		if(!this.lirunPanel.chepaihao.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.lirunPanel.chepaihao.getText().trim() + "%'";
		}
		sql = sql + " order by riqi asc;";
		return sql;
	}
	
	private void appendVectorForWeixiukaixiao(Vector vector) throws SQLException{
		
		Vector head_vector=new Vector();
		head_vector.addElement("ά�޿���");
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("���");
		head_vector.addElement("���ƺ�");
		head_vector.addElement("����");
		head_vector.addElement("ά�޵�");
		head_vector.addElement("ά����Ŀ");
		head_vector.addElement("ά�޽��");
		head_vector.addElement("��ע");
		for(int i=1; i<=7; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = initweixiukaixiaosql();
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
			for(int i=1; i<=7; i++){
				rec_vector.addElement(null);
			}
			weixiujineheji += rs.getFloat("weixiujine");
			
			vector.addElement(rec_vector);
		}
		
		Vector tail_vector=new Vector();
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement("�ϼƣ�");
		weixiujineheji = (float)(Math.round(weixiujineheji*100))/100;
		tail_vector.addElement(weixiujineheji);
		for(int i=1; i<=8; i++){
			tail_vector.addElement(null);
		}
		vector.addElement(tail_vector);
		
		this.lirunYuqi -= weixiujineheji;
		this.lirunYide -= weixiujineheji;
	}
	
	private String initweixiukaixiaosql(){
		String sql;
		sql = "select chepaihao, riqi, weixiudian, weixiuxiangmu, weixiujine, beizhu from weixiudan where "
				+ "riqi >= " + "'" + this.lirunPanel.riqi_start.getDate() + "'" 
				+ " and " + "riqi <=" + "'" + this.lirunPanel.riqi_end.getMonthEndDate() + "'";
		if(!this.lirunPanel.chepaihao.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.lirunPanel.chepaihao.getText().trim() + "%'";
		}
		sql = sql + " order by riqi asc;";
		return sql;
	}
	
	private void appendVectorForHeji(Vector vector){
		Vector head_vector=new Vector();
		for(int i=1; i<=9; i++){
			head_vector.addElement(null);
		}
		head_vector.addElement("�ܡ��ƣ�");
		head_vector.addElement("Ԥ������");
		this.lirunYuqi = (float)(Math.round(this.lirunYuqi*100))/100;
		head_vector.addElement(this.lirunYuqi);
		head_vector.addElement("�ѵ�����");
		this.lirunYide = (float)(Math.round(this.lirunYide*100))/100;
		head_vector.addElement(this.lirunYide);
		
		vector.addElement(head_vector);
	}
	
	private void appendVectorForBlank(Vector vector){
		Vector head_vector=new Vector();
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		
		vector.addElement(head_vector);
	}
}