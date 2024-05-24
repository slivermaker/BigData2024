package demo.mrunit;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

public class MRUnitWordCount {

	@Test
	public void testMapper() throws Exception{
		//创建一个WordCountMapper的测试对象
		WordCountMapper mapper = new WordCountMapper();
		
		//创建一个Driver进行单元测试
		MapDriver<LongWritable,Text,Text,IntWritable> driver = new MapDriver(mapper);
		
		//指定Map输入的数据
		driver.withInput(new LongWritable(1), new Text("I love Beijing"));
		//指定Map的输出(是我们希望得到结果)
		driver.withOutput(new Text("I"), new IntWritable(1))
			  .withOutput(new Text("love"), new IntWritable(1))
			  .withOutput(new Text("Beijing"), new IntWritable(1));
		
		//执行单元测试：对比：我们希望得到结果   和 实际运行的结果
		driver.runTest();
	}
	
	@Test
	public void testReducer() throws Exception{
		WordCountReducer reducer = new WordCountReducer();
		
		ReduceDriver<Text,IntWritable,Text,IntWritable>
			driver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(reducer);
		
		//构造Reduce的输入  List
		List<IntWritable> value3 = new ArrayList<IntWritable>();
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));		
		value3.add(new IntWritable(1));
		driver.withInput(new Text("Beijing"), value3);
		
		//指定reduce的输出：是我们希望得到的结果
		driver.withOutput(new Text("Beijing"), new IntWritable(3));
		
		driver.runTest();
		
	}
	
	@Test
	public void testJob() throws Exception{
		//创建测试对象
		WordCountMapper mapper = new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();
		
		//创建Driver
		//MapReduceDriver<K1, V1, K2, V2, K4, V4>
		MapReduceDriver<LongWritable,Text,Text,IntWritable,Text,IntWritable>
			driver = new MapReduceDriver(mapper,reducer);
		
		//指定Map输入的数据
		driver.withInput(new LongWritable(1), new Text("I love Beijing"))
			  .withInput(new LongWritable(2), new Text("I love China"))
			  .withInput(new LongWritable(3), new Text("Beijing is the capital of China"));
		
		//指定reduce的输出:是我们希望得到的结果
//		driver.withOutput(new Text("I"), new IntWritable(2))
//			  .withOutput(new Text("love"), new IntWritable(2))
//			  .withOutput(new Text("Beijing"), new IntWritable(2))
//			  .withOutput(new Text("China"), new IntWritable(2))
//			  .withOutput(new Text("is"), new IntWritable(1))
//			  .withOutput(new Text("the"), new IntWritable(1))
//			  .withOutput(new Text("capital"), new IntWritable(1))
//			  .withOutput(new Text("of"), new IntWritable(1));
		
		//考虑排序规则
		driver.withOutput(new Text("Beijing"), new IntWritable(2))
			  .withOutput(new Text("China"), new IntWritable(2))
			  .withOutput(new Text("I"), new IntWritable(2))
			  .withOutput(new Text("capital"), new IntWritable(1))
			  .withOutput(new Text("is"), new IntWritable(1))
			  .withOutput(new Text("love"), new IntWritable(2))
			  .withOutput(new Text("of"), new IntWritable(1))
			  .withOutput(new Text("the"), new IntWritable(1));
		
		driver.runTest();
	}
}



















































