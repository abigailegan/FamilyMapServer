
package Service;

import DAO.DatabaseDAO;
import DAO.EventDAO;
import Model.EventModel;
import RequestResult.EventRequest;
import RequestResult.EventResult;

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

            EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());
            EventModel event = eventDAO.find(eventID);
            databaseDAO.closeConnection(true);
            return new EventResult(event, true);
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(true);
            throw new SQLException(error.getMessage());
        }
    }
}
