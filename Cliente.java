import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
public class Cliente  
{ 
    public static void main(String args[]) throws UnknownHostException, IOException  
    { 
        Socket socket = new Socket(InetAddress.getByName("localhost"), 1234); 
        DataInputStream dis = new DataInputStream(socket.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner nombre = new Scanner(System.in);
        System.out.println("Ingresa tu nombre");
        String nickname = nombre.nextLine();
        dos.writeUTF(nickname);
        Scanner mensaje = new Scanner(System.in); 
        Thread hiloEnviarMensaje = new Thread(new Runnable(){ 
            @Override
            public void run() { 
                while (true) { 
                    String enviarMensaje = mensaje.nextLine(); 
                    try { 
                        dos.writeUTF(enviarMensaje); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                } 
            } 
        }); 
        hiloEnviarMensaje.start(); 
        Thread hiloRecibeMensaje = new Thread(new Runnable()  { 
            @Override
            public void run() { 
                while (true) { 
                    try { 
                        String recibeMensaje = dis.readUTF(); 
                        System.out.println(recibeMensaje); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                } 
            } 
        });
        hiloRecibeMensaje.start(); 
    } 
} 
