package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Rents {
	
	private ArrayList<Rent> rents = null;
	
	public Rents(){
		rents = new ArrayList<>();
		readRents();
	}
	
	public String listCustomers(String customer_id){
		String output = "";
		
		for(Rent r : rents){
			if(r.getCustomer_id().equals(customer_id)){
				output += r.toString();
			}
		}
		
		return output;
	}
	
	public boolean carBusy(String car_id){
		boolean output = false;
		
		for(Rent r : rents){
			if(r.getCar_id().equals(car_id)){
				if(r.getEnd_date().equals("-")){
					output = true;
					break;
				}
			}
		}
		
		return output;
	}
	
	public String addRent(String customer_id, String car_id){
		String output = "";
		Customers customers = new Customers();
		Cars cars = new Cars();
		SimpleDateFormat sdt = null;
		Date curr_date = null;
		String start_date, end_date;
		Car car;
		Customer customer;
		Rent rent;
		
		customer = customers.getCustomer(customer_id);
		car = cars.getCar(car_id);
		
		if(customer == null || car == null){
			output = "Invalid car or customer id!";
		} else {
			if(Integer.parseInt(customer.getCategory()) < Integer.parseInt(car.getCategory())){
				output = "Car category is not allowed for this customer category!";
			} else {
				if(this.carBusy(car_id)){
					output = "This car is busy!";
				} else {
					sdt = new SimpleDateFormat("dd.MM.yyyy.");
					curr_date = new Date();
					start_date = sdt.format(curr_date);
					end_date = "-";
					
					rent = new Rent(customer_id, car_id, start_date, end_date);
					rents.add(rent);
					
					try(FileWriter fw = new FileWriter("rents.txt", true);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter out = new PrintWriter(bw)){
							out.println(rent.toString());
							out.close();
						} catch (IOException e){	
							e.printStackTrace();
					}
					
					output = "Car rented successfully.";
				}
			}
		}
		
		return output;	
	}
	
	public String returnCar(String customer_id, String car_id) throws FileNotFoundException{
		String output = "";
		Customer customer;
		Car car;
		Customers customers = null;
		Cars cars = null;
		Rent rent = null;
		SimpleDateFormat sdf = null;
		Date currDate = null;
		PrintWriter pw = null;
		
		customers = new Customers();
		cars = new Cars();
		
		customer = customers.getCustomer(customer_id);
		car = cars.getCar(car_id);
		
		if(customer == null || car == null){
			output = "Invalid car or customer id!";
		} else {
			for(Rent r : rents){
				if(r.getCar_id().equals(car_id) && r.getCustomer_id().equals(customer_id) && r.getEnd_date().equals("-")){
					rent = r;
					sdf = new SimpleDateFormat("dd.MM.yyyy.");
					currDate = new Date();
					r.setEnd_date(sdf.format(currDate));
					break;
				}
			}
			
			if(rent == null){
				output = "No rent found!";
			} else {
				pw = new PrintWriter("rents.txt");
				pw.print("");
				
				for(Rent r : rents){
					output = r.toString();
					pw.append(output);
				}
				
				pw.close();
				
				output = "Car returned successfully.";
			}
		}
		
		return output;
		
	}
	
	public String listCars(String car_id){
		String output = "";
		
		for(Rent r : rents){
			if(r.getCar_id().equals(car_id)){
				output += r.toString();
			}
		}
		
		return output;
	}
	
	private synchronized void readRents(){
		BufferedReader in = null;
		File file = new File("rents.txt");
		String line, customer_id, car_id, start_date, end_date;
		
		try {
			in = new BufferedReader(new FileReader(file));
			
			try {
				while((line = in.readLine()) != null){
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					String[] tokens = line.split(";");
					customer_id = tokens[0].trim();
					car_id = tokens[1].trim();
					start_date = tokens[2].trim();
					end_date = tokens[3].trim();
					this.rents.add(new Rent(customer_id, car_id, start_date, end_date));
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
