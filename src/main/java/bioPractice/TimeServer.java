package bioPractice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author HDY
 *
 */
public class TimeServer {
	public static void main(String[] args) {
		int port = 21980;
		ServerSocket server = null;
		
		try {
			// new a server
			server = new ServerSocket(port);
			System.out.println("The time server start at: " + port);
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 1000);
			
			while(true){
				Socket socket = null;
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
				System.out.println("The current threads in pool: " + singleExecutor.getThreadNums() );
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(server!=null){
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
