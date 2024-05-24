package part;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//                                          k1             v1   k2 部门号          v2 员工对象
public class EmployeeMapper extends Mapper<LongWritable, Text, IntWritable, Employee> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//数据：  7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
		String data = value1.toString();
		
		//分词
		String[] words = data.split(",");
		
		//创建员工对象
		Employee e = new Employee();
		
		//设置员工号
		e.setEmpno(Integer.parseInt(words[0]));
		//姓名
		e.setEname(words[1]);
		//职位
		e.setJob(words[2]);
		//老板
		e.setMgr(Integer.parseInt(words[3]));
		//入职日期
		e.setHiredate(words[4]);
		//薪水
		e.setSal(Integer.parseInt(words[5]));
		//奖金
		e.setComm(Integer.parseInt(words[6]));
		//部门号
		e.setDeptno(Integer.parseInt(words[7]));
		
		
		//输出            k2 员工号                                            v2 员工对象
		context.write(new IntWritable(e.getDeptno()), e);
	}
}














