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

public class RegisterController implements Initializable{
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button registerButton;
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField userPassword;
	
	@FXML
	private TextField userEmail;
	
	@FXML
	private Text regCheck;

	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void existingUser(){
		regCheck.setFill(Color.RED);
		regCheck.setText("username already exist!");
	}
	
	public void emptyField(){
		regCheck.setFill(Color.RED);
		regCheck.setText("any field is empty");
	}
	
	public void succesfulRegistry(){
		regCheck.setFill(Color.GREEN);
		regCheck.setText("registration Succesfull!");
	}
	
	public void userAlreadyExists(){
		regCheck.setFill(Color.RED);
		regCheck.setText("user already exists!");
	}
	
	public void register(){	
		DBHandler dbhandler = new DBHandler();
		User user = new User(userName.getText(), userPassword.getText(), userEmail.getText());
		
		if(userName.getText().isEmpty() || userPassword.getText().isEmpty() || userEmail.getText().isEmpty()){
			emptyField();
		}
		else if(dbhandler.checkUser(user)){
			userAlreadyExists();
		}
		else{
			succesfulRegistry();						
			dbhandler.newRegistry(user);
		}
	}

}
