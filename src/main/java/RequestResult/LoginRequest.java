

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
     * Constructor for /user/login request body (LoginService)
     * @param username String containing user's username
     * @param password String containing user's password
     * @return LoginRequest object
     */
    public LoginRequest LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;

        return this;
    }
}
