package part;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//分区器：根据Map的输出<k2 v2>                    k2部门号    		v2 员工对象
public class MyPartitioner extends Partitioner<IntWritable, Employee>{

	@Override
	public int getPartition(IntWritable k2, Employee v2, int numTask) {
		//建立对应的分区
		//numTask 表示的就是分区的个数
		//得到部门号
		int deptno = v2.getDeptno();
		if(deptno == 10) {
			//放入一号分区
			return 1%numTask;
		}else if(deptno == 20) {
			//放入二号分区
			return 2%numTask;
		}else {
			//放入0号分区
			return 3%numTask;
		}
	}
}
