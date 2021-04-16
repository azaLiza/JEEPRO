import databaseconnector.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;


@Named
@RequestScoped
public class Login implements Serializable {
    private String login1;
    private String password1;

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login1) {
        this.login1 = login1;
    }

    public boolean dispatch() throws SQLException, NoSuchAlgorithmException {
    public String dispatch() throws SQLException, NoSuchAlgorithmException {
        PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT pwd from users where psd like ?;");
        query.setString(1, login1);
        ResultSet result = query.executeQuery();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return Base64.getEncoder().encodeToString(digest.digest(password1.getBytes(StandardCharsets.UTF_8))).equals(result.getString(1));
        if (result.next()) {
            if (Base64.getEncoder().encodeToString(digest.digest(password1.getBytes(StandardCharsets.UTF_8))).equals(result.getString(1))) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", login1);
                return "home.xhtml";
            }
        }
        return "preHome.xhtml";
    }
}
