package demo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class TestDownload {

	@Test
	public void test1() throws Exception{
		//构造一个输出流，指向本地
		OutputStream output = new FileOutputStream("d:\\temp\\x.tar.gz");
		
		//指定NameNode的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		//得到一个输入流
		InputStream input = client.open(new Path("/folder1/b.tag.gz"));
		
		//构造一个缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=input.read(buffer)) > 0) {
			output.write(buffer, 0, len);
		}
		
		output.flush();
		output.close();
		input.close();
	}
	
	@Test
	public void test2() throws Exception{
		//构造一个输出流，指向本地
		OutputStream output = new FileOutputStream("d:\\temp\\y.tar.gz");
		
		//指定NameNode的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//创建HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		//得到一个输入流
		InputStream input = client.open(new Path("/folder1/b.tag.gz"));
		
		//使用工具类简化程序
		IOUtils.copyBytes(input, output, 1024);
	}
}



















