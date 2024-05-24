package datasource;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSource implements DataSource {
	//��ʼ�������ӳ�
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://192.168.157.21:3306/hive";
	private static String user = "hiveowner";
	private static String password = "Welcome_1";
	
	//��ʼ����ʱ�򣬷���10������
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	static {
		try {
			//ע������
			Class.forName(driver);
			for(int i=0;i<10;i++) {
				//�����Ķ���
				Connection conn = DriverManager.getConnection(url, user, password);
				pool.add(conn);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public Connection getConnection() throws SQLException {
		// �Ӹ����ӳ��л�ȡһ������
//		if(pool.size() > 0) {
//			//�����ӣ�ֱ�ӷ���
//			return pool.removeFirst();
//		}else {
//			throw new SQLException("ϵͳæ�����Ժ�....");
//		}
		if(pool.size() > 0) {
			Connection conn = pool.removeFirst(); //�����Ķ���
			
			//����Connection�Ĵ������
			return (Connection)Proxy.newProxyInstance(MyDataSource.class.getClassLoader(),
					              conn.getClass().getInterfaces(),
					              new InvocationHandler() {
									
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// ��дclose����
					if(method.getName().equals("close")) {
						System.out.println("�����ӻ���");
						pool.add(conn);
						return null;
					}else {
						//��������
						return method.invoke(conn, args);
					}
				}
			});
		}else {
			throw new SQLException("ϵͳæ�����Ժ�....");
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
