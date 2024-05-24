package demo.wc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class WordCountMain {

	public static void main(String[] args) throws Exception {
		//指定的配置信息: ZooKeeper
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//创建任务，指定任务的入口
		Job job = Job.getInstance(conf);
		job.setJarByClass(WordCountMain.class);

		//定义一个扫描器，指定读取的列
		Scan scan = new Scan();
		scan.addColumn(Bytes.toBytes("content"), Bytes.toBytes("info"));
		
		//使用工具类指定任务的Map
		TableMapReduceUtil.initTableMapperJob(Bytes.toBytes("word"), 
				                              scan,  //扫描器
				                              WordCountMapper.class, 
				                              Text.class,   //k2
				                              IntWritable.class, //v2
				                              job);
		
		
		//使用工具类指定任务的reducer
		TableMapReduceUtil.initTableReducerJob("stat", WordCountReducer.class, job);
		
		//执行任务
		job.waitForCompletion(true);
	}
}


















