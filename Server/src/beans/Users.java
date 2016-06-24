package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Users {
	private HashMap<String, User> users = null;
	
	public Users(){
		users = new HashMap<String, User>();
		readUsers();
	}
	
	public int getUsersCount(){
		return users.size();
	}
	
	public User getUser(String user){
		return users.get(user);
	}
	
	public synchronized String registerUser(String user, String pass) throws IOException{
		String output, id, logged;
		
		id = Integer.toString(users.size() + 1);
		logged = "no";
		
		output = id + ";" + user + ";" + pass + ";" + logged;
		
		try(FileWriter fw = new FileWriter("users.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)){
			out.println(output);
			out.close();
		} catch (IOException e){	
			e.printStackTrace();
		}
		
		return("User registered successfully");
	}
	
	public synchronized String logUser(String user, String pass){
		User u = users.get(user);
		
		if(u == null){
			return("No such username!");
		}
		
		if(!pass.equals(u.getPass())){
			return("Incorrect password provided!");
		}
		
		return("Successfully logged in!");
	}
	
	private synchronized void readUsers(){
		BufferedReader in = null;
		File file = new File("users.txt");
		String line, id, user, pass, logged;
		
		try {
			in = new BufferedReader(new FileReader(file));
			
			try {
				while((line = in.readLine()) != null){
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					String[] tokens = line.split(";");
					id = tokens[0].trim();
					user = tokens[1].trim();
					pass = tokens[2].trim();
					logged = tokens[3].trim();
					users.put(user, new User(id, user, pass, logged));
				}
				
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
