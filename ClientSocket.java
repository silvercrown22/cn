import java.io.*;
import java.net.*;
import java.util.*;
public class ClientSocket{
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",5555);
            Scanner scan = new Scanner(System.in);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while(true){
                System.out.print("Me: ");
                String text = scan.nextLine();
                out.writeUTF(text);

                if(text.equals("bye")) break;


                System.out.println("Friend: "+input.readUTF());
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
