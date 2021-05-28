

package DAO;

import Model.EventModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO class
 * Interacts with event data in a SQL database
 */
public class EventDAO {
    private Connection connection;

    public EventDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    /**
     * Adds an event to the database
     * @param eventModel eventModel object to add to table
     */
    public void add(EventModel eventModel) throws SQLException {
        PreparedStatement statement;

        try {
            String sql = "insert into Events (eventID, username, personID, latitude, longitude, country, city, eventType, year)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, eventModel.getEventID());
            statement.setString(2, eventModel.getUsername());
            statement.setString(3, eventModel.getPersonID());
            statement.setFloat(4, eventModel.getLatitude());
            statement.setFloat(5, eventModel.getLongitude());
            statement.setString(6, eventModel.getCountry());
            statement.setString(7, eventModel.getCity());
            statement.setString(8, eventModel.getEventType());
            statement.setInt(9, eventModel.getYear());

            statement.executeUpdate();
        }
        catch (SQLException error) {
            throw new SQLException("Error encountered while inserting into Events table");
        }
    }

    /**
     * Returns event with specified ID
     * @param eventID eventID of desired event
     * @return EventModel object
     */
    public EventModel find(String eventID) throws SQLException {
        EventModel event = new EventModel();

        try {
            PreparedStatement statement = null;
            ResultSet rs = null;

            try {
                String sql = "select * from Events WHERE eventID = '" + eventID + "'";
                statement = connection.prepareStatement(sql);

                rs = statement.executeQuery();

                if (rs.getString(1) == null) throw new SQLException();

                while (rs.next()) {
                    event.setEventID(rs.getString("eventID"));
                    event.setUsername(rs.getString("username"));
                    event.setPersonID(rs.getString("personID"));
                    event.setLatitude(rs.getFloat("latitude"));
                    event.setLongitude(rs.getFloat("longitude"));
                    event.setCountry(rs.getString("country"));
                    event.setCity(rs.getString("city"));
                    event.setEventType(rs.getString("eventType"));
                    event.setYear(rs.getInt("year"));
                }
            }
            finally {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            }
        }
        catch (SQLException error) {
            throw new SQLException("find Event failed");
        }
        return event;
    }

    /**
     * Clears Events table
     */
    public void clear() throws SQLException {
        PreparedStatement statement = null;

        try {
            String sql = "delete from Events";

            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        finally {
            if (statement != null) statement.close();
        }
    }

    /**
     * Removes specified event from table
     * @param eventID eventID of event to delete
     */
    public void delete(String eventID) {}
}
