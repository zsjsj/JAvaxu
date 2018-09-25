package myself.common1;

import java.io.ByteArrayOutputStream;

public class ClientFileMessage extends BaseMessage {
    private byte[] message;
    private String filename;
    @Override
    public int getMessageType() {
        return CLINT_TO_SERVER_FILE;
    }
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //写入数据类型
        baos.write(getMessageType());
        //写入数据
        baos.write(message);
        return baos.toByteArray();
    }
}
