package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseconnector.DBConnection;
import tables.Post;
import tables.User;

public class UseUser implements DAOUser{

	@Override
	public void ajoutUtilisateur(User user) {
		// TODO Auto-generated method stub
		
		//Requete pour inserer un nouvel utilisateur
		String requete = "INSERT INTO users (pseudo,nom,mdp) VALUES (?,?,?)";

		//Connexion à la base de données
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement;

		try {
			//Preparation de la requete
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getMdp());

			//Execution de la requete
			preparedStatement.executeUpdate();
			
			//preparedStatement.close();
			//connection.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void supprimerUtilisateur(User user) {
		// TODO Auto-generated method stub
		//requete pour supprimer un utilisateur
		String requete = "DELETE FROM users WHERE id_user = ?";

		//Connexion à la base de données
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			//Preparation de la requete
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, user.getId());

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
	public void updateUtilisateur(User user, String[] parametres) {
		// TODO Auto-generated method stub
		
		//requete pour modifier un utilisateur 
		String requete = "UPDATE users Set "
				+ "pseudo = ?, nom = ?, mdp = ?"
				+ "WHERE id_user = ?";

		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {

			//Preparation de la requete
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setString(1, parametres[0]);
			preparedStatement.setString(2, parametres[1]);
			preparedStatement.setString(3, parametres[2]);
		

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
	public List<Post> getAllPosts(int idUser) {
		// TODO Auto-generated method stub

		//Liste des posts de l'utilisateur
		List<Post> postsUser = new ArrayList<Post>();

		//Requete
		String requete = "SELECT * FROM posts WHERE id_user = '"+idUser+"'";

		//Recuperation de la connexion à la base de données
		Connection connection = DBConnection.getInstance();
		Statement statement = null;

		try {

			//Execution de la requete et recuperation du resultat
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(requete);

			//Pour chaque tuple creation d'un nouveau post inseré dans la liste
			while(rs.next()) {
				Post p = new Post();
				p.setId(rs.getInt("id_post"));
				p.setIdUser(rs.getInt("id_user"));
				p.setMsg(rs.getString("message"));
				p.setDate(rs.getTimestamp("date"));

				postsUser.add(p);
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

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return postsUser;
	}

	public User getUser(String pseudo, String mdp) {

		User user = new User();

		//Requete pour recuperer un utilisateur
		String requete = "SELECT * from users WHERE pseudo = ? AND mdp = ?";

		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setString(1, pseudo);
			preparedStatement.setString(2, mdp);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				user.setId(rs.getInt("id_user"));
				user.setPseudo(rs.getString("pseudo"));
				user.setName(rs.getString("nom"));
				user.setMdp(rs.getString("mdp"));
				
			}

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

		return user;
	}

	public User getUser(int id_user) {

		User user = new User();

		//Requete pour recuperer un utilisateur
		String requete = "SELECT * from users WHERE id_user = ?";

		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, id_user);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				user.setId(rs.getInt("id_user"));
				user.setPseudo(rs.getString("pseudo"));
				user.setName(rs.getString("nom"));
				user.setMdp(rs.getString("mdp"));
			}

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

		return user;
	}

	public User getUser(String pseudo) {

		User user = new User();

		//Requete pour recuperer un utilisateur
		String requete = "SELECT * from users WHERE pseudo = ?";

		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setString(1, pseudo);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				user.setId(rs.getInt("id_user"));
				user.setPseudo(rs.getString("pseudo"));
				user.setName(rs.getString("nom"));
				user.setMdp(rs.getString("mdp"));
			}

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

		return user;
	}

	
	public boolean exist(String pseudo, String mdp) {
		boolean res = false; 

		String requete = "SELECT * FROM users WHERE pseudo = ? AND mdp = ?";

		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setString(1, pseudo);
			preparedStatement.setString(2, mdp);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				if(rs.getString("pseudo").equals(pseudo) && rs.getString("mdp").equals(mdp)) {
					res = true;
				}
			}

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
		
		return res;
	}
}


