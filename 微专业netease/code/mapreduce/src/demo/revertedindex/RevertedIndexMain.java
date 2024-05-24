package demo.revertedindex;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RevertedIndexMain {

	public static void main(String[] args) throws Exception {
		//1、创建任务Job，并且指定任务的入口
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(RevertedIndexMain.class);
		
		//2、指定任务的Map，Map的输出类型
		job.setMapperClass(RevertedIndexMapper.class);
		job.setMapOutputKeyClass(Text.class); //k2
		job.setMapOutputValueClass(Text.class); //v2
		
		//引入Combiner
		job.setCombinerClass(RevertedIndexCombiner.class);
		
		//3、指定任务的Reduce，Reduce的输出类型
		job.setReducerClass(RevertedIndexReducer.class);
		job.setOutputKeyClass(Text.class); //k4
		job.setOutputValueClass(Text.class); //v4
				
		//4、指定任务的输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5、执行任务
		job.waitForCompletion(true);
	}

}
