import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databaseconnector.DBConnection;
import tables.User;
import DAO.DAORelation;
import DAO.UseRelation;


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
		int id_session = 0;
		/*HttpServletRequest request = null;
		HttpSession session = request.getSession(true);
		int id_user = (Integer) session.getAttribute("id_user");*/
		
		String catchsess="select * from session";
		Connection con=DBConnection.getInstance();
		PreparedStatement pre=null;
		try {
			pre=con.prepareStatement(catchsess);
			ResultSet resu=pre.executeQuery();
			id_session=resu.getInt("id_session");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DAORelation daorel=new UseRelation();
		myfriends=daorel.getAllFriends(id_session);
		return myfriends;
	}

}
