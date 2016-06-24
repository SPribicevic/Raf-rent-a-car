package beans;

import javax.ws.rs.FormParam;

public class Log {
	@FormParam("user")
	private String user;
	@FormParam("pass")
	private String pass;
	
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Log(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
