package com.fdmgroup.finalproject.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.finalproject.dao.UserDAO;
import com.fdmgroup.finalproject.exceptions.JPAException;
import com.fdmgroup.finalproject.model.User;

public class TestUserDao {

	private UserDAO classUnderTest;
	private User testUser;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new UserDAO(javax.persistence.Persistence.createEntityManagerFactory("finalproject"));
		testUser = new User("testUser2", "testPassword", "testFirstName",
				"testLastName", "testEmail");
		if (classUnderTest.getUser(testUser.getUsername()) != null) {
			classUnderTest.removeUser(testUser.getUsername());
		}
		testUser = new User("testUser", "testPassword", "testFirstName",
				"testLastName", "testEmail");
		if (classUnderTest.getUser(testUser.getUsername()) != null) {
			classUnderTest.removeUser(testUser.getUsername());
		}
		
		classUnderTest.addUser(testUser);
	}

	@After
	public void tearDown() throws JPAException {

	}

	@Test
	public void test_addUser_Password() throws JPAException {
		String actual = classUnderTest.getUser(testUser.getUsername())
				.getPassword();
		String expected = testUser.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void test_getUser_FirstName() throws JPAException {
		User user = classUnderTest.getUser(testUser.getUsername());
		String actual = user.getFirstName();
		String expected = testUser.getFirstName();
		assertEquals(expected, actual);
	}

	@Test
	public void test_removeUser() throws JPAException {
		classUnderTest.removeUser(testUser.getUsername());
		User user = classUnderTest.getUser(testUser.getUsername());
		assertNull(user);
	}

	@Test
	public void test_updateUser_Password() throws JPAException {
		testUser.setPassword("New_testPassword");
		classUnderTest.updateUser(testUser);
		User user = classUnderTest.getUser(testUser.getUsername());
		String actual = user.getPassword();
		String expected = testUser.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void test_updateUser_FirstName() throws JPAException {
		testUser.setFirstName("New_testFirstName");
		classUnderTest.updateUser(testUser);
		User user = classUnderTest.getUser(testUser.getUsername());
		String actual = user.getFirstName();
		String expected = testUser.getFirstName();
		assertEquals(expected, actual);
	}

	@Test
	public void test_updateUser_LastName() throws JPAException {
		testUser.setLastName("New_testLastName");
		classUnderTest.updateUser(testUser);
		User user = classUnderTest.getUser(testUser.getUsername());
		String actual = user.getLastName();
		String expected = testUser.getLastName();
		assertEquals(expected, actual);
	}

	@Test
	public void test_updateUser_Email() throws JPAException {
		testUser.setEmail("New_testEmail");
		classUnderTest.updateUser(testUser);
		User user = classUnderTest.getUser(testUser.getUsername());
		String actual = user.getEmail();
		String expected = testUser.getEmail();
		assertEquals(expected, actual);
	}

	@Test(expected = JPAException.class)
	public void test_updateUser_NullInput() throws JPAException {
		classUnderTest.updateUser(null);
	}

	@Test(expected = JPAException.class)
	public void test_addUser_NullInput() throws JPAException {
		classUnderTest.addUser(null);
	}

	@Test(expected = JPAException.class)
	public void test_addUser_DuplicateUsername() throws JPAException {
		classUnderTest.addUser(testUser);
	}

	@Test
	public void test_listUsers_count() throws JPAException {
		int originalCount = classUnderTest.listUsers().size();
		testUser.setUsername("testUser2");
		testUser.setEmail("testEmail2");
		classUnderTest.addUser(testUser);
		int actualCount = classUnderTest.listUsers().size();
		classUnderTest.removeUser("testUser2");
		int expectedCount = originalCount + 1;
		assertEquals(expectedCount, actualCount);
	}

	@Test(expected = JPAException.class)
	public void test_removeUser_NotExist() throws JPAException {
		testUser.setUsername("testUser2");
		classUnderTest.removeUser(testUser.getUsername());
	}

	@Test(expected = JPAException.class)
	public void test_updateUser_NotExists() throws JPAException {
		testUser.setEmail("New_testEmail");
		testUser.setUsername("testUser2");
		classUnderTest.updateUser(testUser);
	}
}