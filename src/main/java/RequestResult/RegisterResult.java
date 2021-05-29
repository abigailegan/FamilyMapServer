

package RequestResult;

/**
 * /user/register response body
 */
public class RegisterResult {
    /**
     * Non-empty auth token string
     */
    private String authtoken;

    /**
     * Username passed in with request
     */
    private String username;

    /**
     * Non-empty string containing the Person ID of the user's generated person object
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * Constructor for a successful result
     * @param authtoken String generated authtokean
     * @param username String user's username
     * @param personID String user's personID
     * @param success boolean identifier
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /**
     * Constructor for an unsuccessful result
     * @param message String error message
     * @param success boolean identifier
     */
    public RegisterResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
