package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * �����ࣺ
 * 1����ȡHive�����ݿ�����
 * 2���ͷ����ݿ���Դ��Connection��Statement��ResultSet
 */
public class JDBCUtils {
	//Hive������
	private static String driver = "org.apache.hive.jdbc.HiveDriver";
	
	//Hive��URL
	private static String url = "jdbc:hive2://192.168.157.111:10000/default";
	
	//ע������
	static {
		try {
			Class.forName(driver);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//��ȡ����
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//�ͷ���Դ
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

























