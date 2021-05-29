

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

    public boolean isSuccess() {
        return success;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public String getMessage() {
        return message;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
