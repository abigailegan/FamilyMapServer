package Service;

import DAO.DatabaseDAO;
import DAO.EventDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import Model.EventModel;
import Model.LocationModel;
import Model.PersonModel;
import Model.UserModel;
import RequestResult.FillRequest;
import RequestResult.FillResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

/**
 * Implements the /fill/[username]/{generations} endpoint
 */
public class FillService {
    ArrayList<LocationModel> locations = new ArrayList<LocationModel>();
    ArrayList<String> fnames = new ArrayList<String>();
    ArrayList<String> mnames = new ArrayList<String>();
    ArrayList<String> surnames = new ArrayList<String>();

    private void fillLists() throws FileNotFoundException{
        Reader locationsReader;
        Reader fnamesReader;
        Reader mnamesReader;
        Reader surnamesReader;
        try {
            locationsReader = new FileReader("json/locations.json");
            fnamesReader = new FileReader("json/fnames.json");
            mnamesReader = new FileReader("json/mnames.json");
            surnamesReader = new FileReader("json/snames.json");
        }
        catch (FileNotFoundException error) {
            throw new FileNotFoundException(error.getMessage());
        }

        Gson gson = new Gson();
        locations = gson.fromJson(locationsReader, new TypeToken<ArrayList<LocationModel>>(){}.getType());
        fnames = gson.fromJson(fnamesReader, new TypeToken<ArrayList<String>>(){}.getType());
        mnames = gson.fromJson(mnamesReader, new TypeToken<ArrayList<String>>(){}.getType());
        surnames = gson.fromJson(surnamesReader, new TypeToken<ArrayList<String>>(){}.getType());
    }

    private void generateGenerations(PersonModel child, int generations, EventModel personBirthEvent) throws SQLException {
        Collections.shuffle(fnames);
        Collections.shuffle(mnames);
        Collections.shuffle(surnames);

        DatabaseDAO databaseDAO = new DatabaseDAO();
        PersonDAO personDAO = new PersonDAO(databaseDAO.getConnection());

        //Set parent IDs
        if (child.getMotherID() == null) child.setMotherID(UUID.randomUUID().toString());
        if (child.getFatherID() == null) child.setFatherID(UUID.randomUUID().toString());

        //Make mother
        String motherFirstName = fnames.get(0);
        String motherLastName = surnames.get(0);
        String mgrandmotherID;
        String mgrandfatherID;
        if (generations > 1) {
            mgrandmotherID = UUID.randomUUID().toString();
            mgrandfatherID = UUID.randomUUID().toString();
        }
        else {
            mgrandmotherID = null;
            mgrandfatherID = null;
        }
        PersonModel mother = new PersonModel(child.getMotherID(), child.getUsername(), motherFirstName, motherLastName,
                "f", mgrandfatherID, mgrandmotherID, child.getFatherID());
        personDAO.add(mother);

        //Make father
        String fatherFirstName = mnames.get(0);
        String fatherLastName = surnames.get(0);
        String fgrandmotherID;
        String fgrandfatherID;
        if (generations > 1) {
            fgrandmotherID = UUID.randomUUID().toString();
            fgrandfatherID = UUID.randomUUID().toString();
        }
        else {
            fgrandmotherID = null;
            fgrandfatherID = null;
        }
        PersonModel father = new PersonModel(child.getFatherID(), child.getUsername(), fatherFirstName, fatherLastName,
                "m", fgrandfatherID, fgrandmotherID, child.getMotherID());
        personDAO.add(father);

        Random random = new Random();
        EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());

        //Generate mother birth event data
        String motherBirthEventID = UUID.randomUUID().toString();
        float motherBirthLatitude = locations.get(0).getLatitude();
        float motherBirthLongitude = locations.get(0).getLongitude();
        String motherBirthCountry = locations.get(0).getCountry();
        String motherBirthCity = locations.get(0).getCity();
        String motherBirthEventType = "birth";
        int motherBirthYear = personBirthEvent.getYear() - (random.nextInt(50 - 15) + 15);
        EventModel motherBirthEvent = new EventModel(motherBirthEventID, child.getUsername(), mother.getPersonID(), motherBirthLatitude, motherBirthLongitude,
                motherBirthCountry, motherBirthCity, motherBirthEventType, motherBirthYear);
        eventDAO.add(motherBirthEvent);

        //Generate mother death event data
        String motherDeathEventID = UUID.randomUUID().toString();
        float motherDeathLatitude = locations.get(1).getLatitude();
        float motherDeathLongitude = locations.get(1).getLongitude();
        String motherDeathCountry = locations.get(1).getCountry();
        String motherDeathCity = locations.get(1).getCity();
        int motherDeathYear = personBirthEvent.getYear() + (random.nextInt(60 - 1) + 1);
        EventModel motherDeathEvent = new EventModel(motherDeathEventID, child.getUsername(), mother.getPersonID(),
                motherDeathLatitude, motherDeathLongitude, motherDeathCountry, motherDeathCity, "death", motherDeathYear);
        eventDAO.add(motherDeathEvent);

