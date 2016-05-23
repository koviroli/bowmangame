 // CHECKSTYLE:OFF
package controller;

import model.BowManGameObject;
import model.TargetModel;
import view.TargetView;

public class TargetController extends BowManGameObject{
	
	private TargetModel model = new TargetModel();
	private TargetView view = new TargetView(model);
	
	public TargetController() {
		setPosX(tdx1);
		setPosY(tdy1);
		model.setX1(getPosX());	
		model.setY1(getPosY());
		model.setX2(getPosX());	//x2 same as x1
		model.setY2(getPosY() + model.getImage().getHeight()); //y2 is y1 + the height of the image
	}

	public TargetModel getModel() {
		return model;
	}

	public void setModel(TargetModel model) {
		this.model = model;
	}

	public TargetView getView() {
		return view;
	}

	public void setView(TargetView view) {
		this.view = view;
	}
	
	public void setPosX(double x){
		model.setPosX(x);
		update();
	}
	
	public double getPosX(){
		return model.getPosX();
	}
	
	public void setPosY(double y){
		model.setPosY(y);
		update();
	}
		
	public double getPosY(){
		return model.getPosY();
	}
	
	public double getX1(){
		return model.getX1();
	}
	
	public void setX1(double x1){
		model.setX1(x1);
	}
	
	public double getY1(){
		return model.getY1();
	}
	
	public void setY1(double y1){
		model.setY1(y1);
	}
	
	public double getX2(){
		return model.getX2();
	}
	
	public void setX2(double x2){
		model.setX2(x2);
	}
	
	public double getY2(){
		return model.getY2();
	}

	
	public void setRotation(double angle){
		model.setRotation(angle);
	}
	
	public double getRotation(){
		return model.getRotation();
	}
	
	public void rotate(double cx, double cy){
		view.rotate(cx, cy);
	}
	
	public void update(){
		view.update();
	}
}
