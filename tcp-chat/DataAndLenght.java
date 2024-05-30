public class DataAndLenght {
    private byte[] DATA;

    /**
     * The first DATA_LEN_L.
     */
    private int DATA_LEN_L;
    /**
     * The second DATA_LEN_L.
     */
    private int DATA_LEN_M;
    public DataAndLenght(byte[] DATA, int DATA_LEN_L, int DATA_LEN_M) {
        this.DATA = DATA;
        this.DATA_LEN_L = DATA_LEN_L;
        this.DATA_LEN_M = DATA_LEN_M;
    }

    public byte[] getData() {
        return DATA;
    }

    public int getLength() {
        return DATA_LEN_L;
    }
    public int getLength2() {
        return DATA_LEN_M;
    }
}
