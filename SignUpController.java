import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignUpController {

    @FXML
    private TextField Email;

    @FXML
    private TextField Password;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button Signup;

    @FXML
    private TextField UserName;

    @FXML
    private Button cancel;

    @FXML
    void EnterEmail(ActionEvent event) {

    }

    @FXML
    void EnterPassword(ActionEvent event) {
        
    }

    @FXML
    void EnterPhonNumber(ActionEvent event) {

    }

    @FXML
    void EnterUserName(ActionEvent event) {
        
    }

    
    @FXML
    void cancelButton(ActionEvent event) throws IOException {
        Stage signupStage = (Stage) Signup.getScene().getWindow();
        Parent signup = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(signup);
        signupStage.setScene(scene);
        signupStage.show();
    }

    @FXML
    void SignUpButton(ActionEvent event) throws IOException, SQLException {

        String userName = UserName.getText();
        String email = Email.getText();
        String password = Password.getText();
        int phoneNumber = Integer.parseInt(PhoneNumber.getText());
        
        ChatServer.signup(userName, email, password, phoneNumber);
        Stage signupStage = (Stage) Signup.getScene().getWindow();
        Parent signup = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(signup);
        signupStage.setScene(scene);
        signupStage.show();
    }

}
