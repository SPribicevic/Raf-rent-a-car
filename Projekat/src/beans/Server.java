package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Server {
	
	public static final int PORT = 9001;
	
	public static String server(String request){
		String response = "";
		String line;
		
		try {
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			Socket sock = new Socket(addr, PORT);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
				
			out.println(request);
				
			while((line = in.readLine()) != null){
				response += line;
			}
			
			in.close();
			out.close();
			sock.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = "Could not connect to server!";
			e.printStackTrace();
		}
		
		return response;
	}
	
}
