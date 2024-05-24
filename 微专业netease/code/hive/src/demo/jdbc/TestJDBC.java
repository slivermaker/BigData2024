package demo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) {
		String sql = "select * from emp1";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//获取连接
			conn = JDBCUtils.getConnection();
			//创建SQL的执行环境
			st = conn.createStatement();
			//执行SQL
			rs = st.executeQuery(sql);
			while(rs.next()) {
				//姓名  薪水
				String name = rs.getString("ename");
				double sal = rs.getDouble("sal");
				System.out.println(name+"\t"+sal);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(conn, st, rs);
		}
	}
}















