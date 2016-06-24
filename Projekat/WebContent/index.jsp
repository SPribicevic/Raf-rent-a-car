<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rent a car</title>

<script src="jquery.min.js"></script>
<script type="text/javascript">

	function register(){
		var reg = new Object;
		reg.user = $( "#reg_user" ).val();
		reg.pass = $( "#reg_pass" ).val();
		
		var s = JSON.stringify(reg);
		console.log(s);
		
		$.ajax({
			url: "rest/rent/register",
			type:"POST",
			data: s,
			contentType:"application/json",
			dataType:"json",
			complete: function(data){
				console.log(data);
				$( "#messages" ).text(data.responseText);
			}
		});
	}
	
	function login(){
		var log = new Object;
		log.user = $( "#log_user" ).val();
		log.pass = $( "#log_pass" ).val();
		
		var s = JSON.stringify(log);
		
		console.log(s);
		
		$.ajax({
			url: "rest/rent/login",
			type:"POST",
			data: s,
			contentType:"application/json",
			dataType:"json",
			complete: function(data){
				console.log(data);
				if(data.responseText === "Successfully logged in!"){
					console.log("Logged in!");
					/*$( "#reg_log" ).hide();
					$( "#app" ).show();*/
					window.location.replace("http://localhost:8080/Projekat/app.jsp");
				} else {
					$( "#messages" ).text(data.responseText);
				}
			}
		});
	}
	
	
	
</script>

<style>
	fieldset {
		display: inline-block;
	}
</style>

</head>
<body>

	<div id="reg_log">
		<h1>Welcome to rent a car management software</h1>
		<fieldset id="reg">
			<legend>Register:</legend>
			Username:
			<input type="text" id="reg_user">
			Password: 
			<input type="text" id="reg_pass">
			<input type="button" onclick="register()" value="Submit">
		</fieldset>
		<fieldset id="log">
			<legend>Login:</legend>
			Username:
			<input type="text" id="log_user">
			Password: 
			<input type="text" id="log_pass"> 
			<input type="button" onclick="login()" value="Submit">
		</fieldset>
		<p id="messages"></p>
	</div>

</body>
</html>