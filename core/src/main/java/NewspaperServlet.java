import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/register"})
public class NewspaperServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewspaperDao newspaperDao;

    public NewspaperServlet() {
    }

    public void init() {
        this.newspaperDao = new NewspaperDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String article = request.getParameter("article");
        String text = request.getParameter("text");

        Newspaper newspaper = new Newspaper();
        newspaper.setArticle(article);
        newspaper.setText(text);

        try {
            this.newspaperDao.addNewspaper(newspaper);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("core/src/main/web/newspaperdetails.jsp");
    }
}

