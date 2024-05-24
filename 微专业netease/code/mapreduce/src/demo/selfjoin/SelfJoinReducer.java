package demo.selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SelfJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		// ���������������ϰ�������Ա������
		String bossName = "";
		String empNameList = "";
		
		for(Text v:v3) {
			String str = v.toString();
			
			//�ж��Ƿ����*��
			int index = str.indexOf("*");
			if(index >= 0) {
				//��ʾ���ϰ����� 
				bossName = str.substring(1);
			}else {
				//��ʾ��Ա������
				empNameList = str + ";"+ empNameList;
			}
		}
		
		//�жϣ���������ϰ��Ա���������
		if(bossName.length() > 0 && empNameList.length() > 0)
			context.write(new Text(bossName), new Text(empNameList));
	}
}















