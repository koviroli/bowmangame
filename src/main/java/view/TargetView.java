// CHECKSTYLE:OFF
package view;

import javafx.scene.image.Image;
import model.TargetModel;

public class TargetView extends GameObjectView {

	public TargetView(TargetModel model) {
		super(model);
		model.setImage(new Image("pictures/target/target100.png"));
	}

}
