package com.qa.MapTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	JSONUtil jsonUtil;
	
	AccountMapRepository myRepo;
	
	@Before
	public void setup() {
		
		myRepo = new AccountMapRepository();
		jsonUtil = new JSONUtil();
		
	}
	
	@Test
	public void addAccountTest() {
		
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));
		
		Account[] accounts = jsonUtil.getObjectForJSON(myRepo.getAllAccounts(), Account[].class);
		
		assertEquals("John", accounts[0].getFirstName());
		
	}
	
	@Test
	public void add2AccountTest() {
		
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("Sam", "Davis", 2)));
		
		Account[] accounts = jsonUtil.getObjectForJSON(myRepo.getAllAccounts(), Account[].class);
		
		assertEquals("John", accounts[0].getFirstName());
		assertEquals("Sam", accounts[1].getFirstName());
		
	}

	@Test
	public void removeAccountTest() {

		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));

		assertEquals("Remove successful!", myRepo.deleteAccount((long) 1));
		
	}
	
	@Test
	public void remove2AccountTest() {

		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("Sam", "Davis", 2)));

		assertEquals("Remove successful!", myRepo.deleteAccount((long) 1));
		assertEquals("Remove successful!", myRepo.deleteAccount((long) 2));
		
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {

		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));

		assertEquals("Remove successful!", myRepo.deleteAccount((long) 1));
		assertEquals("Remove unsuccessful!", myRepo.deleteAccount((long) 2));
		
	}
	
	@Test
	public void accountConversionToJSONTestWithEmptyMap() {
	
	}
	
	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion() {
	
	}

	@Test
	public void accountConversionToJSONTest() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("Sam", "Davis", 2)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Wayne", 3)));
		
		assertEquals(0, myRepo.findName("Jayne"));
		
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("Sam", "Davis", 2)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Wayne", 3)));
		
		assertEquals(1, myRepo.findName("Sam"));
			
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {

		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Smith", 1)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("Sam", "Davis", 2)));
		myRepo.createAccount(jsonUtil.getJSONForObject(new Account("John", "Wayne", 3)));
		
		assertEquals(2, myRepo.findName("John"));
		
	}

}
