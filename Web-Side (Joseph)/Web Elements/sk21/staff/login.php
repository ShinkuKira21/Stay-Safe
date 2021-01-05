<?php session_start();
	include "conn.php";
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	# Test Username and Password output
	#echo "Username: $username <br> Password: $password <br>";
	
	$qry = "SELECT * FROM accounts WHERE username = ? LIMIT 1";
	
	$qry = $link->prepare($qry);
	$qry->bind_param("s", $username);
	$qry->execute();
	$result = $qry->get_result();
	$qry->close();
	
	$row = $result->fetch_array();
	
	# Test Password
	#echo $row[2];
		
	# BCrypt log in (Python BCrypt Generation)
	if(password_verify($password, $row['password']))
	{
		# Login Message
		echo "Logged in, welcome ".$row['firstName']." ".$row['lastName']."<br>";
		
		# If login clearance is staff member
		# Redirection to StaffArea homepage.
		if($row['role'] == "Staff")
		{
			header("refresh:5;url=staffarea/index.php");
			$_SESSION['username'] = $username; // Set username to staff only 
			echo "Redirecting you to <a href='staffarea/index.php'/>Add Products.</a>";
		}
			
		
		# If login clearance is staff member
		#
		# Justification of Redirection:
		# The GitHub Wiki Page is Stay Safe's project for
		# Changemakers.
		#
		# This current website is aimed for the Business Staff Side, 
		# where the application is aimed at Customer Side.

		else
		{
			header("refresh:10;url=https://github.com/ShinkuKira21/Stay-Safe/wiki/Stay-Safe");
			echo "Unfortunately, you do not have clearance to visit this page, redirecting to the APK download page, to access Stay Safe's application. <br><br>";
			#Test Unauthorised Access
			echo "<a href='staffarea/index.php'>Test if username can access staff area</a>";
		}
	}
	
	else
	{
		header("refresh:5;url=index.php");
		echo "Password Incorrect <br> Redirecting";
	}
?>