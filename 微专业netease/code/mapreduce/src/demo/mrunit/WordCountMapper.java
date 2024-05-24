package demo.mrunit;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//                                           k1           v1      k2       v2
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		/*
		 * context代表Map的上下文
		 * 上文：HDFS的输入
		 * 下文：Reduce
		 */
		//获取数据: I love Beijing
		String data = value1.toString();
		
		//分词
		String[] words = data.split(" ");
		
		//输出
		for(String w:words) {
			//            k2单词                      v2记一次数
			context.write(new Text(w), new IntWritable(1));
		}
	}
}



















