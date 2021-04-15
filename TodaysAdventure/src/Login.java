import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;


/**
 * A managed bean that handle a little authentication.
 */
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

    public String dispatch() {
        return "";
    }

}
