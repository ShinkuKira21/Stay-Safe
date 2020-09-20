<?php
	$server = 'stay-safe.database.windows.net'; # my sql server
	$port = '1433'; # port of sql server
	$database = 'staysafe'; # my sql server's database name
	$username = 'owner'; # my username to sql's server
	$password = 's5hhYSr@LRyD'; # my password to sql's server

	// PHP Data Objects(PDO) Sample Code:
	try {
		$conn = new PDO("sqlsrv:server = tcp:".$server.",".$port."; Database = ".$database.", ".$username.", ".$password);
		$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	}
	catch (PDOException $e) {
		print("Error connecting to SQL Server.");
		die(print_r($e));
	}

	echo "Hello"
?>