
package Service;

import DAO.*;
import Model.PersonModel;
import RequestResult.ClearRequest;
import RequestResult.ClearResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * implements the /clear endpoint
 */
public class ClearService {
    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data.
     * @param request ClearRequest object
     * @return ClearResult object
     */
    public ClearResult clear(ClearRequest request) throws SQLException {
        try {
            DatabaseDAO databaseDAO = new DatabaseDAO();
            databaseDAO.getConnection();
            databaseDAO.clearDatabase();

            databaseDAO.closeConnection(true);
        }
        catch (SQLException error) {
            String message = error.getMessage();
            boolean success = false;
            ClearResult clearResult = new ClearResult(message, success);
            return clearResult;
        }

        String message = "Clear succeeded.";
        boolean success = true;
        ClearResult clearResult = new ClearResult(message, success);
        return clearResult;
    }
}
