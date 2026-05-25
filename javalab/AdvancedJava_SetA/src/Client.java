import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            DataInputStream input =
                    new DataInputStream(socket.getInputStream());

            DataOutputStream output =
                    new DataOutputStream(socket.getOutputStream());

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in));

            String clientMsg = "";
            String serverMsg = "";

            while (!clientMsg.equalsIgnoreCase("exit")) {

                System.out.print("Client : ");

                clientMsg = br.readLine();

                output.writeUTF(clientMsg);

                output.flush();

                serverMsg = input.readUTF();

                System.out.println("Server : " + serverMsg);
            }

            input.close();
            output.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}