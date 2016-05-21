import java.time.LocalDate;

public class User {
	
	private static int idCounter;
	private int userId;
	private String username;
	private String userpassword;
	private String emailaddress;
	private String registrationDate;

	public User(String username, String userpassword, String emailaddress) {
		super();
		this.username = username;
		this.userpassword = userpassword;
		this.emailaddress = emailaddress;
		idCounter++;
		userId = (idCounter);
		registrationDate = LocalDate.now().toString();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", userpassword=" + userpassword
				+ ", emailaddress=" + emailaddress + ", registrationDate=" + registrationDate + "]";
	}
}
