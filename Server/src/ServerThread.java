

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import beans.Cars;
import beans.Customers;
import beans.Rents;
import beans.User;
import beans.Users;

public class ServerThread extends Thread {
	
	private Socket sock;
	private BufferedReader in;
	private PrintWriter out;
	private int value;
	
	public ServerThread(Socket sock, int value){
		
		this.sock = sock;
		this.value = value;
		
		try {
			//inicijalizacija ulazno/izlaznog sistema
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			//citanje zahteva
			String request = in.readLine();
			//odgovor
			//out.println(request);
			
			String tokens[] = request.split(" ");
			
			String user, pass, id, name, surname, jmbg, category, reg_table, model;
			Customers customers = new Customers();
			Cars cars = new Cars();
			Users users = new Users();
			Rents rents = new Rents();
			
			switch(tokens[0].toUpperCase()){
			case "REG":
				user = tokens[1];
				pass = tokens[2];
				if(user.equals("") || pass.equals("")){
					out.println("User and pass has to be provided");
				} else {
					out.println(users.registerUser(user, pass));
				}
				break;
			case "LOG":
				user = tokens[1];
				pass = tokens[2];
				if(user.equals("") || pass.equals("")){
					out.println("User and pass has to be provided");
				} else {
					out.println(users.logUser(user, pass));
				}
				break;
			case "ADD":
				if(tokens[1] == null){
					out.println("No second parameter!");
				} else {
					if(tokens[1].toLowerCase().equals("customer")){ // add customer
				
						name = tokens[2];
						surname = tokens[3];
						jmbg = tokens[4];
						category = tokens[5];
						
						out.println(customers.addCustomer(name, surname, jmbg, category));
					
					}else{
						if(tokens[1].toLowerCase().equals("car")){ // add car
							
							reg_table = tokens[2];
							category = tokens[3];
							model = tokens[4];
							
							out.println(cars.addCar(reg_table, category, model));
						}
						else{
							out.println("Wrong second parameter!");
						}
					}
				}
				
				break;
			case "DEL":
				if(tokens[1] == null){
					out.println("No second parameter!");
				} else {
					if(tokens[1].toLowerCase().equals("customer")){ // delete customer
						
						id = tokens[2];
						
						out.println(customers.deleteCustomer(id));
					
					}else{
						if(tokens[1].toLowerCase().equals("car")){ // add car
							
							id = tokens[2];
							
							out.println(cars.deleteCar(id));
						}
						else{
							out.println("Wrong second parameter!");
						}
					}
				}
				break;
			case "LIST":
				switch(tokens.length){
				case 1:
					out.println(customers.list());
					break;
				case 2:
					out.println("No third parameter!");
					break;
				case 3:
					if(tokens[1].toLowerCase().equals("customer")){
						out.println(customers.list(tokens[2]));
					} else {
						if(tokens[1].toLowerCase().equals("car")){
							out.println(cars.list(tokens[2]));
						} else {
							out.println("Wrong second parameter!");
						}
					}
				}
				break;
			case "RENT":
					if(tokens.length != 3){
						out.println("Wrong parameters!");
					} else {
						out.println(rents.addRent(tokens[1], tokens[2]));
					}
				break;
			case "RETURN":
				if(tokens.length != 3){
					out.println("Wrong parameters!");
				} else {
					out.println(rents.returnCar(tokens[1], tokens[2]));
				}
				break;
			default:
				out.println("Wrong first parameter!");
				break;
			}
			
			in.close();
			out.close();
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
