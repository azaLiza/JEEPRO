import databaseconnector.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;


@Named
@RequestScoped
public class SignUp implements Serializable {
    private String name;
    private String login1;
    private String password1;
    private String confirmPassword;

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login1) {
        this.login1 = login1;
    }


    public boolean dispatch() throws SQLException, NoSuchAlgorithmException {

    public String dispatch() throws SQLException, NoSuchAlgorithmException {


        if (confirmPassword.equals(password1)) {
            PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO users (`psd`, `name`, `pwd`) VALUES (?,?,?);");
            query.setString(1, login1);
            query.setString(2, name);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            query.setString(3, Base64.getEncoder().encodeToString(digest.digest(password1.getBytes(StandardCharsets.UTF_8))));

            return query.execute();
        }
        return false;

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", login1);
            query.execute();
            return query.getUpdateCount() > 0 ? "home.xhtml" : "preHome.xhtml";
        }
        return "preHome.xhtml";

    }

}
