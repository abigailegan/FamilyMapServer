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
    public LoginResult login(LoginRequest request) {
        try {
            DatabaseDAO databaseDAO = new DatabaseDAO();
            //Verify username and password
            UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
            UserModel matchedUser = userDAO.findByUsername(request.getUsername());

            if (!matchedUser.getPassword().equals(request.getPassword())) {
                throw new Exception("Incorrect password.");
            }

            //Generate authtoken and add to table
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());

            String authtoken = UUID.randomUUID().toString();
            AuthTokenModel authTokenModel = new AuthTokenModel(request.getPersonID(), request.getUsername(), authtoken);

            //Return authtoken
            return new LoginResult(authtoken, matchedUser.getUsername(), matchedUser.getPersonID(), true);
        }
        catch (Exception error) {
            String message = "Error: " + error.getMessage();
            return new LoginResult(message, false);
        }
    }
}
