import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login {

	public Login() {	
		try {
			Stage stage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
	    	
		} catch (IOException e) {
			System.err.println("something went wrong while loading the Login.fxml");
			e.printStackTrace();
		}
	}
}
