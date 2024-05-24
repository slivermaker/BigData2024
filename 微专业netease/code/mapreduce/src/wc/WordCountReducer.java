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
		 * context����reduce��������
		 * ���ģ�Map
		 * ���ģ������HDFS
		 */
		//��v3���
		int total = 0;
		for(IntWritable v:v3) {
			total += v.get();
		}
		
		//���  k4�ǵ���k3�� v4��total
		context.write(k3, new IntWritable(total));
	}
}
