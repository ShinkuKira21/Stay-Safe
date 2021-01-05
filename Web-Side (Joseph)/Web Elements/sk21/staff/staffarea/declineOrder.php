<!-- Declines an Order -->
<!-- Content:
		Deletes a order from orders
-->

<?php include "../conn.php";
	$orderID = $_POST['id'];
	
	$qry = "DELETE FROM orders WHERE id = ?";
	$qry = $link->prepare($qry);
	$qry->bind_param('s', $orderID);
	$qry->execute();
	$qry->close();
	
	header("Location: orders.php");
?>