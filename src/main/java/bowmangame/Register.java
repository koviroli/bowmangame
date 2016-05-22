package bowmangame;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Register {
	
	
	public Register(){
		try {
			Stage stage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Register.fxml"));
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
