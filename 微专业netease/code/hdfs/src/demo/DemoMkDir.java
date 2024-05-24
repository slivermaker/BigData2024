package demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/*
 * ����
 * org.apache.hadoop.security.AccessControlException: 
	Permission denied: user=lenovo, access=WRITE, inode="/folder1":root:supergroup:drwxr-xr-x
	
���������
1������ִ�е��û���root�û����������� ��HADOOP_USER_NAME
2������ʹ��Java��-D���������û�������HADOOP_USER_NAME
3��ʹ��chmod������: hdfs dfs -chmod 777 /folder2
4���޸Ĳ������ã�
 */
public class DemoMkDir {

	@Test
	public void test1() throws Exception{
		//ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ��HDFS�Ŀͻ��� DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/a123"));
		
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void test2() throws Exception{
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ��HDFS�Ŀͻ��� DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/folder1"));
		
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void test3() throws Exception{
		//ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ��HDFS�Ŀͻ��� DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/folder2"));
		
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void test4() throws Exception{
		//ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ��HDFS�Ŀͻ��� DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/folder2/folder3"));
		
		//�رտͻ���
		client.close();
	}
}





















