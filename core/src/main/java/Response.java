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

        String header = "";
        List<Newspaper> getNewspaper = EntityFunctions.getAllNewspapers();

        if (getNewspaper != null) {
            Gson gson = new Gson();
            String json = gson.toJson(getNewspaper);

            byte[] data = json.getBytes(StandardCharsets.UTF_8);

            header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length + "\r\n\r\n";

            outputToClient.write(header.getBytes());
            outputToClient.write(data);

            outputToClient.flush();
        } else {
            header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        }
    }

    static void sendAddResponse(OutputStream outputToClient, String url) throws IOException {

        String Title = url.substring(url.indexOf("=") + 1, url.indexOf("&"));

        String Text = url.substring(url.indexOf("&") + 6);

        Newspaper newspaper = new Newspaper(Title, Text);
        EntityFunctions.addArticle(newspaper);

        sendDefaultResponse(outputToClient);
    }

    static void sendResponseForm(OutputStream outputToClient, String url) throws IOException {
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources/addForm.html").toFile();

        handleResponse(outputToClient, data, f);
    }

    static void sendPngResponse(OutputStream outputToClient, String url) throws IOException{
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources" + url).toFile();
        handleResponse(outputToClient, data, f);
    }

    static void sendJpgResponse(OutputStream outputToClient, String url) throws IOException{
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources" + url).toFile();
        handleResponse(outputToClient, data, f);
    }

    static void sendPdfResponse(OutputStream outputToClient, String url) throws IOException{
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources" + url).toFile();
        handleResponse(outputToClient, data, f);
    }

    static void sendDefaultResponse(OutputStream outputToClient) throws IOException {
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources/index.html").toFile();
        handleResponse(outputToClient, data, f);
    }

    static void sendFaultResponse(OutputStream outputToClient) throws IOException {
        String header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        outputToClient.write(header.getBytes());
        outputToClient.flush();
    }

    private static void handleResponse(OutputStream outputToClient, byte[] data, File f) throws IOException {
        String header = "";

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
