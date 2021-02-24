package kr.or.connect.jdbcexam.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// db의 입력, 조회, 수정, 삭제등의 일들을 묶어놓은 하나의 클래스
public class RoleDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	public void DBclose(Connection con, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void DBclose(Connection con, PreparedStatement ps) {
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	
	// roleId를 이용해 Role 테이블의 정보를 가져오는 함수입니다.
	public Role getRole(Integer roleId) {
		Role role = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
			String sql = "SELECT description,role_id FROM role WHERE role_id = ?";
			ps = conn.prepareStatement(sql);
			// sql에서 role_id = ? 같은 부분을  '?'를 대신해서 사용해주는 것이 preparedStatement의 특징입니다.
			// 쿼리자체는 변경되지 않고 이 '?'가 바인딩되는 부분만 바뀌기 때문에 효율이 더 좋습니다.
			ps.setInt(1, roleId);
			// 1은 몇번째의 '?' 인지를 말합니다. 첫 번째의 '?'에 roleId를 넣어주겠다는 의미입니다.
			rs = ps.executeQuery();
			
			if (rs.next()) {
				// select문에서 결과의 순서를 지켜줍니다.
				String description = rs.getString(1);
				// 첫번째로 오는 것이 description 이기 때문 1을 넣고 값을 받아옵니다.
				int id = rs.getInt("role_id");
				// 쿼리에서 두번째로 오는 것이기 때문에 2라고 적어줘도 괜찮지만 컬럼명을 적어주셔도 됩니다.
				role = new Role(id,description);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBclose(conn, ps, rs);
		}
		
		return role;
	}
	
	// Role 테이블에 자료를 추가해주는 함수입니다.
	public int addRole(Role role) {
		int insertCount = 0;
		// sql을 실행할 때, insert, update, delete 문이 몇 건을 수행했는지 같은 결과를 담을 변수
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "INSERT INTO role (role_id, description) VALUES ( ?, ? )";
			// '?'가 있는 쿼리는 완전한 쿼리가 아니기 때문에 '?'에 대한 값을 바인딩하는 코드가 있어야 합니다.
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, role.getRoleId());
			ps.setString(2, role.getDescription());
			
			insertCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBclose(conn, ps);
		}
		
		return insertCount;
	}
	
	// Role 테이블에 레코드를 업데이트 해주는 함수입니다.
	public int updateRole(Role role) {
		int insertCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "UPDATE Role SET description = ? WHERE role_id = ? ";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, role.getDescription());
			ps.setInt(2, role.getRoleId());
			
			insertCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBclose(conn, ps);
		}
		
		return insertCount;
	}
	
	// Role 테이블에 레코드를 삭제 해주는 함수입니다.
	public int deleteRole(Integer roleId) {
		int insertCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "DELETE FROM Role WHERE role_id = ? ";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, roleId);
			
			insertCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBclose(conn, ps);
		}
		
		return insertCount;
	}
	
	// Role테이블의 전체를 가져오는 함수입니다.
	public List<Role> getRoles() {
		List<Role> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT description, role_id FROM role order by role_id desc";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			// try-with-resource라는 기법입니다.
			// try 부분에 사용할 리소르를 얻어오는 코드를 만들어주게 되면 알아서 close를 해줍니다.
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String description = rs.getString(1);
					int id = rs.getInt("role_id");
					Role role = new Role(id, description);
					list.add(role); // list에 반복할때마다 Role인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}
