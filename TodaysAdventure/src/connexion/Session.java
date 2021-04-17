package connexion;

import DAO.UseUser;
/**
 * 
 * @author Liza
 * this class stores the ids of connection during a session
 * this class will be helpfull for managing the relations after
 *
 */

public class Session extends UseUser {
	private int idUser;
	private String pseudo;
	//the password is not needed
	
	public Session() {
		super();
	}

	public Session(int idUser, String pseudo) {
		super();
		this.idUser = idUser;
		this.pseudo = pseudo;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public void deconnexion() {
		this.idUser=0;
		this.pseudo=null;
		System.out.println("Logged out!");
	}

}
