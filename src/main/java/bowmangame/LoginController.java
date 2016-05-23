package bowmangame;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public static Logger logger = LoggerFactory.getLogger(DBHandler.class);
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void wrongData(){
		checkText.setFill(Color.RED);
		logger.error("wrong username/password.");
	}
	
	public void loginSucces(){
		checkText.setFill(Color.GREEN);
		logger.info("Succesfully logged in!");
	}
	
	public void login(){
		logger.info("login() - logged in");
		
		DBHandler dbhandler = new DBHandler();
		
		User user = dbhandler.DBLogin(userName.getText(), userPassword.getText());
		
		if (user != null){
			loginSucces();
			@SuppressWarnings("unused")
			Game game = new Game(user);
		}
		else{
			wrongData();
		}		
	}
	
	public void register(){
		logger.info("Register");
		@SuppressWarnings("unused")
		Register register = new Register();
	}

}
