package DAO;


import java.util.List;

import tables.Post;
import tables.User;

public interface DAOUser {
	
	public void ajoutUtilisateur(User user);
	
	public void supprimerUtilisateur(User user);
	
	public void updateUtilisateur(User user, String[] parametres);
	
	public List<Post> getAllPosts(int idUser);
	
	public User getUser(String pseudo, String mdp);
	
	public User getUser(int id_user);
	
	public User getUser(String pseudo);
	
	public boolean exist(String pseudo, String mdp);
}

