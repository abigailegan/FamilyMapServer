

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
     * Constructor for request body for /event/[eventID] endpoint (EventIDService)
     * @param eventID String containing ID of desired event
     * @return EventRequest object
     */
    public EventRequest EventRequest(String eventID) {
        this.eventID = eventID;

        return this;
    }

    /**
     * Constructor for request body for /event endpoint (EventFamilyService)
     * @return EventRequest object
     */
    public EventRequest EventRequest() {
        return this;
    }
}
