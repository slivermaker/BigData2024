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
		//执行离线计算DataSet，创建一个运行环境
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		
		//注册需要缓存的文件，并且其一个别名
		env.registerCachedFile("d:\\temp\\data.txt", "localfile");
		
		//处理数据
		//数据源
		DataSet<Integer> source = env.fromElements(1,2,3,4,5,6,7,8,9,10);
		source.map(new RichMapFunction<Integer, String>() {

			private String cacheData = "";
			
			//初始化
			@Override
			public void open(Configuration parameters) throws Exception {
				// 初始化的时候，读取缓存的数据
				File file = getRuntimeContext().getDistributedCache().getFile("localfile");
				
				List<String> list = FileUtils.readLines(file);
				
				//得到缓存的数据
				cacheData = list.get(0);
			}
			
			@Override
			public String map(Integer value) throws Exception {
				//真正执行数据处理的地方
				return value + cacheData;
			}
		}).print();

	}
}

















