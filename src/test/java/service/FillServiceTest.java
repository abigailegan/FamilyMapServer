package service;

import DAO.DatabaseDAO;
import DAO.EventDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import Model.EventModel;
import Model.PersonModel;
import Model.UserModel;
import RequestResult.FillRequest;
import RequestResult.FillResult;
import Service.FillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {
    FillRequest request = new FillRequest("qatest1", 4);
    FillService fillService = new FillService();
    UserModel userModel = new UserModel("qatest1", "password123", "qatest1@gmail.com", "QA", "Test", "f", UUID.randomUUID().toString());

    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.getConnection();
        databaseDAO.clearDatabase();
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
        userDAO.add(userModel);
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
    public void checkGenerations() throws SQLException {
        FillResult result = fillService.fill(request);

        assertTrue(result.isSuccess());

        DatabaseDAO databaseDAO = new DatabaseDAO();
        PersonDAO personDAO = new PersonDAO(databaseDAO.getConnection());
        EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());

        int numPersonsCount = (int) (Math.pow(2, (5)) - 1);
        int numEventsCount = (numPersonsCount - 1) * 3 + 1;

        ArrayList<PersonModel> people = personDAO.findByUsername("qatest1");
        int numPeople = people.size();

        ArrayList<EventModel> events = eventDAO.findByUsername("qatest1");
        int numEvents = events.size();

        assertEquals(numPersonsCount, numPeople);
        assertEquals(numEventsCount, numEvents);

        UserModel user = userDAO.findByUsername("qatest1");
        assertEquals(userModel, user);

        databaseDAO.closeConnection(true);
    }

    @Test
    public void checkUser() throws SQLException {
        FillResult result = fillService.fill(request);

        assertTrue(result.isSuccess());
        DatabaseDAO databaseDAO = new DatabaseDAO();
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());

        UserModel matchedUser = userDAO.findByUsername(userModel.getUsername());
        assertEquals(userModel, matchedUser);
        databaseDAO.closeConnection(true);
    }

}
