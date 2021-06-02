package service;

import DAO.DatabaseDAO;
import Model.UserModel;
import RequestResult.EventRequest;
import RequestResult.EventResult;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import Service.EventFamilyService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EventFamilyServiceTest {
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
    public void eventFamily() throws SQLException {
        RegisterService registerService = new RegisterService();
        RegisterRequest request = new RegisterRequest(userModel);
        RegisterResult result = registerService.register(request);

        String authtoken = result.getAuthtoken();

        EventFamilyService eventFamilyService = new EventFamilyService();
        EventRequest eventRequest = new EventRequest(authtoken);
        EventResult eventResult = eventFamilyService.event(eventRequest);

        assertTrue(eventResult.isSuccess());
        assertNotNull(eventResult.getData());
        assertEquals(91, eventResult.getData().size());
    }

    @Test
    public void emptyDatabase() throws SQLException {
        String authtoken = UUID.randomUUID().toString();
        EventFamilyService eventFamilyService = new EventFamilyService();
        EventRequest eventRequest = new EventRequest(authtoken);
        EventResult eventResult = eventFamilyService.event(eventRequest);

        assertFalse(eventResult.isSuccess());
        assertEquals("Error: Invalid authtoken.", eventResult.getMessage());
    }
}
