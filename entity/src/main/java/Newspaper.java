import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Newspaper implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Basic
    private String Article;
    @Basic
    private String Text;

    public Newspaper() {
    }

    public Newspaper(String article, String text) {
        Article = article;
        Text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return Article;
    }

    public void setArticle(String Article) {
        this.Article = Article;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    @Override
    public String toString() {
        return "Newspaper:" + id + " Article:" + Article + " Text:" + Text;
    }
}
