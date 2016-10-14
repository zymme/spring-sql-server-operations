package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;


@SpringBootApplication
public class SqlqueryApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(SqlqueryApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;


	public static void main(String[] args) {
		SpringApplication.run(SqlqueryApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		log.info("starting query for sql server");
			
		String sqlSelect = "SELECT * FROM zed_test_customer";
		List<Customer> listCustomers = this.jdbcTemplate.query(sqlSelect, new RowMapper<Customer>() {
			
			public Customer mapRow(ResultSet result, int rowNum) throws SQLException {
				Customer customer = new Customer();
				customer.setFirst_name(result.getString("first_name"));
				customer.setLast_name(result.getString("last_name"));
				customer.setId(Long.parseLong(result.getString("ID")));
				return customer;
			}
		});
		
		for(Customer cust : listCustomers) {
			System.out.println(cust);
		}
		
		SpringTranManagerCustomerTransaction springCustomerManager = new SpringTranManagerCustomerTransaction(jdbcTemplate);
		
		springCustomerManager.testInsert(false);
		
		
		System.out.println("Application finished ... exiting ...");
		System.exit(0);
		
	}
	
}
