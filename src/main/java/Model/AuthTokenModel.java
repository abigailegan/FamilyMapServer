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

    /**
     * Unique personID
     */
    String personID;

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        if (o instanceof AuthTokenModel) {
            AuthTokenModel oAuthTokenModel = (AuthTokenModel) o;
            return oAuthTokenModel.getPersonID().equals(getPersonID()) &&
                    oAuthTokenModel.getUsername().equals(getUsername()) &&
                    oAuthTokenModel.getAuthToken().equals(getAuthToken());
        }
        else return false;
    }

    /**
     * Creates a model with authorization token information
     * @param username username for user to which this token belongs
     * @param authtoken authorization token returned by successful login request
     */
    public AuthTokenModel(String personID, String username, String authtoken) {
        this.personID = personID;
        this.username = username;
        this.authtoken = authtoken;
    }

    /**
     * Constructor for AuthTokenModel with no parameters
     */
    public AuthTokenModel() {

    }
}
