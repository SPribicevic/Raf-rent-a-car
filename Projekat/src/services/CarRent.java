package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Car;
import beans.Customer;
import beans.Log;
import beans.Param;
import beans.Server;

@Path("/rent")
public class CarRent {
	
	@GET
	@Path("/test")
	public String test(){
		return "REST is working!";
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(Log log){
		String server_req, server_resp;
		
		server_req = "REG " + log.getUser() + " " + log.getPass();
		server_resp = Server.server(server_req);
		
		return server_resp;
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(Log log, @Context HttpServletRequest req){
		String server_req, server_resp;
		
		server_req = "LOG " + log.getUser() + " " + log.getPass();
		server_resp = Server.server(server_req);
		
		if(server_resp.equals("Successfully logged in!")){
			HttpSession session = req.getSession(true);
			session.setAttribute("user", log.getUser());
		}
		
		return server_resp;
	}
	
	@POST
	@Path("/addcar")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addCar(Car car, @Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String server_req, server_resp;
		if(session.getAttribute("user") == null){
			resp.sendRedirect("index.jsp");
			return("User not logged in!");
		} else {
			server_req = "ADD car " + car.getReg_plate() + " " + car.getCategory() + " " + car.getModel();
			server_resp = Server.server(server_req);
			return server_resp;
		}
	}
	
	@POST
	@Path("/addcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addCustomer(Customer customer, @Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String server_req, server_resp;
		if(session.getAttribute("user") == null){
			resp.sendRedirect("index.jsp");
			return("User not logged in!");
		} else {
			server_req = "ADD customer " + customer.getName() + " " + customer.getSurname() + " " + customer.getJmbg() + " " + customer.getCategory();
			server_resp = Server.server(server_req);
			return server_resp;
		}
	}
	
	@POST
	@Path("/del")
	public String delete(Param param,  @Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String param1, param2, tokens[], server_req, server_resp;
		if(session.getAttribute("user") == null){
			resp.sendRedirect("http://localhost:8080/Projekat/index.jsp");
			return("User not logged in!");
		} else {
			tokens = param.getInfo().split(";");
			param1 = tokens[0];
			param2 = tokens[1];
			
			if(param1.equals("car")){
				server_req = "DEL car " + param2;
				server_resp = Server.server(server_req);
			} else {
				server_req = "DEL customer " + param2;
				server_resp = Server.server(server_req);
			}
			
			return(server_resp);
		}
	
	}
	
	@POST
	@Path("/list")
	public String listCustomers(Param param, @Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String server_req, server_resp, tokens[];
		if(session.getAttribute("user") == null){
			resp.sendRedirect("index.jsp");
			return("User not logged in!");
		} else {
			tokens = param.getInfo().split(";");
			if(tokens[0].equals("customer") && tokens[1].equals("")){
				server_req = "LIST";
			} else {
				if(!tokens[1].equals("")){
					server_req = "LIST " + tokens[0] + " " + tokens[1];
				} else {
					server_req = "LIST " + tokens[0] + " " + tokens[2];
				}
			}
			server_resp = Server.server(server_req);
			return server_resp;
		}
	}
	
	@POST
	@Path("/rent")
	public String renting(Param param, @Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String server_req, server_resp, tokens[];
		if(session.getAttribute("user") == null){
			resp.sendRedirect("index.jsp");
			return("User not logged in!");
		} else {
			tokens = param.getInfo().split(";");
			server_req = tokens[2] + " " + tokens[0] + " " + tokens[1];	
			server_resp = Server.server(server_req);
			return server_resp;
		}
	}
	
}
