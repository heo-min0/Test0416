package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.EmpDto;

public class EmpDao {
	
	public EmpDao() {
		DBConnection.initConnection();
	}
	
	public int insert(EmpDto dto) {
		String sql = "INSERT INTO TESTEMPLOYEES (EMPNO, NAME, PHONE, EMAIL, DEPARTMENT, HIREDATE, SALARY, DEL) "
				   + " VALUES(SEQ_TESTEMPLOYEES.nextval,?,?,?,?,?,?,0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPhone());
			psmt.setString(3, dto.getEmail());
			psmt.setInt(4, dto.getDepartment());
			psmt.setString(5, dto.getHiredate());
			psmt.setInt(6, dto.getSalary());
			
			count = psmt.executeUpdate();
		}
		catch (SQLException e) {System.out.println("insert fail");}
		finally {DBClose.Close(conn, psmt, null);}
		
		return count;
	}
	
	public int delete(int empno) {
		String sql = "UPDATE TESTEMPLOYEES SET DEL=1 WHERE EMPNO=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empno);
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {System.out.println("delete fail");}
		finally {DBClose.Close(conn, psmt, null);	}
		return count;
	}
	
	
	public List<EmpDto> search(String name) {
		String sql = "SELECT EMPNO, NAME, PHONE, EMAIL, DEPARTMENT, HIREDATE, SALARY, DEL "
				+ " FROM TESTEMPLOYEES "
				+ " WHERE DEL=0 ";
		
		String sql1 = "";
		if(name!=null || !name.equals("")) {
			sql1 += " AND NAME LIKE '%"+name+"%' ";
		}
		
		sql += sql1;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<EmpDto> list = new ArrayList<EmpDto>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				int i = 1;
				EmpDto dto = new EmpDto( rs.getInt(i++), 
					 			  	rs.getString(i++),
					 			  	rs.getString(i++),
					 			  	rs.getString(i++),
					 			  	rs.getInt(i++),
					 			  	rs.getString(i++),
					 			  	rs.getInt(i) );
				list.add(dto);
			}
			
		} catch (SQLException e) {System.out.println("search fail");}
		finally {DBClose.Close(conn, psmt, rs);	}
		return list;
	}
	
	public int update(String phone, String email, int empno) {
		String sql = "UPDATE TESTEMPLOYEES SET PHONE=?, EMAIL=? WHERE EMPNO=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, phone);
			psmt.setString(2, email);
			psmt.setInt(3, empno);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {System.out.println("update fail");}
		finally {DBClose.Close(conn, psmt, rs);	}
		return count;
	}
	
	public void print(List<EmpDto> list) {
		for (EmpDto emplist : list) {
			System.out.println(emplist.toString());
		}
	}
	
	public void menu() {
		System.out.println("================= M E N U ======================");
		System.out.println("| 1.추가   2.삭제   3.검색   4.수정   5.출력   6.종료 |");
		System.out.println("================================================");
	}
	
	
}
