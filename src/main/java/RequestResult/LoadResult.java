

package RequestResult;

import Model.EventModel;
import Model.PersonModel;
import Model.UserModel;

import java.util.ArrayList;

/**
 * /load response body
 */
public class LoadResult {
    /**
     * Array of User objects
     */
    private ArrayList<UserModel> users;

    /**
     * Array of Person objects
     */
    private ArrayList<PersonModel> persons;

    /**
     * Array of Event objects
     */
    private ArrayList<EventModel> events;

    /**
     * Reports success or contains descrption of the error
     */
    private String message;

    /**
     * Boolean identifier
     */
    private boolean success;

    /**
     * Constructor for successful /load response body
     * @param users ArrayList containing UserModel objects
     * @param persons ArrayList containing PersonModel objects
     * @param events ArrayList containing EventModel objects
     */
    public LoadResult(ArrayList<UserModel> users, ArrayList<PersonModel> persons, ArrayList<EventModel> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Constructor for an unsuccessful load request response body
     * @param message String containing error message
     * @param success boolean identifier
     */
    public LoadResult(String message, boolean success) {
        this.success = success;
    }
}
