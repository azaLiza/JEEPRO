package tables;

import java.io.Serializable;

public class Relation implements Serializable {
	private int idRelation;
	private int idUser1;
	private int idUser2;
	
public Relation() {}
	
	public Relation(int id1,int id2) {
		this.idUser1 = id1;
		this.idUser2 = id2;
	}
	
	public int getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(int id1) {
		this.idUser1 = id1;
	}
	public int getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(int id2) {
		this.idUser2 = id2;
	}

	public int getIdRelation() {
		return idRelation;
	}

	public void setIdRelation(int idRelation) {
		this.idRelation = idRelation;
	}

}
