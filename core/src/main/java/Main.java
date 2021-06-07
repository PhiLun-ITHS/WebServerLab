public class Main {

    public static void main(String[] args) {

        Utils utils = giveObject();

        System.out.println(utils.message());
    }

    private static Utils giveObject() {
        return new Utils();
    }
}
