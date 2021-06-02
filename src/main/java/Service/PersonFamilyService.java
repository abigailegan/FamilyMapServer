package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import DAO.PersonDAO;
import Model.AuthTokenModel;
import Model.PersonModel;
import RequestResult.PersonRequest;
import RequestResult.PersonResult;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the /person endpoint
 */
public class PersonFamilyService {
    /**
     * Returns ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param request PersonRequest object
     * @0return PersonResult object
     */
    public PersonResult person(PersonRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            PersonDAO personDAO = new PersonDAO(databaseDAO.getConnection());
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
            ArrayList<PersonModel> family = new ArrayList<PersonModel>();

            AuthTokenModel authTokenModel;
            try {
                authTokenModel = authTokenDAO.findUsername(request.getAuthtoken());
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(false);
                String message = "Error: Invalid authtoken.";
                return new PersonResult(message, false);
            }

            family = personDAO.findByUsername(authTokenModel.getUsername());
            if (family == null) {
                databaseDAO.closeConnection(false);
                String message = "Error: No persons found.";
                return new PersonResult(message, false);
            }
            databaseDAO.closeConnection(true);
            return new PersonResult(family, true);
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
            String message = "Error: " + error.getMessage();
            return new PersonResult(message, false);
        }
    }
}
