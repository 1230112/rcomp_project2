package org.shared.board.app;


import java.io.File;
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
    public int upload(final String userData) throws IllegalArgumentException {
        String content = userData.substring(0, userData.indexOf("\0"));
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        String name = userData.substring(userData.indexOf("\0") + 1, userData.length() - 1);
        System.out.println(content+name);

        String directory = "./uploadFiles/";
        // Ensure the directory ends with a file separator
        if (!directory.endsWith(File.separator)) {
            directory += File.separator;
        }

        if (new File(directory).canWrite()) {
            System.out.println("You have write permission!");
        } else {
            System.out.println("You do not have write permission!");
        }

        try {
            // Create a FileOutputStream to write the file
            FileOutputStream fos = new FileOutputStream(directory + name);
            fos.write(contentBytes);
            fos.close();
            System.out.println("File uploaded successfully to " + directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return MessageCodes.ACK;
    }
    public int download(final String userData) throws IllegalArgumentException {
        String content = userData.substring(0, userData.indexOf("\0"));
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        String name = userData.substring(userData.indexOf("\0") + 1, userData.length() - 1);
        System.out.println(content+name);

        String directory = "./downloadFiles/";
        // Ensure the directory ends with a file separator
        if (!directory.endsWith(File.separator)) {
            directory += File.separator;
        }

        if (new File(directory).canWrite()) {
            System.out.println("You have write permission!");
        } else {
            System.out.println("You do not have write permission!");
        }

        try {
            // Create a FileOutputStream to write the file
            FileOutputStream fos = new FileOutputStream(directory + name);
            fos.write(contentBytes);
            fos.close();
            System.out.println("File download successfully to " + directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return MessageCodes.ACK;
    }
    public int delete(final String userData) throws IllegalArgumentException {
        int separatorIndex = userData.indexOf("\0");
        if (separatorIndex == -1) {
            System.out.println("Invalid user data format!");
            return MessageCodes.ERR;
        }

        String name = userData.substring(0, separatorIndex);
        System.out.println("Deleting file: " + name);

        String directory = "./uploadFiles/";
        // Ensure the directory ends with a file separator
        if (!directory.endsWith(File.separator)) {
            directory += File.separator;
        }

        File fileToDelete = new File(directory + name);
        if (fileToDelete.exists() && fileToDelete.canWrite()) {
            if (fileToDelete.delete()) {
                System.out.println("File deleted successfully from " + directory);
                return MessageCodes.ACK;
            } else {
                System.out.println("Failed to delete file from " + directory);
                return MessageCodes.ERR;
            }
        } else {
            System.out.println("File does not exist or you do not have write permission!");
            return MessageCodes.ERR;
        }
    }


    private static String getStringByIndex(int index, String input) {
        String[] substrings = input.split("\0");

        if (index < 0 || index >= substrings.length) {
            return "";
        }

        return substrings[index];
    }

}
