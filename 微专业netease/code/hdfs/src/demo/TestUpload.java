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
		//����һ��������������Ҫ�ϴ�������
		InputStream input = new FileInputStream("d:\\temp\\hadoop-2.7.3.tar.gz");
				
		//NameNodeProxies
		//NameNode
		
		//ָ��NameNode ��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����HDFS�Ŀͻ��� DistributedFileSystem
		FileSystem client = FileSystem.get(conf);
		
		//����һ���������ָ��HDFS
		OutputStream output = client.create(new Path("/folder1/a.tag.gz"));
		
		//������
		byte[] buffer = new byte[1024];
		//����
		int len = 0;
		while( (len = input.read(buffer)) > 0 ) {
			//д�������
			output.write(buffer, 0, len);
		}
		
		output.flush();
		output.close();
		input.close();
	}
	
	@Test
	public void test2() throws Exception {
		//����һ��������������Ҫ�ϴ�������
		InputStream input = new FileInputStream("d:\\temp\\hadoop-2.7.3.tar.gz");
		
		//ָ��NameNode ��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		
		//����һ���������ָ��HDFS
		OutputStream output = client.create(new Path("/folder1/b.tag.gz"));
		
		//ʹ�ù������
		IOUtils.copyBytes(input, output, 1024);
	}
}


















