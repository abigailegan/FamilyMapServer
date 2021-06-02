package service;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import DAO.EventDAO;
import Model.AuthTokenModel;
import Model.EventModel;
import RequestResult.EventRequest;
import RequestResult.EventResult;
import Service.EventIDService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EventIDServiceTest {
    EventModel event;

    @BeforeEach
    public void setup() throws SQLException {
        String eventID = "eventID";
        String username = "qatest";
        String personID = "qatest1personID";
        float latitude = 3;
        float longitude = 4;
        String country = "Paraguay";
        String city = "Guarambare";
        String eventType = "baptism";
        int year = 2019;

        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());
        event = new EventModel(eventID, username, personID, latitude, longitude, country, city, eventType, year);
        eventDAO.add(event);

        AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
        AuthTokenModel authTokenModel = new AuthTokenModel("qatest", "qatest");
        authTokenDAO.add(authTokenModel);
        databaseDAO.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.getConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
    }

    @Test
    public void findPass() throws SQLException {
        EventRequest eventRequest = new EventRequest("eventID", "qatest");
        EventIDService eventIDService = new EventIDService();
        EventResult eventResult = eventIDService.event(eventRequest);

        assertNotNull(eventResult);
        assertEquals(event, eventResult.getEvent());
    }

    @Test
    public void findFail() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.getConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);

        EventRequest eventRequest = new EventRequest(event.getEventID());
        EventIDService eventIDService = new EventIDService();
        assertNull(eventIDService.event(eventRequest).getEvent());
    }
}
