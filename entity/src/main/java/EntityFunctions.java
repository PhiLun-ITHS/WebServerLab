import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class EntityFunctions {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("newspaper");
    static Scanner sc = new Scanner(System.in);

    public static List<Newspaper> getAllNewspapers(){
        EntityManager em = emf.createEntityManager();
        List<Newspaper> newspaper = em
                .createQuery("SELECT n from Newspaper n", Newspaper.class)
                .getResultList();

        em.close();

        return newspaper;
    }

    public static void addArticle(Newspaper newspaper) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(newspaper);
        em.getTransaction().commit();
        em.close();
        /*
        System.out.print("Article title: ");
        String articleTitle = sc.nextLine();

        System.out.println("Article text: ");
        String articleText = sc.nextLine();

        Newspaper n = new Newspaper(articleTitle, articleText);
*/
    }

    public static void deleteArticle(int id){

        EntityManager em = emf.createEntityManager();

        Newspaper n = em.find(Newspaper.class, id);

        em.getTransaction().begin();
        em.remove(n);
        em.getTransaction().commit();
        em.close();
        /*
        System.out.print("Article ID: ");
        int id = sc.nextInt();
        sc.nextLine();



         */
    }

    public static void updateArticle(Newspaper newspaper, String newArticleName){

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        newspaper.setArticle(newArticleName);
        em.getTransaction().commit();
        em.close();
        /*
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Newspaper n = em.find(Newspaper.class, id);

        System.out.print("New Article Title: ");
        String newArticleName = sc.nextLine();

         */
    }

    public static void updateText(Newspaper newspaper, String newTextName){

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        newspaper.setText(newTextName);
        em.getTransaction().commit();
        em.close();

        /*
        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Newspaper n = em.find(Newspaper.class, id);

        System.out.println("New Article Text: ");
        String newTextName = sc.nextLine();

         */
    }
}
