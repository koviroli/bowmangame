import org.junit.Test;

import bowmangame.DBHandler;
import bowmangame.User;

import static org.junit.Assert.*;

public class PlayerPointsTest {

	@Test
	public void DBTest(){
		User user = new User("username1", "pw123", "user1@gmail.com");
		
		assertEquals("username1", user.getUsername());
		assertEquals("pw123", user.getPassword());
	}
	
}
