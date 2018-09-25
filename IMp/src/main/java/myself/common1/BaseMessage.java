package myself.common1;

public abstract class BaseMessage {
    public static final int CLINT_TO_SERVER_CHATS = 0;
    public static final int CLINT_TO_SERVER_CHAT = 1;
    public static final int CLINT_TO_SERVER_REFRESHFRIENDS = 2;
    public static final int SERVER_TO_CLINT_CHATS = 3;
    public static final int SERVER_TO_CLINT_CHAT = 4;
    public static final int SERVER_TO_CLINT_FRIENDS = 5;
    public static final int CLINT_TO_SERVER_FILE = 6;
    public static final int SERVER_TO_CLINT_FiLE = 7;
    public static final int CLINT_TO_SERVER_FILES = 8;
    public static final int SERVER_TO_CLINT_FiLES = 9;
    public abstract int getMessageType() ;

    /**
     * 组装报文
     */
    public byte[] popPack() throws Exception{
        return null ;
    }

}
