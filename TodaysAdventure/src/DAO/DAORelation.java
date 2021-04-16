package DAO;

import java.util.List;

import tables.Relation;
import tables.User;

public interface DAORelation {
	
	
		public void addRelation(Relation r);
		public void deleteRelation(Relation r);
		
		public List<User> getAllFriends(int idUser);
		
		public  boolean relationExist(int idUser, int idUser2);


	


}
