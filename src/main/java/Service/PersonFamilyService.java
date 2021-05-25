package Service;

import RequestResult.PersonRequest;
import RequestResult.PersonResult;

/**
 * Implements the /person endpoint
 */
public class PersonFamilyService {
    /**
     * Returns ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param request PersonRequest object
     * @return PersonResult object
     */
    public PersonResult person(PersonRequest request) {
        return null;
    }
}
