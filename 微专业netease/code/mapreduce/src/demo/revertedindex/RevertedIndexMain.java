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
		//1����������Job������ָ����������
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(RevertedIndexMain.class);
		
		//2��ָ�������Map��Map���������
		job.setMapperClass(RevertedIndexMapper.class);
		job.setMapOutputKeyClass(Text.class); //k2
		job.setMapOutputValueClass(Text.class); //v2
		
		//����Combiner
		job.setCombinerClass(RevertedIndexCombiner.class);
		
		//3��ָ�������Reduce��Reduce���������
		job.setReducerClass(RevertedIndexReducer.class);
		job.setOutputKeyClass(Text.class); //k4
		job.setOutputValueClass(Text.class); //v4
				
		//4��ָ�����������·�������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5��ִ������
		job.waitForCompletion(true);
	}

}
