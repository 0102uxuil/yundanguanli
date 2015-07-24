package wuliuxitong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	//Database connection object
	private static Connection dbConnection = null;//不能写成static因为dbConnection在多数据库情况下可能会不同，写成static之后就不再是线程安全的。但是单数据库不存在线程不安全
	//Statement and resultset
	private PreparedStatement preStatement = null; 
	private ResultSet resultSet = null;
	
	private static String driverVersion = "mysql";
	//User and password
	private static String databaseUser = "root";
	private static String databasePwd = "aa112233"; //real host
//	private static String databasePwd = "aa778899"; //local host
	
	private static final String DRIVER_CLASS_MYSQL_5_6 = "com.mysql.jdbc.Driver";
	
//	private static final String DATABASE_URL_MYSQL_5_6 = "jdbc:mysql://192.168.1.199:3306/yundanguanli";
	private static final String DATABASE_URL_MYSQL_5_6 = "jdbc:mysql://localhost:3306/yundanguanli";

	private static DBManager connectionManager = null;
	
	//私有化默认构造 作用于单例模式的应用，防止类被直接使用new关键字实例化
	private DBManager() {
		super();
	} 
	//单例模式构造函数
	
	public static DBManager getInstance(){
		if(connectionManager == null){
			connectionManager = new DBManager();//不把整个类都写成static的，在保证数据库管理类的静态性下，保持链接dbConnection等的多态性
		}
		
		return connectionManager;
	}
	
//	public static DBManager getInstance(
//			String version, 
//			String user, 
//			String password)
//			throws Exception{
//		if(!(version == "mysql")){
//			throw new Exception("数据库驱动不正确，只能是mysql!");
//		}
//		driverVersion = version;
//		if(user == null||user.equals("")){
//			throw new Exception("数据库登录用户名不能为空！");
//		}
//		databaseUser = user;
//		databasePwd = password;
//		
//		if(connectionManager == null){
//			connectionManager = new DBManager();//不把整个类都写成static的，在保证数据库管理类的静态性下，保持链接dbConnection等的多态性
//		}
//		
//		return connectionManager;
//	}
	//连接数据库
	public Connection getConnection() throws SQLException{
		try{
			Class.forName(DRIVER_CLASS_MYSQL_5_6);//forname创建的类继承了DriverManager接口，后面可以用调用DriverManager接口里的静态函数找到创建类的地址
			if(DBManager.dbConnection == null){
				DBManager.dbConnection = DriverManager.getConnection(DATABASE_URL_MYSQL_5_6, databaseUser, databasePwd);
			}
		} catch (ClassNotFoundException ex){
			System.err.println("未找到" + driverVersion + "数据库驱动类：" + ex.getMessage());
		}
//		} catch (Exception e){
//			System.err.println("获得数据库连接错误：" + e.getMessage());
//		}
		return DBManager.dbConnection;
	}
	//获得sql
	private PreparedStatement getPreparedStatement(String sql) throws SQLException{
//		try{
			this.preStatement = this.getConnection().prepareStatement(sql);
//		} catch (Exception e){
//			System.err.println("获取数据库命令执行对象错误：" + e.getMessage());
//		}
		return this.preStatement;
	}
	//执行sql更新语句(Insert|Update|Delete)
	public int excuteUpdate(String sql) throws SQLException{
//		try{
			this.resultSet = null;
			return this.getPreparedStatement(sql).executeUpdate();
//		} catch(SQLException e){
//			System.err.println("更新数据错误：" + e.getMessage()); 
//			return 0;
//		}
	}
	//执行sql查询语句(Select)
	public ResultSet excuteQuery(String sql) throws SQLException{
//		try{
			this.resultSet = null;
			this.resultSet = this.getPreparedStatement(sql).executeQuery();
//		} catch(SQLException e){
//			System.err.println("查询数据错误：" + e.getMessage()); 
//		}
		return this.resultSet;
	}
	//获得查询结果的条数
	public int getResultSetCount(String sql) throws SQLException{
		int count = 0;
//		try{
			this.resultSet = null;
			this.resultSet = this.getPreparedStatement(sql).executeQuery();
			while(this.resultSet.next()){
				count++;
			}
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
		return count;
	}
	//关闭数据库连接资源(包括结果集对象、命令执行对象、连接对象) 
	public void closeDBResource(){
		try{
			closeResultSet();
			closePreparedStatement();
			closeConnection();
		} catch(SQLException e){
			System.err.println(e.getMessage()); 
		}
	}
	//关闭结果集对象
	private void closeResultSet() throws SQLException{
		try{
			if(this.resultSet != null){
				this.resultSet.close();
				this.resultSet = null;
			}
		} catch(SQLException e){
			throw new SQLException("关闭结果集对象错误：" + e.getMessage());
		}
	}
	//关闭命令执行对象
	private void closePreparedStatement() throws SQLException{
		try{
			if(this.preStatement != null){
				this.preStatement.close();
				this.preStatement = null;
			}
		} catch(SQLException e){
			throw new SQLException("关闭命令执行对象错误：" + e.getMessage());
		}
	}
	//关闭连接对象
	private void closeConnection() throws SQLException{
		try{
			if(DBManager.dbConnection != null && !DBManager.dbConnection.isClosed()){
				DBManager.dbConnection.close();
				DBManager.dbConnection = null;
			}
		} catch(SQLException e){
			throw new SQLException("关闭连接对象错误：" + e.getMessage());
		}
	}
}
