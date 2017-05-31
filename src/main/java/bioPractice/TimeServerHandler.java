package bioPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable{
	private Socket socket;
	
	public TimeServerHandler(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {

		BufferedReader in = null;
		PrintWriter out = null;
		
		
		try {
			
			// �����������������������Ҫ�ȴ���ʱ��(BIO�ȴ���ʱ��)Ϊ500ms
			Thread.sleep(500);
			
			in = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String currentTime = null;
			String body = null;
			while(true){
				body = in.readLine();
				if(body==null){
					break;
				}
				System.out.println("The time server receive order: " + body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
						: "DAD ORDER";
				out.println(currentTime);
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}finally{
			if( in != null ){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(out!=null){
				out.close();
			}
			
			if(this.socket!=null){
				try {
					this.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}