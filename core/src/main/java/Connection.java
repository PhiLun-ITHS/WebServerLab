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

            switch (url) {
                case "/", "/index.html" -> Response.sendIndexResponse(outputToClient);
                case "/cat.jpg" -> Response.sendImageResponse(outputToClient);
                case "/lab1.pdf" -> Response.sendPdfResponse(outputToClient);
                case "/newspaper" -> JsonResponse.sendJsonResponse(outputToClient);
                case "/addForm" -> Response.sendFormResponse(outputToClient);
                case "/addArticle" -> JsonResponse.sendJsonAddResponse(outputToClient);
                case "/deleteArticle" -> JsonResponse.sendJsonDeleteResponse(outputToClient);
                case "/updateArticleTitle" -> JsonResponse.sendJsonUpdateResponse(outputToClient);
                case "/updateArticleText" -> JsonResponse.sendJsonUpdateTextResponse(outputToClient);
                default -> Response.sendFaultResponse(outputToClient);
            }

            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
