/**
 * DAO class
 * Opens or closes a connection with a SQL database
 */

package DAO;

import java.sql.Connection;

public class DatabaseDAO {
    private Connection connection;

    /**
     * Opens a connection with the database
     */
    public Connection openConnection() { return null; }

    /**
     * Closes connection with the database
     * @param commit: whether or not to commit changes to the database
     */
    public void closeConnection(boolean commit) {}

    public Connection getConnection() {
        return connection;
    }
}
