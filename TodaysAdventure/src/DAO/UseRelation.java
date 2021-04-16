package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseconnector.DBConnection;
import tables.Relation;
import tables.User;

public class UseRelation implements DAORelation {

	@Override
	public void addRelation(Relation r) {
		// TODO Auto-generated method stub
		//Requete pour inserer une relation entre deux utilisateurs
				String requete = "INSERT INTO friends (id_user1,id_user2) VALUES (?,?)";

				//Connexion à la base de données
				Connection connection = DBConnection.getInstance();
				PreparedStatement preparedStatement = null; 

				try {
					//Preparation de la requete
					preparedStatement = connection.prepareStatement(requete);
					preparedStatement.setInt(1, r.getIdUser1());
					preparedStatement.setInt(2, r.getIdUser2());

					//Execution de la requete
					preparedStatement.executeUpdate();

				} catch(SQLException e) {
					e.printStackTrace();
				} finally {

					if (preparedStatement != null) {
						try {
							preparedStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			
		
	}

	@Override
	public void deleteRelation(Relation r) {
		// TODO Auto-generated method stub
		//requete pour supprimer une relation
				String requete = "DELETE FROM friends WHERE id_user1 = ? AND id_user2=?";

				//Connexion à la base de données
				Connection connection = DBConnection.getInstance();
				PreparedStatement preparedStatement = null;

				try {
					//Preparation de la requete
					preparedStatement = connection.prepareStatement(requete);
					preparedStatement.setInt(1, r.getIdUser1());
					preparedStatement.setInt(2, r.getIdUser2());

					//Execution de la requete
					preparedStatement.executeUpdate();

				} catch(SQLException e) {
					e.printStackTrace();
				} finally {

					if (preparedStatement != null) {
						try {
							preparedStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

		
	}

	@Override
	public List<User> getAllFriends(int idUser) {
		// TODO Auto-generated method stub
		//Liste des amis d'un utilisateur
		
				List<User> friends = new ArrayList<User>();
				
				//Requete de recuperation d'ami
				String requete = "SELECT id_user2 FROM friends WHERE id_user1 = '"+idUser+"'";
				
				//Connexion base de donnees
				Connection connection = DBConnection.getInstance();
				Statement statement = null;
				
				Statement statement2 = null;
				
				try {
					
					//Execution de la requete et recuperation des id
					statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(requete);
					
					statement2 = connection.createStatement();//va accueillir les friends
					
					//Nous parcourons tout les identifiants d'amis
					while(rs.next()) {
						//Nous executons une nouvelle requete pour recuperer l'utilisateur associé à chaque id
						String requete2 = "SELECT * from user WHERE id_user = '"+rs.getInt("id_user2")+"'";
						ResultSet rs2 = statement2.executeQuery(requete2);
						
						//Nous ajoutons l'utilisateur à la liste d'amis
						while(rs2.next()) {
							User u = new User();
							u.setId(rs2.getInt("user_id"));
							u.setPseudo(rs2.getString("pseudo"));
							u.setName(rs2.getString("nom"));
							u.setMdp(rs2.getString("mdp"));
							
							
							friends.add(u);
						}
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				} finally {

					if (statement != null) {
						try {
							statement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if (statement2 != null) {
						try {
							statement2.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				
				return friends;
	}

	@Override
	public boolean relationExist(int idUser, int idUser2) {
		// TODO Auto-generated method stub
boolean res = false;
		
		//Requete pour recuperer la relation
		String requete = "SELECT * FROM friends WHERE id_user1 = ? AND id_user2 = ?";
		
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null; 
		
		try {
			
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, idUser);
			preparedStatement.setInt(2, idUser2);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				if((rs.getInt("id_user1") == idUser) && (rs.getInt("id_user2") == idUser2)) 
					res = true;
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	}
	

}
