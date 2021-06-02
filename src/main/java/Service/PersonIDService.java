package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import DAO.PersonDAO;
import Model.AuthTokenModel;
import Model.PersonModel;
import RequestResult.PersonRequest;
import RequestResult.PersonResult;

import java.sql.SQLException;

/**
 * Implements the /person/[personID] endpoint
 */
public class PersonIDService {
    /**
     * Returns the single Person object with the specified ID.
     * @param request PersonRequest object
     * @return PersonResult object
     */
    public PersonResult person(PersonRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            String personID = request.getPersonID();
            String authtoken = request.getAuthtoken();

            AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
            AuthTokenModel authTokenModel;
            try {
                authTokenModel = authTokenDAO.findUsername(request.getAuthtoken());
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(false);
                String message = "Error: Invalid authtoken.";
                return new PersonResult(message, false);
            }

            PersonDAO personDAO = new PersonDAO(databaseDAO.getConnection());
            PersonModel person;
            try {
                person = personDAO.find(personID);
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(false);
                String message = "Error: Invalid personID.";
                return new PersonResult(message, false);
            }

            if (!authTokenModel.getUsername().equals(person.getUsername())) {
                databaseDAO.closeConnection(false);
                String message = "Error: Requested person does not belong to this user.";
                return new PersonResult(message, false);
            }
            databaseDAO.closeConnection(true);
            PersonResult result = new PersonResult(person, true);
            return result;
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
        }
        return null;
    }
}
