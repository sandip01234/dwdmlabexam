import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client Connected");

            DataInputStream input =
                    new DataInputStream(socket.getInputStream());

            DataOutputStream output =
                    new DataOutputStream(socket.getOutputStream());

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in));

            String clientMsg = "";
            String serverMsg = "";

            while (!clientMsg.equalsIgnoreCase("exit")) {

                clientMsg = input.readUTF();

                System.out.println("Client : " + clientMsg);

                System.out.print("Server : ");

                serverMsg = br.readLine();

                output.writeUTF(serverMsg);

                output.flush();
            }

            input.close();
            output.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}