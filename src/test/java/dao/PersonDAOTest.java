package dao;

import DAO.DatabaseDAO;
import DAO.PersonDAO;
import Model.EventModel;
import Model.PersonModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private DatabaseDAO database = new DatabaseDAO();
    private PersonModel person;
    private PersonDAO personDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String personID = "qatest1personID";
        String username = "qatest1";
        String firstName = "QA";
        String lastName = "Test";
        String gender = "m";
        String fatherID = "qatest1fatherID";
        String motherID = "qatest1motherID";
        String spouseID = "qatest1spouseID";
        person = new PersonModel(personID, username, firstName, lastName, gender, fatherID, motherID, spouseID);
        Connection connection = database.getConnection();
        //database.createTable();
        personDAO = new PersonDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        database.closeConnection(false);
    }

    @Test
    public void addPass() throws SQLException {
        personDAO.add(person);
        PersonModel compareTest = personDAO.find(person.getPersonID());
        assertNotNull(compareTest);
        assertEquals(person, compareTest);
    }

    @Test
    public void addFail() throws SQLException {
        personDAO.add(person);
        assertThrows(SQLException.class, ()-> personDAO.add(person));
    }

    @Test
    public void findPass() throws SQLException {
        personDAO.add(person);
        PersonModel compareTest = personDAO.find(person.getPersonID());
        assertNotNull(compareTest);
        assertEquals(person, compareTest);
    }

    @Test
    public void findFail() throws SQLException {
        personDAO.add(person);
        assertThrows(SQLException.class, ()-> personDAO.find(UUID.randomUUID().toString()));
    }

    @Test
    public void clear() throws SQLException {
        personDAO.add(person);
        personDAO.clear();
        assertThrows(SQLException.class, ()-> personDAO.find("qatest1personID"));
    }

    @Test
    public void clearTwice() throws SQLException {
        personDAO.add(person);
        personDAO.clear();
        personDAO.clear();
        assertThrows(SQLException.class, ()-> personDAO.find("personID"));
    }

    @Test
    public void findByUsernamePass() throws SQLException {
        personDAO.clear();
        personDAO.add(person);
        ArrayList<PersonModel> matchedPersons = personDAO.findByUsername(person.getUsername());
        assertNotNull(matchedPersons);
        assertEquals(person, matchedPersons.get(0));
    }

    @Test
    public void findByUsernameFail() throws SQLException {
        personDAO.clear();
        assertThrows(SQLException.class, ()-> personDAO.findByUsername(person.getUsername()));
    }
}
