<?php
	/*
		uname		pwd
		web - SUWs8eyaiMe7yFIk
		prt 3306
		MySQL Connection
		IP: foodtray.co.uk
		DB: foodtray-7261
	*/

	# Variables
	$ip = "foodtray.co.uk"; 
	$username = "web-RBV"; 
	$password = "SUWs8eyaiMe7yFIk";
	$database = "foodtray-7261";

	 #Establishes connection & stores connection into con variable
	$conn = new mysqli($ip, $username, $password, $database);

	if ($conn->connect_error) { #If statement to kill connection if it fails
		die("Connection failed: " . $conn->connect_error);
	}
?>