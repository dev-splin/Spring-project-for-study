package kr.or.connect.selfmvcguestbook.dao;

// sql문을 모아놓은 클래스입니다.
public class GuestBookDaoSqls {
	final static String SELECT_ALL = "SELECT id, name, content, regdate FROM selfguestbook order by id";
	final static String SELECT_COUNT = "SELECT count(*) FROM selfguestbook";
	final static String DELETE_BY_ID = "DELETE FROM selfguestbook WHERE id = :id";
}
