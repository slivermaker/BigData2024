package saltotal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

//                                              k3���ź�   v3�ò��������е�Ա��нˮ   k4 ���ź�           v4�����ܶ�
public class SalaryTotalReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable k3, Iterable<IntWritable> v3,Context context)
			throws IOException, InterruptedException {
		//��v3���
		int total = 0;
		for(IntWritable v:v3) {
			total = total + v.get();
		}
		
		//���   k4���ź�      v4 �����ܶ�
		context.write(k3, new IntWritable(total));
	}

}
