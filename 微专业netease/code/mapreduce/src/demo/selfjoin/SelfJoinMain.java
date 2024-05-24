package demo.selfjoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SelfJoinMain {

	public static void main(String[] args) throws Exception {
		//1����������Job������ָ����������
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SelfJoinMain.class);
		
		//2��ָ�������Map��Map���������
		job.setMapperClass(SelfJoinMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); //k2
		job.setMapOutputValueClass(Text.class); //v2
		
		//3��ָ�������Reduce��Reduce���������
		job.setReducerClass(SelfJoinReducer.class);
		job.setOutputKeyClass(Text.class); //k4
		job.setOutputValueClass(Text.class); //v4
				
		//4��ָ�����������·�������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5��ִ������
		job.waitForCompletion(true);

	}

}
