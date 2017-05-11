package bioPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClientHandler implements Runnable{

	private Socket socket;
	public TimeClientHandler(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader( new InputStreamReader(this.socket.getInputStream()));
			
			out = new PrintWriter(this.socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			
			System.out.println(Thread.currentThread().getName() + " Send order to server succeed.");
			
			String resp = in.readLine();
			System.out.println(Thread.currentThread().getName() + " Now is " + resp);
		} catch (IOException e) {
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
