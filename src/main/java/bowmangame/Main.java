package bowmangame;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * A legfelsőbb osztály, ez indítja az alkalmazást.
 * @author koviroli
 *
 */
public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		@SuppressWarnings("unused")
		Login login = new Login();
	}
	
	/**
	 * A fő függvény.
	 * @param args parancssori argumentumok.
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
