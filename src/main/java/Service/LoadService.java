package Service;

import RequestResult.LoadRequest;
import RequestResult.LoadResult;

/**
 * Implements the /load endpoint
 */
public class LoadService {
    /**
     * Clears all data from the database (just like the /clear API),
     * and then loads the posted user, person, and event data into the database.
     * @param request LoadRequest object
     * @return LoadResult object
     */
    public LoadResult load(LoadRequest request) {
        return null;
    }
}
