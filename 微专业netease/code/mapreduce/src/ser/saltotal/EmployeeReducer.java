package ser.saltotal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

//                                             k3     v3       		k4  ���ź�            v4 �����ܶ�
public class EmployeeReducer extends Reducer<IntWritable, Employee, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Employee> v3,Context context)
			throws IOException, InterruptedException {
		//��Ҫ��Ա����нˮ�������
		int total = 0;
		for(Employee e:v3) {
			total = total + e.getSal();
		}
		
		//��� k4  ���ź�            v4 �����ܶ�
		context.write(k3, new IntWritable(total));
	}
}
