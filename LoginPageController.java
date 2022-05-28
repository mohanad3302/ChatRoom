
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private PasswordField Password;

    @FXML
    private Button Signup;

    @FXML
    private TextField UserName;

    @FXML
    private Button login;

    @FXML
    private Button Exit;

    @FXML
    private Label erroLabel;

    @FXML
    void EnterPassword(ActionEvent event) {

    }

    @FXML
    void ExitButton(ActionEvent event){
        Platform.exit();
    }

    @FXML
    void Login(ActionEvent event) throws SQLException, IOException {
        String userName = UserName.getText();
        String password = Password.getText();
        boolean confirmation  = ChatServer.login(userName, password);
        if (confirmation == true ){
            ChatServer.status(userName, "Online");

            System.out.println("loged in sucssefuly");
            
            //open Chatroom inteface after checking that user have an account
        //-----------------------------------------------------------------------------------
            Stage Chatinterface = (Stage) login.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientInterface.fxml"));
            Parent UserInterface = loader.load();
            ClietnController client = (ClietnController) loader.getController();
            client.UserName = UserName.getText();
            Scene scene = new Scene(UserInterface);
            Chatinterface.setScene(scene);
            Chatinterface.show();
        //-----------------------------------------------------------------------------------

        }else {
            erroLabel.setText("Incorrect UserName or password");
        }
    }   

    @FXML
    void SignUp(ActionEvent event) throws IOException {

        Stage signupStage = (Stage) login.getScene().getWindow();
        Parent signup = FXMLLoader.load(getClass().getResource("signupPage.fxml"));
        Scene scene = new Scene(signup);
        signupStage.setScene(scene);
        signupStage.show();
    }

    @FXML
    void enterUserName(ActionEvent event) {

    }

}