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

public class Cars {
	private HashMap<String, Car> cars = null;
	
	public Cars(){
		cars = new HashMap<>();
		readCars();
	}
	
	public int getCarCount(){
		return cars.size();
	}
	
	public Car getCar(String id){
		return cars.get(id);
	}
	
	public String list(String arg) {
		// TODO Auto-generated method stub
		Rents rents = new Rents();
		String output = "";
		
		if(cars.containsKey(arg)){
			output += cars.get(arg).toString() + "\n";
			output += rents.listCars(arg);
		} else {
			arg = arg.toLowerCase();
			
			switch(arg){
			case "all":
				for(Car c: cars.values()){
					output += c.toString();
				}
				break;
			case "available":
				for(Car c: cars.values()){
					if(!rents.carBusy(c.getId())){
						output += c.toString();
					}
				}
				break;
			case "busy":
				for(Car c: cars.values()){
					if(rents.carBusy(c.getId())){
						output += c.toString();
					}
				}
				break;
			default:
				output = "Wrong third parameter!";
			}
		}
		
		return output;
	}
	
	public synchronized String deleteCar(String id) throws FileNotFoundException{
		String output;
		PrintWriter pw = null;
	
		if(cars.get(id) == null){
			return("Invalid id!");
		}
		
		pw = new PrintWriter("cars.txt");
		pw.print("");
		
		cars.remove(id);
		
		for(Car c : cars.values()){
			output = c.toString();
			pw.append(output);
		}
		
		pw.close();
		
		return("Car successfully deleted");
		
	}
	
	public synchronized String addCar(String reg_plate, String category, String model) throws IOException{
		String output, id;
		
		id = Integer.toString(cars.size() + 1);
		int reg_plate_num = Integer.parseInt(reg_plate);
		int category_num = Integer.parseInt(category);
		
		if(reg_plate == null){
			return("No third parameter!");
		}
		
		if(category == null){
			return("No fourth parameter!");
		}
		
		if(model == null){
			return("No fifth parameter!");
		}
		
		if(reg_plate.length() != 5){	// provera validiteta registarske tablice-a
			return("Invalid registration plate!");
		} else {
			for(Car c : cars.values()){
				if(reg_plate_num == Integer.parseInt(c.getReg_plate())){
					return("Registration plate already in use!");
				}
			}
		}
		
		if(category.length() != 1 || category_num < 1 || category_num > 5){
			return("Invalid category!");
		}
		
		output = id + ";" + reg_plate + ";" + category + ";" + model;
		
		try(FileWriter fw = new FileWriter("cars.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)){
			out.println(output);
			out.close();
		} catch (IOException e){	
			e.printStackTrace();
		}
		
		return("Car added successfully.");
	}
	
	private synchronized void readCars(){
		BufferedReader in = null;
		File file = new File("cars.txt");
		String line, id, reg_table, category, model;
		
		try {
			in = new BufferedReader(new FileReader(file));
			
			try {
				while((line = in.readLine()) != null){
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					String[] tokens = line.split(";");
					id = tokens[0].trim();
					reg_table = tokens[1].trim();
					category = tokens[2].trim();
					model = tokens[3].trim();
					cars.put(id, new Car(id, reg_table, category, model));
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
