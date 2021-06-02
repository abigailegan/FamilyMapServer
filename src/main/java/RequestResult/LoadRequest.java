

package RequestResult;

import Model.EventModel;
import Model.LoadArrayModel;
import Model.PersonModel;
import Model.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * /load request body
 */
public class LoadRequest {
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

    public ArrayList<EventModel> getEvents() {
        return events;
    }

    public ArrayList<PersonModel> getPersons() {
        return persons;
    }

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    /**
     * Constructor for /load request body
     * @return LoadRequest endpoint
     */
    public LoadRequest(String arrays) {
        Gson gson = new Gson();
        LoadArrayModel arrayModel = gson.fromJson(arrays, LoadArrayModel.class);

        users = arrayModel.getUsers();
        persons = arrayModel.getPersons();
        events = arrayModel.getEvents();
    }
}
