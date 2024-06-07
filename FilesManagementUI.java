package org.shared.board.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesManagementUI {
    private BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    private ClientController theController;

    /**
     * Instantiates a new Login ui.
     * @param theControllerp the the controllerp
     */
    public FilesManagementUI(final ClientController theControllerp) {
        this.theController = theControllerp;
    }

    /**
     * Ask user Email and Password to authenticate.
     */
    protected void doShow() {
        try {
            System.out.print("Insert File's path: ");
            String filePath = in.readLine();

            System.out.print("File's Name: ");
            String fileName = in.readLine();
            System.out.println();
            // Read all bytes from the file
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

            // Convert byte array to string
            String data = new String(fileContent, StandardCharsets.UTF_8) + "\0" + fileName + "\0";


            Message result = theController.upload(data);



            if (result.code() == MessageCodes.ACK) {
                System.out.println("User authenticated successfully!\n");
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
