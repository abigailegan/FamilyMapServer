

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
     * Constructor for /person/[personID] endpoint request (PersonIDService)
     * Returns single Person object
     * @param personID String ID of desired person
     * @return PersonRequest object
     */
    public PersonRequest PersonRequest(String personID) {
        this.personID = personID;

        return this;
    }

    /**
     * Constructor for /person endpoint request (PersonFamilyService)
     * Returns all family members for current user
     * @return PersonRequest object
     */
    public PersonRequest PersonRequest() { return this; }
}
