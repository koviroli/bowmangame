import org.junit.Test;
import static org.junit.Assert.*;

import action.ShotActionModel;


public class GameShotTest {
	
	@Test
	public void HitTestSucces(){
		
		ShotActionModel shot = new ShotActionModel(50.0f, 500.0f);
		
		shot.setPower(150);
		shot.setAngle(16);
		shot.setTx1(700);
		shot.setTx1(700);
		shot.setTy1(400);
		shot.setTy2(600);
		
		assertEquals(true, shot.action());
	}
	
	@Test
	public void HitTestFailure(){
		ShotActionModel shot = new ShotActionModel(50.0f, 500.0f);
		
		shot.setPower(150);
		shot.setAngle(45);
		shot.setTx1(700);
		shot.setTx1(700);
		shot.setTy1(400);
		shot.setTy2(600);
		
		assertEquals(false, shot.action());
	}
	
	@Test
	public void XmaxTest1(){
		ShotActionModel shot = new ShotActionModel(50, 500);
		
		shot.setPower(150);
		shot.setAngle(45);
		shot.setTx1(700);
		shot.setTx1(700);
		shot.setTy1(400);
		shot.setTy2(600);
		
		assertEquals(true, shot.xmax() > 800.0);
	}
	
	@Test
	public void XmaxTest2(){
		ShotActionModel shot = new ShotActionModel(50, 500);
		
		shot.setPower(50);
		shot.setAngle(30);
		shot.setTx1(700);
		shot.setTx1(700);
		shot.setTy1(400);
		shot.setTy2(600);
		
		assertEquals(true, shot.xmax() < 600.0);
	}
}
