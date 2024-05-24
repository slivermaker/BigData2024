package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 工具类：
 * 1、获取Hive的数据块连接
 * 2、释放数据库资源：Connection、Statement、ResultSet
 */
public class JDBCUtils {
	//Hive的驱动
	private static String driver = "org.apache.hive.jdbc.HiveDriver";
	
	//Hive的URL
	private static String url = "jdbc:hive2://192.168.157.111:10000/default";
	
	//注册驱动
	static {
		try {
			Class.forName(driver);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//获取连接
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//释放资源
	public static void release(Connection conn,Statement st,ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				rs = null;
			}
		}
		
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				st = null;
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
	}
}

























