package tables;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Comment implements Serializable{
	
	private int id;
	private int idPost;
	private int idUser;
	private String msg;
	private Timestamp date;
	
	public Comment() {}
	
	public Comment(int idPost, int idUser, String msg, Timestamp date) {
		this.idPost = idPost;
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
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
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
	
	
	

}
