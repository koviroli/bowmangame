import org.junit.Test;
import static org.junit.Assert.*;

import model.ArcherModel;
import model.ArrowModel;
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
	public void BowPositionTest(){
		BowModel bow = new BowModel();
		bow.setPosX(20.0f);
		bow.setPosY(25.0f);
		
		assertEquals(20.0f, bow.getPosX(), 0.0f);
		assertEquals(25.0f, bow.getPosY(), 0.0f);
	}
	
	
	
	@Test
	public void ArcherpositionTest1(){
		ArcherModel archer = new ArcherModel();
		archer.setPosX(200.0f);
		archer.setPosY(250.0f);
		
		assertEquals(200, archer.getPosX(), 0.0f);
		assertEquals(250.0f, archer.getPosY(), 0.0f);

	}
	
	@Test
	public void ArrowPositionTest(){
		ArrowModel arrow = new ArrowModel();
		arrow.setPosX(300.0f);
		arrow.setPosY(301.0f);
		
		assertEquals(300, arrow.getPosX(), 0.0f);
		assertEquals(301.0f, arrow.getPosY(), 0.0f);
	}
	
}
