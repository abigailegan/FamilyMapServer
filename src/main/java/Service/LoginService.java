package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import DAO.UserDAO;
import Model.AuthTokenModel;
import Model.UserModel;
import RequestResult.LoginRequest;
import RequestResult.LoginResult;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Implements the /user/login endpoint
 */
public class LoginService {
    /**
     * Logs in the user and returns an auth token.
     * @param request LoginRequest object
     * @return LoginResult object
     */
    public LoginResult login(LoginRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            //Verify username and password
            UserDAO userDAO = new UserDAO(databaseDAO.getConnection());

            UserModel matchedUser;
            try {
                matchedUser = userDAO.findByUsername(request.getUsername());
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(true);
                throw new Exception("Incorrect username or password.");
            }

            if (!matchedUser.getPassword().equals(request.getPassword())) {
                databaseDAO.closeConnection(true);
                throw new Exception("Incorrect username or password.");
            }

            databaseDAO.closeConnection(true);

            //Generate authtoken and add to table
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());

            String authtoken = UUID.randomUUID().toString();
            AuthTokenModel authTokenModel = new AuthTokenModel(request.getPersonID(), request.getUsername(), authtoken);

            //Return authtoken
            return new LoginResult(authtoken, matchedUser.getUsername(), matchedUser.getPersonID(), true);
        }
        catch (Exception error) {
            databaseDAO.closeConnection(false);
            String message = "Error: " + error.getMessage();
            return new LoginResult(message, false);
        }
    }
}
