package com.fdmgroup.finalproject.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.finalproject.model.User;

public class TestUser {
	
	private User classUnderTest;
	

	@Before
	public void setUp() throws Exception {
		classUnderTest = new User("testUser", "testPassword","testFirstName", "testLastName", "testEmail");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_NullUsername() throws IllegalArgumentException {
		classUnderTest.setUsername(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_NullPassword() throws IllegalArgumentException {
		classUnderTest.setPassword(null);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void test_NullFirstName() throws IllegalArgumentException {
		classUnderTest.setFirstName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_NullLastName() throws IllegalArgumentException {
		classUnderTest.setLastName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_NullEmail() throws IllegalArgumentException {
		classUnderTest.setEmail(null);
	}

	

}
