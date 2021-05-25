

package RequestResult;

/**
 * /user/login response body
 */
public class LoginResult {
    /**
     * Non-empty auth token string
     */
    private String authtoken;

    /**
     * Username passed in with request
     */
    private String username;

    /**
     * Non-empty string containing the person ID of the user's generated person object
     */
    private String personID;

    /**
     * Boolean identifier
     */
    private boolean success;

    /**
     * Description of the error
     */
    private String message;

    /**
     * Constructor for a successful request
     * @param authtoken String containing generated authtoken
     * @param username String user's username
     * @param personID String user's personID
     * @param success boolean identifier
     */
    public LoginResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /**
     * Constructor for an unssuccesful request
     * @param message String error message
     * @param success boolean identifier
     */
    public LoginResult(String message, boolean success) {
        this.success = success;
        this.message = message;
    }
}
