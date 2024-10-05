public class HelpSystem {
    
    private AuthenticationManager authManager;

    public HelpSystem() {
        this.authManager = new AuthenticationManager();
    }

    public AuthenticationManager getAuthManager() {
        return authManager;
    }

    public void registerFirstUser(String username, byte[] password) {
        User firstUser = new User(username, password);
        firstUser.addRole(Role.ADMIN);
        authManager.addUser(firstUser);
    }

    public void addUser(User user) {
        authManager.addUser(user);
    }

    public void displayUsers() {
        authManager.listUsers();
    }

    public User authenticateUser(String username, byte[] password) {
        return authManager.authenticate(username, password);
    }

    public static void main(String[] args) {
        HelpSystem helpSystem = new HelpSystem();
        
        // Step 1: Register the first admin user programmatically for testing
        helpSystem.registerFirstUser("admin", "adminPassword".getBytes());

        // Step 2: Add a few users programmatically
        User studentUser = new User("student1", "password123".getBytes());
        studentUser.addRole(Role.STUDENT);
        helpSystem.addUser(studentUser);

        User instructorUser = new User("instructor1", "teachPass".getBytes());
        instructorUser.addRole(Role.INSTRUCTOR);
        helpSystem.addUser(instructorUser);

        // Step 3: Authenticate a user for testing
        User authenticatedUser = helpSystem.authenticateUser("student1", "password123".getBytes());
        if (authenticatedUser != null) {
            System.out.println("Authenticated user: " + authenticatedUser.getUsername() + ", Roles: " + authenticatedUser.getRoles());
        } else {
            System.out.println("Authentication failed.");
        }

        // Display all users for verification
        helpSystem.displayUsers();
    }
}

