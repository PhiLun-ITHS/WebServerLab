
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Client {

    //static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

        var client = HttpClient.newHttpClient();

        var getNewspaper = HttpRequest.newBuilder(URI.create("http://localhost:5050/newspaper")).build();
        System.out.println(client.send(getNewspaper, HttpResponse.BodyHandlers.ofString()).body());

/*
        while (true) {
            System.out.println("\nNEWSPAPER MENU");
            System.out.println("---------------");
            System.out.println("1. Read Newspaper");
            System.out.println("2. Add Article");
            /*
            System.out.println("3. Update Article title");
            System.out.println("4. Update Article text");
            System.out.println("5. Delete Article");



            System.out.println("0. Exit");
            System.out.println("---------------");

            System.out.print("choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    var getNewspaper = HttpRequest.newBuilder(URI.create("http://localhost:5050/newspaper")).build();
                    System.out.println(client.send(getNewspaper, HttpResponse.BodyHandlers.ofString()).body());
                    break;
                case 2:
                    addArticle(client);
                    break;
                case 3:
                    updateArticle();
                    break;
                case 4:
                    updateText();
                    break;
                case 5:
                    deleteArticle();
                    break;


                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice: (" + choice + ") try again!");
                    break;
            }
        }

    }

    private static void addArticle(HttpClient client) throws IOException, InterruptedException {

        /*
        System.out.print("Article title: ");
        String articleTitle = sc.nextLine();

        System.out.print("Article text: ");
        String articleText = sc.nextLine();

        var addArticle = HttpRequest.newBuilder(
                URI.create("http://localhost:5050/add")).
                POST(HttpRequest.BodyPublishers.ofString(articleTitle + articleText)).build();
        client.send(addArticle, HttpResponse.BodyHandlers.ofString()).body();
    }



    private static void updateArticle() {

    }

    private static void updateText() {

    }

    private static void deleteArticle() {

    }
    */
    }
}