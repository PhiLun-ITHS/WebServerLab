
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(5050)){
            while(true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> Connection.handleConnection(client));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}