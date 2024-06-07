package org.shared.board.app;

/**
 * The type Message codes.
 */
public final class MessageCodes {
    /**
     * The constant COMMTEST.
     */
    public static final int COMMTEST = 0;
    /**
     * The constant DISCONN.
     */
    public static final int DISCONN = 1;
    /**
     * The constant ACK.
     */
    public static final int ACK = 2;
    /**
     * The constant ERR.
     */
    public static final int ERR = 3;
    /**
     * The constant AUTH.
     */
    public static final int AUTH = 4;

    /**
     * The constant CB.
     */
    public static final int UPLOAD_FILE = 5;

    public static final int DOWNLOAD_FILE = 6;
    public static final int DELETE_FILE = 7;
    private MessageCodes() {
        // Private constructor to prevent instantiation
    }
}
