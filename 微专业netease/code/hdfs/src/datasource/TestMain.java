package datasource;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// 创建一个自己的数据库的连接池
		MyDataSource pool = new MyDataSource();
		
		for(int i=0;i<11;i++) {
			Connection conn = pool.getConnection();
			System.out.println("获取的连接是:" + conn);
			
			//释放连接
			conn.close();
		}
	}

}
