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


@Named
@RequestScoped
public class Post implements Serializable {

    private ArrayList<Post> myPosts = null;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


    public ArrayList<Post> getPosts() throws SQLException {
        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT content_post from posts INNER JOIN users u on posts.id_user = u.id_user where u.name like ? ORDER BY date_post;");
        query.setString(1, username);
        ResultSet result = query.executeQuery();
        if (myPosts == null) {
            myPosts = new ArrayList<>();
        } else {
            myPosts.clear();
        }
        while (result.next()) {
            myPosts.add(new Post(result.getString(1)));
        }
        return myPosts;
    }

    public String addPost() throws SQLException {
        PreparedStatement stm = DBConnection.getInstance().prepareStatement("SELECT id_user from users where psd like ?;");
        stm.setString(1, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        ResultSet resultSet= stm.executeQuery();
        if (resultSet.next()) {
            PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO posts (`id_user`, `content_post`, `date_post`) VALUES (?,?,?);");
            //query.setString(1, title);
            //query.setString(2, desc);
            query.setInt(1, resultSet.getInt(1));
            query.setString(2, post);
            query.setDate(3, Date.valueOf(LocalDate.now()));
            query.execute();
            return "home.xhtml";
        }
        return "preHome.xhtml";
    }
}
