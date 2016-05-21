package controller;

import model.BowManGameObject;
import model.BowModel;
import view.BowView;

public class BowController extends BowManGameObject {
	
	private BowModel model = new BowModel();
	private BowView view = new BowView(model);
	
	public BowController() {
		setPosX(bdx);
		setPosY(bdy);
	}

	public BowModel getModel() {
		return model;
	}

	public void setModel(BowModel bowM) {
		this.model = bowM;
	}

	public BowView getView() {
		return view;
	}

	public void setView(BowView bowV) {
		this.view = bowV;
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
