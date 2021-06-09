
public class Utils {

    public static RequestType parseHttpRequest(String input) {

        var request = new RequestType();
        request.type = parseHttpRequestType(input);
        request.url = parseUrl(input);
        return request;
    }

    private static String parseUrl(String input) {
        String[] result = input.split(" ");
        return result[1];
    }

    private static HTTPType parseHttpRequestType(String input) {
        if (input.startsWith("G")) {
            return HTTPType.GET;
        } else if (input.startsWith("H")) {
            return HTTPType.HEAD;
        } else if (input.startsWith("PO")) {
            return HTTPType.POST;
        } else {
            throw new RuntimeException("Invalid type");
        }
    }

    public static String handleRequest(RequestType request) {
        return switch (request.type) {
            case GET -> "GET";
            case HEAD -> "HEAD";
            case POST -> "POST";
        };
    }
}
