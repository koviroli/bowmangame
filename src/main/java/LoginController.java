import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable{

	@FXML
	private Button loginButton;
	
	@FXML
	private Button registerButton;
	
	@FXML
	private AnchorPane anchorPane;	
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField userPassword;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void login(){
		System.out.println("Logged in as:");
		System.out.println(userName.getText());
		System.out.println(userPassword.getText());
		@SuppressWarnings("unused")
		Game game = new Game();
	}
	
	public void register(){
		System.out.println("Register");
		@SuppressWarnings("unused")
		Register register = new Register();
	}

}
