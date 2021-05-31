package service;

import DAO.DatabaseDAO;
import DAO.UserDAO;
import Model.UserModel;
import RequestResult.LoginRequest;
import RequestResult.LoginResult;
import Service.LoginService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    String username = "qatest1";
    String password = "password123";
    String email = "qatest1@gmail.com";
    String firstName = "QA";
    String lastName = "Test";
    String gender = "f";
    String personID = UUID.randomUUID().toString();

    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
        UserModel user = new UserModel(username, password, email, firstName, lastName, gender, personID);
        userDAO.add(user);

        databaseDAO.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
    }

    @Test
    public void loginPass() throws SQLException {
        //Create login request and send request
        LoginRequest request = new LoginRequest(username, password);
        LoginService loginService = new LoginService();
        LoginResult loginResult = loginService.login(request);

        //Check for successful login
        assertTrue(loginResult.isSuccess());
        assertEquals(username, loginResult.getUsername());
        assertEquals(personID, loginResult.getPersonID());
        assertNotNull(loginResult.getAuthtoken());
    }

    @Test
    public void loginWrongPassword() throws SQLException {
        //Send login request with incorrect password
        LoginRequest request = new LoginRequest(username, "wrongpassword");
        LoginService loginService = new LoginService();
        LoginResult result = loginService.login(request);

        //Check for failure and correct message
        assertFalse(result.isSuccess());
        assertEquals("Error: Incorrect username or password.", result.getMessage());
    }

    @Test
    public void loginWrongUsername() throws SQLException {
        //Send login request with incorrect username
        LoginRequest request = new LoginRequest("wrongusername", password);
        LoginService loginService = new LoginService();
        LoginResult result = loginService.login(request);

        //Check for failure and correct message
        assertFalse(result.isSuccess());
        assertEquals("Error: Incorrect username or password.", result.getMessage());
    }
}
