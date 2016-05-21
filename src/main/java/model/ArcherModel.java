package model;

import javafx.scene.image.Image;

public class ArcherModel extends GameObjectModel{
	
	public ArcherModel() {
		super();
		setImage(new Image("pictures/archer/archer_image_v3.png"));
	}
	
	//this class for the archer's left arm
	static class LeftArm extends GameObjectModel{
		public LeftArm() {
			super();
			setImage(new Image(""));
		}
	}
	
	//this class is for archer's right arm
	static class RightArm extends GameObjectModel{
		public RightArm() {
			super();
			setImage(new Image(""));
		}
	}
	
}
