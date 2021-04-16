package tables;

import java.io.Serializable;

public class User implements Serializable {
	
	private int id;
	private String pseudo;
	private String name;
	private String mdp;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public User(int id, String pseudo, String name, String mdp) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.name = name;
		this.mdp = mdp;
	}
	
	public User() {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.name = name;
		this.mdp = mdp;
	}
	
	
}
