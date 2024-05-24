package demo.feature;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.configuration.Configuration;

public class DistributedCacheDemo {

	public static void main(String[] args) throws Exception {
		//ִ�����߼���DataSet������һ�����л���
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		
		//ע����Ҫ������ļ���������һ������
		env.registerCachedFile("d:\\temp\\data.txt", "localfile");
		
		//��������
		//����Դ
		DataSet<Integer> source = env.fromElements(1,2,3,4,5,6,7,8,9,10);
		source.map(new RichMapFunction<Integer, String>() {

			private String cacheData = "";
			
			//��ʼ��
			@Override
			public void open(Configuration parameters) throws Exception {
				// ��ʼ����ʱ�򣬶�ȡ���������
				File file = getRuntimeContext().getDistributedCache().getFile("localfile");
				
				List<String> list = FileUtils.readLines(file);
				
				//�õ����������
				cacheData = list.get(0);
			}
			
			@Override
			public String map(Integer value) throws Exception {
				//����ִ�����ݴ���ĵط�
				return value + cacheData;
			}
		}).print();

	}
}

















