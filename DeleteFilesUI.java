package org.shared.board.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteFilesUI {
    private BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    private ClientController theController;

    /**
     * Instantiates a new Login ui.
     * @param theControllerp the the controllerp
     */
    public DeleteFilesUI(final ClientController theControllerp) {
        this.theController = theControllerp;
    }

    /**
     * Ask user Email and Password to authenticate.
     */
    protected void doShow() {
        try {
            File directory = new File("./uploadFiles/");
// Get all files in directory
            File[] files = directory.listFiles();
            if (files != null) { // Ensure directory exists and is readable
                for (File file : files) {
                    if (file.isFile()) { // Ignore subdirectories
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Cannot read directory");
            }

            System.out.print("File's Name: ");
            String fileName = in.readLine();
            System.out.println();

// Ensure the directory ends with a file separator
            String directoryPath = directory.getPath();
            if (!directoryPath.endsWith(File.separator)) {
                directoryPath += File.separator;
            }


// Convert byte array to string
            String data = fileName + "\0";

            Message result = theController.delete(data);


            if (result.code() == MessageCodes.ACK) {
                System.out.println("File delete successfully!\n");
            } else {
                String errorData;

                if (result.data().length > 0) {
                    errorData = new String(result.data(),
                            StandardCharsets.US_ASCII);
                } else {
                    errorData = "Invalid credentials!";
                }

                System.out.println(errorData + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
