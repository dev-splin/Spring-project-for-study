package kr.or.connect.selfmvcguestbook.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DBConfig {
	private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&useSSL=false";

    private String username = "connectuser";
    private String password = "connect123!@#";
    
    // datasource를 이용해 DB에 쉽게 연결할 수 있습니다.
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(driverClassName);
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	return dataSource;
    }
}
