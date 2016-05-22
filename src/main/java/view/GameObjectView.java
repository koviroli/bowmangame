// CHECKSTYLE:OFF
package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import model.BowManGameObject;
import model.GameObjectModel;

public class GameObjectView extends BowManGameObject {
	
	protected GameObjectModel model;
	private Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
	private GraphicsContext gc;
	
	public GameObjectView(GameObjectModel model) {
		this.model = model;
		setGc();
	}
	
	public GameObjectModel getModel() {
		return model;
	}
	
	public void setModel(GameObjectModel model) {
		this.model = model;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	
	public void setGc() {
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(model.getImage(), model.getPosX(), model.getPosY());
	}
	
	public void rotate(double cx, double cy){
		Rotate r = new Rotate(-model.getRotation(), cx, cy);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		update();
	}
	
	public void update(){
		gc = canvas.getGraphicsContext2D();	
		gc.clearRect(0.0, 0.0, WINDOW_WIDTH, WINDOW_HEIGHT);
		gc.drawImage(model.getImage(), model.getPosX(), model.getPosY());
	}	
}
