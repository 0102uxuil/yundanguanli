package wuliuxitong;

import java.sql.ResultSet;
import java.sql.SQLException;

public class YunDanBianHao {
	public static String previous(String yundanbianhao){
		if(yundanbianhao.length() <= 10){
			return null;
		}
		String year, month, day, chepaihao, tangshu;
		year = yundanbianhao.substring(0, 4);
		month = yundanbianhao.substring(4, 6);
		day = yundanbianhao.substring(6, 8);
		chepaihao = yundanbianhao.substring(8, yundanbianhao.length()-2);
		tangshu = yundanbianhao.substring(yundanbianhao.length()-2, yundanbianhao.length());
		if(tangshu.equals("01")){
			String yundanbianhao4sql, year4sql, month4sql, day4sql, chepaihao4sql, tangshu4sql;
			year4sql = year;
			month4sql = month;
			day4sql = day;
			chepaihao4sql = chepaihao;
			tangshu4sql = tangshu;
			if(month.equals("01")){
				month4sql = "12";
				year4sql = String.format("%04d", Integer.parseInt(year)-1);
			} else {
				month4sql = String.format("%02d", Integer.parseInt(month)-1);
			}
			day4sql = "__";
			tangshu4sql = "__";
			yundanbianhao4sql = year4sql + month4sql + day4sql + chepaihao4sql + tangshu4sql;
			String sql;
			sql = "select yundanbianhao from kaixiaodan "
					+ " where yundanbianhao like "
					+ " '" + yundanbianhao4sql + "'"
					+ " order by yundanbianhao desc "
					+ ";";
			try {
				ResultSet rs = DBManager.getInstance().excuteQuery(sql);
				if(rs.next()){
					return rs.getString("yundanbianhao");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String yundanbianhao4sql, year4sql, month4sql, day4sql, chepaihao4sql, tangshu4sql;
			year4sql = year;
			month4sql = month;
			day4sql = day;
			chepaihao4sql = chepaihao;
			tangshu4sql = tangshu;
			day4sql = "__";
			tangshu4sql = String.format("%02d", Integer.parseInt(tangshu)-1);
			yundanbianhao4sql = year4sql + month4sql + day4sql + chepaihao4sql + tangshu4sql;
			String sql;
			sql = "select yundanbianhao from kaixiaodan "
					+ " where yundanbianhao like "
					+ " '" + yundanbianhao4sql + "'"
					+ " order by yundanbianhao desc "
					+ ";";
			try {
				ResultSet rs = DBManager.getInstance().excuteQuery(sql);
				if(rs.next()){
					return rs.getString("yundanbianhao");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null; 
	}
	
	public static String next(String yundanbianhao){
		if(yundanbianhao.length() <= 10){
			return null;
		}
		String year, month, day, chepaihao, tangshu;
		year = yundanbianhao.substring(0, 4);
		month = yundanbianhao.substring(4, 6);
		day = yundanbianhao.substring(6, 8);
		chepaihao = yundanbianhao.substring(8, yundanbianhao.length()-2);
		tangshu = yundanbianhao.substring(yundanbianhao.length()-2, yundanbianhao.length());
		
		String yundanbianhao4sql, year4sql, month4sql, day4sql, chepaihao4sql, tangshu4sql;
		year4sql = year;
		month4sql = month;
		day4sql = day;
		chepaihao4sql = chepaihao;
		tangshu4sql = tangshu;
		
		day4sql = "__";
		tangshu4sql = String.format("%02d", Integer.parseInt(tangshu)+1);
		yundanbianhao4sql = year4sql + month4sql + day4sql + chepaihao4sql + tangshu4sql;
		String sql;
		sql = "select yundanbianhao from kaixiaodan "
				+ " where yundanbianhao like "
				+ " '" + yundanbianhao4sql + "'"
				+ " order by yundanbianhao desc "
				+ ";";
		try {
			ResultSet rs = DBManager.getInstance().excuteQuery(sql);
			if(rs.next()){
				return rs.getString("yundanbianhao");
			} else {
				year4sql = year;
				month4sql = month;
				day4sql = day;
				chepaihao4sql = chepaihao;
				tangshu4sql = tangshu;
				
				if(month.equals("12")){
					year4sql = String.format("%04d", Integer.parseInt(year)+1);
					month4sql = "01";
					day4sql = "__";
					tangshu4sql = "__";
					yundanbianhao4sql = year4sql + month4sql + day4sql + chepaihao4sql + tangshu4sql;
					sql = "select yundanbianhao from kaixiaodan "
							+ " where yundanbianhao like "
							+ " '" + yundanbianhao4sql + "'"
							+ " order by yundanbianhao asc "
							+ ";";
					try {
						rs = DBManager.getInstance().excuteQuery(sql);
						if(rs.next()){
							return rs.getString("yundanbianhao");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					month4sql = String.format("%02d", Integer.parseInt(month)+1);
					day4sql = "__";
					tangshu4sql = "01";
					yundanbianhao4sql = year4sql + month4sql + day4sql + chepaihao4sql + tangshu4sql;
					
					sql = "select yundanbianhao from kaixiaodan "
							+ " where yundanbianhao like "
							+ " '" + yundanbianhao4sql + "'"
							+ " order by yundanbianhao asc "
							+ ";";
					try {
						rs = DBManager.getInstance().excuteQuery(sql);
						if(rs.next()){
							return rs.getString("yundanbianhao");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
