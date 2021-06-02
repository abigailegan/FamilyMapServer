

package RequestResult;

import Model.PersonModel;

import java.util.ArrayList;

/**
 * /person/[personID] and /person response bodies
 */
public class PersonResult {
    /**
     * Name of user account this person belongs to
     */
    private String associatedUsername;

    /**
     * Person's unique ID
     */
    private String personID;

    /**
     * Person's first name
     */
    private String firstName;

    /**
     * Person's last name
     */
    private String lastName;

    /**
     * Person's gender
     */
    private String gender;

    /**
     * ID of person's father (optional)
     */
    private String fatherID;

    /**
     * ID of person's mother (optional)
     */
    private String motherID;

    /**
     * ID of person's spouse
     */
    private String spouseID;

    /**
     * Description of the error
     */
    private String message;

    /**
     * Boolean identifier
     */
    private boolean success;

    /**
     * Array of Person objects
     */
    private ArrayList<PersonModel> data;

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public ArrayList<PersonModel> getData() {
        return data;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Constructor for /person/[personID] response body (PersonIDService)
     * @param person PersonModel object
     * @param success boolean identifier
     */
    public PersonResult(PersonModel person, boolean success) {
        this.associatedUsername = person.getUsername();
        this.personID = person.getPersonID();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.fatherID = person.getFatherID();
        this.motherID = person.getMotherID();
        this.spouseID = person.getSpouseID();
    }

    /**
     * Constructor for /person response body (PersonFamilyService)
     * @param data ArrayList containing PersonModel objects
     * @param success boolean identifier
     */
    public PersonResult(ArrayList<PersonModel> data, boolean success) {
        this.data = data;
        this.success = success;
    }

    /**
     * Constructor for failed person request response body
     * @param message String error message
     * @param success boolean identifier
     */
    public PersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
