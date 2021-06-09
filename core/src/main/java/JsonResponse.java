import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonResponse {

    static void sendJsonResponse(OutputStream outputToClient) throws IOException {

        var newspapers = List.of(EntityFunctions.getAllNewspapers());

        Gson gson = new Gson();
        String json = gson.toJson(newspapers);

        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length +"\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }

    public static void sendJsonAddResponse(OutputStream outputToClient) {

    }

    public static void sendJsonDeleteResponse(OutputStream outputToClient) {

    }

    public static void sendJsonUpdateResponse(OutputStream outputToClient) {

    }

    public static void sendJsonUpdateTextResponse(OutputStream outputToClient) {

    }
}
