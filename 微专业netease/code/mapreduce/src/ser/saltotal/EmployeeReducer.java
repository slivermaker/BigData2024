package ser.saltotal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

//                                             k3     v3       		k4  部门号            v4 工资总额
public class EmployeeReducer extends Reducer<IntWritable, Employee, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Employee> v3,Context context)
			throws IOException, InterruptedException {
		//需要对员工的薪水进行求和
		int total = 0;
		for(Employee e:v3) {
			total = total + e.getSal();
		}
		
		//输出 k4  部门号            v4 工资总额
		context.write(k3, new IntWritable(total));
	}
}
