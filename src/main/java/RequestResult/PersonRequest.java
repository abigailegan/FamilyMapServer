

package RequestResult;

/**
 * /person/[personID] and /person request bodies
 */
public class PersonRequest {
    /**
     * Person ID of person
     */
    private String personID;

    /**
     * Authtoken of user
     */
    private String authtoken;

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    /**
     * Constructor for /person/[personID] endpoint request (PersonIDService)
     * Returns single Person object
     * @param personID String ID of desired person
     * @return PersonRequest object
     */
    public PersonRequest(String personID, String authtoken) {
        this.personID = personID;
        this.authtoken = authtoken;
    }

    /**
     * Constructor for /person endpoint request (PersonFamilyService)
     * Returns all family members for current user
     * @return PersonRequest object
     */
    public PersonRequest(String authtoken) {
        this.authtoken = authtoken;
    }
}
