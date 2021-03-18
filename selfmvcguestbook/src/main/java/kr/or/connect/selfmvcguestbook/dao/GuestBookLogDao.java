package kr.or.connect.selfmvcguestbook.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.selfmvcguestbook.dto.GuestBook;
import kr.or.connect.selfmvcguestbook.dto.GuestBookLog;

@Repository
public class GuestBookLogDao {
	private SimpleJdbcInsert insertAction;
	private RowMapper<GuestBook> rowMapper = BeanPropertyRowMapper.newInstance(GuestBook.class);
	
	public GuestBookLogDao(DataSource dataSource) {
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("selfguestbooklog");
	}
	
	public int insert(GuestBookLog selfGuestBookLog) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(selfGuestBookLog);
		
		return insertAction.execute(params);
	}
}
