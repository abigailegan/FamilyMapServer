package dao;

import DAO.DatabaseDAO;
import DAO.UserDAO;
import Model.EventModel;
import Model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDAOTest {
    private DatabaseDAO database = new DatabaseDAO();
    private UserModel user;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String username = "qatest";
        String password = "password";
        String email = "qatest1@gmail.com";
        String firstName = "QA";
        String lastName = "Test";
        String gender = "m";
        String personID = "qatest1personID";
        user = new UserModel(username, password, email, firstName, lastName, gender, personID);
        Connection connection = database.getConnection();
        //database.createTable();
        userDAO = new UserDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        database.closeConnection(false);
    }

    @Test
    public void addPass() throws SQLException {
        userDAO.add(user);
        UserModel compareTest = userDAO.find(user.getPersonID());
        assertNotNull(compareTest);
        assertEquals(user, compareTest);
    }

    @Test
    public void addFail() throws SQLException {
        userDAO.add(user);
        assertThrows(SQLException.class, ()-> userDAO.add(user));
    }

    @Test
    public void findPass() throws SQLException {
        userDAO.add(user);
        UserModel compareTest = userDAO.find(user.getPersonID());
        assertNotNull(compareTest);
        assertEquals(user, compareTest);
    }

    @Test
    public void findFail() throws SQLException {
        userDAO.add(user);
        assertThrows(SQLException.class, ()-> userDAO.find(UUID.randomUUID().toString()));
    }

    @Test
    public void clear() throws SQLException {
        userDAO.add(user);
        userDAO.clear();
        assertThrows(SQLException.class, ()-> userDAO.find("qatest1personID"));
    }

    @Test
    public void clearTwice() throws SQLException {
        userDAO.add(user);
        userDAO.clear();
        userDAO.clear();
        assertThrows(SQLException.class, ()-> userDAO.find("eventID"));
    }

    @Test
    public void findByUsernamePass() throws SQLException {
        userDAO.clear();
        userDAO.add(user);
        UserModel matchedUser = userDAO.findByUsername(user.getUsername());
        assertNotNull(matchedUser);
        assertEquals(user, matchedUser);
    }

    @Test
    public void findByUsernameFail() throws SQLException {
        userDAO.clear();
        assertThrows(SQLException.class, ()-> userDAO.findByUsername(user.getUsername()));
    }
}
