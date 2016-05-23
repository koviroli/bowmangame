import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bowmangame.DBHandler;
import bowmangame.User;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class DBTest {

	public static Logger logger = LoggerFactory.getLogger(DBHandler.class);
	
	@Test
	public void DBTest1(){
		User user = new User("username1", "pw123", "user1@gmail.com");
		
		assertEquals("username1", user.getUsername());
		assertEquals("pw123", user.getPassword());
	}
	
	@Test
	public void DBTestWrongUser(){
		User user = new User("username1", "pw123", "user1@gmail.com");
		
		DBHandler dbhandler = new DBHandler();
		
		assertEquals(null, dbhandler.DBLogin(user.getUsername(), user.getPassword()));
	}
	
	@Test
	public void DBTest2(){
		User user = new User("test1", "testpw123", "test1@hotmail.com");
		DBHandler dbhandler = new DBHandler();
		
		assertEquals(false, dbhandler.newRegistry(user));
	}
	
	
	@Test
	public void DBTest4LastLogin(){
		User user = new User("test1", "testpw123", "test1@hotmail.com");	
		int old_points = user.getPoints();
		
		DBHandler dbhandler = new DBHandler();
		user.setLastLogin(LocalDate.now().toString());
		
		dbhandler.DBLogin(user.getUsername(), user.getPassword());
		
		assertEquals(old_points, user.getPoints());
	}
	
	@Test
	public void DBTestUserLevel(){
		User user = new User("test1", "testpw123", "test1@hotmail.com");
		DBHandler dbhandler = new DBHandler();
		
		user.setPoints(11);
		dbhandler.modifyUserPoints(user);
	}
	
	

	
}



