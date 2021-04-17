import databaseconnector.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;


/**
 * Classe permettant de gérer les utilisateurs ainsi que leurs relations
 */
@Named
@RequestScoped
public class User implements Serializable {
    private int id;
    private String name;
    private String login;
    private boolean isFriend;

    User() {
        isFriend = false;
    }

    User(int id, String name, String login, boolean isFriend) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.isFriend = isFriend;
    }

    User(int id, String name, String login) {
        this(id, name, login, false);
    }

    /**
     * Méthode pour recupérer les Users depuis la BDD
     *
     * @return HashSet contenant les users
     * @throws SQLException
     */
    public HashSet<User> getUsers() throws SQLException {
        HashSet<User> users = new HashSet<>();
        PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT id_user, psd, name from users where psd != ? ;");
        query.setString(1, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        ResultSet resultSet = query.executeQuery();
        while (resultSet.next()) {
            users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return users;
    }

    /**
     * Méthode pour recupérer les amis de l'utilisateur depuis la BDD
     *
     * @return HashSet contenant les amis de l'utilisateur
     * @throws SQLException
     */
    public HashSet<User> getFriends() throws SQLException {
        HashSet<User> users = new HashSet<>();
        PreparedStatement queryGetID = DBConnection.getInstance().prepareStatement("SELECT id_user from users where name like ?;");
        queryGetID.setString(1, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        ResultSet rs = queryGetID.executeQuery();
        if (rs.next()) {
            int myID = rs.getInt(1);
            PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT id_user,psd,name from users where id_user in (SELECT idf2 from friends where idf1 = ? UNION SELECT idf1 from friends where idf2 = ?);");
            query.setInt(1, myID);
            query.setInt(2, myID);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), false));
            }
        }
        return users;
    }

    /**
     * Méthode pour recupérer les amis en commun entre un ami selectionné et l'utilisateur depuis la BDD
     *
     * @param id
     * @return HashSet contenant les amis en commun
     * @throws SQLException
     */
    public HashSet<User> getCommonFriends(int id) throws SQLException {
        HashSet<User> friends = getFriends();
        HashSet<User> users = new HashSet<>();
        PreparedStatement query = DBConnection.getInstance().prepareStatement("SELECT id_user,psd,name from users where id_user in (SELECT idf2 from friends where idf1 = ? UNION SELECT idf1 from friends where idf2 = ?);");
        query.setInt(1, id);
        query.setInt(2, id);
        ResultSet resultSet = query.executeQuery();
        while (resultSet.next()) {
            users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), false));
        }
        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        users.removeIf(u -> (u.login.equals(username)));
        users.removeAll(friends);
        return users;
    }

    /**
     * Méthode pour recupérer les utilisateurs qui ne sont pas ami avec l'utilisateur depuis la BDD
     *
     * @return HashSet contenant les potentiel amis
     * @throws SQLException
     */
    public HashSet<User> getPotentialFriends() throws SQLException {
        HashSet<User> potentialFriends = getUsers();
        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        potentialFriends.removeIf(u -> (u.login.equals(username)));
        HashSet<User> friends = getFriends();
        System.out.println(friends.size());
        potentialFriends.removeAll(friends);
        return potentialFriends;
    }

    /**
     * Méthode pour ajouter les utilisateurs qui ne sont pas ami avec l'utilisateur dans la BDD
     *
     * @return redirection vers les pages xhtml
     * @throws SQLException
     */
    public String addFriend() throws SQLException {
        PreparedStatement queryGetID = DBConnection.getInstance().prepareStatement("SELECT id_user from users where name like ?;");
        queryGetID.setString(1, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
        ResultSet rs = queryGetID.executeQuery();
        if (rs.next()) {
            int myID = rs.getInt(1);
            PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO friends VALUES (?,?);");
            query.setInt(1, myID);
            query.setInt(2, id);
            query.execute();
        }
        return "addFriends.xhtml?faces-redirect=true";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && isFriend() == user.isFriend() && getName().equals(user.getName()) && getLogin().equals(user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), isFriend());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
