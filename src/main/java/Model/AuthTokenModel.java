package Model;

public class AuthTokenModel {
    /**
     * Authorization token for login request
     */
    String authtoken;

    /**
     * Unique username
     */
    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String authtoken) {
        this.authtoken = authtoken;
    }

    /**
     * Creates a model with authorization token information
     * @param username username for user to which this token belongs
     * @param authtoken authorization token returned by successful login request
     */
    public AuthTokenModel(String username, String authtoken) {
        this.username = username;
        this.authtoken = authtoken;
    }
}
