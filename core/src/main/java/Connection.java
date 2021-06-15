import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {

    static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));

            var url = Request.newRequest(inputFromClient);

            var outputToClient = client.getOutputStream();

            checkUrl(outputToClient, url);
            var sendResponse = Response.sendResponse(outputToClient);


            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkUrl(OutputStream outputToClient, String url) throws IOException {

        if (url.equals("/newspaper")){
            JsonResponse.sendNewspaperResponse(outputToClient);
        } else if (url.equals("/add")){
            JsonResponse.sendAddResponse(outputToClient);
        }
    }
}
