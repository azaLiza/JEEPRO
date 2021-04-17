package connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.UseUser;
import databaseconnector.DBConnection;
/**
 * 
 * @author Liza
 * this class stores the ids of connection during a session
 * this class will be helpfull for managing the relations after
 *
 */

public class Session extends UseUser {
	private int idUser;
	//the password is not needed
	
	public Session() {
		super();
	}

	public Session(int idUser) {
		super();
		this.idUser = idUser;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		//injection dans la bdd
		String query="insert into session (id-session) values (?)";
		Connection con=DBConnection.getInstance();
		PreparedStatement prepared=null;
		try {
			prepared=con.prepareStatement(query);
			prepared.setInt(1, idUser);
			prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.idUser = idUser;
	}


	public void deconnexion() {
		this.idUser=0;
		//delete from la bdd
				String query="delete * from session";
				Connection con=DBConnection.getInstance();
				PreparedStatement prepared=null;
				try {
					prepared=con.prepareStatement(query);
					prepared.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		System.out.println("Logged out!");
	}
	
	public Session getSess() {
		return this;
	}

}
