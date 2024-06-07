package org.shared.board.app;

import java.net.Socket;

/**
 * The type Shared board app controller.
 */
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class ClientController {

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
    public ClientController(Socket sock) {
        this.sock = sock;
        this.mf = new MessageFormat(sock);
    }

    /**
     * Send communication test.
     * @return the int
     */
    public int sendCommunicationTest(){
        mf.sendMessage(VERSION, MessageCodes.COMMTEST, Collections.singletonList(""));

        Message result = mf.readMessage();

        return result.code();
    }

    /**
     * Send end of session.
     * @return the int
     */
    public int sendEndOfSession(){
        mf.sendMessage(VERSION, MessageCodes.DISCONN, Collections.singletonList(""));

        Message result = mf.readMessage();

        return result.code();
    }


    public Message authenticate(List<String> datas){
        mf.sendMessage(VERSION, MessageCodes.AUTH, datas);

        return mf.readMessage();
    }
}