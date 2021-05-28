package service;

import DAO.*;
import Model.AuthTokenModel;
import Model.EventModel;
import Model.PersonModel;
import Model.UserModel;
import RequestResult.ClearRequest;
import Service.ClearService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClearServiceTest {
    private DatabaseDAO databaseDAO = new DatabaseDAO();
    private PersonModel personModel;
    private PersonDAO personDAO;
    private UserModel userModel;
    private UserDAO userDAO;
    private EventModel eventModel;
    private EventDAO eventDAO;
    private AuthTokenDAO authTokenDAO;
    private AuthTokenModel authTokenModel;

    @BeforeEach
    public void setUp() throws SQLException {
        String personID = UUID.randomUUID().toString();
        String username = "qatest1";
        String firstName = "QA";
        String lastName = "Test";
        String gender = "f";
        String fatherID = UUID.randomUUID().toString();
        String motherID = UUID.randomUUID().toString();
        String spouseID = UUID.randomUUID().toString();
        String password = "password123";
        String email = "qatest1@gmail.com";
        String eventID = UUID.randomUUID().toString();
        float latitude = 3;
        float longitude = 4;
        String country = "Paraguay";
        String city = "Asuncion";
        String eventType = "birth";
        int year = 1976;
        String authtoken = UUID.randomUUID().toString();

        personModel = new PersonModel(personID, username, firstName, lastName, gender, fatherID, motherID, spouseID);
        userModel = new UserModel(username, password, email, firstName, lastName, gender, personID);
        eventModel = new EventModel(eventID, username, personID, latitude, longitude, country, city, eventType, year);
        authTokenModel = new AuthTokenModel(personID, username, authtoken);

        Connection connection = databaseDAO.getConnection();
        databaseDAO.clearDatabase();

        personDAO = new PersonDAO(connection);
        userDAO = new UserDAO(connection);
        eventDAO = new EventDAO(connection);
        authTokenDAO = new AuthTokenDAO(connection);

        personDAO.add(personModel);
        userDAO.add(userModel);
        eventDAO.add(eventModel);
        authTokenDAO.add(authTokenModel);

        databaseDAO.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        databaseDAO.getConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
    }

    @Test
    public void clearHappyPath() throws SQLException {
        String personID = personModel.getPersonID();
        String username = userModel.getUsername();
        String eventID = eventModel.getEventID();

        ClearService clearService = new ClearService();
        ClearRequest clearRequest = new ClearRequest();
        clearService.clear(clearRequest);

        assertThrows(SQLException.class, ()-> userDAO.find(personID));
        assertThrows(SQLException.class, ()-> personDAO.find(personID));
        assertThrows(SQLException.class, ()-> eventDAO.find(eventID));
        assertThrows(SQLException.class, ()-> authTokenDAO.findUsername(username));
    }

    @Test
    public void clear() throws SQLException {

        String personID = personModel.getPersonID();
        String username = userModel.getUsername();
        String eventID = eventModel.getEventID();

        Connection connection = databaseDAO.getConnection();
        userDAO.setConnection(connection);
        eventDAO.setConnection(connection);
        userDAO.clear();
        eventDAO.clear();
        databaseDAO.closeConnection(true);

        ClearService clearService = new ClearService();
        ClearRequest clearRequest = new ClearRequest();
        clearService.clear(clearRequest);

        assertThrows(SQLException.class, ()-> userDAO.find(personID));
        assertThrows(SQLException.class, ()-> personDAO.find(personID));
        assertThrows(SQLException.class, ()-> eventDAO.find(eventID));
        assertThrows(SQLException.class, ()-> authTokenDAO.findUsername(username));
    }
}
