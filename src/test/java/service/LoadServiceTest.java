package service;

import DAO.DatabaseDAO;
import DAO.UserDAO;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {


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
    public void checkNumbers() throws SQLException, IOException {
        Reader dataReader = new FileReader("passoffFiles/loadData.json");
        StringBuilder data = new StringBuilder();
        while (dataReader.ready()) {
            data.append((char) dataReader.read());
        }
        LoadRequest request = new LoadRequest(data.toString());
        LoadService loadService = new LoadService();
        LoadResult result = loadService.load(request);

        String expectedMessage = "Successfully added 2 users, 11 persons, and 19 events to the database.";
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    public void clearsDatabase() throws SQLException, IOException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
        UserModel duplicate = new UserModel("sheila", "parker", "sheila@parker.com",
                "Sheila", "Parker", "f", "Sheila_Parker");
        userDAO.add(duplicate);
        databaseDAO.closeConnection(true);

        Reader dataReader = new FileReader("passoffFiles/loadData.json");
        StringBuilder data = new StringBuilder();
        while (dataReader.ready()) {
            data.append((char) dataReader.read());
        }

        LoadRequest request = new LoadRequest(data.toString());
        LoadService loadService = new LoadService();
        LoadResult result = loadService.load(request);

        String expectedMessage = "Successfully added 2 users, 11 persons, and 19 events to the database.";
        assertTrue(result.isSuccess());
        assertEquals(expectedMessage, result.getMessage());
    }

}
