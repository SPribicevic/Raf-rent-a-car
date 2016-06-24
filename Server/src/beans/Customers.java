package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Customers {
		private HashMap<String, Customer> customers = null;
		
		/*public static void main(String[] args){
			Customers cs= new Customers();
			System.out.println(cs.list());
		}*/
	
	
		public Customers(){
			customers = new HashMap<>();
			readCustomers();
		}
		
		public int getCustomerCount(){
			return customers.size();
		}
		
		public Customer getCustomer(String id){
			return customers.get(id);
		}
		
		public String list(){
			String output = "";
			
			for(Customer c : customers.values()){
				output += c.toString();
			}
			
			return output;
		}
		
		public String list(String id){
			String output = "";
			Rents rents = new Rents();
			
			output += customers.get(id).toString() + "\n";
			output += rents.listCustomers(id);
			
			return output;
			
		}
		
		public synchronized String deleteCustomer(String id) throws FileNotFoundException{
			PrintWriter pw = null;
			String output;
			
			if(customers.get(id) == null){
				return("Invalid id!");
			}
			
			pw = new PrintWriter("customers.txt");
			pw.print("");
			
			customers.remove(id);
			
			for(Customer c : customers.values()){
				output = c.toString();
				pw.append(output);
			}
			
			pw.close();
			
			return("Customer successfully deleted");
			
		}
		
		public synchronized String addCustomer(String name, String surname, String jmbg, String category) throws IOException{
			String output, id;
			
			id = Integer.toString(customers.size() + 1);
			int category_num = Integer.parseInt(category);
			
			if(name == null){
				return("No third parameter!");
			}
			
			if(surname == null){
				return("No fourth parameter!");
			}
			
			if(jmbg == null){
				return("No fifth parameter!");
			}
			
			if(category == null){
				return("No sixth parameter!");
			}
			
			if(jmbg.length() != 13){	// provera validiteta jmbg-a
				return("Invalid jmbg!");
			} else {
				for(Customer c : customers.values()){
					if(c.getJMBG().equals(jmbg)){
						return("JMBG already in use!");
					}
				}
			}
			
			if(category.length() != 1 || category_num < 1 || category_num > 5){
				return("Invalid category!");
			}
			
			output = id + ";" + name + ";" + surname + ";" + jmbg + ";" + category;
			
			try(FileWriter fw = new FileWriter("customers.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)){
				out.println(output);
				out.close();
			} catch (IOException e){	
				e.printStackTrace();
			}
			
			return("Customer addded successfully.");
		}
		
		private synchronized void readCustomers(){
			BufferedReader in = null;
			File file = new File("customers.txt");
			String line, id, name, surname, jmbg, category;
			
			try {
				in = new BufferedReader(new FileReader(file));
				
				try {
					while((line = in.readLine()) != null){
						line = line.trim();
						if (line.equals("") || line.indexOf('#') == 0)
							continue;
						String[] tokens = line.split(";");
						id = tokens[0].trim();
						name = tokens[1].trim();
						surname = tokens[2].trim();
						jmbg = tokens[3].trim();
						category = tokens[4].trim();
						customers.put(id, new Customer(id, name, surname, jmbg, category));
						//System.out.println(line);
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
