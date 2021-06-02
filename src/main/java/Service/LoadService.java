package Service;

import DAO.DatabaseDAO;
import DAO.EventDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import Model.EventModel;
import Model.PersonModel;
import Model.UserModel;
import RequestResult.LoadRequest;
import RequestResult.LoadResult;
import passoffmodels.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Implements the /load endpoint
 */
public class LoadService {
    /**
     * Clears all data from the database (just like the /clear API),
     * and then loads the posted user, person, and event data into the database.
     * @param request LoadRequest object
     * @return LoadResult object
     */
    public LoadResult load(LoadRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        try {
            databaseDAO.openConnection();
            databaseDAO.clearDatabase();
            UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
            PersonDAO personDAO = new PersonDAO(databaseDAO.getConnection());
            EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());

            ArrayList<UserModel> users = request.getUsers();
            ArrayList<PersonModel> persons = request.getPersons();
            ArrayList<EventModel> events = request.getEvents();

            int numUsers = 0;
            for (UserModel user: users) {
                userDAO.add(user);
                ++numUsers;
            }

            int numPersons = 0;
            for (PersonModel person: persons) {
                personDAO.add(person);
                numPersons++;
            }

            int numEvents = 0;
            for (EventModel event: events) {
                eventDAO.add(event);
                numEvents++;
            }

            String message = "Successfully added " + numUsers + " users, " + numPersons + " persons, and " +
                    numEvents + " events to the database.";

            databaseDAO.closeConnection(true);
            return new LoadResult(message, true);
        }
        catch (SQLException error) {
            databaseDAO.closeConnection(false);
            String message = "Error: " + error.getMessage();
            return new LoadResult(message, false);
        }
    }
}
