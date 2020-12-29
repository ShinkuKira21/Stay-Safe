<?php
include "dbconnection.php";
$query = "show tables";
$results = mysqli_query($conn, $query); #Pulls table data

echo "Tables:<br>";
while($row = mysqli_fetch_array($results))
{
	echo ucwords($row[0]); #prints each table
	echo "<br>";
}

?>