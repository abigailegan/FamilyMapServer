package dao;

import DAO.DatabaseDAO;
import DAO.EventDAO;
import Model.EventModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventDAOTest {
    private final DatabaseDAO database = new DatabaseDAO();
    private EventModel event;
    private EventDAO eventDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String eventID = "eventID";
        String username = "qatest";
        String personID = "qatest1personID";
        float latitude = 3;
        float longitude = 4;
        String country = "Paraguay";
        String city = "Guarambare";
        String eventType = "baptism";
        int year = 2019;
        event = new EventModel(eventID, username, personID, latitude, longitude, country, city, eventType, year);
        Connection connection = database.getConnection();
        eventDAO = new EventDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        database.closeConnection(false);
    }

    @Test
    public void addPass() throws SQLException {
        eventDAO.add(event);
        EventModel compareTest = eventDAO.find(event.getEventID());
        assertNotNull(compareTest);
        assertEquals(event, compareTest);
    }

    @Test
    public void addFail() throws SQLException {
        eventDAO.add(event);
        assertThrows(SQLException.class, ()-> eventDAO.add(event));
    }

    @Test
    public void findPass() throws SQLException {
        eventDAO.add(event);
        EventModel compareTest = eventDAO.find(event.getEventID());
        assertNotNull(compareTest);
        assertEquals(event, compareTest);
    }

    @Test
    public void findFail() throws SQLException {
        eventDAO.add(event);
        assertThrows(SQLException.class, ()-> eventDAO.find(UUID.randomUUID().toString()));
    }

    @Test
    public void clear() throws SQLException {
        eventDAO.add(event);
        eventDAO.clear();
        assertThrows(SQLException.class, ()-> eventDAO.find("eventID"));
    }
 }
