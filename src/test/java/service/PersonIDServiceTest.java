package service;

import DAO.DatabaseDAO;
import Model.PersonModel;
import Model.UserModel;
import RequestResult.PersonRequest;
import RequestResult.PersonResult;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import Service.PersonFamilyService;
import Service.PersonIDService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonIDServiceTest {
    String username = "qatest1";
    String password = "password123";
    String email = "qatest1@gmail.com";
    String firstName = "QA";
    String lastName = "Test";
    String gender = "f";
    String personID = UUID.randomUUID().toString();
    UserModel qatest1 = new UserModel(username, password, email, firstName, lastName, gender, personID);
    UserModel qatest2 = new UserModel("qatest2", password, email, firstName, lastName, gender, UUID.randomUUID().toString());

    String authtoken;
    PersonModel familyMember;
    PersonModel familyMember2;
    @BeforeEach
    public void setup() throws SQLException {
        //Clear the database
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);

        //Register the test user and store authtoken, personID of family
        RegisterService registerService = new RegisterService();
        RegisterRequest request = new RegisterRequest(qatest1);
        RegisterResult result = registerService.register(request);
        authtoken = result.getAuthtoken();

        PersonFamilyService personFamilyService = new PersonFamilyService();
        PersonRequest personRequest = new PersonRequest(authtoken);
        PersonResult personResult = personFamilyService.person(personRequest);
        familyMember = personResult.getData().get(0);

        //Register the second test user and store family member
        RegisterRequest request2 = new RegisterRequest(qatest2);
        RegisterResult result2 = registerService.register(request);

        PersonRequest personRequest2 = new PersonRequest(authtoken);
        PersonResult personResult2 = personFamilyService.person(personRequest2);
        familyMember2 = personResult2.getData().get(0);

    }

    @AfterEach
    public void tearDown() throws SQLException {
        //Clear the database
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
    }

    @Test
    public void personIDPass() throws SQLException {
        PersonIDService personIDService = new PersonIDService();
        PersonRequest personRequest = new PersonRequest(familyMember.getPersonID(), authtoken);
        PersonResult personResult = personIDService.person(personRequest);
        PersonModel foundPerson = new PersonModel(personResult.getPersonID(), personResult.getAssociatedUsername(),
                personResult.getFirstName(), personResult.getLastName(), personResult.getGender(), personResult.getFatherID(),
                personResult.getMotherID(), personResult.getSpouseID());

        assertNotNull(foundPerson);
        assertEquals(familyMember, foundPerson);
    }

    @Test
    public void invalidAuth() throws SQLException {
        String invalidAuth = UUID.randomUUID().toString();
        PersonIDService personIDService = new PersonIDService();
        PersonRequest personRequest = new PersonRequest(familyMember.getPersonID(), invalidAuth);
        PersonResult personResult = personIDService.person(personRequest);

        assertFalse(personResult.isSuccess());
        assertEquals("Error: Invalid authtoken.", personResult.getMessage());
    }

    @Test
    public void invalidPersonID() throws SQLException {
        String invalidPersonID = UUID.randomUUID().toString();
        PersonIDService personIDService = new PersonIDService();
        PersonRequest personRequest = new PersonRequest(invalidPersonID, authtoken);
        PersonResult personResult = personIDService.person(personRequest);

        assertFalse(personResult.isSuccess());
        assertEquals("Error: Invalid personID.", personResult.getMessage());
    }

    /*@Test
    public void invalidUsername() throws SQLException {
        PersonIDService personIDService = new PersonIDService();
        PersonRequest personRequest = new PersonRequest(familyMember2.getPersonID(), authtoken);
        PersonResult personResult = personIDService.person(personRequest);

        assertFalse(personResult.isSuccess());
        assertEquals("Error: Requested person does not belong to this user.", personResult.getMessage());
    }*/
}
