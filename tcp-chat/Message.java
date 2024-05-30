/**
 * The type Message.
 */
public class Message {
    /**
     * The version.
     */
    private int VERSION;
    /**
     * The CODE.
     */
    private int CODE;
    /**
     * The first length.
     */
    private int DATA_LEN_L;
    /**
     * The second length.
     */
    private int DATA_LEN_M;
    /**
     * The DATA1.
     */
    private byte[] DATA;



    public Message(final int versionp, final int CODEp,
                   final int DATA_LEN_Lp, final int DATA_LEN_Mp,
                   final byte[] DATAp) {
        this.VERSION = versionp;
        this.CODE = CODEp;
        this.DATA_LEN_L = DATA_LEN_Lp;
        this.DATA_LEN_M = DATA_LEN_Mp;
        this.DATA = DATAp;

    }

    /**
     * Version int.
     *
     * @return the int
     */
    public int version() {
        return VERSION;
    }

    /**
     * Code int.
     *
     * @return the int
     */
    public int code() {
        return CODE;
    }

    /**
     * D length 1 int.
     *
     * @return the int
     */
    public int data_len_l() {
        return DATA_LEN_L;
    }

    /**
     * D length 2 int.
     *
     * @return the int
     */
    public int data_len_m() {
        return DATA_LEN_M;
    }

    /**
     * Data byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] data() {
        return DATA;
    }

}