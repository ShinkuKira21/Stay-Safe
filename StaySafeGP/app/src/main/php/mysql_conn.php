<?php
	$server = 'stay-safe.database.windows.net'; # my sql server
	$port = '1433'; # port of sql server
	$database = 'owner@staysafe-23'; # my sql server's database name
	$username = 'owner'; # my username to sql's server
	$password = 's5hhYSr@LRyD'; # my password to sql's server


	$con = mysqli_init(); 
	mysqli_real_connect($con, $server, $username, $password, $database, $port);
	
	if(mysqli_connect_errno())
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	
	$sql = "SELECT * FROM animals";
	
	if($result = mysqli_query($con, $sql));
	{
		$resultArray = array();
		$temp = array();
		
		while($row = $result->fetch_object())
		{
			$tempArray = $row;
			array_push($resultArray, $t
		}
		
		echo json_encode($resultArray);
	}
	
	mysqli_close($con);
?>