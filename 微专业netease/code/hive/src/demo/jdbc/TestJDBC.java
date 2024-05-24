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
			//��ȡ����
			conn = JDBCUtils.getConnection();
			//����SQL��ִ�л���
			st = conn.createStatement();
			//ִ��SQL
			rs = st.executeQuery(sql);
			while(rs.next()) {
				//����  нˮ
				String name = rs.getString("ename");
				double sal = rs.getDouble("sal");
				System.out.println(name+"\t"+sal);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(conn, st, rs);
		}
	}
}















