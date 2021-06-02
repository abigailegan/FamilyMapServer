package dao;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import Model.AuthTokenModel;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseDAOTest {
    @Test
    public void clear() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();

        String username = "qatest1";
        String authtoken = UUID.randomUUID().toString();

        AuthTokenModel authTokenModel = new AuthTokenModel(username, authtoken);
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
        authTokenDAO.add(authTokenModel);

        databaseDAO.clearDatabase();

        assertThrows(SQLException.class, ()-> authTokenDAO.findUsername(authtoken));

        databaseDAO.closeConnection(true);
    }

    @Test
    public void clearTwice() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();

        String username = "qatest1";
        String authtoken = UUID.randomUUID().toString();

        AuthTokenModel authTokenModel = new AuthTokenModel(username, authtoken);
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
        authTokenDAO.add(authTokenModel);

        databaseDAO.clearDatabase();
        databaseDAO.clearDatabase();

        assertThrows(SQLException.class, ()-> authTokenDAO.findUsername(authtoken));

        databaseDAO.closeConnection(true);
    }

    @Test
    public void openPass() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        assertNotNull(databaseDAO.openConnection());
        databaseDAO.closeConnection(true);
    }

    @Test
    public void openTwice() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        assertNotNull(databaseDAO.openConnection());
        databaseDAO.closeConnection(true);
    }

    @Test
    public void closePass() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        Connection connection = databaseDAO.openConnection();
        databaseDAO.closeConnection(true);
        Connection connection2 = databaseDAO.getConnection();
        assertNotEquals(connection, connection2);
    }

    @Test
    public void closeTwice() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.closeConnection(true);
        assertDoesNotThrow(()->databaseDAO.closeConnection(true));
    }
}
