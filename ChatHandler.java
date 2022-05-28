
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class ChatHandler implements Runnable {
    DataInputStream dataInputStream;
    PrintStream printStream ;
    private Socket socket ;

    static Vector<ChatHandler> clientsVector=new Vector<>();

    public ChatHandler(Socket socket){
        try {
            this.socket = socket ;
            dataInputStream=new DataInputStream(socket.getInputStream());
            printStream=new PrintStream(socket.getOutputStream());
            ChatHandler.clientsVector.add(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void run(){
        while(socket.isConnected()){
            try {
                String userMessage = dataInputStream.readLine();
                sendMessageToAll(userMessage);
            } catch (IOException e) {
                e.printStackTrace();
                break ;
            }
        }
    }
    void sendMessageToAll(String message){
        for (ChatHandler chatHandler : clientsVector){
            chatHandler.printStream.println(message);
        }
    }

    public void removeClientHandler(){
        clientsVector.remove(this);
        System.out.println("user left chat ");
    }
}


