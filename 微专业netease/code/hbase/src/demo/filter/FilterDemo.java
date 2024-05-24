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

//1����ֵ�������������е�ֵ���в�ѯ where sal=3000
	@Test
	public void testSingleColumnValueFilter() throws Exception{
		//ָ����������Ϣ: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HTable table = new HTable(conf, "emp");
		
		//����һ��ɨ��������ֵ������
		Scan scan = new Scan();
		SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("empinfo"),     //����
																	 Bytes.toBytes("sal"),     //��
				                                                     CompareOp.EQUAL,  //�Ƚ������
				                                                     Bytes.toBytes("3000"));
		scan.setFilter(filter);
		
		//��ѯ����
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//ȡ������
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(name);			
		}
		
		table.close();
	}
	
//2������ǰ׺����������ѯָ�����У���ѯԱ��������  select ename from emp;
	@Test
	public void testColumnPrefixFilter() throws Exception{
		//ָ����������Ϣ: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HTable table = new HTable(conf, "emp");
		
		//����һ��ɨ����������ǰ׺������
		Scan scan = new Scan();
		ColumnPrefixFilter filter = new ColumnPrefixFilter(Bytes.toBytes("ename"));
		scan.setFilter(filter);
		
		//��ѯ����
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//ȡ������
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(name);			
		}		
		
		table.close();
	}
	
	
//3���������ǰ׺����������ѯָ������У���ѯԱ����������нˮ��select ename,sal from emp;
	@Test
	public void testMultipleColumnPrefixFilter() throws Exception{
		//ָ����������Ϣ: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HTable table = new HTable(conf, "emp");
		
		//����һ��ɨ�����Ͷ������ǰ׺������
		Scan scan = new Scan();
		byte[][] prefix = {Bytes.toBytes("ename"),Bytes.toBytes("sal")};
		MultipleColumnPrefixFilter filter = new MultipleColumnPrefixFilter(prefix);
		
		scan.setFilter(filter);
		
		//��ѯ����
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//ȡ������
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(name+"\t"+sal);			
		}				
		
		table.close();
	}
		
//4��Rowkey��������ͨ���м����в�ѯ
	@Test
	public void testRowFilter() throws Exception{
		//ָ����������Ϣ: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HTable table = new HTable(conf, "emp");
		
		//����һ��ɨ����
		Scan scan = new Scan();
		//����һ���м�������
		//��ѯ�м���7839��Ա����Ϣ
		RowFilter filter = new RowFilter(CompareOp.EQUAL,   //�Ƚ������ 
				                         new RegexStringComparator("7839"));  //ʹ��������ʽ��ʾֵ
		scan.setFilter(filter);
		
		//��ѯ����
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//ȡ������
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(name+"\t"+sal);			
		}	
		
		table.close();
	}
	
//5����ѯ�У���϶��������
	@Test
	public void testFilters() throws Exception{
		/*
		 * ��ѯ���ʵ���3000��Ա������
		 * 1����ֵ���������õ����ʵ���3000
		 * 2������ǰ׺�Ĺ��������õ�����
		 */
		//ָ����������Ϣ: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HTable table = new HTable(conf, "emp");
		
		//����һ��ɨ����
		Scan scan = new Scan();
		
		//��һ��������  ��ֵ���������õ����ʵ���3000
		SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes("empinfo"),     //����
																	 Bytes.toBytes("sal"),     //��
													                CompareOp.EQUAL,  //�Ƚ������
													                Bytes.toBytes("3000"));		
		//�ڶ���������������ǰ׺���������õ�Ա��������
		ColumnPrefixFilter filter2 = new ColumnPrefixFilter(Bytes.toBytes("ename"));
		
		//Operator.MUST_PASS_ALL���൱����and
		//Operator.MUST_PASS_ONE���൱����or
		FilterList list = new FilterList(Operator.MUST_PASS_ALL);
		list.addFilter(filter1);
		list.addFilter(filter2);
		
		scan.setFilter(list);
		
		//��ѯ����
		ResultScanner rs = table .getScanner(scan);
		for(Result r:rs) {
			//ȡ������
			String name = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(name+"\t"+sal);			
		}			
		
		table.close();
	}

}















