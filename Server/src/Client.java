

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static final int PORT = 9000;
	
	public static void main(String[] args) {
		
		try {
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			Socket sock = new Socket(addr, PORT);
			Scanner sc = new Scanner(System.in);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			
			String request;
			
			//posalji zahtev
			System.out.println("Unesite zahtev:");
			request = sc.nextLine();
				
			out.println(request);
				
			//procitaj odgovor
			String response;
			while((response = in.readLine()) != null){
				System.out.println("[Server]:"+response);
			}
			
			in.close();
			out.close();
			sock.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
