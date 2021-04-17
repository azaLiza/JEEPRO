import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tables.User;


/**
 * A managed bean that handle the relation system.
 */
@Named
@RequestScoped
public class RelationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * we may need idUser1 got from the Session
	 * idUser2 of every friend
	 */
	private List<User> myfriends;
	
	public List<User> getThem(){
		HttpServletRequest request = null;
		HttpSession session = request.getSession(true);
		int id_user = (Integer) session.getAttribute("id_user");
		return myfriends;
	}

}
