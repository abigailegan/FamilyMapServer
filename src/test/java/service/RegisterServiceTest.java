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
    UserModel userModel = new UserModel("qatest1", "password123", "qatest1@gmail.com",
            "QA", "Test", "f", "thisisapersonIDbwahahaha");
    RegisterService registerService = new RegisterService();
    RegisterResult result;
    RegisterRequest request;

    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);

        request = new RegisterRequest(userModel);
        result = registerService.register(request);
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
        assertTrue(result.isSuccess());

        DatabaseDAO databaseDAO = new DatabaseDAO();
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
        UserModel addedUser = new UserModel();
        try {
            addedUser = userDAO.find(result.getPersonID());
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
        }
        databaseDAO.closeConnection(true);

        assertEquals(userModel.getUsername(), addedUser.getUsername());
    }

    @Test
    public void registerFail() throws SQLException {
        RegisterResult result2 = registerService.register(request);
        assertFalse(result2.isSuccess());
    }

}
