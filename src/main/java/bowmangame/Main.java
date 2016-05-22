package bowmangame;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		@SuppressWarnings("unused")
		Login login = new Login();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
