package wc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountMain {

	public static void main(String[] args) throws Exception {
		//1、创建任务Job，并且指定任务的入口
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(WordCountMain.class);
		
		//2、指定任务的Map，Map的输出类型
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class); //k2
		job.setMapOutputValueClass(IntWritable.class); //v2
		
		//设置Text的比较规则
		job.setSortComparatorClass(MyTextComparator.class);
		
		//加入一个Combiner
		job.setCombinerClass(WordCountReducer.class);
		
		//3、指定任务的Reduce，Reduce的输出类型
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class); //k4
		job.setOutputValueClass(IntWritable.class); //v4
				
		//4、指定任务的输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5、执行任务
		job.waitForCompletion(true);
	}
}













