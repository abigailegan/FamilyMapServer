package Service;

import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;

/**
 * Implements the /user/register endpoint
 */
public class RegisterService {
    /**
     * Creates a new user account, generates 4 generations of ancestor data for the new user,
     * logs the user in, and returns an auth token.
     * @param request RegisterRequest object
     * @return RegisterResult object
     */
    public RegisterResult register(RegisterRequest request) {
        return null;
    }
}
