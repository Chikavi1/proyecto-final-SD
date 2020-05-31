import java.io.*; 
import java.util.*; 
import java.net.*;
public class Servidor  
{ 
    static Vector<ConnectHelper> vectorCliente = new Vector<>(); 
    public static void main(String[] args) throws IOException  
    { 
      try{ 
        ServerSocket serverSocket = new ServerSocket(1234); 
        Socket socket; 
        while (true){ 
            socket = serverSocket.accept(); 
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String nombre = (String)dis.readUTF();
            System.out.println("--------- Ha ingresado al grupo: " + nombre+" ---------"); 
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());      
            ConnectHelper connectHelper = new ConnectHelper(socket,dis,dos,nombre); 
            Thread hilo = new Thread(connectHelper); 
            vectorCliente.add(connectHelper); 
            hilo.start(); 
        } 
        }catch(IOException e){ 
          e.printStackTrace(); 
        } 
    } 
} 
