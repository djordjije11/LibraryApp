package tcp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Base64;

/**
 *
 * @author Djordjije
 */
public class TcpCommunicator {
    protected final Socket socketCommunication;
    protected final BufferedReader input;
    protected final PrintStream output;

    public TcpCommunicator(Socket socketCommunication) throws IOException{
        this.socketCommunication = socketCommunication;
        input = new BufferedReader(new InputStreamReader(socketCommunication.getInputStream()));
        output = new PrintStream(socketCommunication.getOutputStream());
    }
    
    public boolean isConnectionClosed(){
        return socketCommunication.isClosed();
    }
    
    public void closeConnection() throws IOException{
        if(socketCommunication != null && socketCommunication.isClosed() == false)
            socketCommunication.close();
    }
    
    public void sendMessage(String message){
        if(socketCommunication.isConnected())
            output.println(message);
    }
    
    public String readMessage() throws IOException{
        return input.readLine();
    }
    
    public <T> void sendObject(T object) throws IOException{
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = null;
        try{
            objectOutput = new ObjectOutputStream(bytes) ;
            objectOutput.writeObject(object);
        } finally{
            if(objectOutput != null) objectOutput.close();
        }
        String message = Base64.getEncoder().encodeToString(bytes.toByteArray());
        sendMessage(message);
    }
    public <T> T readObject() throws IOException, ClassNotFoundException{
        String message = readMessage();
        byte [] data = Base64.getDecoder().decode(message);
        ByteArrayInputStream bytes = new ByteArrayInputStream(data);
        ObjectInputStream objectInput = null;
        try{
        objectInput = new ObjectInputStream(bytes);
        return (T)objectInput.readObject();
        } finally{
           objectInput.close();
        }
    }
}
