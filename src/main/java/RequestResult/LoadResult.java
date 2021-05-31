

package RequestResult;

/**
 * /load response body
 */
public class LoadResult {

    /**
     * Reports success or contains descrption of the error
     */
    private String message;

    /**
     * Boolean identifier
     */
    private boolean success;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * Constructor for a load request response body
     * @param message String containing error message
     * @param success boolean identifier
     */
    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
