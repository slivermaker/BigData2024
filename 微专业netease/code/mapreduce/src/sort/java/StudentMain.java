package sort.java;

import java.util.Arrays;

public class StudentMain {

	public static void main(String[] args) {
		// ��������ѧ���Ķ���
		Student s1 = new Student();
		s1.setStuID(1);
		s1.setStuName("Tom");
		s1.setAge(24);

		Student s2 = new Student();
		s2.setStuID(2);
		s2.setStuName("Mary");
		s2.setAge(22);
		
		Student s3 = new Student();
		s3.setStuID(3);
		s3.setStuName("Mike");
		s3.setAge(26);
		
		//����һ������
		Student[] list = {s1,s2,s3};
		
		Arrays.sort(list);
		
		for(Student s :list) {
			System.out.println(s);
		}		
	}
}


















