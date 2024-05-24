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
		//����һ���������ָ�򱾵�
		OutputStream output = new FileOutputStream("d:\\temp\\x.tar.gz");
		
		//ָ��NameNode�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		//�õ�һ��������
		InputStream input = client.open(new Path("/folder1/b.tag.gz"));
		
		//����һ��������
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
		//����һ���������ָ�򱾵�
		OutputStream output = new FileOutputStream("d:\\temp\\y.tar.gz");
		
		//ָ��NameNode�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		//�õ�һ��������
		InputStream input = client.open(new Path("/folder1/b.tag.gz"));
		
		//ʹ�ù�����򻯳���
		IOUtils.copyBytes(input, output, 1024);
	}
}



















