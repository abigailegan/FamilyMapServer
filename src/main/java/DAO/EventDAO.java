/**
 * DAO class
 * Interacts with event data in a SQL database
 */

package DAO;

import Model.EventModel;
import java.sql.Connection;

public class EventDAO {
    private final Connection conn;

    public EventDAO(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Adds an event to the database
     * @param eventModel
     */
    public void add(EventModel eventModel) {}

    /**
     * Returns event with specified ID
     * @param eventID
     * @return
     */
    public EventModel find(String eventID) { return null; }

    /**
     * Removes specified event from table
     * @param eventID
     */
    public void delete(String eventID) {}
}
