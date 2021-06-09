import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {

    static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            var url = Request.readRequest(inputFromClient);

            var outputToClient = client.getOutputStream();

            if (url.equals("/cat.jpg")){
                Response.sendImageResponse(outputToClient);
            } else if (url.equals("/newspaper")){
                Response.sendJsonResponse(outputToClient);
            } else {
                Response.sendTextResponse(outputToClient);
            }
            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
