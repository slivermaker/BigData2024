package ser.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		// ����һ��ѧ������
		Student s = new Student();
		s.setStuID(1);
		s.setStuName("Tom");
		
		//�Ѹö���־û��Ĵ洢���ļ���
		ObjectOutputStream  out = new ObjectOutputStream(new FileOutputStream("d:\\temp\\student.o"));
		out.writeObject(s);
		
		out.close();
		System.out.println("���");
	}
}
