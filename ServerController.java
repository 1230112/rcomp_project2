package org.shared.board.app;



import java.nio.charset.StandardCharsets;

/**
 * The type Shared board server controller.
 */
public class ServerController {
    /**
     * SharedBoardServerService.
     */
    private ServerService sbSvc;

    /**
     * AuthorizationService.
     */


    /**
     * Instantiates a new Shared board server controller.
     *
     * @param sbSvcp the sb svcp

     */
    public ServerController(final ServerService sbSvcp) {
        this.sbSvc = sbSvcp;
    }

    /**
     * Authenticate.
     *
     * @param data the data
     * @return the int
     */
    public int authenticate(final Message data) {
        String result = new String(data.data(), StandardCharsets.US_ASCII);

        return sbSvc.authenticateUser(result);
    }
    public int upload(final Message data) {
        String result = new String(data.data(), StandardCharsets.US_ASCII);

        return sbSvc.upload(result);
    }
    public int download(final Message data) {
        String result = new String(data.data(), StandardCharsets.US_ASCII);

        return sbSvc.download(result);
    }


}
