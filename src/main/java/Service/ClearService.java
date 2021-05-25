
package Service;

import RequestResult.ClearRequest;
import RequestResult.ClearResult;

/**
 * implements the /clear endpoint
 */
public class ClearService {
    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data.
     * @param request ClearRequest object
     * @return ClearResult object
     */
    public ClearResult clear(ClearRequest request) { return null; }
}
