import java.io.*;
import java.net.*;
import java.util.*;
public class ServerSockets{
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(5555);
            Socket socket = ss.accept();

            Scanner scan = new Scanner(System.in);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while(true){
                String userText = input.readUTF();
                System.out.println("Friend: "+userText);
                if(userText.equals("bye")) break;
                System.out.print("Me: ");
                String text = scan.nextLine();
                out.writeUTF(text);

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
