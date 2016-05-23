// CHECKSTYLE:OFF
package view;

import javafx.scene.image.Image;
import model.ArcherModel;

public class ArcherView extends GameObjectView {
	
	//Constructor
	public ArcherView(ArcherModel archerModel) {
		super(archerModel);
		archerModel.setImage(new Image("pictures/archer/archer_image_v3.png"));
	}
}
