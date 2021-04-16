package main;

import java.util.List;
import java.util.Scanner;

import dao.EmpDao;
import dto.EmpDto;

public class MainClass {
	
	
	public static void main(String[] args) {
		MainClass mcls = new MainClass();
		mcls.loop();
	}
	
	public void loop() {
		Scanner sc = new Scanner(System.in);
		EmpDao dao = new EmpDao();
		boolean close = false;
		
		while(true) {
			
			dao.menu();
			System.out.print("입력>>");
			int workNum = sc.nextInt();
							
			switch(workNum) {
				case 1 : 
					System.out.print("이름:");
					String name = sc.next();
					
					System.out.print("전화번호:");
					String phone = sc.next();
					
					System.out.print("이메일:");
					String email = sc.next();
					
					System.out.print("부서번호:");
					int department = sc.nextInt();
					
					System.out.print("입사일:");
					String hiredate = sc.next();
					
					System.out.print("급여:");
					int salary = sc.nextInt();
					
					EmpDto dto = new EmpDto(0, name, phone, email, department, hiredate, salary);
					dao.insert(dto); 	
										
					break;
					
				case 2 : 
					System.out.print("삭제할 사원번호:");
					int empno = sc.nextInt();
					
					dao.delete(empno);
					break;
					
				case 3 : 
					System.out.print("검색할 사원이름:");
					String empname = sc.next();
					
					List<EmpDto> list = dao.search(empname);
					dao.print(list);
					break;
					
				case 4 : 
					System.out.print("수정할 사원번호:");
					int updateempno = sc.nextInt();
					
					System.out.print("변경된 전화번호:");
					String updatePhone = sc.next();
					
					System.out.print("변경된 이메일:");
					String updateEmail = sc.next();
					
					dao.update(updatePhone, updateEmail, updateempno);
					break;
					
				case 5 : 
					
					List<EmpDto> alllist = dao.search("");
					dao.print(alllist);
					break;
					
				case 6 : close = true;	break;
			}
			
			if(close) {
				System.out.println("종료합니다.");
				break;
			}
		}
	}
	

}
