
package Service;

import RequestResult.EventRequest;
import RequestResult.EventResult;

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
    public EventResult event(EventRequest request) {
        return null;
    }
}
