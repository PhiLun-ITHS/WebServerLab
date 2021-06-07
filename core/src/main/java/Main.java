import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try(ServerSocket socket = new ServerSocket(5050)){

            while(true){
                Socket client = socket.accept();
                System.out.println(client.getInetAddress());

                var inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));

                inputFromClient.lines().forEach(System.out::println);

                inputFromClient.close();
                client.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
