package wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//                                             k3      v3         k4     v4
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,Context context) throws IOException, InterruptedException {
		/*
		 * context代表reduce的上下文
		 * 上文：Map
		 * 下文：输出的HDFS
		 */
		//对v3求和
		int total = 0;
		for(IntWritable v:v3) {
			total += v.get();
		}
		
		//输出  k4是单词k3， v4是total
		context.write(k3, new IntWritable(total));
	}
}
