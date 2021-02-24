package kr.or.connect.jdbcexam.dto;

import java.util.List;

public class JDBCExam5 {
	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
		
		List<Role> list = dao.getRoles();
		
		for(Role role : list) {
			System.out.println(role);
		}
	}
}
