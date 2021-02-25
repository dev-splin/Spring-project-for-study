package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
    public List<BusinessCard> searchBusinessCard(String keyword){   		
    	
		List<BusinessCard> list = new ArrayList<>();
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	// 파라미터로 검색할 키워드를 받아오기 때문에 바로 쿼리에 추가해 줍니다.
    	String sql = "SELECT * FROM businesscard where name like '%" + keyword +"%'";
    	try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    			PreparedStatement ps = conn.prepareStatement(sql)){
    		try (ResultSet rs = ps.executeQuery()){
				
    			//쿼리를 실행시키고 나온 결과들로 객체를 만들어 리스트에 저장해 줍니다. 
    			while(rs.next()) {
    				String name = rs.getString(1);
    				String phone = rs.getString(2);
    				String companyname = rs.getString(3);
    				Date createdate = rs.getDate(4);
    				BusinessCard card = new BusinessCard(name, phone, companyname);
    				card.setCreateDate(createdate);
    				
    				list.add(card);
    			}
    			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return list;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String sql = "INSERT INTO businesscard (name, phone, companyname) values(?,?,?)";
    	try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, businessCard.getName());	// values(?, ?, ?,) 3개 중 첫 번째 '?'에 값을 설정해 줍니다.
			ps.setString(2, businessCard.getPhone());	
			ps.setString(3, businessCard.getCompanyName());
			
			ps.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return businessCard;
    }
}
