

package RequestResult;

/**
 * /user/login request body
 */
public class LoginRequest {
    /**
     * Non-empty string containing user's username
     */
    private String username;

    /**
     * Non-empty string containing user's password
     */
    private String password;

    /**
     * Non-empty string containing user's personID
     */
    private String personID;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    /**
     * Constructor for /user/login request body (LoginService)
     * @param username String containing user's username
     * @param password String containing user's password
     * @return LoginRequest object
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
