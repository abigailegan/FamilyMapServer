
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

/**
 * Implements the /event/[eventID] endpoint
 */
public class EventIDService {
    /**
     * Returns the single Event object with the specified ID.
     * @param request EventRequest object
     * @return EventResult object
     */
    public EventResult event(EventRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            String eventID = request.getEventID();
            String authtoken = request.getAuthtoken();

            AuthTokenDAO authTokenDAO = new AuthTokenDAO(databaseDAO.getConnection());
            AuthTokenModel authTokenModel;
            try {
                authTokenModel = authTokenDAO.findUsername(request.getAuthtoken());
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(false);
                String message = "Error: Invalid authtoken.";
                return new EventResult(message, false);
            }

            EventDAO eventDao = new EventDAO(databaseDAO.getConnection());
            EventModel event;
            try {
                event = eventDao.find(eventID);
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(false);
                String message = "Error: Invalid eventID.";
                return new EventResult(message, false);
            }

            if (!authTokenModel.getUsername().equals(event.getUsername())) {
                databaseDAO.closeConnection(false);
                String message = "Error: Requested event does not belong to this user.";
                return new EventResult(message, false);
            }
            databaseDAO.closeConnection(true);
            return new EventResult(event, true);
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
        }
        return null;
    }
}
