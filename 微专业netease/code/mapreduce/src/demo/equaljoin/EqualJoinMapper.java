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
		//部门信息：10,ACCOUNTING,NEW YORK
		//员工信息：7782,CLARK,MANAGER,7839,1981/6/9,2450,0,10
		String data = value1.toString();
		//分词
		String[] words = data.split(",");
		
		//判断数组的长度
		if(words.length == 3) {
			//部门表：部门号，部门名称
			context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
		}else {
			//员工表：部门号，员工姓名
			context.write(new IntWritable(Integer.parseInt(words[7])), new Text(words[1]));
		}
	}
}


















