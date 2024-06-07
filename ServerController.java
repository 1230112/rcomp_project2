package org.shared.board.app;



import java.nio.charset.StandardCharsets;
import java.util.List;

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
        List<DataAndLength> dataList = data.dataAndLengths();
        DataAndLength dataLength1;
        DataAndLength dataLength2;
        dataLength1 = dataList.get(0);
        dataLength2 = dataList.get(1);
        byte [] dataBytes1;
        byte [] dataBytes2;
        dataBytes1 = dataLength1.getData();
        dataBytes2 = dataLength2.getData();

        String result = new String(dataBytes1, StandardCharsets.US_ASCII);
        String result2 = new String(dataBytes2, StandardCharsets.US_ASCII);
        return sbSvc.authenticateUser(result, result2);
    }


}
