import databaseconnector.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Classe permettant de gérer les posts
 */
@Named
@RequestScoped
public class Post implements Serializable {

    private ArrayList<Post> myPosts = null;
    private String post;

    Post() {
    }

    Post(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    /**
     * Méthode pour afficher des posts depuis la BDD
     *
     * @return ArrayList contenant les posts de l'utilisateur
     * @throws SQLException
     */
    public ArrayList<Post> getPosts() throws SQLException {
        if (myPosts == null) {
            myPosts = new ArrayList<>();
        } else {
            myPosts.clear();
        }

        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        PreparedStatement queryGetID = DBConnection.getInstance().prepareStatement("SELECT id_user from users where name like ?;");
        queryGetID.setString(1, username);
        ResultSet rs = queryGetID.executeQuery();
        if (rs.next()) {
            int myID = rs.getInt(1);
            PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT content_post from posts where id_user = ? or id_user in (SELECT idf2 from friends where idf1 = ? UNION SELECT idf1 from friends where idf2 = ?) ORDER BY date_post;");
            query.setInt(1, myID);
            query.setInt(2, myID);
            query.setInt(3, myID);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                myPosts.add(new Post(result.getString(1)));
            }
        }
        return myPosts;
    }

    /**
     * Méthode pour ajouter des posts depuis la BDD
     *
     * @return redirection vers les pages xhtml
     * @throws SQLException
     */
    public String addPost() throws SQLException {
        PreparedStatement stm = DBConnection.getInstance().prepareStatement("SELECT id_user from users where psd like ?;");
        stm.setString(1, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO posts (`id_user`, `content_post`, `date_post`) VALUES (?,?,?);");
            query.setInt(1, resultSet.getInt(1));
            query.setString(2, post);
            query.setDate(3, Date.valueOf(LocalDate.now()));
            query.execute();
            return "home.xhtml";
        }
        return "index.xhtml";
    }
}
