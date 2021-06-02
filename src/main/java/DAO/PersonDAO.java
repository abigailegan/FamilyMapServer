package DAO;

import Model.PersonModel;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO class for generated Person objects
 */
public class PersonDAO {
    private Connection connection;

    public PersonDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    /**
     * Adds a person to the database
     * @param personModel Person object
     */
    public void add(PersonModel personModel) throws SQLException {
        PreparedStatement statement = null;
        try {
            try {
                String sql = "insert into Persons (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) values (?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, personModel.getPersonID());
                statement.setString(2, personModel.getUsername());
                statement.setString(3, personModel.getFirstName());
                statement.setString(4, personModel.getLastName());
                statement.setString(5, personModel.getGender());
                statement.setString(6, personModel.getFatherID());
                statement.setString(7, personModel.getMotherID());
                statement.setString(8, personModel.getSpouseID());

                statement.executeUpdate();
            } finally {
                if (statement != null) {
                    statement.close();
                }
            }
        }
        catch (SQLException error) {
            throw new SQLException("Error encountered while inserting into Persons table\n" + error.getMessage());
        }
    }

    /**
     * Finds a person with specified ID
     * @param id String Person identifier
     * @return PersonModel object
     */
    public PersonModel find(String id) throws SQLException {
        PersonModel person = new PersonModel();
        try {
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
                String sql = "select * from Persons WHERE personID = '" + id + "'";
                statement = connection.prepareStatement(sql);

                rs = statement.executeQuery();

                if (rs.getString(1) == null) throw new SQLException();

                while (rs.next()) {
                    person.setPersonID(rs.getString(1));
                    person.setUsername(rs.getString(2));
                    person.setFirstName(rs.getString(3));
                    person.setLastName(rs.getString(4));
                    person.setGender(rs.getString(5));
                    person.setFatherID(rs.getString(6));
                    person.setMotherID(rs.getString(7));
                    person.setSpouseID(rs.getString(8));
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            }
        }
        catch (SQLException error) {
            throw new SQLException("find Person failed");
        }
        return person;
    }

    /**
     * Returns all Persons with associated username
     * @param username Associated username
     * @return ArrayList of PersonModels
     * @throws SQLException if query fails
     */
    public ArrayList<PersonModel> findByUsername(String username) throws SQLException {
        ArrayList<PersonModel> people = new ArrayList<PersonModel>();
        try {
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
                String sql = "select * from Persons WHERE associatedUsername = '" + username + "'";
                statement = connection.prepareStatement(sql);

                rs = statement.executeQuery();

                if (rs.getString(1) == null) throw new SQLException();

                while (rs.next()) {
                    PersonModel person = new PersonModel();
                    person.setPersonID(rs.getString(1));
                    person.setUsername(rs.getString(2));
                    person.setFirstName(rs.getString(3));
                    person.setLastName(rs.getString(4));
                    person.setGender(rs.getString(5));
                    person.setFatherID(rs.getString(6));
                    person.setMotherID(rs.getString(7));
                    person.setSpouseID(rs.getString(8));
                    people.add(person);
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            }
        }
        catch (SQLException error) {
            throw new SQLException("findByUsername failed");
        }
        return people;
    }

    public void deleteByUsername(String username) throws SQLException {
        try {
            PreparedStatement statement = null;
            try {
                String sql = "delete from Persons WHERE associatedUsername = '" + username + "'";
                statement = connection.prepareStatement(sql);

                statement.executeUpdate();
            } finally {
                if (statement != null) statement.close();
            }
        }
        catch (SQLException error) {
            throw new SQLException("deleteByUsername failed");
        }
    }

    /**
     * Clears Persons table
     * @throws SQLException in case of error
     */
    public void clear() throws SQLException {
        PreparedStatement statement = null;
        try {
            String sql = "delete from Persons";

            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * Removes person with specified ID from database
     * @param personID ID of desired person
     */
    public void delete(String personID) {}
}
