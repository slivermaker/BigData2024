package demo.wc;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//思考：为什么没有输入<k1  v1>?
//现在的输入就是HBase表中的一条记录
//                                                k2单词       v2记一次数
public class WordCountMapper extends TableMapper<Text, IntWritable> {

	@Override
	protected void map(ImmutableBytesWritable key1, Result value1,Context context)
			throws IOException, InterruptedException {
		/*
		 * key1：代表的是行键rowkey
		 * value1：该行记录
		 * 数据 : I love Beijing
		 */
		String data = Bytes.toString(value1.getValue(Bytes.toBytes("content"), Bytes.toBytes("info")));
		//分词
		String[] words = data.split(" ");
		for(String w:words) {
			context.write(new Text(w), new IntWritable(1));
		}
	}
}















