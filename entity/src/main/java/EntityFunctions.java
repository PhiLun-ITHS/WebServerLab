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

    public Newspaper addArticle() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Article title: ");
        String articleTitle = sc.nextLine();

        System.out.println("Article text: ");
        String articleText = sc.nextLine();

        Newspaper n = new Newspaper(articleTitle, articleText);

        em.getTransaction().begin();
        em.persist(n);
        em.getTransaction().commit();
        em.close();

        return null;
    }

    public Newspaper deleteArticle(){

        EntityManager em = emf.createEntityManager();

        System.out.print("Article ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Newspaper n = em.find(Newspaper.class, id);

        em.getTransaction().begin();
        em.remove(n);
        em.getTransaction().commit();
        em.close();

        return null;
    }

    public Newspaper updateArticle(){

        EntityManager em = emf.createEntityManager();

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Newspaper n = em.find(Newspaper.class, id);

        System.out.print("New Article Title: ");
        String newArticleName = sc.nextLine();

        em.getTransaction().begin();
        n.setArticle(newArticleName);
        em.getTransaction().commit();
        em.close();

        return null;
    }

    public Newspaper updateText(){

        EntityManager em = emf.createEntityManager();

        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Newspaper n = em.find(Newspaper.class, id);

        System.out.println("New Article Text: ");
        String newTextName = sc.nextLine();

        em.getTransaction().begin();
        n.setText(newTextName);
        em.getTransaction().commit();
        em.close();

        return null;
    }
}
