import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LoginUI {
    private BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    private ClientMessagesController theController;

    /**
     * Instantiates a new Login ui.
     * @param theControllerp the the controllerp
     */
    public LoginUI(final ClientMessagesController theControllerp) {
        this.theController = theControllerp;
    }

    /**
     * Ask user Email and Password to authenticate.
     */
    protected void doShow() {
        try {
            System.out.print("Email: ");
            String username = in.readLine();

            System.out.print("Password: ");
            String password = in.readLine();
            System.out.println();

            String data1 = username + "\0";
            String data2 = password + "\0";

            Message result = theController.authenticate(data1, data2);

            if (result.code() == MessageCodes.ACK) {
                System.out.println("User authenticated successfully!\n");
            } else {
                String errorData;

                if (result.dataAndLenghts().length > 0) {
                    errorData = new String(result.dataAndLenghts(),
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
