// CHECKSTYLE:OFF
package model;

import javafx.scene.image.Image;

//every xyModel inherit this class
public class GameObjectModel extends BowManGameObject{
	
	//posX is for model's position on x-axis and
	//posY is for model's position on y-axis
	private double posX, posY;
	
	//the X- and Y coordinate of the center of the model
	//This will be initalized in the view class
	private double centerX, centerY;
	
	//rotation of the model
	private double rotation;
	
	//image that representing the model
	private Image image;
	
	//every object starts on the origo till posX or posY have not been set
	public GameObjectModel() {
		posX = 0.0f;
		posY = 0.0f;
	}
	
	public double getPosX() {
		return posX;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	
	public double getCenterY() {
		return centerY;
	}
	
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
}
