import controller.LoginController;
import view.LoginView;

public class Main {
    public static void main(String[] args) {

        // 1.  Create the Login View (UI)
        LoginView loginView = new LoginView();

        // 2. Create the Controller and pass the View to it
        LoginController loginController = new LoginController(loginView);

        // 3. Make the UI visible on the screen
        loginView.setVisible(true);
    }
}