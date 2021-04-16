
import databaseconnector.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@RequestScoped
public class Post implements Serializable {

    static int currentPost;
    static List<Post> myPosts;
    //private String title;
    //private String desc;
    private String post;

    Post() {
    }

    Post(/*String title, String desc, */ String post) {
        /*this.title = title;
        this.desc = desc;*/
        this.post = post;
    }

    /*public String getTitle() {

    private String title;
    private String desc;
    private String post;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;

    }*/

    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


    public static void getPosts() throws SQLException {
        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT content_post from posts INNER JOIN users u on posts.id_user = u.id_user where u.name like ? ORDER BY date_post;");
        query.setString(1, username);
        ResultSet result = query.executeQuery();
        myPosts.clear();
        while (result.next()) {
            myPosts.add(new Post(result.getString(1)));
        }
        currentPost = 0;
    }

    public String addPost() throws SQLException {
        Statement stm = DBConnection.getInstance().createStatement();
        int user_id = stm.executeQuery("SELECT id_user from users where id_user like " + (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") + ";").getInt(1);
        PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO posts (`id_user`, `content_post`, `date_post`) VALUES (?,?,?);");
        //query.setString(1, title);
        //query.setString(2, desc);
        query.setInt(1, user_id);
        query.setString(2, post);
        query.setDate(3, Date.valueOf(LocalDate.now()));
        return query.execute() ? "home.xhtml" : "addPost.xhtml";
    }



    public String addPost() {
        return "";
    }

    public String getAPost() {
        return "";
    }
}
