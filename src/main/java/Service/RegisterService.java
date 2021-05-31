package Service;

import DAO.DatabaseDAO;
import DAO.UserDAO;
import Model.UserModel;
import RequestResult.*;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Implements the /user/register endpoint
 */
public class RegisterService {
    /**
     * Creates a new user account, generates 4 generations of ancestor data for the new user,
     * logs the user in, and returns an auth token.
     * @param request RegisterRequest object
     * @return RegisterResult object
     */
    public RegisterResult register(RegisterRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            UserDAO userDAO = new UserDAO(databaseDAO.getConnection());

            //Create new user account
            UserModel userModel = new UserModel(request.getUsername(), request.getPassword(), request.getEmail(),
                        request.getFirstName(), request.getLastName(), request.getGender(), UUID.randomUUID().toString());
            userDAO.add(userModel);
            databaseDAO.closeConnection(true);

            //Generate 4 generations of ancestor data
            FillService fillService = new FillService();
            FillRequest fillRequest = new FillRequest(userModel.getUsername(), 4);
            FillResult fillResult = fillService.fill(fillRequest);

            //Logs the user in
            LoginRequest loginRequest = new LoginRequest(userModel.getUsername(), userModel.getPassword());
            LoginService loginService = new LoginService();
            LoginResult loginResult = loginService.login(loginRequest);

            //Returns authtoken
            return new RegisterResult(loginResult.getAuthtoken(), loginResult.getUsername(), loginResult.getPersonID(), true);

        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
            String message = "Error" + error.getMessage();
            return new RegisterResult(message, false);
        }
    }
}
