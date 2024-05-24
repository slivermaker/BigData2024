package demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/*
 * 1、缺少一个jar包
 * 2、注意：配置hosts文件
 */
public class TestHBase {

	//创建表
	@Test
	public void testCreateTable() throws Exception{
		//配置ZooKeeper的地址
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		//创建表的描述符
		HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("mystudent"));
		//表中的列族
		htd.addFamily(new HColumnDescriptor("info"));
		htd.addFamily(new HColumnDescriptor("grade"));
		
		//创建表
		admin.createTable(htd);
		
		admin.close();
	}
	
	//插入数据: 单条数据	
	@Test
	public void testPut() throws Exception{
		//配置ZooKeeper的地址
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//得到表的客户端
		HTable table = new HTable(conf, "mystudent");
		
		//插入的数据：构造Put(参数：行键)
		Put put = new Put(Bytes.toBytes("s001"));
//		put.addColumn(family, 		列族的名字
//					  qualifier, 	列的名字
//					  value)		值
		put.addColumn(Bytes.toBytes("info"), 
					  Bytes.toBytes("name"), 
					  Bytes.toBytes("Tom"));
		table.put(put);
		
		//插入数据：多条数据
		//table.put(List<Put>);
		table.close();		
	}
	
	// 查询数据：Get
	@Test
	public void testGet() throws Exception{
		//配置ZooKeeper的地址
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//得到表的客户端
		HTable table = new HTable(conf, "mystudent");
		
		//构造一个Get对象，指定Rowkey
		Get get = new Get(Bytes.toBytes("s001"));
		
		//查询
		Result r = table.get(get);
		//取出数据
		String name = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
		System.out.println(name);
		
		table.close();
	}
	
	// 查询数据：Scan
	@Test
	public void testScan() throws Exception{
		//配置ZooKeeper的地址
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//得到表的客户端
		HTable table = new HTable(conf, "mystudent");
		
		//定义一个扫描器
		Scan scan = new Scan();
		//scan.setFilter(filter) 过滤器
		
		//通过扫描器查询数据
		//在该集合中保存的是Result
		ResultScanner rs = table.getScanner(scan);
		for(Result r:rs) {
			//取出数据
			String name = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
			System.out.println(name);			
		}
		
		table.close();
	}	
	
	@Test
	public void testDropTable() throws Exception{
		//配置ZooKeeper的地址
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		//删除表
		admin.disableTable("mystudent");
		admin.deleteTable("mystudent");
		
		admin.close();
	}
}





















