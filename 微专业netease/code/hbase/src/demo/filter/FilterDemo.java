package demo.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

public class FilterDemo {

//1、列值过滤器：根据列的值进行查询 where sal=3000
	@Test
	public void testSingleColumnValueFilter() throws Exception{
		//指定的配置信息: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HTable table = new HTable(conf, "emp");
		
		//创建一个扫描器和列值过滤器
		Scan scan = new Scan();
		SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("empinfo"),     //列族
																	 Bytes.toBytes("sal"),     //列
				                                                     CompareOp.EQUAL,  //比较运算符
				                                                     Bytes.toBytes("3000"));
		scan.setFilter(filter);
		
		//查询数据
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//取出数据
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(name);			
		}
		
		table.close();
	}
	
//2、列名前缀过滤器：查询指定的列，查询员工的姓名  select ename from emp;
	@Test
	public void testColumnPrefixFilter() throws Exception{
		//指定的配置信息: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HTable table = new HTable(conf, "emp");
		
		//创建一个扫描器和列名前缀过滤器
		Scan scan = new Scan();
		ColumnPrefixFilter filter = new ColumnPrefixFilter(Bytes.toBytes("ename"));
		scan.setFilter(filter);
		
		//查询数据
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//取出数据
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(name);			
		}		
		
		table.close();
	}
	
	
//3、多个列名前缀过滤器：查询指定多个列，查询员工的姓名和薪水：select ename,sal from emp;
	@Test
	public void testMultipleColumnPrefixFilter() throws Exception{
		//指定的配置信息: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HTable table = new HTable(conf, "emp");
		
		//创建一个扫描器和多个列名前缀过滤器
		Scan scan = new Scan();
		byte[][] prefix = {Bytes.toBytes("ename"),Bytes.toBytes("sal")};
		MultipleColumnPrefixFilter filter = new MultipleColumnPrefixFilter(prefix);
		
		scan.setFilter(filter);
		
		//查询数据
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//取出数据
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(name+"\t"+sal);			
		}				
		
		table.close();
	}
		
//4、Rowkey过滤器：通过行键进行查询
	@Test
	public void testRowFilter() throws Exception{
		//指定的配置信息: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HTable table = new HTable(conf, "emp");
		
		//创建一个扫描器
		Scan scan = new Scan();
		//定义一个行键过滤器
		//查询行键是7839的员工信息
		RowFilter filter = new RowFilter(CompareOp.EQUAL,   //比较运算符 
				                         new RegexStringComparator("7839"));  //使用正则表达式表示值
		scan.setFilter(filter);
		
		//查询数据
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//取出数据
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(name+"\t"+sal);			
		}	
		
		table.close();
	}
	
//5、查询中，组合多个过滤器
	@Test
	public void testFilters() throws Exception{
		/*
		 * 查询工资等于3000的员工姓名
		 * 1、列值过滤器，得到工资等于3000
		 * 2、列名前缀的过滤器，得到姓名
		 */
		//指定的配置信息: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//客户端
		HTable table = new HTable(conf, "emp");
		
		//创建一个扫描器
		Scan scan = new Scan();
		
		//第一个过滤器  列值过滤器，得到工资等于3000
		SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes("empinfo"),     //列族
																	 Bytes.toBytes("sal"),     //列
													                CompareOp.EQUAL,  //比较运算符
													                Bytes.toBytes("3000"));		
		//第二个过滤器：列名前缀过滤器，得到员工的姓名
		ColumnPrefixFilter filter2 = new ColumnPrefixFilter(Bytes.toBytes("ename"));
		
		//Operator.MUST_PASS_ALL：相当于是and
		//Operator.MUST_PASS_ONE：相当于是or
		FilterList list = new FilterList(Operator.MUST_PASS_ALL);
		list.addFilter(filter1);
		list.addFilter(filter2);
		
		scan.setFilter(list);
		
		//查询数据
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//取出数据
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(name+"\t"+sal);			
		}			
		
		table.close();
	}

}















