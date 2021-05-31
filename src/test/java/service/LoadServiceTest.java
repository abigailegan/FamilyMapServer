package service;

import DAO.DatabaseDAO;
import Model.EventModel;
import Model.PersonModel;
import Model.UserModel;
import RequestResult.LoadRequest;
import RequestResult.LoadResult;
import Service.LoadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passoffmodels.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {
    private final UserModel sheila = new UserModel("sheila", "parker", "sheila@parker.com", "Sheila", "Parker", "f", "Sheila_Parker");
    private final UserModel patrick = new UserModel("patrick", "spencer", "sheila@spencer.com", "Patrick", "Spencer", "m", "Patrick_Spencer");
    ArrayList<UserModel> users = new ArrayList<UserModel>();

    private final PersonModel grandma = new PersonModel(UUID.randomUUID().toString(), "sheila", "My", "Grandma", "f", UUID.randomUUID().toString(),
            UUID.randomUUID().toString(), UUID.randomUUID().toString());
    private final PersonModel grandpa = new PersonModel(UUID.randomUUID().toString(), "sheila", "My", "Grandpa", "m", UUID.randomUUID().toString(),
            UUID.randomUUID().toString(), UUID.randomUUID().toString());
    private final PersonModel greatgrandma = new PersonModel(UUID.randomUUID().toString(), "sheila", "Great", "Grandma", "f", UUID.randomUUID().toString(),
            UUID.randomUUID().toString(), UUID.randomUUID().toString());
    private final PersonModel greatgrandpa = new PersonModel(UUID.randomUUID().toString(), "patrick", "Great", "Grandma", "m", UUID.randomUUID().toString(),
            UUID.randomUUID().toString(), UUID.randomUUID().toString());
    ArrayList<PersonModel> persons = new ArrayList<PersonModel>();

    private final EventModel grandmaBirth = new EventModel(UUID.randomUUID().toString(), "patrick", grandma.getPersonID(),
            1, 2, "Germany", "Dudeldorf", "birth", 1945);
    private final EventModel grandpaBirth = new EventModel(UUID.randomUUID().toString(), "patrick", grandpa.getPersonID(),
            1, 2, "Germany", "Dudeldorf", "birth", 1945);
    private final EventModel greatgrandmaBirth = new EventModel(UUID.randomUUID().toString(), "patrick", greatgrandma.getPersonID(),
            1, 2, "Germany", "Dudeldorf", "birth", 1920);
    ArrayList<EventModel> events = new ArrayList<EventModel>();

    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);

        users.add(sheila);
        users.add(patrick);

        persons.add(grandma);
        persons.add(grandpa);
        persons.add(greatgrandma);
        persons.add(greatgrandpa);

        events.add(grandmaBirth);
        events.add(grandpaBirth);
        events.add(greatgrandmaBirth);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
    }

    @Test
    public void checkNumbers() throws SQLException {
        LoadRequest request = new LoadRequest(users, persons, events);
        LoadService loadService = new LoadService();
        LoadResult result = loadService.load(request);

        String expectedMessage = "Successfully added 2 users, 4 persons, and 3 events to the database.";
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    public void duplicateUser() throws SQLException {
        UserModel duplicate = new UserModel("sheila", "parker", "sheila@parker.com",
                "Sheila", "Parker", "f", "Sheila_Parker");
        users.add(duplicate);
        LoadRequest request = new LoadRequest(users, persons, events);
        LoadService loadService = new LoadService();
        LoadResult result = loadService.load(request);

        String expectedMessage = "Error: [SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: Users.personID)";
        assertFalse(result.isSuccess());
        assertEquals(expectedMessage, result.getMessage());
    }

}
