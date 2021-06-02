package Model;

import java.util.ArrayList;

public class LoadArrayModel {
    /**
     * Array of UserModel objects
     */
    private ArrayList<UserModel> users;

    /**
     * Array of PersonModel objects
     */
    private ArrayList<PersonModel> persons;

    /**
     * Array of EventModel objects
     */
    private ArrayList<EventModel> events;

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public ArrayList<PersonModel> getPersons() {
        return persons;
    }

    public ArrayList<EventModel> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventModel> events) {
        this.events = events;
    }

    public void setPersons(ArrayList<PersonModel> persons) {
        this.persons = persons;
    }

    public void setUsers(ArrayList<UserModel> users) {
        this.users = users;
    }

    public LoadArrayModel() {

    }
}
