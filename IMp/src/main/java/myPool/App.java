package myPool;



import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class App {
	public static void main(String[] args) {
		final MyDatasource ds = new MyDatasource();
		new Thread(){
			public void run() {
				try {
					Connection conn = ds.getConnection();
					conn.close();
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread(){
			public void run() {
				try {
					Connection conn = ds.getConnection();
					conn.close();
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread(){
			public void run() {
				try {
					Connection conn = ds.getConnection();
					conn.close();
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread(){
			public void run() {
				try {
					Connection conn = ds.getConnection();
					conn.close();
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
