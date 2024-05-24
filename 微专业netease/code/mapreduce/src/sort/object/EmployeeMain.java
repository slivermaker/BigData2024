package sort.object;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EmployeeMain {

	public static void main(String[] args) throws Exception {
		//1、创建任务Job，并且指定任务的入口
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(EmployeeMain.class);
		
		//2、指定任务的Map，Map的输出类型
		job.setMapperClass(EmployeeMapper.class);
		job.setMapOutputKeyClass(Employee.class); //k2 员工对象
		job.setMapOutputValueClass(NullWritable.class); //v2 null值

		job.setOutputKeyClass(Employee.class); //k4
		job.setOutputValueClass(NullWritable.class); //v4
				
		//4、指定任务的输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5、执行任务
		job.waitForCompletion(true);		
	}
}
