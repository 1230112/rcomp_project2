package org.shared.board.app;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MessageFormat {
    /**
     * The Sock.
     */
    private Socket sock;
    /**
     * The S out.
     */
    private DataOutputStream sOut;

    /**
     * The S in.
     */
    private DataInputStream sIn;

    /**
     * 1 byte in decimal.
     */
    private static final int BYTE = 256;

    /**
     * Instantiates a new Message format.
     *
     * @param sockp the sockp
     */
    public MessageFormat(final Socket sockp) {
        try {
            this.sock = sockp;
            this.sOut = new DataOutputStream(sockp.getOutputStream());
            this.sIn = new DataInputStream(sock.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage(final int version,
                            final int code,
                            final List<String> texts) {
        try {
            sOut.writeByte(version);
            sOut.writeByte(code);
            byte[] data;
            int dataLength;

            int data_len_l;
            int data_len_m;
            for (String text : texts) {
                data = text.getBytes();
                dataLength = data.length;

                data_len_l = dataLength % BYTE;
                data_len_m = dataLength / BYTE;

                sOut.writeByte(data_len_l);
                sOut.writeByte(data_len_m);
                sOut.write(data, 0, dataLength);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Read message message.
     *
     * @return the message
     */
    public void sendMessage(final int version,
                            final int code,
                            final String text) {
        byte[] data = text.getBytes();
        int dataLength = data.length;

        int d_length_1 = dataLength % BYTE;
        int d_length_2 = dataLength / BYTE;

        try {
            sOut.writeByte(version);
            sOut.writeByte(code);
            sOut.writeByte(d_length_1);
            sOut.writeByte(d_length_2);
            sOut.write(data, 0, dataLength);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Message readMessage() {
        try {
            int version = sIn.readUnsignedByte();
            int code = sIn.readUnsignedByte();
            int d_length_1;
            int d_length_2;

            // calculate data length
            int dataLength;

            byte[] data;
            List<DataAndLength> dataList = new ArrayList<>();
            while (sIn.available() > 0) {
                d_length_1 = sIn.readUnsignedByte();
                d_length_2 = sIn.readUnsignedByte();
                dataLength = d_length_1 + (d_length_2 * BYTE);
                data = new byte[dataLength];
                if (dataLength > 0) {
                    sIn.readFully(data);
                }
                dataList.add(new DataAndLength(data, d_length_1, d_length_2));
                sleep(100);
            }


            return new Message(version, code, dataList);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);

    }

}
  }