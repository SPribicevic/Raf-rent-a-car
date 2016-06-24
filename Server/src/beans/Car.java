package beans;

public class Car {
	
	private String id, category, reg_plate, model;


	public Car(String id, String reg_plate, String category,String model) {
		super();
		this.id = id;
		this.category = category;
		this.reg_plate = reg_plate;
		this.model = model;
	}
	
	public String toString(){
		return(this.id + ";" + this.reg_plate + ";" + this.category + ";" + this.model + "\n");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReg_plate() {
		return reg_plate;
	}

	public void setReg_plate(String reg_plate) {
		this.reg_plate = reg_plate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
