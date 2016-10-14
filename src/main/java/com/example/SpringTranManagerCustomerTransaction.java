package com.example;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


public class SpringTranManagerCustomerTransaction {

	private PlatformTransactionManager transactionManager;

	public SpringTranManagerCustomerTransaction() 
	{	
		
	}
	
	public void setTransactionManager(PlatformTransactionManager txManager) {
		this.transactionManager = txManager;
	}
	
	public void testInsert(boolean pass) throws Exception {
		
	}
}
