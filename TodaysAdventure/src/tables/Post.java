package tables;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.UsePost;

public class Post implements Serializable{
	
	private int id;
	private int idUser;
	private String msg;
	private Timestamp date;
	
	//private List<Commentaire> commentsList = new ArrayList<Commentaire>();
	
	public Post() {}
	
	public Post(int idUser, String msg, Timestamp date) {
		
		this.idUser = idUser;
		this.msg = msg;
		this.date = date;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/*public List<Commentaire> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList() {
		PostMySQLDao pd = new PostMySQLDao();
		this.commentsList = pd.getAllComments(this.getId());
	}
	
	*/
	

}
