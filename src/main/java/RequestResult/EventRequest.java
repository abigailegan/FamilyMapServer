

package RequestResult;

import Model.EventModel;

import java.util.ArrayList;

/**
 * /event/[eventID] and /event request bodies
 */
public class EventRequest {
    /**
     * ID of desired event
     */
    private String eventID;

    /**
     * authtoken
     */
    private String authtoken;

    public String getEventID() { return eventID; }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    /**
     * Constructor for request body for /event/[eventID] endpoint (EventIDService)
     * @param eventID String containing ID of desired event
     * @return EventRequest object
     */
    public EventRequest(String eventID, String authtoken) {
        this.eventID = eventID;
        this.authtoken = authtoken;
    }

    /**
     * Constructor for request body for /event endpoint (EventFamilyService)
     * @return EventRequest object
     */
    public EventRequest(String authtoken) {
        this.authtoken = authtoken;
    }
}
