package wuliuxitong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	//Database connection object
	private static Connection dbConnection = null;//����д��static��ΪdbConnection�ڶ����ݿ�����¿��ܻ᲻ͬ��д��static֮��Ͳ������̰߳�ȫ�ġ����ǵ����ݿⲻ�����̲߳���ȫ
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
	
	//˽�л�Ĭ�Ϲ��� �����ڵ���ģʽ��Ӧ�ã���ֹ�౻ֱ��ʹ��new�ؼ���ʵ����
	private DBManager() {
		super();
	} 
	//����ģʽ���캯��
	
	public static DBManager getInstance(){
		if(connectionManager == null){
			connectionManager = new DBManager();//���������඼д��static�ģ��ڱ�֤���ݿ������ľ�̬���£���������dbConnection�ȵĶ�̬��
		}
		
		return connectionManager;
	}
	
//	public static DBManager getInstance(
//			String version, 
//			String user, 
//			String password)
//			throws Exception{
//		if(!(version == "mysql")){
//			throw new Exception("���ݿ���������ȷ��ֻ����mysql!");
//		}
//		driverVersion = version;
//		if(user == null||user.equals("")){
//			throw new Exception("���ݿ��¼�û�������Ϊ�գ�");
//		}
//		databaseUser = user;
//		databasePwd = password;
//		
//		if(connectionManager == null){
//			connectionManager = new DBManager();//���������඼д��static�ģ��ڱ�֤���ݿ������ľ�̬���£���������dbConnection�ȵĶ�̬��
//		}
//		
//		return connectionManager;
//	}
	//�������ݿ�
	public Connection getConnection() throws SQLException{
		try{
			Class.forName(DRIVER_CLASS_MYSQL_5_6);//forname��������̳���DriverManager�ӿڣ���������õ���DriverManager�ӿ���ľ�̬�����ҵ�������ĵ�ַ
			if(DBManager.dbConnection == null){
				DBManager.dbConnection = DriverManager.getConnection(DATABASE_URL_MYSQL_5_6, databaseUser, databasePwd);
			}
		} catch (ClassNotFoundException ex){
			System.err.println("δ�ҵ�" + driverVersion + "���ݿ������ࣺ" + ex.getMessage());
		}
//		} catch (Exception e){
//			System.err.println("������ݿ����Ӵ���" + e.getMessage());
//		}
		return DBManager.dbConnection;
	}
	//���sql
	private PreparedStatement getPreparedStatement(String sql) throws SQLException{
//		try{
			this.preStatement = this.getConnection().prepareStatement(sql);
//		} catch (Exception e){
//			System.err.println("��ȡ���ݿ�����ִ�ж������" + e.getMessage());
//		}
		return this.preStatement;
	}
	//ִ��sql�������(Insert|Update|Delete)
	public int excuteUpdate(String sql) throws SQLException{
//		try{
			this.resultSet = null;
			return this.getPreparedStatement(sql).executeUpdate();
//		} catch(SQLException e){
//			System.err.println("�������ݴ���" + e.getMessage()); 
//			return 0;
//		}
	}
	//ִ��sql��ѯ���(Select)
	public ResultSet excuteQuery(String sql) throws SQLException{
//		try{
			this.resultSet = null;
			this.resultSet = this.getPreparedStatement(sql).executeQuery();
//		} catch(SQLException e){
//			System.err.println("��ѯ���ݴ���" + e.getMessage()); 
//		}
		return this.resultSet;
	}
	//��ò�ѯ���������
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
	//�ر����ݿ�������Դ(�����������������ִ�ж������Ӷ���) 
	public void closeDBResource(){
		try{
			closeResultSet();
			closePreparedStatement();
			closeConnection();
		} catch(SQLException e){
			System.err.println(e.getMessage()); 
		}
	}
	//�رս��������
	private void closeResultSet() throws SQLException{
		try{
			if(this.resultSet != null){
				this.resultSet.close();
				this.resultSet = null;
			}
		} catch(SQLException e){
			throw new SQLException("�رս�����������" + e.getMessage());
		}
	}
	//�ر�����ִ�ж���
	private void closePreparedStatement() throws SQLException{
		try{
			if(this.preStatement != null){
				this.preStatement.close();
				this.preStatement = null;
			}
		} catch(SQLException e){
			throw new SQLException("�ر�����ִ�ж������" + e.getMessage());
		}
	}
	//�ر����Ӷ���
	private void closeConnection() throws SQLException{
		try{
			if(DBManager.dbConnection != null && !DBManager.dbConnection.isClosed()){
				DBManager.dbConnection.close();
				DBManager.dbConnection = null;
			}
		} catch(SQLException e){
			throw new SQLException("�ر����Ӷ������" + e.getMessage());
		}
	}
}
