

package DAO;

import Model.AuthTokenModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO class
 * Interacts with auth token data in a database
 */
public class AuthTokenDAO {
    private Connection connection;

    public AuthTokenDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    /**
     * This function adds a new auth token to a username
     * @param authtokenModel AuthTokenModel object to add to table
     */
    public void add(AuthTokenModel authtokenModel) throws SQLException {
        PreparedStatement statement;

        try {
            String sql = "insert into AuthToken (username, authtoken)" +
                    " VALUES(?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, authtokenModel.getUsername());
            statement.setString(2, authtokenModel.getAuthToken());

            statement.executeUpdate();
        }
        catch (SQLException error) {
            throw new SQLException(("Error encountered while inserting into AuthToken table\n" + error.getMessage()));
        }
    }

    /**
     * This function finds the username that corresponds to a specific authtoken
     * @param authtoken authtoken to find
     * @return AuthTokenModel object
     */
    public AuthTokenModel findUsername(String authtoken) throws SQLException {
        AuthTokenModel authTokenModel = new AuthTokenModel();

        try {
            PreparedStatement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from AuthToken WHERE authtoken = '" + authtoken + "'";
                statement = connection.prepareStatement(sql);

                rs = statement.executeQuery();

                if (rs.getString(1) == null) throw new SQLException();

                while (rs.next()) {
                    authTokenModel.setUsername(rs.getString("username"));
                    authTokenModel.setAuthToken(rs.getString("authtoken"));
                }
            }
            finally {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            }
        }
        catch (SQLException error) {
            throw new SQLException("find authtoken failed");
        }
        return authTokenModel;
    }

    /**
     * Clears AuthToken table
     */
    public void clear() throws SQLException {
        PreparedStatement statement = null;
        try {
            String sql = "delete from AuthToken";

            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        finally {
            if (statement != null) statement.close();
        }
    }

}
