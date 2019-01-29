package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	Map<Long, Account> accountMap = new HashMap<>();

	JSONUtil jsonUtil = new JSONUtil();
	
	public Map<Long, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Long, Account> accountMap) {
		this.accountMap = accountMap;
	}

	public String getAllAccounts() {

		return jsonUtil.getJSONForObject(accountMap.values());		
	
	}

	public String createAccount(String account) {

		Account myAccount = jsonUtil.getObjectForJSON(account, Account.class);
		
		accountMap.put(myAccount.getAccountNumber(), myAccount);
		
		return null;
	
	}

	public String deleteAccount(Long id) {

		accountMap.remove(id);
		
		return null;
		
	}

	public String updateAccount(Long id, String account) {

		Account myAccount = jsonUtil.getObjectForJSON(account, Account.class);
		
		accountMap.put(id, myAccount);
		
		return null;
	}
 
}
