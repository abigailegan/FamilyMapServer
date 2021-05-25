package DAO;

import Model.UserModel;
import java.sql.Connection;

public class UserDAO {
    private final Connection conn;

    public UserDAO(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Adds a user to the database
     * @param userModel
     */
    public void add(UserModel userModel) {}

    /**
     * Finds a user with specified person ID
     * @param personID
     * @return
     */
    public UserModel find(String personID) { return null; }

    /**
     * Removes user with specified person ID from the database
     * @param personID
     */
    public void delete(String personID) {}
}
