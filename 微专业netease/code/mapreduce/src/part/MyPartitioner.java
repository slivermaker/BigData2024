package part;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//������������Map�����<k2 v2>                    k2���ź�    		v2 Ա������
public class MyPartitioner extends Partitioner<IntWritable, Employee>{

	@Override
	public int getPartition(IntWritable k2, Employee v2, int numTask) {
		//������Ӧ�ķ���
		//numTask ��ʾ�ľ��Ƿ����ĸ���
		//�õ����ź�
		int deptno = v2.getDeptno();
		if(deptno == 10) {
			//����һ�ŷ���
			return 1%numTask;
		}else if(deptno == 20) {
			//������ŷ���
			return 2%numTask;
		}else {
			//����0�ŷ���
			return 3%numTask;
		}
	}
}
