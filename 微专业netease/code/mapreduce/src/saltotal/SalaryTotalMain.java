package saltotal;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalaryTotalMain {

	public static void main(String[] args) throws Exception {
		//1、创建任务Job，并且指定任务的入口
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SalaryTotalMain.class);
		
		//2、指定任务的Map，和Map的输出
		job.setMapperClass(SalaryTotalMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);  // k2
		job.setMapOutputValueClass(IntWritable.class); //v2
		
		//指定自定义的排序规则
		job.setSortComparatorClass(MyIntComparable.class);
		
		//3、指定任务的Reduce，和Reduce的输出
		job.setReducerClass(SalaryTotalReducer.class);
		job.setOutputKeyClass(IntWritable.class);  //k4
		job.setOutputValueClass(IntWritable.class);//v4
		
		//4、指定任务的输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5、执行任务
		job.waitForCompletion(true);
	}
}
