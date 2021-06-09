import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewspaperDao {

    public int addNewspaper(Newspaper newspaper) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO employee  (article, text) VALUES  (?, ?);";
        int result = 0;
        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper?zeroDateTimeBehavior=convertToNull", "root", "root");

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

                preparedStatement.setString(1, newspaper.getArticle());
                preparedStatement.setString(2, newspaper.getText());

                System.out.println(preparedStatement);
                result = preparedStatement.executeUpdate();

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
