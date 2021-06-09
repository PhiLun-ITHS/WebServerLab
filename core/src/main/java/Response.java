import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Response {

    static void sendTextResponse(OutputStream outputToClient) throws IOException {

        String header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        outputToClient.write(header.getBytes());
        outputToClient.flush();
    }


    static void sendImageResponse(OutputStream outputToClient) throws IOException {

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
}
