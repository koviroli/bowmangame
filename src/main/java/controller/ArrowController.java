package controller;

import model.ArrowModel;
import model.BowManGameObject;
import view.ArrowView;

public class ArrowController extends BowManGameObject {
	
	private ArrowModel model = new ArrowModel();
	private ArrowView view = new ArrowView(model);
	
	public ArrowController() {
		setPosX(ardx);
		setPosY(ardy);
	}

	public ArrowModel getModel() {
		return model;
	}

	public void setModel(ArrowModel model) {
		this.model = model;
	}

	public ArrowView getView() {
		return view;
	}

	public void setView(ArrowView view) {
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
