package demo.revertedindex;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class RevertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//数据: data01.txt   I love Beijing and love Shanghai
		//获取输入数据的路径: /indexdata/data01.txt
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		//查询最后一个斜线
		int index = path.lastIndexOf("/");
		//得到文件名
		String fileName = path.substring(index + 1);
		
		String data = value1.toString();
		//分词
		String[] words = data.split(" ");
		
		//输出
		for(String w:words) {
			context.write(new Text(w+":"+fileName), new Text("1"));
		}
	}
}


















