import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonResponse {

    static void sendNewspaperResponse(OutputStream outputToClient) throws IOException {

        List<Newspaper> getNewspaper = EntityFunctions.getAllNewspapers();

        Gson gson = new Gson();
        String json = gson.toJson(getNewspaper);

        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length + "\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }

    public static void sendAddResponse(OutputStream outputToClient) throws IOException {
/*
        Gson gson = new Gson();
        //Newspaper newspaper = new Newspaper();
        String header = "";

        Newspaper newspaper = gson.fromJson(header, Newspaper.class);
        EntityFunctions.addArticle();


        String json = gson.fromJson()


 */

    }
}
