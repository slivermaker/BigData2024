package sort.java;

public class Student implements Comparable<Student> {

	private int stuID;
	private String stuName;
	private int age;
	
//	@Override
//	public int compareTo(Student o) {
//		// �������ǵ������������һ���е�����
//		if(this.age >= o.getAge()) {
//			return 1;
//		}else {
//			return -1;
//		}
//	}	

	@Override
	public int compareTo(Student o) {
		//�����н�������
		if(this.age > o.getAge()) {
			return 1;
		}else if(this.age < o.getAge()) {
			return -1;
		}
		
		if(this.stuID >= o.getStuID()) {
			return 1;
		}else {
			return -1;
		}
	}	
	
	@Override
	public String toString() {
		return "Student [stuID=" + stuID + ", stuName=" + stuName + ", age=" + age + "]";
	}

	public int getStuID() {
		return stuID;
	}
	public void setStuID(int stuID) {
		this.stuID = stuID;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
