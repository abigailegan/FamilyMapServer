

package RequestResult;

import Model.EventModel;

import java.util.ArrayList;

/**
 * Response body for the /event and /event/[eventID] endpoints
 */
public class EventResult {
    /**
     * Array of Event objects
     */
    private ArrayList<EventModel> data;

    /**
     * Boolean identifier
     */
    private boolean success;

    /**
     * Description of the error
     */
    private String message;

    /**
     * Username of of user account this event belongs to
     */
    private String associatedUsername;

    /**
     * Event's unique ID
     */
    private String eventID;

    /**
     * ID of the person this event belongs to
     */
    private String personID;

    /**
     * Latitude of this event's location
     */
    private float latitude;

    /**
     * Longitude of this event's location
     */
    private float longitude;

    /**
     * Name of country where event occurred
     */
    private String country;

    /**
     * Name of city where event occurred
     */
    private String city;

    /**
     * Type of event
     */
    private String eventType;

    /**
     * Year event occurred
     */
    private int year;

    /**
     * EventModel object returned
     */
    private EventModel event;

    public EventModel getEvent() { return event; }

    public String getPersonID() {
        return personID;
    }

    public String getEventID() { return eventID; }

    public String getUsername() { return associatedUsername; }
    public float getLatitude() { return latitude; }
    public float getLongitude() { return longitude; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getEventType() { return eventType; }
    public int getYear() { return year; }

    public ArrayList<EventModel> getData() {
        return this.data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * Constructor for successful /event/[eventID] response body
     * @param event EventModel object
     * @param success boolean identifier
     */
    public EventResult(EventModel event, boolean success) {
        this.event = event;
        this.associatedUsername = event.getUsername();
        this.eventID = event.getEventID();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
        this.success = success;
    }

    /**
     * Constructor for successful /event response body
     * @param data ArrayList containing events
     * @param success boolean identifier
     */
    public EventResult(ArrayList<EventModel> data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /**
     * Constructor for unsuccessful response body
     * @param message String containing error message
     * @param success boolean identifier
     */
    public EventResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        if (o instanceof EventResult) {
            EventResult oEventResult = (EventResult) o;
            return oEventResult.getEventID().equals(getEventID()) &&
                    oEventResult.getUsername().equals(getUsername()) &&
                    oEventResult.getPersonID().equals(getPersonID()) &&
                    oEventResult.getLatitude() == getLatitude() &&
                    oEventResult.getLongitude() == getLongitude() &&
                    oEventResult.getCountry().equals(getCountry()) &&
                    oEventResult.getCity().equals(getCity()) &&
                    oEventResult.getEventType().equals(getEventType()) &&
                    oEventResult.getYear() == getYear();
        }
        else return false;
    }
}
