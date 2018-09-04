package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import monsterGame.Login;
import monsterGame.SignUp;
import monsterGame.User;

public class TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	String username="abc";
	String pwd="123456";
	String pwd0="123456";
	String pwd1="12340056";
	User a = new User(username, pwd.toCharArray());
	User b = new User("username0", "password".toCharArray());
	Login admin = new Login();
	SignUp test0 = new SignUp();
	@Before
	public void setUp() throws Exception {
		a.setScore(100);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSignUp() {
		//SignUp
		assertTrue(test0.pwdCompare(pwd.toCharArray(), pwd0.toCharArray()));
		//test if two password is same signup success
		assertFalse(test0.pwdCompare(pwd.toCharArray(), pwd1.toCharArray()));
		//test if two password is not same signup fail
	}
	@Test
	public void testLogin() {
		//Login
		assertTrue(admin.auth(username, pwd.toCharArray()));
		//test if the username/password combination is correct, login success
		assertFalse(admin.auth("cba", pwd.toCharArray()));
		//test if the username/password combination is not correct, login fail
	}
	@Test
	public void testUser() {
		//User
		assertEquals(a, admin.userList.get(0));
		//test if a user is successfully added
		assertEquals("abc",a.getUserName());
		//test if the username matches
		assertEquals("123456",a.getPwdString());
		//test if the password matches
		assertEquals(100, a.getScore());
		//test if read correct value for score from user object.
	}

}
