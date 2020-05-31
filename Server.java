import java.io.*; 
import java.util.*; 
import java.net.*;
public class Server  
{ 
    static Vector<ClientHandler> ar = new Vector<>(); 
    public static void main(String[] args) throws IOException  
    { 
        ServerSocket serverSocket = new ServerSocket(1234); 
        Socket socket; 
        while (true)  
        { 
            socket = serverSocket.accept(); 
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String nombre = (String)dis.readUTF();
            System.out.println("--------- Ha ingresado al grupo: " + nombre+" ---------"); 
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());      
            ClientHandler clientHandler = new ClientHandler(socket,nombre, dis, dos); 
            Thread t = new Thread(clientHandler); 
            ar.add(clientHandler); 
            t.start(); 
        } 
    } 
} 
