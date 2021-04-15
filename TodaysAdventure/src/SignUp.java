import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;


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

    public String dispatch() {
        return "";
    }

}
