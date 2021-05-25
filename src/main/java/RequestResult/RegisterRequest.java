

package RequestResult;

import Model.UserModel;

/**
 * /user/register request body
 */
public class RegisterRequest {
    /**
     * Non-empty, unique identifier for a user
     */
    private String username;

    /**
     * Non-empty string containing user's password
     */
    private String password;

    /**
     * Non-empty string containing user's email
     */
    private String email;

    /**
     * User's first name
     */
    private String firstName;

    /**
     * User's last name
     */
    private String lastName;

    /**
     * User's gender ("m" or "f")
     */
    private String gender;

    /**
     * Constructor for /user/register response body (RegisterService)
     * @param userModel UserModel object
     * @return RegisterRequest object
     */
    public RegisterRequest RegisterRequest(UserModel userModel) {
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.gender = userModel.getGender();

        return this;
    }
}