        //Generate mother marriage event
        String motherMarriageEventID = UUID.randomUUID().toString();
        float motherMarriageLatitude = locations.get(2).getLatitude();
        float motherMarriageLongitude = locations.get(2).getLongitude();
        String motherMarriageCountry = locations.get(2).getCountry();
        String motherMarriageCity = locations.get(2).getCity();
        int motherMarriageYear = personBirthEvent.getYear() - 1;
        EventModel motherMarriageEvent = new EventModel(motherMarriageEventID, child.getUsername(), mother.getPersonID(),
                motherMarriageLatitude, motherMarriageLongitude, motherMarriageCountry, motherMarriageCity, "marriage", motherMarriageYear);
        eventDAO.add(motherMarriageEvent);

        //Generate father birth event
        String fatherBirthEventID = UUID.randomUUID().toString();
        float fatherBirthLatitude = locations.get(3).getLatitude();
        float fatherBirthLongitude = locations.get(3).getLongitude();
        String fatherBirthCountry = locations.get(3).getCountry();
        String fatherBirthCity = locations.get(3).getCity();
        int fatherBirthYear = personBirthEvent.getYear() - (random.nextInt(50 - 15) + 15);
        EventModel fatherBirthEvent = new EventModel(fatherBirthEventID, child.getUsername(), father.getPersonID(),
                fatherBirthLatitude, fatherBirthLongitude, fatherBirthCountry, fatherBirthCity, "birth", fatherBirthYear);
        eventDAO.add(fatherBirthEvent);

        //Generate father death event
        String fatherDeathEventID = UUID.randomUUID().toString();
        float fatherDeathLatitude = locations.get(4).getLatitude();
        float fatherDeathLongitude = locations.get(4).getLongitude();
        String fatherDeathCountry = locations.get(4).getCountry();
        String fatherDeathCity = locations.get(4).getCity();
        int fatherDeathYear = personBirthEvent.getYear() + (random.nextInt(60 - 1) + 1);
        EventModel fatherDeathEvent = new EventModel(fatherDeathEventID, child.getUsername(), father.getPersonID(),
                fatherDeathLatitude, fatherDeathLongitude, fatherDeathCountry, fatherDeathCity, "death", fatherDeathYear);
        eventDAO.add(fatherDeathEvent);

        //Generate father marriage event
        EventModel fatherMarriageEvent = new EventModel(UUID.randomUUID().toString(), child.getUsername(), father.getPersonID(),
                motherMarriageLatitude, motherMarriageLongitude, motherMarriageCountry, motherMarriageCity, "marriage", motherMarriageYear);
        eventDAO.add(fatherMarriageEvent);

        databaseDAO.closeConnection(true);

        generations--;
        if (generations > 0) {
            generateGenerations(mother, generations, motherBirthEvent);
            generateGenerations(father, generations, fatherBirthEvent);
        }
        /*if (generations == 0) {
            mother.setFatherID(null);
            mother.setMotherID(null);
            father.setFatherID(null);
            father.setMotherID(null);
        }*/
    }

    /**
     * Populates the server's database with generated data for the specified user name.
     * @param request FillRequest object
     * @return FillResult object
     */
    public FillResult fill(FillRequest request) throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();

        try {
            String username = request.getUsername();
            int generations = request.getGenerations();

            UserDAO userDAO = new UserDAO(databaseDAO.getConnection());
            PersonDAO personDAO = new PersonDAO(databaseDAO.getConnection());
            EventDAO eventDAO = new EventDAO(databaseDAO.getConnection());

            UserModel user = new UserModel();
            try {
                user = userDAO.findByUsername(username);
            }
            catch (SQLException error) {
                databaseDAO.closeConnection(true);
                throw new SQLException("Error: Username does not belong to a registered user.");
            }

            personDAO.deleteByUsername(username);
            eventDAO.deleteByUsername(username);

            String personID = user.getPersonID();
            String fatherID = UUID.randomUUID().toString();
            String motherID = UUID.randomUUID().toString();
            String spouseID = UUID.randomUUID().toString();
            PersonModel personModel = new PersonModel(personID, user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(), fatherID, motherID, spouseID);
            personDAO.add(personModel);

            try {
                fillLists();
            }
            catch (FileNotFoundException error) {
                databaseDAO.closeConnection(true);
                throw new FileNotFoundException(error.getMessage());
            }
            String birthEventID = UUID.randomUUID().toString();
            Collections.shuffle(locations);
            float birthLatitude = locations.get(0).getLatitude();
            float birthLongitude = locations.get(0).getLongitude();
            String birthCountry = locations.get(0).getCountry();
            String birthCity = locations.get(0).getCity();
            String birthEventType = "birth";
            int birthYear = 1990;
            EventModel birthEvent = new EventModel(birthEventID, username, personID, birthLatitude, birthLongitude, birthCountry, birthCity, birthEventType, birthYear);
            eventDAO.add(birthEvent);

            databaseDAO.closeConnection(true);

            generateGenerations(personModel, generations, birthEvent);

            int numPersons = (int) (Math.pow(2, (generations + 1)) - 1);
            int numEvents = (numPersons - 1) * 3 + 1;

            String message = "Successfully added " + numPersons + " persons and " + numEvents + " events.";
            return new FillResult(message, true);
        }
        catch (SQLException | FileNotFoundException error) {
            databaseDAO.closeConnection(false);
            String message = error.getMessage();
            FillResult result = new FillResult(message, false);
        }
        return null;
    }
}
