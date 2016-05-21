package model;

public class BowManGameObject {
	
	//global window width
	protected static final double WINDOW_WIDTH = 800.0f;
	
	//global window height
	protected static final double WINDOW_HEIGHT = 600.0f;
	
	//global window's center's x coordinate
	protected static final double WINDOW_CENTERX = WINDOW_WIDTH / 2.0f;
	
	//global window's center's y coordinate
	protected static final double WINDOW_CENTERY = WINDOW_HEIGHT / 2.0f;
	
	//the Default X position of archer
	protected static final double adx = 20.0f;	
	
	//the Default Y position of archer
	protected static final double ady = WINDOW_HEIGHT - 120.0f;	
	
	//the Default X position of bow	
	protected static final double bdx = adx + 40.0f;	
	
	//the Default Y position of bow
	protected static final double bdy = ady + 15.0f ;	
	
	//the Default X position of arrow
	protected static final double ardx = bdx - 10.0f;	
	
	//the Default Y position of arrow
	protected static final double ardy = bdy + 27.0f;
	
	//the default X1 position of the target
	protected static final double tdx1 = WINDOW_WIDTH - 30.0f;
	
	//the default Y1 position of the target
	protected static final double tdy1 = WINDOW_HEIGHT - 200.0f;
	
	//G is representing the gravity
	protected static final double G = 9.81f;
	
}
