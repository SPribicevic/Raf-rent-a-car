package beans;

import javax.ws.rs.FormParam;

public class Customer {
	@FormParam("name")
	private String name;
	@FormParam("surname")
	private String surname;
	@FormParam("jmbg")
	private String jmbg;
	@FormParam("category")
	private String category;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, String surname, String jmbg, String category) {
		super();
		this.name = name;
		this.surname = surname;
		this.jmbg = jmbg;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
