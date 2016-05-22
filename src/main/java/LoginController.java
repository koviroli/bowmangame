import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
	
	@FXML
	private Text checkText;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void wrongData(){
		checkText.setFill(Color.RED);
		checkText.setText("wrong username/password.");
	}
	
	public void loginSucces(){
		checkText.setFill(Color.GREEN);
		checkText.setText("Succesfully logged in!");
	}
	
	public void login(){
		System.out.println("Logged in as:");
		
		DBHandler dbhandler = new DBHandler();
		if (dbhandler.DBLogin(userName.getText(), userPassword.getText())){
			loginSucces();
			@SuppressWarnings("unused")
			Game game = new Game();
		}
		else{
			wrongData();
			System.err.println("user/pw ERR");
		}		
	}
	
	public void register(){
		System.out.println("Register");
		@SuppressWarnings("unused")
		Register register = new Register();
	}

}
