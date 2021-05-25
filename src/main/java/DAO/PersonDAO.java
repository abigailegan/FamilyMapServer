package DAO;

import Model.PersonModel;
import java.sql.Connection;

public class PersonDAO {
    private final Connection conn;

    public PersonDAO(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Adds a person to the database
     * @param personModel
     */
    public void add(PersonModel personModel) {}

    /**
     * Finds a person with specified ID
     * @param personID
     * @return
     */
    public PersonModel find(String personID) {return null; }

    /**
     * Removes person with specified ID from database
     * @param personID
     */
    public void delete(String personID) {}
}
