package demo.selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//员工信息：7782,CLARK,MANAGER,7839,1981/6/9,2450,0,10
		String data = value1.toString();
		
		//分词
		String[] words = data.split(",");
		
		//输出
		try {
			//1、作为老板表: 员工号  姓名
			context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
			
			//2、作为员工表：老板号  姓名
			context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		}catch(Exception ex) {
			//表示是大老板
			context.write(new IntWritable(-1), new Text(words[1]));
		}
	}
}



















