package demo.distinct;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//                                                             ��job��Ϊk2  v2Ϊ null
public class DistinctMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//���ݣ�  7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
		String data = value1.toString();
		//�ִ�
		String[] words = data.split(",");
		
		//��� ��job��Ϊk2  v2Ϊ null
		context.write(new Text(words[2]), NullWritable.get());
	}
}
