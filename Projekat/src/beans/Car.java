package beans;

import javax.ws.rs.FormParam;

public class Car {
	@FormParam("reg_plate")
	private String reg_plate;
	@FormParam("category")
	private String category;
	@FormParam("model")
	private String model;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String reg_plate, String category, String model) {
		super();
		this.reg_plate = reg_plate;
		this.category = category;
		this.model = model;
	}
	public String getReg_plate() {
		return reg_plate;
	}
	public void setReg_plate(String reg_plate) {
		this.reg_plate = reg_plate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
}
