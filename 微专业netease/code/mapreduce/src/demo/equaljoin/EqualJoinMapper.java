package demo.equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EqualJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//������Ϣ��10,ACCOUNTING,NEW YORK
		//Ա����Ϣ��7782,CLARK,MANAGER,7839,1981/6/9,2450,0,10
		String data = value1.toString();
		//�ִ�
		String[] words = data.split(",");
		
		//�ж�����ĳ���
		if(words.length == 3) {
			//���ű����źţ���������
			context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
		}else {
			//Ա�������źţ�Ա������
			context.write(new IntWritable(Integer.parseInt(words[7])), new Text(words[1]));
		}
	}
}


















