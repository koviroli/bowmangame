import org.junit.Test;
import static org.junit.Assert.*;

import model.ArcherModel;
import model.BowModel;

public class GameTest {
	
	@Test
	public void rotationTest1(){
		BowModel bow = new BowModel();
		bow.setRotation(45.0f);
		assertEquals(45.0f, bow.getRotation(), 0.0f);
	}
	
	@Test
	public void rotationTest2(){
		BowModel bow = new BowModel();
		bow.setRotation(90.0f);
		assertNotEquals(90.1f, bow.getRotation());
	}
	
	@Test
	public void positionTest1(){
		ArcherModel archer = new ArcherModel();
		archer.setPosX(200.0f);
		archer.setPosY(250.0f);
		assertEquals(200, archer.getPosX(), 0.0);
	}
	
}
