package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.NameNodeProxies;
import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class TestUpload {

	@Test
	public void test1() throws Exception {
		//构造一个输入流，代表要上传的数据
		InputStream input = new FileInputStream("d:\\temp\\hadoop-2.7.3.tar.gz");
				
		//NameNodeProxies
		//NameNode
		
		//指定NameNode 地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建HDFS的客户端 DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//构造一个输出流，指向HDFS
		OutputStream output = client.create(new Path("/folder1/a.tag.gz"));
		
		//缓冲区
		byte[] buffer = new byte[1024];
		//长度
		int len = 0;
		while( (len = input.read(buffer)) > 0 ) {
			//写到输出流
			output.write(buffer, 0, len);
		}
		
		output.flush();
		output.close();
		input.close();
	}
	
	@Test
	public void test2() throws Exception {
		//构造一个输入流，代表要上传的数据
		InputStream input = new FileInputStream("d:\\temp\\hadoop-2.7.3.tar.gz");
		
		//指定NameNode 地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		
		//构造一个输出流，指向HDFS
		OutputStream output = client.create(new Path("/folder1/b.tag.gz"));
		
		//使用工具类简化
		IOUtils.copyBytes(input, output, 1024);
	}
}


















