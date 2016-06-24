package beans;

public class Rent {
	
	private String customer_id, car_id, start_date, end_date;

	public Rent(String customer_id, String car_id, String start_date,
			String end_date) {
		super();
		this.customer_id = customer_id;
		this.car_id = car_id;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public String toString(){
		return(this.customer_id + ";" + this.car_id + ";" + this.start_date + ";" + this.end_date + "\n");
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
}
