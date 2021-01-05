<!-- 
  * Order transactions work, however, the SQL database has a trigger to remove orders after 10 minutes.
  * The Stay Safe application communicates with the database to add products.
  * The SQL file will have Order data but if not tested in 10 minutes, it will soon disappear.
  * If that's true then, manually add a transaction via database side.
-->

<!-- Content:
	Inner Joins, PHP arrays and showProducts.js (recycled)
	PHP strtolower() is used and queries
-->

<?php session_start();
if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { ?>

<?php include "../conn.php";
	$qry = "SELECT orders.id, orders.productID, orders.customerID, orders.active, products.*, accounts.* FROM orders INNER JOIN products INNER JOIN accounts ON orders.productID = products.id AND orders.customerID = accounts.id";
	
	$orderID = [];
	$orderActive = [];
	$customerID = [];
	$productID = [];
	
	$productName = [];
	$productPrice = [];
	
	$customerFName = [];
	$customerLName = [];
	$customerTelPhone = [];
	
	$results = mysqli_query($link, $qry);

	while($row = $results->fetch_array())
	{
		# Test Code
		#print_r(array_keys($row));
		#print("<br>");
		
		# Order Info
		array_push($orderID, $row[0]);
		
		# Set Active to accepted or pending (order status)
		if($row['active'] == 0)
			array_push($orderActive, "Pending");
		else
			array_push($orderActive, "Accepted");
		
		# Product Info
		array_push($productID, $row['productID']);
		array_push($productName, $row['name']);
		array_push($productPrice, $row['price']);
		
		# Customer Info
		array_push($customerID, $row['customerID']);
		array_push($customerFName, $row['firstName']);
		array_push($customerLName, $row['lastName']);
		
		# Test Code
		#echo "<br>";
		#echo "ID: ".$customerID[0]."<br>First Name: ".$customerFName[0]."<br>Last Name: ".$customerLName[0]."<br><br>";
	}
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body onload="LoadCategories()">
	<?php include "navbar.php"; ?>
	
	<?php
		if(count($orderID) > 0)
			for($i = 0; $i < count($orderID); $i++)
			{
				?>
				
				<div id="<?php echo strtolower($orderID[$i]); ?>" class="container border text-center categories" onclick="ShowCategory('<?php echo strtolower($orderID[$i]); ?>')">
					<?php echo $orderID[$i]; ?> | Status: <?php echo $orderActive[$i]; ?>
					
					<div id="row-ctitle" class="row font-weight-bold">
						<div id="col-title-customerID" class="col">Customer ID</div>
						<div id="col-title-customerFName" class="col">First Name</div>
						<div id="col-title-customerLName" class="col">Last Name</div>
					</div>
					
					<div id="row-r1-<?php echo strtolower($orderID[$i]); ?>" class="row">
						<div id="col-r1-<?php echo strtolower($orderID[$i]); ?>-cid" class="col"><?php echo $customerID[$i]; ?></div>
						
						<div id="col-<?php echo strtolower($orderID[$i]); ?>-cfn"class="col"><?php echo $customerFName[$i]; ?></div>
						
						<div id="col-<?php echo strtolower($orderID[$i]); ?>-cln" class="col"><?php echo $customerLName[$i]; ?></div>
					</div>
					
					<div id="row-ptitle" class="row font-weight-bold" style="margin-top: 2.5%;">
						<div id="col-title-productID" class="col">Product ID</div>
						<div id="col-title-productName" class="col">Name(s)</div>
						<div id="col-title-productPrice" class="col">Price</div>
					</div>
					
					<div id="row-r2-<?php echo strtolower($orderID[$i]); ?>" class="row">
						<div id="col-r2-<?php echo strtolower($orderID[$i]); ?>-pid" class="col"><?php echo $productID[$i]; ?></div>
						
						<div id="col-<?php echo strtolower($orderID[$i]); ?>-pname"class="col"><?php echo $productName[$i]; ?></div>
						
						<div id="col-<?php echo strtolower($orderID[$i]); ?>-pprice" class="col">&#163;<?php echo $productPrice[$i]; ?></div>
					</div>
					<div id="row-form-control-<?php echo strtolower($orderID[$i]); ?>" class="row" style="margin-top: 5%; margin-bottom: 2.5%;">
						<?php if($orderActive[$i] == "Pending") { ?>
							<div id="col-edit-<?php echo strtolower($orderID[$i]); ?>" class="col" align="center">
								<form action='acceptOrder.php' method='post'>
									<input name='id' type='hidden' value='<?php echo $orderID[$i]; ?>'>
									<input type='submit' value="Accept"/>
								</form>
							</div>
						<?php } ?>
						<div id="col-delete-<?php echo strtolower($orderID[$i]); ?>" class="col" align="center">
							<form action='declineOrder.php' method='post'>
								<input name='id' type='hidden' value='<?php echo $orderID[$i]; ?>'>
								<input type='submit' value="Decline"/>
							</form>
						</div>
					</div>
				</div>
				
				<?php
			}
			
		else
		{
			echo "<div id='error-container' class='container border text-center'>";
				echo "No Orders";
			echo "</div>";
		}
			
	?>
    
	
	<?php } 
		else echo "Unauthorised access. Reporting IP address..."; 
	?>
	
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/showProducts.js"></script>
</body>

</html>