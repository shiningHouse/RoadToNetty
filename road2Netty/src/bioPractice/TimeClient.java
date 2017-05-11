package bioPractice;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {

	public static void main(String[] args) {
		TimeServerHandlerExecutePool singleExcutor = new TimeServerHandlerExecutePool(10, 200);
		
		
		// ks
		Socket socket = null;
		
		
		
		for(int i=0; i<100; i++){
			System.out.println(i);
			
			try {
				socket = new Socket("localhost",21980);
				singleExcutor.execute(new TimeClientHandler(socket));
				//new Thread(new TimeClientHandler(socket)).start();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
