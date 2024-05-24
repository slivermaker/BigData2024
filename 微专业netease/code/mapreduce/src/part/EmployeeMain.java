package part;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EmployeeMain {

	public static void main(String[] args) throws Exception {
		//1����������Job������ָ����������
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(EmployeeMain.class);
		
		//2��ָ�������Map��Map���������
		job.setMapperClass(EmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); //k2 ���ź�
		job.setMapOutputValueClass(Employee.class); //v2 Ա������
		
		//ָ����������
		job.setPartitionerClass(MyPartitioner.class);
		//�����ĸ���
		job.setNumReduceTasks(3);
		
		job.setReducerClass(EmployeeReducer.class);
		job.setOutputKeyClass(IntWritable.class); //k4
		job.setOutputValueClass(Employee.class); //v4
				
		//4��ָ�����������·�������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5��ִ������
		job.waitForCompletion(true);		
	}
}





