import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClientMessagesController {

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
        public ClientMessagesController(Socket sock) {
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

        /**
         * Authenticate.
         * @param data the data
         * @return the message
         */
        public Message authenticate(String data1, String data2){
            List<String> dataList = Arrays.asList(data1, data2);
            mf.sendMessage(VERSION, MessageCodes.AUTH, dataList);

            return mf.readMessage();
        }
}
