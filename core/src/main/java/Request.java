
import java.io.BufferedReader;
import java.io.IOException;

public class Request {

    static String readRequest(BufferedReader inputFromClient) throws IOException {

        var url = "";

        while (true) {
            var line = inputFromClient.readLine();
            if (line.startsWith("GET")){
                url = line.split(" ")[1];
            } else if (line.startsWith("POST")){
                url = line.split(" ")[1];
                handlePost(url);
            } if (line == null || line.isEmpty()){
                break;
            }
        }
        return url;
    }

    private static void handlePost(String url) {


    }
}
