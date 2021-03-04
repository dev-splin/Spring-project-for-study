package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	GuestbookDao dao = new GuestbookDao();
    	
    	request.setCharacterEncoding("UTF-8");
    	// 응답할 때 언어설정을 해주듯이 request를 가져올 때도 마찬가지로 언어설정을 해주어야 한글이 깨지지 않습니다.
    	
    	try {
    		String name = request.getParameter("name");
    		String content = request.getParameter("content");

    		Guestbook guestbook = new Guestbook(name, content);
    		dao.addGuestbook(guestbook);
    		// 파라미터 값을 받아서 guestbook 객체를 하나 만들고 방명록을 db에 추가해주는 dao객체의 addGuestbook메소드에 넣어줍니다.
    		
    		response.sendRedirect("../guestbooks");
    		// 이 페이지 작업이 끝나면 Redirect방식으로 방명록 페이지로 돌아갑니다.			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    }
}
