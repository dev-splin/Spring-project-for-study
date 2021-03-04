package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {
    public List<Guestbook> getGuestbooks(){	// DB에서 모든 방명록 데이터를 가져와서 list에 저장하고 리턴하는 메서드
       
    	List<Guestbook> list = new ArrayList<>();

        String sql = "SELECT * FROM guestbook";
        
        try (Connection conn = DBUtil.getConnection();	// DBUtil을 이용해 Connection 객체를 만들고 가져옵니다.
        		PreparedStatement ps = conn.prepareStatement(sql)){
			// try-with-resources 방식으로 DB를 연결합니다.
        	
        	try (ResultSet rs = ps.executeQuery()){
				while(rs.next())
				{
					long id = rs.getInt(1);
					String name = rs.getString(2);
					String content = rs.getString(3);
					Date regdate = rs.getDate(4);
					Guestbook guestBook = new Guestbook(id, name,content, regdate);
					list.add(guestBook);
				}	// 쿼리문을 통해 가져온 데이터를 변수에 저장하고 Guestbook 객체를 만든 후 list에 저장합니다.	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        return list;
    }

    public void addGuestbook(Guestbook guestbook){	// 파라미터로 받은 Guestbook객체의 정보를 DB에 저장하는 메서드
    	
    	String sql = "INSERT INTO guestbook (name, content, regdate) VALUES (?,?,?)";
    	
        try (Connection conn = DBUtil.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)){
        	
        	ps.setString(1, guestbook.getName());
        	ps.setString(2, guestbook.getContent());
        	
        	java.sql.Timestamp date = new java.sql.Timestamp(guestbook.getRegdate().getTime());
        	ps.setTimestamp(3, date);
        	// java.util.Date 를 java.sql.Timestamp로 변환후 3번째 '?'에 설정합니다.
        	
        	ps.executeUpdate();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
