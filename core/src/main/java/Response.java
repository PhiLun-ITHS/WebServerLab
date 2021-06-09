import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Response {

    static void sendFaultResponse(OutputStream outputToClient) throws IOException {

        String header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        outputToClient.write(header.getBytes());
        outputToClient.flush();
    }

    static void sendIndexResponse(OutputStream outputToClient) throws IOException{

        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources/index.html").toFile();
        handleResponse(outputToClient, header, data, f);
    }

    static void sendFormResponse(OutputStream outputToClient) throws IOException {

        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources/dbForm.html").toFile();
        handleResponse(outputToClient, header, data, f);
    }

    static void sendImageResponse(OutputStream outputToClient) throws IOException {

        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/target/classes/cat.jpg").toFile();
        handleResponse(outputToClient, header, data, f);
    }

    public static void sendPdfResponse(OutputStream outputToClient) throws IOException {

        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("core/src/main/resources/lab1.pdf").toFile();
        handleResponse(outputToClient, header, data, f);
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
