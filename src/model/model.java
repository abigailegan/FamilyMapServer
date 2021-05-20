/**
 * Model class
 * This class is used to create models that will contain data for users, persons, events, etc.
 *
 */

public class Model {
    /**
     * Unique username
     */
    String username;

    /**
     * User's password
     */
    String password;

    /**
     * User's email address
     */
    String email;

    /**
     *First name of user or person
     */
    String first_name;

    /**
     * Last name of user or person
     */
    String last_name;

    /**
     * Gender of user or person
     */
    String gender;

    /**
     * Person ID of user or person, unique identifier
     */
    String person_id;

    /**
     * Person ID of person's father
     */
    String father_id;

    /**
     * Person ID of person's mother
     */
    String mother_id;

    /**
     * Person ID of person's spouse
     */
    String spouse_id;

    /**
     * Unique identifier for an event
     */
    String event_id;

    /**
     * Latitude of event
     */
    float latitude;

    /**
     * Longitude of event
     */
    float longitude;

    /**
     * Country in which event occurred
     */
    String country;

    /**
     * City in which event occurred
     */
    String city;

    /**
     * Type of event (birth, baptism, marriage, death, etc)
     */
    String event_type;

    /**
     * Year in which event occurred
     */
    int year;

    /**
     * Authorization token for login request
     */
    String auth_token;

    /**
     * Creates a model with user information.
     * @param username unique username.
     * @param password user's password (non-empty string).
     * @param email user's email address (non-empty string).
     * @param first_name user's first name (non-empty string).
     * @param last_name user's last name (non-empty string).
     * @param gender user's gender (string "f" or "m").
     * @param person_id unique person ID assigned to this user's generated Person object
     */
    public Model(String username, String password, String email, String first_name, String last_name, String gender, String person_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.person_id = person_id; //TODO: the constructor won't be able to know the person_id, should this still be included????
    }

    /**
     * Creates a model with person information
     * @param person_id unique identifier for this person(non-empty string).
     * @param username associated username for user to which this person belongs
     * @param first_name person's first name (non-empty string).
     * @param last_name person's last name (non-empty string).
     * @param gender person's gender (string "f" or "m").
     * @param father_id person ID of person's father
     * @param mother_id person ID of person's mother
     * @param spouse_id person ID of person's spouse
     *
     */
    public Model(String person_id, String username, String first_name, String last_name, String gender, String father_id, String mother_id, String spouse_id) {
        this.person_id = person_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.father_id = father_id;
        this.mother_id = mother_id;
        this.spouse_id = spouse_id;
    }

    /**
     * Creates a model with event information
     * @param event_id unique identifier for this event (non-empty string)/.
     * @param username username for user to which this event belongs
     * @param person_id person ID for person to which this even belongs.
     * @param latitude latitude of event's location
     * @param longitude longitude of event's location
     * @param country country in which event occurred
     * @param city city in which event occurred
     * @param event_type type of event
     * @param year year in which event occurred
     */
    public Model(String event_id, String username, String person_id, float latitude, float longitude, String country, String city, String event_type, int year) {
        this.event_id = event_id;
        this.username = username;
        this.person_id = person_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.event_type = event_type;
        this.year = year;
    }

    /**
     * Creates a model with authorization token information
     * @param username username for user to which this token belongs
     * @param auth_token authorization token returned by successful login request
     */
    public Model(String username, String auth_token) {
        this.username = username;
        this.auth_token = auth_token;
    }
}