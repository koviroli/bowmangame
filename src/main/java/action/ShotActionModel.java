package action;

import model.BowManGameObject;


public class ShotActionModel extends BowManGameObject {
	/**
	 * A lövés szöge.
	 */
	protected double angle;
	
	/**
	 * A lövés ereje.
	 */
	protected double power;
	
	/**
	 * A lövés kezdő koordinátái. sx az x, sy az y koordinátája a lövésnek.
	 */
	protected double sx, sy;
	
	/**
	 * A céltábla kezdő és vég koordinátái.
	 */
	protected double tx1, ty1, tx2, ty2;
	
	/**
	 * A hit azt jelzi eltaláltuk-e a céltáblát vagy sem.
	 */
	protected boolean hit = false;
	

	public ShotActionModel() {
	
	}
	
	public ShotActionModel(double x, double y){
		sx = x;
		sy = y;
	}
	

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getSx() {
		return sx;
	}

	public void setSx(double sx) {
		this.sx = sx;
	}

	public double getSy() {
		return sy;
	}

	public void setSy(double sy) {
		this.sy = sy;
	}
	
	public void setSxy(double sx, double sy){
		this.sx = sx;
		this.sy = sy;
	}

	public double getTx1() {
		return tx1;
	}
	
	public void setTx1(double tx1) {
		this.tx1 = tx1;
	}

	public double getTy1() {
		return ty1;
	}

	public void setTy1(double ty1) {
		this.ty1 = ty1;
	}
	
	public void setTxy1(double tx1, double ty1){
		this.tx1 = tx1;
		this.ty1 = ty1;
	}
	
	public double getTx2() {
		return tx2;
	}
	
	public void setTx2(double tx2) {
		this.tx2 = tx2;
	}

	public double getTy2() {
		return ty2;
	}

	public void setTy2(double ty2) {
		this.ty2 = ty2;
	}
	
	public void setTxy2(double tx2, double ty2){
		this.tx2 = tx2;
		this.ty2 = ty2;
	}
	
    /**
     * A lövés ívét szolgátató függvény
     * @param x egy double változó amit a shotArc függvénybe helyettesítve megkapjuk a hozzátartozó y értéket.
     * @return	a függvény x-hez tartozó y értéke.
     */
    public double shotArc(double x){
    	double y = (Math.tan(Math.toRadians(angle)) * x) 
    			- (G / (2.0f * Math.pow(power, 2) * Math.pow(Math.cos(Math.toRadians(angle)), 2))) * Math.pow(x, 2);
    	
    	return y;
    }
    
    /**
     * A matematikai definíciója ennek a függvénynek:(v0^2) * sin(2*alfa)/g
     * @return Adott erejű és szögű lövés esetén a maximum x értéket adja vissza. 
     */
    public double xmax(){
    	double max = (Math.pow(power, 2) * Math.sin(2 * Math.toRadians(angle))) / G;
    	return max;
    }
   
	/**
	 * Inicializáljuk a képet, az ut és az animációt.
	 * @return
	 */
	public boolean Initalize(){

    	
    	double arcx = 0.0f, arcy = 0.0f;
    	for(double i = this.sx; arcy < WINDOW_HEIGHT; i += 1.0f){
	    	arcx = i;
	    	arcy = -shotArc(i)+this.sy;
    		
    		if((arcy > ty1) && (arcy < ty2) && (Math.abs(arcx - tx1) < 5.0f)){ 
    			return true;
    			//break;
    		}
	    }
    	return false;
    }
	
    /**
     * Maga a lövést reprezentáló függvény.
     * @return igazat ad vissza, ha talált, egyébként hamisat.
     */
    public boolean action(){	  
    	hit = Initalize();

	    return hit;
    }
}
