package Model;

public class EventModel {
    /**
     * Unique identifier for an event
     */
    String eventID;

    /**
     * Unique username for the user to which this event belongs
     */
    String username;

    /**
     * Person ID of person to whom this event belongs
     */
    String personID;

    /**
     * Latitude of event
     */
    float latitude;

    /**
     * Longitude of event
     */
    float longitude;

    /**
     * Country in which event occurred
     */
    String country;

    /**
     * City in which event occurred
     */
    String city;

    /**
     * Type of event (birth, baptism, marriage, death, etc)
     */
    String eventType;

    /**
     * Year in which event occurred
     */
    int year;

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    /**
     * Creates a model with event information
     * @param eventID unique identifier for this event (non-empty string)/.
     * @param username username for user to which this event belongs
     * @param personID person ID for person to which this even belongs.
     * @param latitude latitude of event's location
     * @param longitude longitude of event's location
     * @param country country in which event occurred
     * @param city city in which event occurred
     * @param eventType type of event
     * @param year year in which event occurred
     */
    public EventModel(String eventID, String username, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.username = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }
}
