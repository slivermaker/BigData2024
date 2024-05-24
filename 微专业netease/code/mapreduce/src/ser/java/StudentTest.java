package ser.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		// 创建一个学生对象
		Student s = new Student();
		s.setStuID(1);
		s.setStuName("Tom");
		
		//把该对象持久化的存储在文件中
		ObjectOutputStream  out = new ObjectOutputStream(new FileOutputStream("d:\\temp\\student.o"));
		out.writeObject(s);
		
		out.close();
		System.out.println("完成");
	}
}
