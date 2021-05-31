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
            throw new SQLException("An error occurred when clearing the database.");
        }
    }

    /**
     * Creates AuthToken, Events, Users, and Persons tables in the database
     * @throws SQLException in case of any error
     */
    public void createTable() throws SQLException {
        try {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                statement.executeUpdate("drop table if exists AuthToken");
                statement.executeUpdate("drop table if exists Events");
                statement.executeUpdate("drop table if exists Persons");
                statement.executeUpdate("drop table if exists Users");

                statement.executeUpdate("CREATE TABLE \"AuthToken\" (" +
                        "\"personID\"   TEXT NOT NULL," +
                        "\"username\"   TEXT NOT NULL," +
                        "\"authtoken\"  TEXT NOT NULL UNIQUE" +
                        ");");
                statement.executeUpdate("CREATE TABLE \"Events\" (" +
                        "\"eventID\"	TEXT NOT NULL," +
                        "\"username\"	TEXT NOT NULL," +
                        "\"personID\"	TEXT NOT NULL," +
                        "\"latitude\"	REAL NOT NULL," +
                        "\"longitude\"	REAL NOT NULL," +
                        "\"country\"	TEXT NOT NULL," +
                        "\"city\"	TEXT NOT NULL," +
                        "\"eventType\"	TEXT NOT NULL," +
                        "\"year\"	TEXT NOT NULL," +
                        "PRIMARY KEY(\"eventID\")" +
                        ");"
                );
                statement.executeUpdate("CREATE TABLE \"Persons\" (" +
                        "\"personID\"   TEXT NOT NULL," +
                        "\"username\"   TEXT NOT NULL," +
                        "\"firstName\"  TEXT NOT NULL," +
                        "\"lastName\"   TEXT NOT NULL," +
                        "\"gender\"     TEXT NOT NULL," +
                        "\"fatherID\"   TEXT," +
                        "\"motherID\"   TEXT," +
                        "\"spouseID\"   TEXT" +
                        "PRIMARY KEY(\"personID\")" +
                        ");");
                statement.executeUpdate("CREATE TABLE \"Users\" (" +
                        "\"username\"   TEXT NOT NULL UNIQUE," +
                        "\"password\"   TEXT NOT NULL," +
                        "\"email\"  TEXT NOT NULL," +
                        "\"firstName\"  TEXT NOT NULL," +
                        "\"lastName\"   TEXT NOT NULL," +
                        "\"gender\"     TEXT NOT NULL," +
                        "\"personID\"   TEXT NOT NULL," +
                        "PRIMARY KEY(\"personID\")" +
                        ");");
            }
            finally {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
            }
        }
        catch (SQLException error) {
            throw new SQLException("createTables failed", error);
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            return openConnection();
        }
        return connection;
    }
}
