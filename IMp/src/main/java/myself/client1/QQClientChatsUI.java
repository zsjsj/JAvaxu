package myself.client1;




import myself.common1.ClientChatsMessage;
import myself.common1.ClientServerFile;
import myself.util1.QQUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 客户端群聊界面
 */
public class QQClientChatsUI extends JFrame implements ActionListener {
	//所有私聊窗口
	public Map<String,QQClientChatSingleUI> allSingleChart = new HashMap<String,QQClientChatSingleUI>() ;

	//通信线程
	public QQClientCommThread commThread ;

	//历史聊天区/消息输入区
	private JTextArea taHistory,taInputMessage;

	private MenuItem openItem, saveItem, closeItem;// 定义条目“退出”和“子条目”菜单项
	private FileDialog openDia, saveDia;// 定义“打开、保存”对话框

	//好友列表
	private JList<String> lstFriends;


	private TextArea ta;

	//发送按钮
	private JButton btnSend;
	private MenuBar bar;
	//刷新好友列表按钮
	private JButton btnRefresh;

	private Menu fileMenu;// 定义"文件"和"子菜单"菜单

	public QQClientChatsUI() {
		init();
		this.setVisible(true);
	}

	/**
	 * 初始化布局
	 */
	private void init() {
		this.setTitle("QQClient");
		this.setBounds(100, 100, 800, 600);
		this.setLayout(null);

		 bar = new MenuBar();// 创建菜单栏
		ta = new TextArea();// 创建文本域

		fileMenu = new Menu("wenjin");// 创建“文件”菜单

		openItem = new MenuItem("open");// 创建“打开"菜单项
		saveItem = new MenuItem("save");// 创建“保存"菜单项
		closeItem = new MenuItem("exit");// 创建“退出"菜单项

		fileMenu.add(openItem);// 将“打开”菜单项添加到“文件”菜单上
		fileMenu.add(saveItem);// 将“保存”菜单项添加到“文件”菜单上
		fileMenu.add(closeItem);// 将“退出”菜单项添加到“文件”菜单上

		bar.add(fileMenu);// 将文件添加到菜单栏上
		this.setMenuBar(bar);

		//历史区
		taHistory = new JTextArea();
		taHistory.setBounds(0, 0, 600, 400);

		JScrollPane sp1 = new JScrollPane(taHistory);
		sp1.setBounds(0, 0, 600, 400);
		this.add(sp1);

		//listFriends
		lstFriends = new JList<String>();
		lstFriends.setBounds(620, 0, 160, 400);
		lstFriends.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//双击事件打开私聊窗口
				if(e.getClickCount() == 2){
					String recvAddr = lstFriends.getSelectedValue();
					QQClientChatSingleUI singleUI = allSingleChart.get(recvAddr) ;

					if(singleUI == null){
						singleUI = new QQClientChatSingleUI(recvAddr,commThread) ;
						allSingleChart.put(recvAddr , singleUI) ;
					}
					singleUI.setVisible(true);
				}
			}
		});
		this.add(lstFriends);

		//taInputMessage消息输入框
		taInputMessage = new JTextArea();
		taInputMessage.setBounds(0, 420, 540, 160);
		this.add(taInputMessage);

		//btnSend发送按钮
		btnSend = new JButton("发送");
		btnSend.setBounds(560, 420, 100, 160);
		btnSend.addActionListener(this);
		this.add(btnSend);

		//btnRefresh
		btnRefresh = new JButton("刷新");
		btnRefresh.setBounds(680, 420, 100, 160);
		btnRefresh.addActionListener(this);
		this.add(btnRefresh);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
		myEvent();// 加载事件处理
		openDia = new FileDialog(this, "open", FileDialog.LOAD);
		saveDia = new FileDialog(this, "save", FileDialog.SAVE);

	}

	/**
	 * 按钮的点击事件
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		//发送按钮
		if(source == btnSend){
			String txt = taInputMessage.getText();
			if(txt != null && !txt.trim().equals("")){
				ClientChatsMessage msg = new ClientChatsMessage();
				msg.setMessage(txt);

				taInputMessage.setText("");
				try {
					commThread.sendMessage(msg);
					System.out.println("发送成功!");
				} catch (Exception e1) {
					System.out.println("发送失败! " + e1.getMessage());
				}
			}
		}
	}

	/**
	 * 刷新好友列表
	 */
	public void refreshFriendList(List<String> list) {
		String localAddr = QQUtil.getLocalAddr(commThread.s) ;
	    list.remove(localAddr);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String s : list) {
			listModel.addElement(s);
		}
		lstFriends.setModel(listModel);
	}

	/**
	 * 更新历史区域内容
	 */
	public void updateHistory(String who ,String msg) {
		taHistory.append("[" + who + "]说:\r\n");
		String formatStr = msg.replace("\n", "\n\t");
		formatStr = "\t" + formatStr + "\r\n";
		taHistory.append(formatStr);
	}
	//菜单监听事件
	private void myEvent() {

		// 打开菜单项监听
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openDia.setVisible(true);//显示打开文件对话框
				String dirpath = openDia.getDirectory();//获取打开文件路径并保存到字符串中。
				String fileName = openDia.getFile();//获取打开文件名称并保存到字符串中
				if (dirpath == null || fileName == null)//判断路径和文件是否为空
					return;
				File file = new File(dirpath, fileName);//创建新的路径和名称
				ClientServerFile mes = new ClientServerFile();
				String path = file.getAbsolutePath();
				mes.setFilename(path);
				try {
					commThread.sendFile(mes);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
	}
	public static void main(String[] a){
		new QQClientChatsUI();
	}
	}
