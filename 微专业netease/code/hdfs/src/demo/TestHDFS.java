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
	//查找某个文件的数据块在HDFS的DataNode位置
	@Test
	public void test1() throws Exception{
		//指定NameNode 地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		FileSystem client = FileSystem.get(conf);
		
		//得到该文件的状态
		FileStatus status = client.getFileStatus(new Path("/folder1/a.tag.gz"));
		
		//获取该文件的数据块信息
		BlockLocation[] list = client.getFileBlockLocations(status, 0, status.getLen());
		for(BlockLocation blk:list) {
			System.out.println("数据块：主机：" + Arrays.toString(blk.getHosts()));
		}
		
		client.close();
	}
	
	//查看HDFS的数据节点
	@Test
	public void test2() throws Exception{
		//指定NameNode 地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//使用FileSystem的子类
		DistributedFileSystem client = (DistributedFileSystem)FileSystem.get(conf);
		
		//获取所有的数据节点
		DatanodeInfo[] list =client.getDataNodeStats();
		for(DatanodeInfo data:list) {
			System.out.println(data.getHostName());
		}
		
		client.close();
	}
	
	//删除HDFS数据
	@Test
	public void test3() throws Exception{
		//指定NameNode 地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		FileSystem client = FileSystem.get(conf);
		
		//删除数据
		//第二个参数false：是否使用递归
		boolean flag = client.delete(new Path("/folder1/a.tag.gz"), false);
		System.out.println(flag?"删除成功":"删除失败");
		
		client.close();
	}
}












































