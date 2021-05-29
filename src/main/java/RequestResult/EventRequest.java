

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

    public String getEventID() { return eventID; }

    /**
     * Constructor for request body for /event/[eventID] endpoint (EventIDService)
     * @param eventID String containing ID of desired event
     * @return EventRequest object
     */
    public EventRequest(String eventID) {
        this.eventID = eventID;
    }

    /**
     * Constructor for request body for /event endpoint (EventFamilyService)
     * @return EventRequest object
     */
    public EventRequest() {
    }
}
