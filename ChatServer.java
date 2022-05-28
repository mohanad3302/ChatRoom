
import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatServer {
    private  ServerSocket serverSocket  ;

    public static ArrayList<AvaliableUsers> UsersOnline = new ArrayList<>();

    public ChatServer ( ServerSocket serverSocket  ){
        this.serverSocket = serverSocket ;
    }

    public void StartServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ChatHandler chatHandler = new ChatHandler(socket);
                Thread thread = new Thread(chatHandler);
                thread.start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean login(String UserName , String password) throws SQLException{
        Connection connection = Database.connection();
        return Database.loginConfirmation(connection, UserName, password);
    }

    public static void signup(String UserName ,String Email , String password , int phoneNumber) throws SQLException{
        Connection connection = Database.connection();
        Database.InserUserData(connection, UserName, Email, phoneNumber, password);
    }

    public static void status( String UserName ,String status) throws SQLException{
        Connection connection = Database.connection();
        Database.UpdateUserStatus(connection, UserName, status);
    }

    public static ResultSet Users (String status) throws SQLException {
        Connection connection = Database.connection();
        ResultSet results = Database.getUsers(status, connection);
        return results ;
    }

    public static void main (String [] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(90);
        ChatServer Server = new ChatServer(serverSocket);
        Server.StartServer();
        
    }

    
}






