package myself.common1;

import java.io.File;

public class ClientServerFile extends BaseMessage {
    //服务器路径
    //private final File file=new File("e:/zhongzhuan");
    //文件路径
    private String path;
    //接收文件名
    private String filename;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    @Override
    public int getMessageType() {
        return CLINT_TO_SERVER_FILE;
    }
    public byte[] popPack() throws Exception{

        return null ;
    }

}
