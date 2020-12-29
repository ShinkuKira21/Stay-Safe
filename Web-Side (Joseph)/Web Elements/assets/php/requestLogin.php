<?php 
	session_start();
	include "dbconnection.php";
	
	$loginUsername = $_POST['loginUsername'];
	$loginPassword = $_POST['loginPassword'];
	
	echo $loginUsername;
	
	$query = "SELECT * FROM accounts WHERE username = '$loginUsername'";
	
	$results = mysqli_query($conn, $query) or die("Wrong Username/Password.");
	
	$row = mysqli_fetch_array($results);
	
	if(password_verify($loginPassword, $row['password']))
	{
		$_SESSION['username'] = $loginUsername;
		$_SESSION['firstName'] = $row['firstName'];
		if($row['role'] == "Staff")
			header("Location: ../../sk21/staff/staffarea");
		
		else
			header("Location: ../../menu.php");
	}
	
	else echo "Wrong Username/Password.";
?>