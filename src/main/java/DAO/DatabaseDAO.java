package DAO;

import java.sql.*;

/**
 * DAO class
 * Opens or closes a connection with a SQL database
 */
public class DatabaseDAO {

    private Connection connection;

    /**
     * Opens a connection with the database
     */
    public Connection openConnection() throws SQLException {
        try {
            if (connection != null) return connection;
            final String CONNECTION_URL = "jdbc:sqlite:fms.db";

            connection = DriverManager.getConnection(CONNECTION_URL);
            connection.setAutoCommit(false);
        }
        catch (SQLException error) {
            throw new SQLException("openConnection failed", error);
        }

        return connection;
    }

    /**
     * Closes connection with the database
     * @param commit: whether or not to commit changes to the database
     */
    public void closeConnection(boolean commit) throws SQLException {
        if (connection == null) return;
        try {
            if (commit) {
                connection.commit();
            }
            else {
                connection.rollback();
            }

            connection.close();
            connection = null;
        }
        catch (SQLException error) {
            error.printStackTrace();
            throw new SQLException("closeConnection failed", error);
        }
    }

    /**
     * Clears database
     */
    public void clearDatabase() throws SQLException {
        try {
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(connection);
            EventDAO eventDAO = new EventDAO(connection);
            PersonDAO personDAO = new PersonDAO(connection);
            UserDAO userDAO = new UserDAO(connection);

            authTokenDAO.clear();
            eventDAO.clear();
            personDAO.clear();
            userDAO.clear();


        }
        catch (SQLException error) {
            throw new SQLException("An error occurred when clearing the database.\n" + error.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            return openConnection();
        }
        return connection;
    }
}
