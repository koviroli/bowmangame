// CHECKSTYLE:OFF
package view;

import javafx.scene.image.Image;
import model.ArrowModel;

public class ArrowView extends GameObjectView {
	
	public ArrowView(ArrowModel arrowModel) {
		super(arrowModel);
		arrowModel.setImage(new Image("pictures/arrow/arrow_v3.png"));
	}
}
