package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	
    	try {
    		GuestbookDao dao = new GuestbookDao();
    		List<Guestbook> list = dao.getGuestbooks();
    		// db에서 방명록의 데이터를 가져오는 dao의 getGuestbooks메소드를 통해 Guestbook 객체를 리스트로 받습니다.
    		request.setAttribute("list", list);
    		// jsp에서 사용할 수 있게 request scope에 등록해줍니다.
		} catch (NullPointerException e) {
			e.printStackTrace();
		}	
    	
    	RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/guestbook/guestbooks.jsp");
    	requestDispatcher.forward(request, response);
    	// 프로그램을 실행시키면 최초에 이 클래스가 실행되기 때문에 forward방식으로 jsp페이지를 호출해줍니다.
    }
}
