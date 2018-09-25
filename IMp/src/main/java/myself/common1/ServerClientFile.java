package myself.common1;

public class ServerClientFile extends BaseMessage {
    private final String DIR = "e:/fuwuqi";
    private String path;
    private String recAddr;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRecAddr() {
        return recAddr;
    }

    public void setRecAddr(String recAddr) {
        this.recAddr = recAddr;
    }

    public String getPath() {
        return path;
    }

    public void setPath() {
        this.path = DIR;
    }

    @Override
    public int getMessageType() {
        return SERVER_TO_CLINT_FiLE;
    }
}
