
<?php 
	session_start();
	include "dbconnection.php";

	$productID = $_POST['id'];

	$query = "SELECT name, price FROM products WHERE id = $productID LIMIT 1";
	$result = mysqli_query($conn, $query);
	$row = mysqli_fetch_array($result);
	
	array_push($_SESSION['trayID'], $productID);
	array_push($_SESSION['trayName'], $row['name']);
	array_push($_SESSION['trayPrice'], $row['price']);
	
	header("Location: ../../menu.php");
?>


