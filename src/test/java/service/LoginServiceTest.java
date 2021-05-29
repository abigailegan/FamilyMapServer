package service;

import DAO.DatabaseDAO;
import DAO.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class LoginServiceTest {
    @BeforeEach
    public void setup() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.openConnection();
        databaseDAO.clearDatabase();

        UserDAO userDAO = new UserDAO(databaseDAO.getConnection());


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
    public void loginPass() {

    }
}
