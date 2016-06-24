package beans;

public class Customer {
	
	private String id, name, surname, jmbg, category;
	
	public Customer(String id, String name, String surname, String jmbg,
			String category) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.jmbg = jmbg;
		this.category = category;
	}
	
	public String toString(){
		return(this.id + ";" + this.name + ";" + this.surname + ";" + this.jmbg + ";" + this.category + "\n");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getJMBG() {
		return jmbg;
	}

	public void setJMBG(String jMBG) {
		jmbg = jMBG;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
