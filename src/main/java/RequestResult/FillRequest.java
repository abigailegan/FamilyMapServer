

package RequestResult;

/**
 * /fill/[username]/{generations} request body
 */
public class FillRequest {
    /**
     * username
     */
    private String username;

    /**
     * number of generations requested (non-negative int)
     */
    private int generations;

    /**
     * Constructor for /fill/[username]/{generations} request body
     * @param username String containing username
     * @param generations int of generations requested
     * @return FillRequest object
     */
    public FillRequest FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;

        return this;
    }
}
