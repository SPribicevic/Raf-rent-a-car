package beans;

import javax.ws.rs.FormParam;

public class Param {
	@FormParam("info")
	private String info;

	public Param() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Param(String info) {
		super();
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
