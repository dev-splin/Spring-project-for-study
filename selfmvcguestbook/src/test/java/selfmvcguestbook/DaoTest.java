package selfmvcguestbook;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.selfmvcguestbook.config.ApplicationConfig;
import kr.or.connect.selfmvcguestbook.dao.GuestBookLogDao;
import kr.or.connect.selfmvcguestbook.dto.GuestBookLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DaoTest {
	@Autowired
	GuestBookLogDao selfGuestBookLogDao;
	
	@Test
	public void logInsert() throws Exception {
		GuestBookLog log = new GuestBookLog();
		log.setIp("111");
		log.setMethod("insert");
		log.setRegdate(new Date());
		
		int result = selfGuestBookLogDao.insert(log);
		
		Assert.assertEquals(result, 1);
	}
}
