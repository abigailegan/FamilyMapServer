package service;

import DAO.DatabaseDAO;
import RequestResult.FillRequest;
import Service.FillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class FillServiceTest {
    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.getConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);


    }

    @AfterEach
    public void tearDown() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.getConnection();
        databaseDAO.clearDatabase();
        databaseDAO.closeConnection(true);
    }

    /*@Test
    public void checkClear() throws SQLException {
        FillService fillService = new FillService();
        FillRequest request = new FillRequest();
    }*/

    @Test
    public void testLists() throws SQLException {

    }
}
