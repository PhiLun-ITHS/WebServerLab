import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {

    static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));

            var url = Request.readRequest(inputFromClient);

            var outputToClient = client.getOutputStream();

            checkUrl(outputToClient, url);

            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkUrl(OutputStream outputToClient, String url) throws IOException {

        if (url.equals("/newspaper")){
            Response.sendNewspaperResponse(outputToClient);
        } else if (url.equals("/add")){
            Response.sendAddResponse(outputToClient, url);
        } else if (url.contains(".png")){
            Response.sendPngResponse(outputToClient, url);
        } else if (url.contains(".jpg")){
            Response.sendJpgResponse(outputToClient, url);
        } else if (url.contains(".pdf")){
            Response.sendPdfResponse(outputToClient, url);
        } else if (url.equals("/")){
            Response.sendDefaultResponse(outputToClient);
        } else if (url.equals("/index.html")){
            Response.sendDefaultResponse(outputToClient);
        } else if (url.contains("/addForm")){
            Response.sendResponseForm(outputToClient, url);
        } else if (url.contains("/Servlet")){
            Response.sendAddResponse(outputToClient, url);
        }
        else {
            Response.sendFaultResponse(outputToClient);
        }
    }
}
