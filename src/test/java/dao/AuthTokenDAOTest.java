package dao;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import Model.AuthTokenModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private DatabaseDAO database = new DatabaseDAO();
    private AuthTokenModel authTokenModel;
    private AuthTokenDAO authTokenDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String personID = "qatest1personID";
        String username = "qatest1";
        String authtoken = UUID.randomUUID().toString();

        authTokenModel = new AuthTokenModel(username, authtoken);
        Connection connection = database.getConnection();
        authTokenDAO = new AuthTokenDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        database.closeConnection(false);
    }

    @Test
    public void addPass() throws SQLException {
        authTokenDAO.add(authTokenModel);
        AuthTokenModel compareTest = authTokenDAO.findUsername(authTokenModel.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(authTokenModel, compareTest);
    }

    @Test
    public void addFail() throws SQLException {
        authTokenDAO.add(authTokenModel);
        assertThrows(SQLException.class, ()-> authTokenDAO.add(authTokenModel));
    }

    @Test
    public void findPass() throws SQLException {
        authTokenDAO.add(authTokenModel);
        AuthTokenModel compareTest = authTokenDAO.findUsername(authTokenModel.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(authTokenModel, compareTest);
    }

    @Test
    public void findFail() throws SQLException {
        authTokenDAO.add(authTokenModel);
        assertThrows(SQLException.class, ()-> authTokenDAO.findUsername(UUID.randomUUID().toString()));
    }

    @Test
    public void clear() throws SQLException {
        authTokenDAO.add(authTokenModel);
        String authtoken = authTokenModel.getAuthToken();
        authTokenDAO.clear();
        assertThrows(SQLException.class, ()-> authTokenDAO.findUsername(authtoken));
    }
}
