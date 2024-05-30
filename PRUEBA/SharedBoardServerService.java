package org.shared.board.app;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Shared board server service.
 */
public class SharedBoardServerService {
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
    public SharedBoardServerService() {
        this.synchronizer = Synchronizer.getInstance();
    }

    /**
     * Authenticate user.
     *
     * @param userData the user data
     * @return the int
     * @throws IllegalArgumentException the illegal argument exception
     */
    public int authenticateUser(final String userData)
            throws IllegalArgumentException {
        String email = userData.substring(0, userData.indexOf("\0"));
        String password = userData.substring(
                userData.indexOf("\0") + 1, userData.length() - 1);



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
