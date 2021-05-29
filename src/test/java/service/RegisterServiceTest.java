package service;

import DAO.DatabaseDAO;
import DAO.UserDAO;
import Model.UserModel;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {
    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
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
    public void checkUser() throws SQLException {
        RegisterService registerService = new RegisterService();
        UserModel userModel = new UserModel("qatest1", "password123", "qatest1@gmail.com",
                "QA", "Test", "f", UUID.randomUUID().toString());
        RegisterRequest request = new RegisterRequest(userModel);
        RegisterResult result = registerService.register(request);

        assertTrue(result.isSuccess());

        DatabaseDAO databaseDAO = new DatabaseDAO();
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
        databaseDAO.closeConnection(true);

        UserModel addedUser = userDAO.find(userModel.getPersonID());
        assertEquals(userModel.getPersonID(), addedUser.getPersonID());
        assertEquals(userModel.getUsername(), addedUser.getUsername());
    }

    @Test
    public void registerFail() throws SQLException {
        RegisterService registerService = new RegisterService();
        UserModel userModel = new UserModel("qatest1", "password123", "qatest1@gmail.com",
                "QA", "Test", "f", UUID.randomUUID().toString());
        RegisterRequest request = new RegisterRequest(userModel);
        RegisterResult result = registerService.register(request);
        RegisterResult result2 = registerService.register(request);

        assertFalse(result2.isSuccess());
    }

    @Test
    public void checkGenerations() {
        RegisterService registerService = new RegisterService();
        UserModel userModel = new UserModel("qatest1", "password123", "qatest1@gmail.com",
                "QA", "Test", "f", UUID.randomUUID().toString());
        RegisterRequest request = new RegisterRequest(userModel);
        RegisterResult result = registerService.register(request);



    }
}
