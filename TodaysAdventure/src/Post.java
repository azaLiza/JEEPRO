import databaseconnector.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named
@RequestScoped
public class Post implements Serializable {
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
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean addPost() throws SQLException {
        PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO posts (`id_user`, `content_post`, `date_post`) VALUES (?,?,?);");
        query.setString(1, title);
        query.setString(2, desc);
        query.setString(3, post);
        return query.execute();
    }

    public boolean getAPost() throws SQLException {

        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT content_post from posts INNER JOIN users u on posts.id_user = u.id_user where u.name like ? ORDER BY date_post;");
        query.setString(1, username);
        ResultSet result = query.executeQuery();
        return true;

    }


}
