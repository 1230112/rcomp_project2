package org.shared.board.app;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
    public int upload(final String userData)
            throws IllegalArgumentException {
        String content = userData.substring(0, userData.indexOf("\0"));
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        String name = userData.substring(
                userData.indexOf("\0") + 1, userData.length() - 1);
        System.out.println(content+name);
      /* try{
            FileOutputStream fos = new FileOutputStream(name);
            fos.write(contentBytes);
            fos.close();
            System.out.println("File uploaded successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        */


        return MessageCodes.ACK;



    }



    private static String getStringByIndex(int index, String input) {
        String[] substrings = input.split("\0");

        if (index < 0 || index >= substrings.length) {
            return "";
        }

        return substrings[index];
    }

}
