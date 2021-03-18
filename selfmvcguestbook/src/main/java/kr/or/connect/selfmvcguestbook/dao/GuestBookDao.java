package kr.or.connect.selfmvcguestbook.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.selfmvcguestbook.dto.GuestBook;

//static import를 사용하면 RoleDaoSqls 객체에 선언된 static 변수를 클래스 이름없이 바로 사용할 수 있습니다.
import static kr.or.connect.selfmvcguestbook.dao.GuestBookDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

@Repository
public class GuestBookDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<GuestBook> rowMapper = BeanPropertyRowMapper.newInstance(GuestBook.class);
	
	public GuestBookDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("selfguestbook");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// selfguestbook 테이블에서 모든 데이터를 가져와 리스트에 넣어 반환하는 메서드
	public List<GuestBook> selectAll() {
		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
	}
	
	// guestbook객체를 받아 insert해주는 메서드
	public int insert(GuestBook guestBook) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(guestBook);
	
		return insertAction.execute(params);
	}
	
	// selfguestbook테이블의 모든 row 개수를 반환해주는 메서드(jdbcTemplate을 사용했습니다.)
	public int selectCount() {
		return this.jdbcTemplate.queryForObject(SELECT_COUNT,Integer.class);
	}
	
	// 인자로 받은 id에 해당하는 row를 db에서 지워주는 메서드
	public int deleteById(int id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, params);
	}
	
}
