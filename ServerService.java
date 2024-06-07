package org.shared.board.app;


/**
 * The type Shared board server service.
 */
public class ServerService {
    /**
     * Get AuthenticationService.
     */


    /**
     * The Synchronizer.
     */
    Synchronizer synchronizer;

    /**
     * The constant MIN_ROWS_COLUMNS.
     */
    private static final String MIN_ROWS_COLS = "1";

    /**
     * Instantiates a new SharedBoardServerService.
     */
    public ServerService() {
        this.synchronizer = Synchronizer.getInstance();
    }

    /**
     * Authenticate user.
     *
     * @param userData the user data
     * @return the int
     * @throws IllegalArgumentException the illegal argument exception
     */
    public int authenticateUser(final String userData, final String password1)
            throws IllegalArgumentException {
        String email = userData;
        String password = password1;



        if (email.equals("admin") && password.equals("admin")) {
            return MessageCodes.ACK;
        }

        return MessageCodes.ERR;
    }



    private static String getStringByIndex(int index, String input) {
        String[] substrings = input.split("\0");

        if (index < 0 || index >= substrings.length) {
            return "";
        }

        return substrings[index];
    }

}
