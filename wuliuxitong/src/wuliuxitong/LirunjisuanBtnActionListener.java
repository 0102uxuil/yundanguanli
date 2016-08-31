package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class LirunjisuanBtnActionListener implements ActionListener {

	LirunPanel lirunPanel;
	float lirunYuqi, lirunYide;
	float lirunYuqiDetail, lirunYideDetail;
	
	LirunjisuanBtnActionListener(LirunPanel lrp){
		this.lirunPanel = lrp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.lirunYuqi = 0;
		this.lirunYide = 0;
		this.lirunYideDetail = 0;
		this.lirunYuqiDetail = 0;
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
			//detailvector
			this.generateYunDanVectorDetail(this.lirunPanel.detailvector);
			this.appendVectorForBlankDetail(this.lirunPanel.detailvector);
			this.appendVectorForYuekaixiaoDetail(this.lirunPanel.detailvector);
			this.appendVectorForBlankDetail(this.lirunPanel.detailvector);
			this.appendVectorForNiankaixiaoDetail(this.lirunPanel.detailvector);
			this.appendVectorForBlankDetail(this.lirunPanel.detailvector);
			this.appendVectorForWeixiukaixiaoDetail(this.lirunPanel.detailvector);
			this.appendVectorForBlankDetail(this.lirunPanel.detailvector);
			this.appendVectorForHejiDetail(this.lirunPanel.detailvector);
			this.lirunPanel.lirunDetailModel.resetVector(this.lirunPanel.detailvector);
//			this.lirunPanel.lirunDetailModel.fireTableStructureChanged();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.lirunPanel.lirunModel.fireTableStructureChanged();
	}
	
	public void generateYunDanVector(Vector vector) throws SQLException{
		String sql = inityundansql();
		vector.removeAllElements();//初始化向量对象
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float zongkaixiaoheji, yijiezhangzonge, weijiezhangzonge, yuqizonglirun, yidezonglirun, sijizonglirun;
		zongkaixiaoheji = 0;
		yijiezhangzonge = 0; 
		weijiezhangzonge = 0; 
		yuqizonglirun = 0; 
		yidezonglirun = 0;
		sijizonglirun = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=9; i++){
				rec_vector.addElement(rs.getString(i));
			}
			
			String sql_chuchehuodan, sql_huichehuodan;
			sql_chuchehuodan = "select yingfujine, shifujine, shifouqingsuan, sijijiagejine from huowudan where "
					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '0%';";
			sql_huichehuodan = "select yingfujine, shifujine, shifouqingsuan, sijijiagejine from huowudan where "
					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '1%';";
			ResultSet rsc = DBManager.getInstance().excuteQuery(sql_chuchehuodan);
			ResultSet rsh = DBManager.getInstance().excuteQuery(sql_huichehuodan);
			float chucheyijiezhangjine, huicheyijiezhangjine, chucheweijiezhangjine, huicheweijiezhangjine, sijilirun;
			chucheyijiezhangjine = 0;
			huicheyijiezhangjine = 0;
			chucheweijiezhangjine = 0;
			huicheweijiezhangjine = 0;
			sijilirun = 0;
			while(rsc.next()){
				if(rsc.getString("shifouqingsuan").equals("yes")){
					chucheyijiezhangjine += rsc.getFloat("shifujine");
				}
				if(rsc.getString("shifouqingsuan").equals("no")){
					chucheyijiezhangjine += rsc.getFloat("shifujine");
					chucheweijiezhangjine =chucheweijiezhangjine + rsc.getFloat("yingfujine") - rsc.getFloat("shifujine");
				}
				sijilirun += rsc.getFloat("sijijiagejine");
			}
			while(rsh.next()){
				if(rsh.getString("shifouqingsuan").equals("yes")){
					huicheyijiezhangjine += rsh.getFloat("shifujine");
				}
				if(rsh.getString("shifouqingsuan").equals("no")){
					huicheyijiezhangjine += rsh.getFloat("shifujine");
					huicheweijiezhangjine = huicheweijiezhangjine + rsh.getFloat("yingfujine") - rsh.getFloat("shifujine");
				}
				sijilirun += rsh.getFloat("sijijiagejine");
			}
			float yuqilirun, yidelirun;
			float yijiezhangjine, weijiezhangjine;
			yijiezhangjine = chucheyijiezhangjine + huicheyijiezhangjine;
			weijiezhangjine = chucheweijiezhangjine + huicheweijiezhangjine;
			//重算总开销
			float youhao = 0;
			String yundanbianhao_next = YunDanBianHao.next(rs.getString("yundanbianhao"));
			if(yundanbianhao_next != null){
				String sql_yn = "select jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia from kaixiaodan where "
						+ "yundanbianhao = "
						+ "'" + yundanbianhao_next + "'"
						+ ";";
				ResultSet rs_yn = DBManager.getInstance().excuteQuery(sql_yn);
				if(rs_yn.next()){
					if(rs_yn.getString("jiayouzhanjiayou") != null && !rs_yn.getString("jiayouzhanjiayou").equals("")){
						youhao += rs_yn.getFloat("jiayouzhanjiayou");
					}
					if(rs_yn.getString("tingchechangjiayou") != null && !rs_yn.getString("tingchechangjiayou").equals("")){
						youhao += rs_yn.getFloat("tingchechangjiayou")*rs_yn.getFloat("tingchechangyoujia");
					}
				}
			}
			float zongkaixiao;
			zongkaixiao = rs.getFloat("zongkaixiao");
//			zongkaixiao += rs.getFloat("youhao");
			zongkaixiao += youhao;
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
			sijilirun -= zongkaixiao;
//			rec_vector.addElement(chucheyijiezhangjine);
//			rec_vector.addElement(huicheyijiezhangjine);
//			rec_vector.addElement(chucheweijiezhangjine);
//			rec_vector.addElement(huicheweijiezhangjine);
			yijiezhangjine = (float)(Math.round(yijiezhangjine*100))/100;
			weijiezhangjine = (float)(Math.round(weijiezhangjine*100))/100;
			yuqilirun = (float)(Math.round(yuqilirun*100))/100;
			yidelirun = (float)(Math.round(yidelirun*100))/100;
			sijilirun = (float)(Math.round(sijilirun*100))/100;
			rec_vector.addElement(yijiezhangjine);
			rec_vector.addElement(weijiezhangjine);
			rec_vector.addElement(yuqilirun);
			rec_vector.addElement(yidelirun);
			rec_vector.addElement(sijilirun);

			yijiezhangzonge += yijiezhangjine;
			weijiezhangzonge += weijiezhangjine;
			yuqizonglirun += yuqilirun;
			yidezonglirun += yidelirun;
			sijizonglirun += sijilirun;
			
//			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			zongkaixiaoheji += zongkaixiao;
			
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
		
		//合计行
		Vector rec_vector=new Vector();
		for(int i=1; i<=8; i++){
			rec_vector.addElement(null);
		}
		rec_vector.addElement("合计：");
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		yijiezhangzonge = (float)(Math.round(yijiezhangzonge*100))/100;
		weijiezhangzonge = (float)(Math.round(weijiezhangzonge*100))/100;
		yuqizonglirun = (float)(Math.round(yuqizonglirun*100))/100;
		yidezonglirun = (float)(Math.round(yidezonglirun*100))/100;
		sijizonglirun = (float)(Math.round(sijizonglirun*100))/100;
		rec_vector.addElement(zongkaixiaoheji);
		rec_vector.addElement(yijiezhangzonge);
		rec_vector.addElement(weijiezhangzonge);
		rec_vector.addElement(yuqizonglirun);
		rec_vector.addElement(yidezonglirun);
		rec_vector.addElement(sijizonglirun);
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
		head_vector.addElement("月开销");
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("序号");
		head_vector.addElement("车牌号");
		head_vector.addElement("日期");
		head_vector.addElement("粤通卡");
		head_vector.addElement("电话费");
		head_vector.addElement("其他费用");
		head_vector.addElement("备注");
		head_vector.addElement("总开销");
		for(int i=1; i<=7; i++){
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
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=7; i++){
				rec_vector.addElement(rs.getString(i));
			}
			for(int i=1; i<=7; i++){
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
		tail_vector.addElement("合计：");
		
		yuetongkaheji = (float)(Math.round(yuetongkaheji*100))/100;
		dianhuafeiheji = (float)(Math.round(dianhuafeiheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		tail_vector.addElement(yuetongkaheji);
		tail_vector.addElement(dianhuafeiheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		tail_vector.addElement(zongkaixiaoheji);
		for(int i=1; i<=7; i++){
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
		head_vector.addElement("年开销");
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("序号");
		head_vector.addElement("车牌号");
		head_vector.addElement("日期");
		head_vector.addElement("审车");
		head_vector.addElement("车险");
		head_vector.addElement("个人险");
		head_vector.addElement("审营运证");
		head_vector.addElement("GPRS");
		head_vector.addElement("其他费用");
		head_vector.addElement("备注");
		head_vector.addElement("总开销");
		for(int i=1; i<=4; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = initniankaixiaosql();
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float shencheheji, baoxianheji, gerenxianheji,shenyingyunzhengheji, gprsheji, qitafeiyongheji, zongkaixiaoheji;
		shencheheji = 0;
		baoxianheji = 0;
		gerenxianheji = 0;
		shenyingyunzhengheji = 0;
		gprsheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
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
			rec_vector.addElement(rs.getString("shenyingyunzheng"));
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
			shenyingyunzhengheji += rs.getFloat("shenyingyunzheng");
			gprsheji += rs.getFloat("gprs");
			qitafeiyongheji += rs.getFloat("qitafeiyong");
			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			
			vector.addElement(rec_vector);
		}
		
		Vector tail_vector=new Vector();
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement("合计：");
		shencheheji = (float)(Math.round(shencheheji*100))/100;
		baoxianheji = (float)(Math.round(baoxianheji*100))/100;
		gerenxianheji = (float)(Math.round(gerenxianheji*100))/100;
		shenyingyunzhengheji = (float)(Math.round(shenyingyunzhengheji*100))/100;
		gprsheji = (float)(Math.round(gprsheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		tail_vector.addElement(shencheheji);
		tail_vector.addElement(baoxianheji);
		tail_vector.addElement(gerenxianheji);
		tail_vector.addElement(shenyingyunzhengheji);
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
		sql = "select chepaihao, DATE_FORMAT(riqi ,'%Y'), shenche, baoxian, gerenxian, shenyingyunzheng, gprs, qitafeiyong, beizhu, zongkaixiao from niankaixiaodan where "
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
		head_vector.addElement("维修开销");
		for(int i=1; i<=14; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("序号");
		head_vector.addElement("车牌号");
		head_vector.addElement("日期");
		head_vector.addElement("维修点");
		head_vector.addElement("维修项目");
		head_vector.addElement("维修金额");
		head_vector.addElement("备注");
		for(int i=1; i<=8; i++){
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
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=6; i++){
				rec_vector.addElement(rs.getString(i));
			}
			for(int i=1; i<=8; i++){
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
		tail_vector.addElement("合计：");
		weixiujineheji = (float)(Math.round(weixiujineheji*100))/100;
		tail_vector.addElement(weixiujineheji);
		for(int i=1; i<=9; i++){
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
		for(int i=1; i<=10; i++){
			head_vector.addElement(null);
		}
		head_vector.addElement("总　计：");
		head_vector.addElement("预期利润：");
		this.lirunYuqi = (float)(Math.round(this.lirunYuqi*100))/100;
		head_vector.addElement(this.lirunYuqi);
		head_vector.addElement("已得利润：");
		this.lirunYide = (float)(Math.round(this.lirunYide*100))/100;
		head_vector.addElement(this.lirunYide);
		
		vector.addElement(head_vector);
	}
	
	private void appendVectorForBlank(Vector vector){
		Vector head_vector=new Vector();
		for(int i=1; i<=15; i++){
			head_vector.addElement(null);
		}
		
		vector.addElement(head_vector);
	}
	
	
	/*
	 * detailtable
	 */
	public void generateYunDanVectorDetail(Vector vector) throws SQLException{
		String sql = inityundandetailsql();
		vector.removeAllElements();//初始化向量对象
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
			//从结果集中取数据放入向量rec_vector中
			rec_vector.addElement(count);
			count++;
//			for(int i=1; i<=9; i++){
//				rec_vector.addElement(rs.getString(i));
//			}
//			for(int i=1; i<=28; i++){
//				rec_vector.addElement(rs.getString(i));
//			}
			rec_vector.addElement(rs.getString("yundanbianhao"));
			rec_vector.addElement(rs.getString("chepaihao"));
			rec_vector.addElement(rs.getString("siji"));
			rec_vector.addElement(rs.getString("chucheriqi"));
			rec_vector.addElement(rs.getString("chuchechufadi"));
			rec_vector.addElement(rs.getString("chuchemudidi"));
			rec_vector.addElement(rs.getString("huicheriqi"));
			rec_vector.addElement(rs.getString("huichechufadi"));
			rec_vector.addElement(rs.getString("huichemudidi"));
			rec_vector.addElement(rs.getString("guolufei"));
			rec_vector.addElement(rs.getString("yuetongka"));
			rec_vector.addElement(rs.getString("gongzi"));
			rec_vector.addElement(rs.getString("yifugongzi"));
			rec_vector.addElement(rs.getString("chifan"));
			rec_vector.addElement(rs.getString("zhusu"));
			rec_vector.addElement(rs.getString("jiashui"));
			rec_vector.addElement(rs.getString("zuochefei"));
			rec_vector.addElement(rs.getString("cailiaofei"));
			rec_vector.addElement(rs.getString("tingchefei"));
			rec_vector.addElement(rs.getString("guobangfei"));
			rec_vector.addElement(rs.getString("zhuangchefei"));
			rec_vector.addElement(rs.getString("xiechefei"));
			rec_vector.addElement(rs.getString("luntai"));
			rec_vector.addElement(rs.getString("fakuan"));
			rec_vector.addElement(rs.getString("xiaofei"));
			rec_vector.addElement(rs.getString("xiulifei"));
			rec_vector.addElement(rs.getString("qitafeiyong"));
			rec_vector.addElement(rs.getString("beizhu"));
			
			
			String sql_chuchehuodan, sql_huichehuodan;
			sql_chuchehuodan = "select yingfujine, shifujine, shifouqingsuan from huowudan where "
					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '0%';";
			sql_huichehuodan = "select yingfujine, shifujine, shifouqingsuan from huowudan where "
					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
					+ " and huowubianhao like '1%';";
//			sql_chuchehuodan = "select riqi, chufadi, mudidi, huozhu, huoming, zhongliang, zhongliang2, jiage, baodijia, qitafeiyong, beizhu, shouxufei, yingfujine, shifujine, shifouqingsuan, jiezhangbeizhu from huowudan where "
//					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
//					+ " and huowubianhao like '0%';";
//			sql_huichehuodan = "select riqi, chufadi, mudidi, huozhu, huoming, zhongliang, zhongliang2, jiage, baodijia, qitafeiyong, beizhu, shouxufei, yingfujine, shifujine, shifouqingsuan, jiezhangbeizhu from huowudan where "
//					+ " yundanbianhao = " + "'" + rs.getString("yundanbianhao") + "'"
//					+ " and huowubianhao like '1%';";
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
			//重算总开销
			float youhao = 0;
			String yundanbianhao_next = YunDanBianHao.next(rs.getString("yundanbianhao"));
			if(yundanbianhao_next != null){
				String sql_yn = "select jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia from kaixiaodan where "
						+ "yundanbianhao = "
						+ "'" + yundanbianhao_next + "'"
						+ ";";
				ResultSet rs_yn = DBManager.getInstance().excuteQuery(sql_yn);
				if(rs_yn.next()){
					if(rs_yn.getString("jiayouzhanjiayou") != null && !rs_yn.getString("jiayouzhanjiayou").equals("")){
						youhao += rs_yn.getFloat("jiayouzhanjiayou");
					}
					if(rs_yn.getString("tingchechangjiayou") != null && !rs_yn.getString("tingchechangjiayou").equals("")){
						youhao += rs_yn.getFloat("tingchechangjiayou")*rs_yn.getFloat("tingchechangyoujia");
					}
				}
			}
			float zongkaixiao;
			zongkaixiao = rs.getFloat("zongkaixiao");
//			zongkaixiao += rs.getFloat("youhao");
			zongkaixiao += youhao;
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
			
			rec_vector.addElement(rs.getFloat("jiayouzhanjiayou"));
			rec_vector.addElement(rs.getFloat("tingchechangjiayou"));
			rec_vector.addElement(rs.getFloat("tingchechangyoujia"));
			zongkaixiao = (float)(Math.round(zongkaixiao*100))/100;
			rec_vector.addElement(zongkaixiao);
			chucheyijiezhangjine = (float)(Math.round(chucheyijiezhangjine*100))/100;
			huicheyijiezhangjine = (float)(Math.round(huicheyijiezhangjine*100))/100;
			chucheweijiezhangjine = (float)(Math.round(chucheweijiezhangjine*100))/100;
			huicheweijiezhangjine = (float)(Math.round(huicheweijiezhangjine*100))/100;
			rec_vector.addElement(chucheyijiezhangjine);
			rec_vector.addElement(huicheyijiezhangjine);
			rec_vector.addElement(chucheweijiezhangjine);
			rec_vector.addElement(huicheweijiezhangjine);
			rec_vector.addElement(chucheyijiezhangjine+chucheweijiezhangjine);
			rec_vector.addElement(huicheyijiezhangjine+huicheweijiezhangjine);
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
			
			vector.addElement(rec_vector);//向量rec_vector加入向量vect中 
		}
		
		//合计行
		Vector rec_vector=new Vector();
		for(int i=1; i<=31; i++){
			rec_vector.addElement(null);
		}
		rec_vector.addElement("合计：");
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		yijiezhangzonge = (float)(Math.round(yijiezhangzonge*100))/100;
		weijiezhangzonge = (float)(Math.round(weijiezhangzonge*100))/100;
		yuqizonglirun = (float)(Math.round(yuqizonglirun*100))/100;
		yidezonglirun = (float)(Math.round(yidezonglirun*100))/100;
		rec_vector.addElement(zongkaixiaoheji);
		for(int i=1; i<=6; i++){
			rec_vector.addElement(null);
		}
		rec_vector.addElement(yijiezhangzonge);
		rec_vector.addElement(weijiezhangzonge);
		rec_vector.addElement(yuqizonglirun);
		rec_vector.addElement(yidezonglirun);
		vector.addElement(rec_vector);
		
		this.lirunYuqiDetail += yuqizonglirun;
		this.lirunYideDetail += yidezonglirun;
	}
	
	private String inityundandetailsql(){
		String sql;
//		sql = "select yundanbianhao, chepaihao, chucheriqi, chuchechufadi, chuchemudidi, huicheriqi, huichechufadi, huichemudidi, zongkaixiao, youhao, jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia from kaixiaodan where "
//				+ "chucheriqi >= " + "'" + this.lirunPanel.riqi_start.getDate() + "'" 
//				+ " and " + "chucheriqi <=" + "'" + this.lirunPanel.riqi_end.getMonthEndDate() + "'";
		sql = "select yundanbianhao, chepaihao, siji, "
				+ "chucheriqi, chuchechufadi, chuchemudidi,"
				+ " huicheriqi, huichechufadi, huichemudidi,"
				+ " jiayouzhanjiayou, tingchechangjiayou, tingchechangyoujia,"
				+ " guolufei, yuetongka, gongzi, yifugongzi,"
				+ " chifan, zhusu, jiashui, zuochefei,"
				+ " cailiaofei, tingchefei, guobangfei,"
				+ " zhuangchefei, xiechefei, luntai,"
				+ " fakuan, xiaofei, xiulifei,"
				+ " qitafeiyong, beizhu,"
				+ " zongkaixiao"
				+ " from kaixiaodan where "
				+ "chucheriqi >= " + "'" + this.lirunPanel.riqi_start.getDate() + "'" 
				+ " and " + "chucheriqi <=" + "'" + this.lirunPanel.riqi_end.getMonthEndDate() + "'";
		if(!this.lirunPanel.chepaihao.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.lirunPanel.chepaihao.getText().trim() + "%'";
		}
		sql = sql + " order by chucheriqi asc;";
		return sql;
	}
	
	private void appendVectorForYuekaixiaoDetail(Vector vector) throws SQLException{
		
		Vector head_vector=new Vector();
		head_vector.addElement("月开销");
		for(int i=1; i<=42; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("序号");
		head_vector.addElement("车牌号");
		head_vector.addElement("日期");
		head_vector.addElement("粤通卡");
		head_vector.addElement("电话费");
		head_vector.addElement("其他费用");
		head_vector.addElement("备注");
		head_vector.addElement("总开销");
		for(int i=1; i<=35; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = inityuekaixiaodetailsql();
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float yuetongkaheji, dianhuafeiheji, qitafeiyongheji, zongkaixiaoheji;
		yuetongkaheji = 0;
		dianhuafeiheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=7; i++){
				rec_vector.addElement(rs.getString(i));
			}
			for(int i=1; i<=35; i++){
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
		tail_vector.addElement("合计：");
		
		yuetongkaheji = (float)(Math.round(yuetongkaheji*100))/100;
		dianhuafeiheji = (float)(Math.round(dianhuafeiheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		tail_vector.addElement(yuetongkaheji);
		tail_vector.addElement(dianhuafeiheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		tail_vector.addElement(zongkaixiaoheji);
		for(int i=1; i<=35; i++){
			tail_vector.addElement(null);
		}
		vector.addElement(tail_vector);
		
		this.lirunYuqiDetail -= zongkaixiaoheji;
		this.lirunYideDetail -= zongkaixiaoheji;
	}
	
	private String inityuekaixiaodetailsql(){
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
	
	private void appendVectorForNiankaixiaoDetail(Vector vector) throws SQLException{
		
		Vector head_vector=new Vector();
		head_vector.addElement("年开销");
		for(int i=1; i<=42; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("序号");
		head_vector.addElement("车牌号");
		head_vector.addElement("日期");
		head_vector.addElement("审车");
		head_vector.addElement("车险");
		head_vector.addElement("个人险");
		head_vector.addElement("审营运证");
		head_vector.addElement("GPRS");
		head_vector.addElement("其他费用");
		head_vector.addElement("备注");
		head_vector.addElement("总开销");
		for(int i=1; i<=32; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = initniankaixiaodetailsql();
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float shencheheji, baoxianheji, gerenxianheji,shenyingyunzhengheji, gprsheji, qitafeiyongheji, zongkaixiaoheji;
		shencheheji = 0;
		baoxianheji = 0;
		gerenxianheji = 0;
		shenyingyunzhengheji = 0;
		gprsheji = 0;
		qitafeiyongheji = 0;
		zongkaixiaoheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
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
			rec_vector.addElement(rs.getString("shenyingyunzheng"));
			rec_vector.addElement(rs.getString("gprs"));
			rec_vector.addElement(rs.getString("qitafeiyong"));
			rec_vector.addElement(rs.getString("beizhu"));
			rec_vector.addElement(rs.getString("zongkaixiao"));
			for(int i=1; i<=32; i++){
				rec_vector.addElement(null);
			}
			shencheheji += rs.getFloat("shenche");
			baoxianheji += rs.getFloat("baoxian");
			gerenxianheji += rs.getFloat("gerenxian");
			shenyingyunzhengheji += rs.getFloat("shenyingyunzheng");
			gprsheji += rs.getFloat("gprs");
			qitafeiyongheji += rs.getFloat("qitafeiyong");
			zongkaixiaoheji += rs.getFloat("zongkaixiao");
			
			vector.addElement(rec_vector);
		}
		
		Vector tail_vector=new Vector();
		tail_vector.addElement(null);
		tail_vector.addElement(null);
		tail_vector.addElement("合计：");
		shencheheji = (float)(Math.round(shencheheji*100))/100;
		baoxianheji = (float)(Math.round(baoxianheji*100))/100;
		gerenxianheji = (float)(Math.round(gerenxianheji*100))/100;
		shenyingyunzhengheji = (float)(Math.round(shenyingyunzhengheji*100))/100;
		gprsheji = (float)(Math.round(gprsheji*100))/100;
		qitafeiyongheji = (float)(Math.round(qitafeiyongheji*100))/100;
		tail_vector.addElement(shencheheji);
		tail_vector.addElement(baoxianheji);
		tail_vector.addElement(gerenxianheji);
		tail_vector.addElement(shenyingyunzhengheji);
		tail_vector.addElement(gprsheji);
		tail_vector.addElement(qitafeiyongheji);
		tail_vector.addElement(null);
		zongkaixiaoheji = (float)(Math.round(zongkaixiaoheji*100))/100;
		tail_vector.addElement(zongkaixiaoheji);
		for(int i=1; i<=32; i++){
			tail_vector.addElement(null);
		}
		vector.addElement(tail_vector);
		
		this.lirunYuqiDetail -= zongkaixiaoheji;
		this.lirunYideDetail -= zongkaixiaoheji;
	}
	
	private String initniankaixiaodetailsql(){
		String sql;
		sql = "select chepaihao, DATE_FORMAT(riqi ,'%Y'), shenche, baoxian, gerenxian, shenyingyunzheng, gprs, qitafeiyong, beizhu, zongkaixiao from niankaixiaodan where "
				+ "riqi >= " + "'" + this.lirunPanel.riqi_start.getYear() + "-01-01" + "'" 
				+ " and " + "riqi <=" + "'" + this.lirunPanel.riqi_end.getYear() + "-12-31" + "'";
		if(!this.lirunPanel.chepaihao.getText().trim().equals("")){
			sql = sql + " and " + "chepaihao like " + "'%" + this.lirunPanel.chepaihao.getText().trim() + "%'";
		}
		sql = sql + " order by riqi asc;";
		return sql;
	}
	
	private void appendVectorForWeixiukaixiaoDetail(Vector vector) throws SQLException{
		
		Vector head_vector=new Vector();
		head_vector.addElement("维修开销");
		for(int i=1; i<=42; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		head_vector = new Vector();
		head_vector.addElement("序号");
		head_vector.addElement("车牌号");
		head_vector.addElement("日期");
		head_vector.addElement("维修点");
		head_vector.addElement("维修项目");
		head_vector.addElement("维修金额");
		head_vector.addElement("备注");
		for(int i=1; i<=36; i++){
			head_vector.addElement(null);
		}
		vector.addElement(head_vector);
		
		String sql = initweixiukaixiaodetailsql();
		ResultSet rs = DBManager.getInstance().excuteQuery(sql);
		int count = 1;
		float weixiujineheji;
		weixiujineheji = 0;
		while(rs.next()){ 
			Vector rec_vector=new Vector(); 
			//从结果集中取数据放入向量rec_vector中 
			rec_vector.addElement(count);
			count++;
			for(int i=1; i<=6; i++){
				rec_vector.addElement(rs.getString(i));
			}
			for(int i=1; i<=36; i++){
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
		tail_vector.addElement("合计：");
		weixiujineheji = (float)(Math.round(weixiujineheji*100))/100;
		tail_vector.addElement(weixiujineheji);
		for(int i=1; i<=37; i++){
			tail_vector.addElement(null);
		}
		vector.addElement(tail_vector);
		
		this.lirunYuqiDetail -= weixiujineheji;
		this.lirunYideDetail -= weixiujineheji;
	}
	
	private String initweixiukaixiaodetailsql(){
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
	
	private void appendVectorForHejiDetail(Vector vector){
		Vector head_vector=new Vector();
		for(int i=1; i<=38; i++){
			head_vector.addElement(null);
		}
		head_vector.addElement("总　计：");
		head_vector.addElement("预期利润：");
		this.lirunYuqiDetail = (float)(Math.round(this.lirunYuqiDetail*100))/100;
		head_vector.addElement(this.lirunYuqiDetail);
		head_vector.addElement("已得利润：");
		this.lirunYideDetail = (float)(Math.round(this.lirunYideDetail*100))/100;
		head_vector.addElement(this.lirunYideDetail);
		
		vector.addElement(head_vector);
	}
	
	private void appendVectorForBlankDetail(Vector vector){
		Vector head_vector=new Vector();
		for(int i=1; i<=43; i++){
			head_vector.addElement(null);
		}
		
		vector.addElement(head_vector);
	}
}
