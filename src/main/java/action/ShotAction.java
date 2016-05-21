package action;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.BowManGameObject;
import model.GameObjectModel;
import model.TargetModel;

public class ShotAction extends BowManGameObject{
	
	//path will be the path of the object that have been shot
	private Path path = new Path();
	private PathTransition pTransition = new PathTransition();
	
	//the imageview, representing the image.
	//later will get an image
	private ImageView imw = new ImageView();
	
	private Image image;
	
	//angle of the shot
	private double angle;
	
	//power of the shot
	private double power;
	
	//the starting position of the shot
	private double sx, sy;
	
	//the target's position
	private double tx1, ty1, tx2, ty2;
	
	//sign hit or not
	private boolean hit = false;
	
	//default constructor
	public ShotAction() {
	
	}
	
	//shot action need a model to be shot, and a target to be hit
	public ShotAction(GameObjectModel model, TargetModel target){
		image = model.getImage();
		//imw.setImage(model.getImage());
		sx = model.getPosX();
		sy = model.getPosY();
		angle = model.getRotation();
		imw.setTranslateX(model.getPosX());
		imw.setTranslateY(model.getPosY());
		this.tx1 = target.getX1();
		this.ty1 = target.getY1();
		this.tx2 = target.getX2();
		this.ty2 = target.getY2();
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public PathTransition getpTransition() {
		return pTransition;
	}

	public void setpTransition(PathTransition pTransition) {
		this.pTransition = pTransition;
	}

	public ImageView getImw() {
		return imw;
	}

	public void setImw(ImageView imw) {
		this.imw = imw;
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
	
    //the arc of the shot
    public double shotArc(double x){
    	double y = (Math.tan(Math.toRadians(angle)) * x) 
    			- (G / (2.0f * Math.pow(power, 2) * Math.pow(Math.cos(Math.toRadians(angle)), 2))) * Math.pow(x, 2);
    	
    	return y;
    }
    
    //the maximum value of the shot
    //(v0^2)*sin(2*alfa)/g
    public double xmax(){
    	double max = (Math.pow(power, 2) * Math.sin(2 * Math.toRadians(angle))) / G;
    	return max;
    }
    
    //the time of the shot
    // 2 * v0 * sin(alfa) / g
    public double to(){
    	double t;
    	t = ((2.0f * power * Math.sin(Math.toRadians(angle)))/G) / 10.0f;
    	return t;
    }
	
	public boolean Initalize(){
		imw.setImage(image);
	    path.setOpacity(0.0);
	    pTransition.setDuration(Duration.seconds(1.0f));
	    pTransition.setDelay(Duration.seconds(0.0f));
	    pTransition.setPath(path);
	    pTransition.setNode(imw);
	    pTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

    	path.getElements().add(new MoveTo(this.sx, this.sy));
    	double arcx = 0.0f, arcy = 0.0f;
    	for(double i = this.sx; arcy < WINDOW_HEIGHT; i += 1.0f){
	    	arcx = i;
	    	arcy = -shotArc(i)+this.sy;
    		path.getElements().add(new LineTo(arcx, arcy ));
    		if((arcy > ty1) && (arcy < ty2) && (Math.abs(arcx - tx1) < 5.0f)){ 
    			return true;
    			//break;
    		}
	    }
    	return false;
    }
	
    //plays the animation
    public boolean action(){	  
    	hit = Initalize();
	    pTransition.play();
	    path.getElements().clear();
	    return hit;
    }	
}
