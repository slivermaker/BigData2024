package demo.revertedindex;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RevertedIndexCombiner extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text k21, Iterable<Text> v21, Context context)
			throws IOException, InterruptedException {
		//对v21求和：得到某个单词在某个文件中的频率
		int total = 0;
		for(Text v:v21) {
			total = total + Integer.parseInt(v.toString());
		}
		
		//k21的数据是:  love:data01.txt
		String data = k21.toString();
		int index = data.indexOf(":");
		
		String word = data.substring(0, index);// 单词
		String fileName = data.substring(index+1);//文件名
		
		//                love              date01.txt:2
		context.write(new Text(word), new Text(fileName+":"+total));
	}

}





















