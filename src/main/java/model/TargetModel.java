package model;

import javafx.scene.image.Image;

public class TargetModel extends GameObjectModel{
	
	//x1, y1 representing the start coordinates of the target
	private double x1, y1;
	
	//x2, y2 representing the ending coordinates of the target
	private double x2, y2;
	
	//constructor
	public TargetModel() {
		super();
		setImage(new Image("pictures/target/target100.png"));
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}
	
	public void setXY1(double x1, double y1){
		this.x1 = x1;
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
	
	public void setXY2(double x2, double y2){
		this.x2 = x2;
		this.y2 = y2;
	}	
}


