package service;

import DAO.DatabaseDAO;
import Model.UserModel;
import RequestResult.*;
import Service.EventFamilyService;
import Service.PersonFamilyService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonFamilyServiceTest {
    UserModel userModel = new UserModel("qatest1", "password123", "qatest1@gmail.com",
            "QA", "Test", "f", "thisisapersonIDbwahahaha");

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
    public void personFamily() throws SQLException {
        RegisterService registerService = new RegisterService();
        RegisterRequest request = new RegisterRequest(userModel);
        RegisterResult result = registerService.register(request);

        String authtoken = result.getAuthtoken();

        PersonFamilyService personFamilyService = new PersonFamilyService();
        PersonRequest personRequest = new PersonRequest(authtoken);
        PersonResult personResult = personFamilyService.person(personRequest);

        assertTrue(personResult.isSuccess());
        assertNotNull(personResult.getData());
        assertEquals(31, personResult.getData().size());
    }

    @Test
    public void emptyDatabase() throws SQLException {
        String authtoken = UUID.randomUUID().toString();
        PersonFamilyService personFamilyService = new PersonFamilyService();
        PersonRequest personRequest = new PersonRequest(authtoken);
        PersonResult personResult = personFamilyService.person(personRequest);

        assertFalse(personResult.isSuccess());
        assertEquals("Error: Invalid authtoken.", personResult.getMessage());
    }
}
