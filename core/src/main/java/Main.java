import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(5050)){
            while(true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> handleConnection(client));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            var url = readRequest(inputFromClient);

            var outputToClient = client.getOutputStream();

            if (url.equals("/cat.jpg")){
                sendImageResponse(outputToClient);
            } else if (url.equals("/newspaper")){
                sendJsonResponse(outputToClient);
            } else {
                sendTextResponse(outputToClient);
            }
            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendTextResponse(OutputStream outputToClient) throws IOException{

        String header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        outputToClient.write(header.getBytes());
        outputToClient.flush();
    }


    private static void sendImageResponse(OutputStream outputToClient) throws IOException {

        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/target/classes/cat.jpg").toFile();
        if (!(f.exists() && !f.isDirectory())) {
            header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        }
        else
        {
            try(FileInputStream fileInputStream = new FileInputStream(f)){
                data = new byte[(int) f.length()];
                fileInputStream.read(data);
                var contentType = Files.probeContentType(f.toPath());

                header = "HTTP/1.1 200 OK\r\nContent-Type: "+contentType+"\r\nContent-length: " + data.length +"\r\n\r\n";
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }


    private static void sendJsonResponse(OutputStream outputToClient) throws IOException {

        var newspapers = List.of(EntityFunctions.getAllNewspapers());

        Gson gson = new Gson();
        String json = gson.toJson(newspapers);

        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length +"\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }

    private static String readRequest(BufferedReader inputFromClient) throws IOException {

        var url = "";

        while (true) {
            var line = inputFromClient.readLine();
            if (line.startsWith("GET")){
                url = line.split(" ")[1];
            } if (line == null || line.isEmpty()){
                break;
            }
            System.out.println(line);
        }
        return url;
    }
}