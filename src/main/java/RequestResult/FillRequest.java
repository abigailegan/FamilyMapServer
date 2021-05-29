

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

    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return username; }

    public void setGenerations(int generations) { this.generations = generations; }

    public int getGenerations() { return generations; }

    /**
     * Constructor for /fill/[username]/{generations} request body
     * @param username String containing username
     * @param generations int of generations requested
     * @return FillRequest object
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }
}
