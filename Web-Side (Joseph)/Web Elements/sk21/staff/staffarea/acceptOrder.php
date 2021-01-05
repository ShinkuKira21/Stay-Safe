<!-- Accept an Order (change active field to 1) -->
<!-- Content:
		Updates one field where id is equal to id 
		Uses Prepared Statement
-->
<?php 
	include "../conn.php";

	$orderID = $_POST['id'];
	$active = 1;
	$qry = "UPDATE orders SET active = ? WHERE id = ? LIMIT 1";
	
	$qry = $link->prepare($qry);
	$qry->bind_param("is", $active, $orderID);
	$qry->execute();
	$qry->close();
	
	Header("Location: orders.php");
?>