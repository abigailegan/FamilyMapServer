package Model;

public class PersonModel {
    /**
     * Unique username (non-empty string)
     */
    String username;

    /**
     *First name of person (non-empty string)
     */
    String firstName;

    /**
     * Last name of person (non-empty string)
     */
    String lastName;

    /**
     * Gender of person (non-empty string)
     */
    String gender;

    /**
     * Person ID of person, unique identifier (non-empty string)
     */
    String personID;

    /**
     * Person ID of person's father (possibly null)
     */
    String fatherID;

    /**
     * Person ID of person's mother (possibly null)
     */
    String motherID;

    /**
     * Person ID of person's spouse (possibly null)
     */
    String spouseID;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    /**
     * Creates a model with person information
     * @param personID unique identifier for this person(non-empty string).
     * @param username associated username for user to which this person belongs
     * @param firstName person's first name (non-empty string).
     * @param lastName person's last name (non-empty string).
     * @param gender person's gender (string "f" or "m").
     * @param fatherID person ID of person's father (possibly null)
     * @param motherID person ID of person's mother (possibly null)
     * @param spouseID person ID of person's spouse (possibly null)
     *
     */
    public PersonModel(String personID, String username, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }
}
