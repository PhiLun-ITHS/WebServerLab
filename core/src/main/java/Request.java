import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Request {


    static String newRequest(BufferedReader inputFromClient) throws IOException {

        var url = readRequest(inputFromClient);

        return url;
        /*
        if (url.equals("/newspaper")) {
            JsonResponse.getNewspaper();
            List<Newspaper> getNewspaper = EntityFunctions.getAllNewspapers();
            if (getNewspaper != null) {
                Gson gson = new Gson();

                byte[] data = gson.toJson(getNewspaper).getBytes(StandardCharsets.UTF_8);

                url = ("200 OK" + data.length + "application/json");
            } else {
                url = ("404 Not Found");
            }
        }

        if (url.equals("/add")) {

        } else {

        }
        if (url.equals("/png")) {

        } else {

        }

        if (url.equals("/jpg")) {

        } else {

        }

        if (url.equals("/pdf")) {

        } else {

        }
*/
    }



    static String readRequest(BufferedReader inputFromClient) throws IOException {

        var url = "";

        while (true) {
            var line = inputFromClient.readLine();
            if (line.startsWith("GET")){
                url = line.split(" ")[1];
            } if (line == null || line.isEmpty()){
                break;
            }
        }
        return url;
    }






}
