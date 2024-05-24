package part;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

//                                            k3     v3             k4        v4
public class EmployeeReducer extends Reducer<IntWritable, Employee, IntWritable, Employee> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Employee> v3,Context context)
			throws IOException, InterruptedException {
		for(Employee e:v3) {
			//          部门号  员工对象
			context.write(k3, e);
		}
	}

}
