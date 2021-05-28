package Model;

public class UserModel {
    /**
     * Unique username of user (non-empty string)
     */
    String username;

    /**
     * User's password (non-empty string)
     */
    String password;

    /**
     * User's email address (non-empty string)
     */
    String email;

    /**
     *First name of user
     */
    String firstName;

    /**
     * Last name of user
     */
    String lastName;

    /**
     * Gender of user
     */
    String gender;

    /**
     * Person ID of user
     */
    String personID;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof UserModel) {
            UserModel oUserModel = (UserModel) o;
            return oUserModel.getUsername().equals(getUsername()) &&
                    oUserModel.getPassword().equals(getPassword()) &&
                    oUserModel.getEmail().equals(getEmail()) &&
                    oUserModel.getFirstName().equals(getFirstName()) &&
                    oUserModel.getLastName().equals(getLastName()) &&
                    oUserModel.getGender().equals(getGender()) &&
                    oUserModel.getPersonID().equals(getPersonID());
        }
        else return false;
    }

    /**
     * Creates a model with user information.
     * @param username unique username.
     * @param password user's password (non-empty string).
     * @param email user's email address (non-empty string).
     * @param firstName user's first name (non-empty string).
     * @param lastName user's last name (non-empty string).
     * @param gender user's gender (string "f" or "m").
     * @param personID unique person ID assigned to this user's generated Person object
     */
    public UserModel(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Constructor for UserModel object with no parameters
     */
    public UserModel() {}
}
