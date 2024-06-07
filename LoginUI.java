package org.shared.board.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * The type Login ui.
 */
public class LoginUI {
    private BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    private ClientController theController;

    /**
     * Instantiates a new Login ui.
     * @param theControllerp the the controllerp
     */
    public LoginUI(final ClientController theControllerp) {
        this.theController = theControllerp;
    }

    /**
     * Ask user Email and Password to authenticate.
     */
    protected void doShow() {
        try {
            System.out.print("Username: ");
            String username = in.readLine();

            System.out.print("Password: ");
            String password = in.readLine();
            System.out.println();

            String data = username + "\0" + password + "\0";

            Message result = theController.authenticate(data);

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
