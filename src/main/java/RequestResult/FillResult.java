

package RequestResult;

/**
 * /fill/[username]/{generations} response body
 */
public class FillResult {
    /**
     * Message containing success or description of the error
     */
    private String message;

    /**
     * Boolean identifier
     */
    private boolean success;

    /**
     * Constructor for /fill/[username]/{generations} response body
     * @param message String containing message
     * @param success boolean identifier
     */
    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
