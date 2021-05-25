

package RequestResult;

/**
 * /clear response body
 */
public class ClearResult {
    /**
     * Message reporting success or description of the error
     */
    private String message;

    /**
     * Boolean identifier
     */
    private boolean success;

    /**
     * Constructor for /clear response body
     * @param message String reporting success or description of the error
     * @param success boolean identifier
     */
    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
