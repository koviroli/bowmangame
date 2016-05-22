import java.time.LocalDate;

public class User {
	
	private static int idCounter;
	private int userId;
	private String username;
	private String password;
	private String emailaddress;
	private String registrationDate;
	private int points;
	private int level;

	public User(String username, String userpassword, String emailaddress) {
		super();
		this.username = username;
		this.password = userpassword;
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
		return password;
	}

	public void setUserpassword(String userpassword) {
		this.password = userpassword;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", userpassword=" + password
				+ ", emailaddress=" + emailaddress + ", registrationDate=" + registrationDate + "]";
	}
}
