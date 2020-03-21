package com.example.db.demo_h2_jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoH2JdbcApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoH2JdbcApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		showConnection();
		showData();
	}
	
	private void showConnection() throws SQLException{
		log.info(dataSource.toString());
		Connection conn = dataSource.getConnection();
		log.info(conn.toString());
		conn.close();
	}
	
	private void showData() {
		jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> log.info(row.toString()));
	}
}
