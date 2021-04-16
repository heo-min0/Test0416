package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpDto {
	private int empno;
	private String name;
	private String phone;
	private String email;
	private int department;
	private String hiredate;
	private int salary;
	
	public EmpDto() {
	}

	public EmpDto(int empno, String name, String phone, String email, int department, String hiredate, int salary) {
		this.empno = empno;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.department = department;
		this.hiredate = hiredate;
		this.salary = salary;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		SimpleDateFormat newform = new SimpleDateFormat("yyyy/MM/dd");
		
		String sdate = "";
		try {
			Date newdate = form.parse(hiredate);
			sdate = newform.format(newdate);
		}
		catch (ParseException e) {e.printStackTrace();}
		
		return "사원번호=" + empno + ", 이름=" + name + ", 전화번호=" + phone + ", 이메일=" + email + ", 부서번호="
				+ department + ", 입사일=" + sdate + ", 급여=" + salary + "]";
	}
	
}
