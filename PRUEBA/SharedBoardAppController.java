package org.shared.board.app;

import org.shared.board.app.Message;
import org.shared.board.app.MessageCodes;
import org.shared.board.app.MessageFormat;

import java.net.Socket;

/**
 * The type Shared board app controller.
 */
public class SharedBoardAppController {
    private Socket sock;
    private MessageFormat mf;

    /**
     * The constant VERSION.
     */
    public static final int VERSION = 1;

    /**
     * Instantiates a new Shared board app controller.
     * @param sock the sock
     */
    public SharedBoardAppController(Socket sock) {
        this.sock = sock;
        this.mf = new MessageFormat(sock);
    }

    /**
     * Send communication test.
     * @return the int
     */
    public int sendCommunicationTest(){
        mf.sendMessage(VERSION, MessageCodes.COMMTEST, "");

        Message result = mf.readMessage();

        return result.code();
    }

    /**
     * Send end of session.
     * @return the int
     */
    public int sendEndOfSession(){
        mf.sendMessage(VERSION, MessageCodes.DISCONN, "");

        Message result = mf.readMessage();

        return result.code();
    }

    /**
     * Authenticate.
     * @param data the data
     * @return the message
     */
    public Message authenticate(String data){
        mf.sendMessage(VERSION, MessageCodes.AUTH, data);

        return mf.readMessage();
    }

    /**
     * Create board.
     * @param data the data
     * @return the message
     */

}
