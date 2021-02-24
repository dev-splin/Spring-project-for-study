package kr.or.connect.jdbcexam.dto;

public class JDBCExam3 {
	public static void main(String[] args) {
		Role role = new Role(500,"CEO");
		
		RoleDao dao = new RoleDao();
		int insertCount = dao.updateRole(role);
		
		System.out.println(insertCount);
	}
}
