<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rent a car App</title>

<script src="jquery.min.js"></script>

<script type="text/javascript">

	function addCar(){
		var car = new Object();
		car.reg_plate = $( "#car_reg_plate" ).val();
		car.category = $( "#car_category" ).val();
		car.model = $( "#car_model" ).val();
		
		var s = JSON.stringify(car);
		
		console.log(s);
		
		$.ajax({
			url: "rest/rent/addcar",
			type: "POST",
			data: s,
			contentType:"application/json",
			dataType:"json",
			complete: function(data){
				$( "#add_car_message" ).text(data.responseText);		
			}
		});
	}
	
	function addCustomer(){
		var customer = new Object();
		customer.name = $( "#customer_name" ).val();
		customer.surname = $( "#customer_surname" ).val();
		customer.jmbg = $( "#customer_jmbg" ).val();
		customer.category = $( "#customer_category" ).val();
		
		var s = JSON.stringify(customer);
		
		$.ajax({
			url: "rest/rent/addcustomer/",
			type: "POST",
			data: s,
			contentType:"application/json",
			dataType:"json",
			complete: function(data){
				$( "#add_customer_message" ).text(data.responseText);		
			}
		});
	}
	
	function delCar(){
		var id = $( "#del_car_id" ).val();
		var parameter = new Object();
		parameter.info = "car;" + id;
		var s = JSON.stringify(parameter);
		
		console.log(parameter);
		
		$.ajax({
			url: "rest/rent/del",
			type: "POST",
			dataType: "json",
			data: s,
			contentType:"application/json",
			complete: function(data){
				console.log(data.responseText);
				$( "#del_car_message" ).text(data.responseText);		
			}
		});
	}
	
	function delCustomer(){
		var id = $( "#del_customer_id" ).val(); 
		var parameter = new Object();
		parameter.info = "customer;" + id;
		var s = JSON.stringify(parameter);
		
		$.ajax({
			url: "rest/rent/del",
			type: "POST",
			dataType: "json",
			data: s,
			contentType:"application/json",
			complete: function(data){
				console.log(data.responseText);
				$( "#del_customer_message" ).text(data.responseText);		
			}
		});
	}
	
	/*function listCustomers(){
		$.ajax({
			url: "rest/rent/list/noparam",
			type: "GET",
			complete: function(data){
				$( "#list_customers_message" ).text(data);		
			}
		});
	}*/
	
	function listing(){
		var param;
		var type, id, car_type;
		
		type = $( "#list_type" ).val();
		id = $( "#list_id" ).val();
		car_type = $( "#list_car_type" ).val();
		
		var param = new Object();
		param.info = type + ";" + id + ";" + car_type;
		s = JSON.stringify(param);
		
		$.ajax({
			url: "rest/rent/list",
			type: "POST",
			dataType: "json",
			data: s,
			contentType:"application/json",
			complete: function(data){
				console.log(data.responseText);
				$( "#list_message" ).text(data.responseText);		
			}
		});
		
	}
	
	function rent(){
		var param;
		var car_id, customer_id, action;
		
		customer_id = $( "#renting_customer_id" ).val();
		car_id = $( "#renting_car_id" ).val();
		action = $( "#renting_action" ).val();
		
		var param = new Object();
		param.info = customer_id + ";" + car_id + ";" + action;
		s = JSON.stringify(param);
		
		$.ajax({
			url: "rest/rent/rent",
			type: "POST",
			dataType: "json",
			data: s,
			contentType:"application/json",
			complete: function(data){
				console.log(data.responseText);
				$( "#renting_message" ).text(data.responseText);		
			}
		});
		
	}

</script>

<style>
	fieldset {
		display: inline-block;
		float : left;
		color : red;
	}
	#app{
		background-color : gray;  
	}
	
</style>

</head>
<body>
	<div id="app">
		<h1>Car rent management</h1>
		
		<fieldset id="add_car">
			<legend>Add car:</legend>
			Registration plate:
			<input type="text" id="car_reg_plate">
			Category: 
			<input type="text" id="car_category">
			Model: 
			<input type="text" id="car_model">
			<input type="button" onclick="addCar()" value="Submit">
			<p id="add_car_message"></p>
		</fieldset>
		
		<fieldset id="add_customer">
			<legend>Add customer:</legend>
			Name:
			<input type="text" id="customer_name">
			Surname: 
			<input type="text" id="customer_surname">
			JMBG: 
			<input type="text" id="customer_jmbg">
			Category: 
			<input type="text" id="customer_category">
			<input type="button" onclick="addCustomer()" value="Submit">
			<p id="add_customer_message"></p>
		</fieldset>
		
		<fieldset id="del_car">
			<legend>Delete car:</legend>
			Car id:
			<input type="text" id="del_car_id">
			<input type="button" onclick="delCar()" value="Submit">
			<p id="del_car_message"></p>
		</fieldset>
		
		<fieldset id="del_customer">
			<legend>Delete customer:</legend>
			Customer id:
			<input type="text" id="del_customer_id">
			<input type="button" onclick="delCustomer()" value="Submit">
			<p id="del_customer_message"></p>
		</fieldset>
		
		<fieldset id="list">
			<legend>List:</legend>
			Type:
			<select id="list_type">
						<option value="car">Car</option>
						<option value="customer">Customer</option>
			</select>
			Input Id:
			<input type="text" id="list_id">
			Or choose between(for cars only):
			<select id="list_car_type">
						<option value="all">All</option>
						<option value="busy">Busy</option>
						<option value="available">Available</option>
			</select>
			<input type="button" onclick="listing()" value="Submit">
			<p id="list_message"></p>
		</fieldset>
		
		<fieldset id="renting">
			<legend>Renting:</legend>
			Customer id:
			<input type="text" id="renting_customer_id">
			Car id:
			<input type="text" id="renting_car_id">
			Action:
			<select id="renting_action">
						<option value="rent">Rent</option>
						<option value="return">Return</option>
			</select>
			<p id="renting_message"></p>
			<input type="button" onclick="rent()" value="Submit">
			<p id="renting_message"></p>
		</fieldset>
		
	</div>
</body>
</html>