import java.util.List;

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
    private List<DataAndLenght> dataList;

    public Message(final int versionp, final int CODEp,
                   final List<DataAndLenght> dataListp) {
        this.VERSION = versionp;
        this.CODE = CODEp;
        this.dataList = dataListp;
    }



    public List<DataAndLenght> dataAndLenghts() {
        return dataList;
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



}