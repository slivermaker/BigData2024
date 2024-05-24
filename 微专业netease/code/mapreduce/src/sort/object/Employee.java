package sort.object;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

//数据  7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
public class Employee implements WritableComparable<Employee>{

	private int empno;//员工号
	private String ename;//姓名
	private String job;//职位
	private int mgr;//老板的员工号
	private String hiredate;//入职日期
	private int sal;//薪水
	private int comm;//奖金
	private int deptno;//部门号
	
//	@Override
//	public int compareTo(Employee o) {
//		// 一个列排序：按照员工的薪水排序
//		if(this.sal >= o.getSal()) {
//			return 1;
//		}else {
//			return -1;
//		}
//	}	
	
	@Override
	public int compareTo(Employee o) {
		// 两个列排序：按照员工的部门排序；如果部门号相同，再按照薪水排序
		if(this.deptno > o.getDeptno()) {
			return 1;
		}else if(this.deptno < o.getDeptno()) {
			return -1;
		}
		
		if(this.sal >= o.getSal()) {
			return 1;
		}else {
			return -1;
		}
	}	
	
	@Override
	public void readFields(DataInput input) throws IOException {
		// 实现反序列化
		this.empno = input.readInt();
		this.ename = input.readUTF();
		this.job = input.readUTF();
		this.mgr = input.readInt();
		this.hiredate = input.readUTF();
		this.sal = input.readInt();
		this.comm = input.readInt();
		this.deptno = input.readInt();
	}

	//一定注意：反序列化和序列化的顺序一定要一致！！！！！！
	
	@Override
	public void write(DataOutput output) throws IOException {
		// 实现序列化的过程
		//7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
		output.writeInt(this.empno);
		output.writeUTF(this.ename);
		output.writeUTF(this.job);
		output.writeInt(this.mgr);
		output.writeUTF(this.hiredate);
		output.writeInt(this.sal);
		output.writeInt(this.comm);
		output.writeInt(this.deptno);
	}	
	
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", deptno=" + deptno + "]";
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}


}
