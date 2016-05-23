// CHECKSTYLE:OFF
package view;

import javafx.scene.image.Image;
import model.BowModel;

public class BowView extends GameObjectView {

	//Constructor
	public BowView(BowModel bowModel) {
		super(bowModel);
		bowModel.setImage(new Image("pictures/bow/bow_v4.png"));
	}
}
