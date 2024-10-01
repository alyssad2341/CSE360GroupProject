package userNameRecognizerTestbed;

//Alyssa (Instructor, extends User)
public class Instructor extends User{
	private String userName;
  private String email;
  private SecurePassword password;
  
	public Instructor(String userName, String email, SecurePassword password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
}
