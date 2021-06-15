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

    static void sendAddResponse(OutputStream outputToClient, String url) throws IOException {

/*
        Newspaper newspaper = new Newspaper(ArticleTitle, ArticleText);

        Gson gson = new Gson();
        String json = gson.toJson(newspaper);

        byte[] data = json.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length + "\r\n\r\n";

        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();

 */
    }

    public static void sendPngResponse(OutputStream outputToClient, String url) throws IOException{
        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources" + url).toFile();
        handleResponse(outputToClient, header, data, f);
    }

    public static void sendJpgResponse(OutputStream outputToClient, String url) throws IOException{
        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources" + url).toFile();
        handleResponse(outputToClient, header, data, f);
    }

    public static void sendPdfResponse(OutputStream outputToClient, String url) throws IOException{
        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources" + url).toFile();
        handleResponse(outputToClient, header, data, f);
    }

    public static void sendDefaultResponse(OutputStream outputToClient) throws IOException {
        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources/index.html").toFile();
        handleResponse(outputToClient, header, data, f);
    }

    public static void sendFaultResponse(OutputStream outputToClient) throws IOException {
        String header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        outputToClient.write(header.getBytes());
        outputToClient.flush();
    }

    private static void handleResponse(OutputStream outputToClient, String header, byte[] data, File f) throws IOException {

        if (!(f.exists() && !f.isDirectory())) {
            header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        } else {

            try (FileInputStream fileInputStream = new FileInputStream(f)) {
                data = new byte[(int) f.length()];
                fileInputStream.read(data);
                var contentType = Files.probeContentType(f.toPath());

                header = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-length: " + data.length + "\r\n\r\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }
}
