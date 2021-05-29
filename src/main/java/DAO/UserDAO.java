package DAO;

import Model.UserModel;
import java.sql.*;

/**
 * DAO class for generated user objects
 */
public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    /**
     * Adds a user to the database
     * @param userModel userModel object
     */
    public void add(UserModel userModel) throws SQLException {
        PreparedStatement statement;
        try {
            String sql = "insert into Users (username, password, email, firstName, lastName, gender, personID)" +
                    " VALUES(?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, userModel.getUsername());
            statement.setString(2, userModel.getPassword());
            statement.setString(3, userModel.getEmail());
            statement.setString(4, userModel.getFirstName());
            statement.setString(5, userModel.getLastName());
            statement.setString(6, userModel.getGender());
            statement.setString(7, userModel.getPersonID());

            statement.executeUpdate();
        }
        catch (SQLException error) {
            throw new SQLException("Error encountered while inserting into Users table");
        }
    }

    /**
     * Finds a user with specified person ID
     * @param personID ID of desired User
     * @return UserModel object
     */
    public UserModel find(String personID) throws SQLException {
        UserModel user = new UserModel();

        try {
            PreparedStatement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from Users WHERE personID = '" + personID + "'";
                statement = connection.prepareStatement(sql);

                rs = statement.executeQuery();

                if (rs.getString(1) == null) throw new SQLException();

                while (rs.next()) {
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setGender(rs.getString("gender"));
                    user.setPersonID(rs.getString("personID"));
                }
            } finally {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            }
        }
        catch (SQLException error) {
            throw new SQLException("find User failed");
        }
        return user;
    }

    public UserModel findByUsername(String username) throws SQLException {
        UserModel user = new UserModel();

        try {
            PreparedStatement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from Users WHERE username = '" + username + "'";
                statement = connection.prepareStatement(sql);

                rs = statement.executeQuery();

                if (rs.getString(1) == null) throw new SQLException();

                while (rs.next()) {
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setGender(rs.getString("gender"));
                    user.setPersonID(rs.getString("personID"));
                }
            } finally {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            }
        }
        catch (SQLException error) {
            throw new SQLException("find User failed");
        }
        return user;
    }

    /**
     * Clears Users table
     */
    public void clear() throws SQLException {
        PreparedStatement statement = null;
        try {
            String sql = "delete from Users";

            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Removes user with specified person ID from the database
     * @param personID ID of specific User to delete
     */
    public void delete(String personID) {}
}
