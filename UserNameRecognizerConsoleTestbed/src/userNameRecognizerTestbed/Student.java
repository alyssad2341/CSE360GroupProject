package userNameRecognizerTestbed;

import java.util.List;

//Student class extending the User class
public class Student extends User {

 // Constructor for initializing a Student object
 public Student(String userName, String email, SecurePassword password, 
                String firstName, String middleName, String lastName, 
                String preferredFirstName, List<Role> roles) {
     super(userName, email, password, firstName, middleName, lastName, preferredFirstName, roles);
 }

 // This method allows the student to input an invitation code
 public void inputInvitationCode(String invitationCode) {
     // Here, I'm checking if the invitation code is not null or empty
     if (invitationCode != null && !invitationCode.isEmpty()) {
         System.out.println("Invitation code accepted: " + invitationCode);
         // Ideally, this code would be verified with some database or service
     } else {
         System.out.println("Invalid invitation code.");
     }
 }

 // This method handles the login process for the student
 @Override
 public void login(String userName, SecurePassword password) {
     // Using a simple validation check here to compare input with stored values
     if (validateLogin(userName, password)) {
         System.out.println("Login successful for user: " + userName);
         // If needed, additional login processes (like updating login time) can be added
     } else {
         System.out.println("Login failed. Invalid username or password.");
     }
 }

 // Method to log the student out of the system
 public void logout() {
     System.out.println("Logging out user: " + this.getUserName());
     // This is where we'd perform any necessary cleanup after logout
 }

 // A basic method to validate the login credentials
 // In real projects, this would be more complex and likely involve a database check
 private boolean validateLogin(String userName, SecurePassword password) {
     // Just checking if the username and password match the current user's credentials
     return this.getUserName().equals(userName) && this.getPassword().equals(password);
 }
}
