package demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/*
 * 错误：
 * org.apache.hadoop.security.AccessControlException: 
	Permission denied: user=lenovo, access=WRITE, inode="/folder1":root:supergroup:drwxr-xr-x
	
解决方案：
1、设置执行的用户是root用户，环境变量 ：HADOOP_USER_NAME
2、可以使用Java的-D参数来设置环境变量HADOOP_USER_NAME
3、使用chmod的命令: hdfs dfs -chmod 777 /folder2
4、修改参数配置：
 */
public class DemoMkDir {

	@Test
	public void test1() throws Exception{
		//指定NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建一个HDFS的客户端 DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//创建目录
		client.mkdirs(new Path("/a123"));
		
		//关闭客户端
		client.close();
	}
	
	@Test
	public void test2() throws Exception{
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//指定NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建一个HDFS的客户端 DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//创建目录
		client.mkdirs(new Path("/folder1"));
		
		//关闭客户端
		client.close();
	}
	
	@Test
	public void test3() throws Exception{
		//指定NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建一个HDFS的客户端 DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//创建目录
		client.mkdirs(new Path("/folder2"));
		
		//关闭客户端
		client.close();
	}
	
	@Test
	public void test4() throws Exception{
		//指定NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建一个HDFS的客户端 DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//创建目录
		client.mkdirs(new Path("/folder2/folder3"));
		
		//关闭客户端
		client.close();
	}
}





















