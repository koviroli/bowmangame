// CHECKSTYLE:OFF
package controller;

import model.ArcherModel;
import model.BowManGameObject;
import view.ArcherView;

public class ArcherController extends BowManGameObject{
	
	private ArcherModel model = new ArcherModel();
	private ArcherView view = new ArcherView(model);
	
	public ArcherController() {
		setPosX(adx);
		setPosY(ady);
		setCenterX();
		setCenterY();
	}

	public ArcherModel getModel() {
		return model;
	}

	public void setModel(ArcherModel archerM) {
		this.model = archerM;
	}

	public void getView(ArcherView archerV) {
		this.view = archerV;
	}
	
	public ArcherView setView() {
		return view;
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
	
	public void setCenterX(){
		model.setCenterX(getPosX() + (model.getImage().getWidth() / 2.0f));
	}
	
	public void setCenterY(){
		model.setCenterY(getPosY() + (model.getImage().getHeight() / 2.0f));
	}
	
	public void update(){
		view.update();
	}
}
