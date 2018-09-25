package myself.common1;



import java.util.List;

/**
 * 客户端刷新好友
 */
public class ClientRefreshFriendsMessage extends BaseMessage{

	private List<String> friendsList ;

	public int getMessageType() {
		return CLINT_TO_SERVER_REFRESHFRIENDS;
	}

	public List<String> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<String> friendsList) {
		this.friendsList = friendsList;
	}
	public byte[] popPack() throws Exception {
		return new byte[]{(byte)getMessageType()} ;
	}
}
