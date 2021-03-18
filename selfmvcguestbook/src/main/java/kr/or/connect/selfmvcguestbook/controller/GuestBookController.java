package kr.or.connect.selfmvcguestbook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.connect.selfmvcguestbook.dao.GuestBookDao;
import kr.or.connect.selfmvcguestbook.dao.GuestBookLogDao;
import kr.or.connect.selfmvcguestbook.dto.GuestBook;
import kr.or.connect.selfmvcguestbook.dto.GuestBookLog;

@Controller
@SessionAttributes({"guestBooks", "count"})	// 방명록정보와 개수는 항상 최신으로 유지되어야 하기 때문에 session으로 두었습니다.
public class GuestBookController {
	@Autowired
	GuestBookDao guestBookDao;
	@Autowired
	GuestBookLogDao guestBookLogDao;
	
	// guestbook.jsp는 guestbook/list로 리다이렉트 합니다.
	@GetMapping(path = "/guestbook")
	public String RedirectToList() {
		return "guestbook";
	}
	
	
	// guestbook/list get요청이 들어오면 selfguestbook 테이블의 모든 정보를 리스트에 담고
	// db의 모든 row의 개수를 가져와서 model객체로 scope에 넘겨줍니다.
	// 이름이 start인 파라미터가 있으면 받아오고 없으면 디폴트로 0을 저장해 model객체로 넘겨줍니다. 
	@GetMapping(path = "/guestbook/list")
	public String getGuestBookAndCount(@RequestParam(name = "start", required = false, defaultValue = "0") int start, ModelMap model) {
		List<GuestBook> guestBooks = guestBookDao.selectAll();
		
		int count = guestBookDao.selectCount();
		
//		for(GuestBook g : guestBooks)
//			System.out.println(g);
		
		model.addAttribute("guestBooks",guestBooks);
		model.addAttribute("count",count);
		model.addAttribute("start",start);

		return "list";
	}
	
	// guestbook/write post요청이 들어오면 파라미터르 name과 content를 받아서 객체를 만들어 selfguestbook테이블에 insert합니다.
	// 방명록이 입력될 때 insertLog 메서드를 이용해 selfguestbooklog 테이블에 상태(삭제 or 삽입)정보와 ip주소를 insert합니다.
	@PostMapping(path ="/guestbook/write")
	public String insertGuestBook(@RequestParam(name = "name", required = true) String name,
									@RequestParam(name = "content", required = true) String cotent,
										HttpServletRequest request) {
		
		insertLog(request, "write");
		
		GuestBook guestbook = new GuestBook(name, cotent);
		
		guestBookDao.insert(guestbook);
		
		return "redirect:list";
	}
	
	// guestbook/delete post요청이 들어오면 파라미터르 id를 받아서 해당 id의 정보를 selfguestbook테이블에서 delete합니다.
	// 방명록이 입력될 때 selfguestbooklog 테이블에 상태(삭제 or 삽입)정보와 ip주소를 insert합니다.
	@PostMapping(path = "/guestbook/delete")
	public String deleteById(@RequestParam(name = "id", required = true) int id,
								HttpServletRequest request) {
		insertLog(request, "delete");
		
		guestBookDao.deleteById(id);
		return "redirect:list";
	}
	
	// ip주소와 상태(삭제or삽입)을 insert해주는 메서드
	public int insertLog(HttpServletRequest request, String method) {
		String ip = request.getRemoteAddr();
		GuestBookLog guestBookLog = new GuestBookLog(ip, method);
		
		return guestBookLogDao.insert(guestBookLog); 
	}
}
