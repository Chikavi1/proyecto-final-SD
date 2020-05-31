import java.io.*; 
import java.util.*; 
import java.net.*;
class ClientHandler implements Runnable  
{ 
  private String name; 
  final DataInputStream dis; 
  final DataOutputStream dos; 
  Socket s; 
  boolean isloggedin; 
      
    public ClientHandler(Socket s, String name, 
                            DataInputStream dis, DataOutputStream dos) { 
        this.dis = dis; 
        this.dos = dos; 
        this.name = name; 
        this.s = s; 
        this.isloggedin=true; 
    } 
  
    @Override
    public void run() { 
  
        String received; 
        while (isloggedin)  
        { 
            try
            { 
                received = dis.readUTF(); 
                System.out.println(received); 
                if(received.equals("logout")){ 
                    this.isloggedin=false; 
                    this.s.close(); 
                    break; 
                } 
                StringTokenizer st = new StringTokenizer(received, "#"); 
                String MsgToSend = st.nextToken();
                String recipient = "global";
                try {
                    recipient = st.nextToken(); 
                } catch (Exception e) {
                    
                }
                for (ClientHandler mc : Server.ar)  
                { 
                    if (recipient.equals("global") && mc.isloggedin && !mc.name.equals(this.name)) {
                        mc.dos.writeUTF(this.name+": "+MsgToSend);
                    }
                    if (mc.name.equals(recipient) && mc.isloggedin==true)  
                    { 
                        mc.dos.writeUTF("tu:"+this.name+": "+MsgToSend); 
                        break; 
                    }
                } 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
        try
        { 
          this.dis.close(); 
          this.dos.close(); 
        }catch(IOException e){ 
          e.printStackTrace(); 
        } 
    } 
} 
