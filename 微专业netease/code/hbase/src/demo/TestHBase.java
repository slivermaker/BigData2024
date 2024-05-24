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
 * 1��ȱ��һ��jar��
 * 2��ע�⣺����hosts�ļ�
 */
public class TestHBase {

	//������
	@Test
	public void testCreateTable() throws Exception{
		//����ZooKeeper�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		//�������������
		HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("mystudent"));
		//���е�����
		htd.addFamily(new HColumnDescriptor("info"));
		htd.addFamily(new HColumnDescriptor("grade"));
		
		//������
		admin.createTable(htd);
		
		admin.close();
	}
	
	//��������: ��������	
	@Test
	public void testPut() throws Exception{
		//����ZooKeeper�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�õ���Ŀͻ���
		HTable table = new HTable(conf, "mystudent");
		
		//��������ݣ�����Put(�������м�)
		Put put = new Put(Bytes.toBytes("s001"));
//		put.addColumn(family, 		���������
//					  qualifier, 	�е�����
//					  value)		ֵ
		put.addColumn(Bytes.toBytes("info"), 
					  Bytes.toBytes("name"), 
					  Bytes.toBytes("Tom"));
		table.put(put);
		
		//�������ݣ���������
		//table.put(List<Put>);
		table.close();		
	}
	
	// ��ѯ���ݣ�Get
	@Test
	public void testGet() throws Exception{
		//����ZooKeeper�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�õ���Ŀͻ���
		HTable table = new HTable(conf, "mystudent");
		
		//����һ��Get����ָ��Rowkey
		Get get = new Get(Bytes.toBytes("s001"));
		
		//��ѯ
		Result r = table.get(get);
		//ȡ������
		String name = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
		System.out.println(name);
		
		table.close();
	}
	
	// ��ѯ���ݣ�Scan
	@Test
	public void testScan() throws Exception{
		//����ZooKeeper�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�õ���Ŀͻ���
		HTable table = new HTable(conf, "mystudent");
		
		//����һ��ɨ����
		Scan scan = new Scan();
		//scan.setFilter(filter) ������
		
		//ͨ��ɨ������ѯ����
		//�ڸü����б������Result
		ResultScanner rs = table.getScanner(scan);
		for(Result r:rs) {
			//ȡ������
			String name = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
			System.out.println(name);			
		}
		
		table.close();
	}	
	
	@Test
	public void testDropTable() throws Exception{
		//����ZooKeeper�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//�ͻ���
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		//ɾ����
		admin.disableTable("mystudent");
		admin.deleteTable("mystudent");
		
		admin.close();
	}
}





















