package DAO;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import databaseconnector.DBConnection;
//import tables.Commentaire;
import tables.Post;

public class UsePost implements PostDao{

	@Override
	public void ajouterPost(Post post) {
		// TODO Auto-generated method stub
		//Requete d'ajout de post
		String requete = "INSERT INTO posts (id_user, message, date) VALUES (?,?,?)";

		//Connexion base de donnees
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {

			//Preparation de la requete
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, post.getIdUser());
			preparedStatement.setString(2, post.getMsg());
			preparedStatement.setTimestamp(3, (Timestamp) post.getDate());

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
	public void supprimerPost(Post post) {
		// TODO Auto-generated method stub

		//requete pour supprimer un post
		String requete = "DELETE FROM post WHERE id_post = ?";

		//Connexion à la base de données
		Connection connection = DBConnection.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			//Preparation de la requete
			preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setInt(1, post.getId());

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
/*
	@Override
	public List<Commentaire> getAllComments(int idPost) {
		// TODO Auto-generated method stub

		//List des commentaires d'un post
		List<Commentaire> listComments = new ArrayList<Commentaire>();

		//Requete pour recuperer les commentaires
		String requete = "SELECT * FROM commentaire WHERE id_post = '"+idPost+"'";

		//Connexion base de donnees
		Connection connection = DBConnection.getInstance();
		Statement statement = null;

		try {

			//Execution de la requete et recuperation du resultat
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(requete);

			while(rs.next()) {
				Commentaire c = new Commentaire();
				c.setId(rs.getInt("id_comm"));
				c.setIdPost(rs.getInt("id_post"));
				c.setIdUser(rs.getInt("id_user"));
				c.setMsg(rs.getString("message"));
				c.setDate(rs.getTimestamp("date"));

				listComments.add(c);
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

		return listComments;
	}
*/
}

