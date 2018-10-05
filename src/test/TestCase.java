package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.User;
import monsterGame.Login;
import monsterGame.SignUp;

public class TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	Login admin = new Login();
	SignUp test0 = new SignUp();
	
	
	@Before
	public void setUp() throws Exception {
		User test = new User("abc", "123456".toCharArray());
		admin.userList.add(test);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSignUp1() {
		// SignUp
		char[] pwd = "123456".toCharArray();
		char[] pwd0 = "123456".toCharArray();
		boolean result = test0.pwdCompare(pwd, pwd0);
		assertTrue(result);
		// test if two password is same signup success
	}

	@Test
	public void testSignUp2() {
		char[] pwd1 = "123456".toCharArray();
		char[] pwd2 = "1234567".toCharArray();
		boolean result = test0.pwdCompare(pwd1, pwd2);
		assertFalse(result);
		// test if two password is not same signup fail
	}

	@Test
	public void testLogin1() {
		// Login
		String username = "abc";
		char[] pwd = "123456".toCharArray();
		boolean result = admin.auth(username, pwd);
		assertTrue(result);
		// test if the username/password combination is correct, login success
	}

	@Test
	public void testLogin2() {
		String username = "cba";
		char[] pwd = "123456".toCharArray();
		boolean result = admin.auth(username, pwd);
		assertFalse(result);
		// test if the username/password combination is not correct, login fail
	}


	@Test
	public void testUser1() {
		User a = new User("abc", "123456".toCharArray());
		a.setScore(100);
		int score = a.getScore();
		assertEquals(100, score);
		// test if read correct value for score from user object.
	}

}
