import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ServerInterface {

    @FXML
    private Label BusyLabel;

    @FXML
    private  TextArea BusyUsers;

    @FXML
    private Label OfflineLabel;

    @FXML
    private  TextArea OfflineUsers;

    @FXML
    private Label onlineLabel;

    @FXML
    private  TextArea onlineUsers;

    public ServerInterface(){

        new Thread(){
            public void run(){
                try {
                    ResultSet results =  ChatServer.Users("Offline");
                    while(results.next()){
                        String UserName = results.getString(1);
                        OfflineUsers.setText(OfflineUsers.getText()+"\n"+UserName);
                    }    
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    ResultSet results =  ChatServer.Users("Busy");
                     while(results.next()){
                        String UserName = results.getString(1);
                        BusyUsers.setText(BusyUsers.getText()+"\n"+UserName);
                    }    
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    ResultSet results =  ChatServer.Users("online");
                    while(results.next()){
                        String UserName = results.getString(1);
                        onlineUsers.setText(onlineUsers.getText()+"\n"+UserName);
                    }    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public  void UpdateOnlineUsers() throws SQLException{
        ResultSet results =  ChatServer.Users("online");
        while(results.next()){
            String UserName = results.getString(1);
            onlineUsers.setText(onlineUsers.getText()+"\n"+UserName);
        }
    }
    public  void UpdateOfflineUsers() throws SQLException{
         
    }
    public  void UpdateBusyUsers() throws SQLException{
        
    }
}

