package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MyConcatString extends UDF {

	//执行 select MyConcatString(a,b) from emp1;
	
	//重写的函数必须叫 evaluate
	public String evaluate(String a,String b) {
		return a + "********" + b;
	}
}
