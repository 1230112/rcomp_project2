package org.shared.board.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
            System.out.print("Email: ");
            String email = in.readLine();

            System.out.print("Password: ");
            String password = in.readLine();
            System.out.println();

            String data1 = email + "\0";
            String data2 = password + "\0";
            List<String> dataList = new ArrayList<>();
            Message result = theController.authenticate(dataList);

            if (result.code() == MessageCodes.ACK) {
                System.out.println("User authenticated successfully!\n");
            } else {
                String errorData;


                    errorData = "Invalid credentials!";


                System.out.println(errorData + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
