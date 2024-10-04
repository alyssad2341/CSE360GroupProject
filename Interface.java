package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    // Simulated user database
    private static class User {
        String username;
        String password;
        boolean isSetupComplete;

        User(String username, String password, boolean isSetupComplete) {
            this.username = username;
            this.password = password;
            this.isSetupComplete = isSetupComplete;
        }
    }

    // Simulated user data
    private Map<String, User> userDatabase = new HashMap<>();

    public Main() {
        // Initialize some users for testing
        userDatabase.put("user1", new User("user1", "password1", false)); // Needs setup
        userDatabase.put("user2", new User("user2", "password2", true));  // Setup complete
    }

    @Override
    public void start(Stage primaryStage) {
        showLoginPage(primaryStage);
    }

    private void showLoginPage(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // UI components for login
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        // Add components to the grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);

        // Login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Validate login
            User user = userDatabase.get(username);
            if (user != null && user.password.equals(password)) {
                // Check if account setup is complete
                if (!user.isSetupComplete) {
                    showSetupAccountPage(primaryStage, user);
                } else {
                    messageLabel.setText("Login successful!"); // Proceed to the main application
                }
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        });

        // Create a scene and set the stage
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showSetupAccountPage(Stage primaryStage, User user) {
        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Create UI components for account setup
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        Label middleNameLabel = new Label("Middle Name:");
        TextField middleNameField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        Label preferredNameLabel = new Label("Preferred First Name (optional):");
        TextField preferredNameField = new TextField();

        Button finishButton = new Button("Finish Setup");
        Label messageLabel = new Label(); // To show success or error messages

        // Add components to the grid
        grid.add(emailLabel, 0, 0);
        grid.add(emailField, 1, 0);
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);
        grid.add(middleNameLabel, 0, 2);
        grid.add(middleNameField, 1, 2);
        grid.add(lastNameLabel, 0, 3);
        grid.add(lastNameField, 1, 3);
        grid.add(preferredNameLabel, 0, 4);
        grid.add(preferredNameField, 1, 4);
        grid.add(finishButton, 1, 5);
        grid.add(messageLabel, 1, 6);

        // Set the button action
        finishButton.setOnAction(event -> {
            String email = emailField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();

            // Simple validation
            if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                messageLabel.setText("Please fill in the required fields: Email, First Name, and Last Name.");
            } else {
                messageLabel.setText("Account setup completed successfully!");
                user.isSetupComplete = true; // Mark the user as having completed setup

                // Clear the input fields (optional)
                emailField.clear();
                firstNameField.clear();
                middleNameField.clear();
                lastNameField.clear();
                preferredNameField.clear();
            }
        });

        // Create a scene
        Scene scene = new Scene(grid, 400, 300);

        // Set the stage
        primaryStage.setTitle("Finish Setting Up Your Account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
