package datasource;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// ����һ���Լ������ݿ�����ӳ�
		MyDataSource pool = new MyDataSource();
		
		for(int i=0;i<11;i++) {
			Connection conn = pool.getConnection();
			System.out.println("��ȡ��������:" + conn);
			
			//�ͷ�����
			conn.close();
		}
	}

}
