package sort.object;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//                                          k1             v1  k2һ����Ϊk2    v2 ��ֵ
public class EmployeeMapper extends Mapper<LongWritable, Text, Employee, NullWritable> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//���ݣ�  7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(",");
		
		//����Ա������
		Employee e = new Employee();
		
		//����Ա����
		e.setEmpno(Integer.parseInt(words[0]));
		//����
		e.setEname(words[1]);
		//ְλ
		e.setJob(words[2]);
		//�ϰ�
		e.setMgr(Integer.parseInt(words[3]));
		//��ְ����
		e.setHiredate(words[4]);
		//нˮ
		e.setSal(Integer.parseInt(words[5]));
		//����
		e.setComm(Integer.parseInt(words[6]));
		//���ź�
		e.setDeptno(Integer.parseInt(words[7]));
		
		
		//���       k2 Ա������        v2 ��ֵ
		context.write(e,NullWritable.get());
	}
}














