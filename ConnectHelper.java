import java.io.*; 
import java.util.*; 
import java.net.*;
class ConnectHelper implements Runnable  
{ 
   String user; 
   DataInputStream dis; 
   DataOutputStream dos; 
   Socket socket; 
		public ConnectHelper(Socket socket,DataInputStream dis, DataOutputStream dos,String user) { 
	    this.socket = socket; 
	    this.dis = dis; 
	    this.dos = dos; 
	    this.user = user; 
		} 
    @Override
    public void run() { 
  		boolean cicle = true; 
        String received; 
        while (cicle){ 
            try { 
                received = dis.readUTF(); 
                System.out.println(user+" escribio: "+received); 
                for (ConnectHelper client : Servidor.vectorCliente)  
                { 
                if(!client.user.equals(this.user)) {
                    client.dos.writeUTF(user+" escribio: "+received);
                }
                } 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        }
        try{ 
          this.dis.close(); 
          this.dos.close(); 
        }catch(IOException e){ 
          e.printStackTrace(); 
        } 
    } 
} 
