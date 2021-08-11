package mysql.hikari.lombok.spring.connection.test;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mysql.hikari.lombok.spring.main.SpringBootLombokMain;

/**
 * Test Spring Boot Application with JUnit Test.
 * 
 * @author metanoia
 * @version 1.0
 */

@SpringBootTest(classes = SpringBootLombokMain.class)
public class DataSourceTest {

	@Autowired
	private DataSource dataSource; // interface

	@Test
	public void testHikariCPConnection() throws Exception {
		System.out.println(dataSource.getConnection());
	}

}
