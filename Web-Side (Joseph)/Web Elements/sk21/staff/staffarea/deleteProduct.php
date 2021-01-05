<!-- Declines an Order -->
<!-- Content:
		Deletes a product from products
-->

<?php include "../conn.php";
	$id = $_POST['id'];
	
	$qry = "DELETE FROM products WHERE id = ?";
	$qry = $link->prepare($qry);
	$qry->bind_param('i', $id);
	$qry->execute();
	$qry->close();
	
	header("Location: showProducts.php")
?>