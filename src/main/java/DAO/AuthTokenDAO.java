/**
 * DAO class
 * Interacts with auth token data in a database
 */

package DAO;

import Model.AuthTokenModel;
import java.sql.Connection;

public class AuthTokenDAO {
    private final Connection conn;

    public AuthTokenDAO(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * This function adds a new auth token to a username
     * @param authtokenModel
     */
    public void addAuthToken(AuthTokenModel authtokenModel) {}

    /**
     * This function finds the auth tokens that correspond to a specific username
     * @param username
     * @return AuthTokenModel
     */
    public AuthTokenModel findAuthTokens(String username) { return null; }

    /**
     * This function finds the username that corresponds to a specific authtoken
     * @param authtoken
     * @return AuthTokenModel
     */
    public AuthTokenModel findUsername(String authtoken) { return null; }

    /**
     * Removes a specific auth token from a username
     * @param authtoken
     */
    public void delete(String authtoken) {}

}
