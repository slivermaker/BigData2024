package demo;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.junit.Test;

public class TestHDFS {
	//����ĳ���ļ������ݿ���HDFS��DataNodeλ��
	@Test
	public void test1() throws Exception{
		//ָ��NameNode ��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		FileSystem client = FileSystem.get(conf);
		
		//�õ����ļ���״̬
		FileStatus status = client.getFileStatus(new Path("/folder1/a.tag.gz"));
		
		//��ȡ���ļ������ݿ���Ϣ
		BlockLocation[] list = client.getFileBlockLocations(status, 0, status.getLen());
		for(BlockLocation blk:list) {
			System.out.println("���ݿ飺������" + Arrays.toString(blk.getHosts()));
		}
		
		client.close();
	}
	
	//�鿴HDFS�����ݽڵ�
	@Test
	public void test2() throws Exception{
		//ָ��NameNode ��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//ʹ��FileSystem������
		DistributedFileSystem client = (DistributedFileSystem)FileSystem.get(conf);
		
		//��ȡ���е����ݽڵ�
		DatanodeInfo[] list =client.getDataNodeStats();
		for(DatanodeInfo data:list) {
			System.out.println(data.getHostName());
		}
		
		client.close();
	}
	
	//ɾ��HDFS����
	@Test
	public void test3() throws Exception{
		//ָ��NameNode ��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		FileSystem client = FileSystem.get(conf);
		
		//ɾ������
		//�ڶ�������false���Ƿ�ʹ�õݹ�
		boolean flag = client.delete(new Path("/folder1/a.tag.gz"), false);
		System.out.println(flag?"ɾ���ɹ�":"ɾ��ʧ��");
		
		client.close();
	}
}












































