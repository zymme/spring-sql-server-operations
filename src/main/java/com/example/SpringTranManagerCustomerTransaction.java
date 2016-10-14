package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


public class SpringTranManagerCustomerTransaction {

	private PlatformTransactionManager transactionManager;

	private JdbcTemplate jdbcTemplate;
	
    public DataSource dataSource() {
        // configure and return the necessary JDBC DataSource
		return this.jdbcTemplate.getDataSource();
    }
	
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

	public SpringTranManagerCustomerTransaction() {

	}
	
	public SpringTranManagerCustomerTransaction(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	public void setTransactionManager(PlatformTransactionManager txManager) {
		this.transactionManager = txManager;
	}

	public void testInsert(boolean pass) throws Exception {

		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = null;
		this.setTransactionManager(this.txManager());
		
		try {

			txStatus = transactionManager.getTransaction(txDef);

			String sqlInsert = "INSERT INTO zed_test_customer (first_name, last_name) " + " VALUES ('Audra', 'Zimmer')";
			this.jdbcTemplate.execute(sqlInsert);

			if (!pass) {
				throw new Exception("ERROR OCCURED - IN INSERT !!");
			}

			// commit transation
			this.transactionManager.commit(txStatus);
			System.out.println("COMMITED INSERT ---");
			
		} catch (Exception e) {
			System.out.println("Exception occured in Insert " + e.getMessage());
			transactionManager.rollback(txStatus);
			System.out.println("ROLLED BACK INSERT ---");
		}
	}
}
