package demo.wc;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//˼����Ϊʲôû������<k1  v1>?
//���ڵ��������HBase���е�һ����¼
//                                                k2����       v2��һ����
public class WordCountMapper extends TableMapper<Text, IntWritable> {

	@Override
	protected void map(ImmutableBytesWritable key1, Result value1,Context context)
			throws IOException, InterruptedException {
		/*
		 * key1����������м�rowkey
		 * value1�����м�¼
		 * ���� : I love Beijing
		 */
		String data = Bytes.toString(value1.getValue(Bytes.toBytes("content"), Bytes.toBytes("info")));
		//�ִ�
		String[] words = data.split(" ");
		for(String w:words) {
			context.write(new Text(w), new IntWritable(1));
		}
	}
}















