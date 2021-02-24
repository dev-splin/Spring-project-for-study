package kr.or.connect.jdbcexam.dto;

public class JDBCExam4 {
	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
		int insertCount = dao.deleteRole(500);
		
		System.out.println(insertCount);
	}
}
