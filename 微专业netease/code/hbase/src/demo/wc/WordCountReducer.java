package demo.wc;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//                                                  k3      v3          k4是输出记录的行键
public class WordCountReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {

	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,Context context)
			throws IOException, InterruptedException {
		//对v3求和
		int total = 0;
		for(IntWritable v:v3) {
			total += v.get();
		}
		
		//输出的是表中的一条记录
		//构造一个Put对象
		Put put = new Put(Bytes.toBytes(k3.toString())); //把单词作为行键
		put.addColumn(Bytes.toBytes("content"), Bytes.toBytes("result"), Bytes.toBytes(String.valueOf(total)));
		
		//输出                单词作为rowkey
		context.write(new ImmutableBytesWritable(Bytes.toBytes(k3.toString())), put);
	}
}




















