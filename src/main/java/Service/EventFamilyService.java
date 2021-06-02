
package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseDAO;
import DAO.EventDAO;
import DAO.PersonDAO;
import Model.AuthTokenModel;
import Model.EventModel;
import Model.PersonModel;
import RequestResult.EventRequest;
import RequestResult.EventResult;
import RequestResult.PersonResult;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * EventFamilyService.java
 * Implements the /event endpoint
 */
public class EventFamilyService {
    /**
     * Returns ALL events for ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param request EventRequest object
     * @return EventResult endpoint
     */
    public EventResult event(EventRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
            ArrayList<EventModel> events = new ArrayList<EventModel>();

            AuthTokenModel authTokenModel;
            try {
                authTokenModel = authTokenDAO.findUsername(request.getAuthtoken());
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(false);
                String message = "Error: Invalid authtoken.";
                return new EventResult(message, false);
            }

            events = eventDAO.findByUsername(authTokenModel.getUsername());
            if (events == null) {
                databaseDAO.closeConnection(false);
                String message = "Error: No persons found.";
                return new EventResult(message, false);
            }
            databaseDAO.closeConnection(true);
            return new EventResult(events, true);
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
            String message = "Error: " + error.getMessage();
            return new EventResult(message, false);
        }
    }
}
