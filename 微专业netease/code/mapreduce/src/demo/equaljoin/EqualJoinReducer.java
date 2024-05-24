package demo.equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EqualJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		// 定义变量，分别保存部门名称和员工姓名（s）
		String dname = "";
		String empNameList = "";
		for(Text v:v3) {
			String str = v.toString();
			
			//判断是否存在*号
			int index = str.indexOf("*");
			if(index >= 0) {
				//是部门名称
				dname = str.substring(1);
			}else {
				//是员工姓名
				empNameList = str +";" + empNameList;
			}
		}
		
		//输出
		context.write(new Text(dname), new Text(empNameList));
	}
}


















