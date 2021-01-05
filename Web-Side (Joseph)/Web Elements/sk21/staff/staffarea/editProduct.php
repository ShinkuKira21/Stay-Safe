<?php include "../conn.php";
	$id = $_POST['id'];
	$name = $_POST['name'];
	$calories = $_POST['calories'];
	$availability = $_POST['availability'];
	$allergies = $_POST['allergies'];
	$price = $_POST['price'];
	$img = $_FILES['img']['name'];

	$initQry = "UPDATE products SET ";
	$qry = $initQry;

	//Checks if ProductID is set
	if(isset($id))
	{
		//If name is set then add to qry string name = $name
		if(!empty($name)) 
		{
			// if qry does not match initial qry then add comma
			if($qry != $initQry) $qry .= ", ";
			$qry .= "name = '$name'";
		}

		//If calories is set then add to qry string , calories = $calories
		if(!empty($calories)) 
		{
			// if qry does not match initial qry then add comma
			if($qry != $initQry) $qry .= ", ";
			$qry .= "calories = $calories";
		}

		//If availability is set then add to qry string , availability = $availability
		if(!empty($availability)) 
		{
			// if qry does not match initial qry then add comma
			if($qry != $initQry) $qry .= ", ";
			$qry .= "availability = $availability";
		}

		//If allergies is set then add to qry string , allergies = $allergies
		if(!empty($allergies))
		{
			//Empties string is null is detected
			if($allergies == "null") $allergies = "";

			// if qry does not match initial qry then add comma
			if($qry != $initQry) $qry .= ", ";
			$qry .= "allergies = '$allergies'";
		}

		if(!empty($price)) 
		{
			// if qry does not match initial qry then add comma
			if($qry != $initQry) $qry .= ", ";
			$qry .= "price = $price";
		}

		if(!empty($img))
		{
			/* Deletion Needs Reworking */	
			// Deletes img REFERENCE:
			/* [1]NishanthReddy7, “How to delete a file using PHP ?,” How to delete a file using PHP ?, Jul. 30, 2019. https://www.geeksforgeeks.org/how-to-delete-a-file-using-php/ (accessed Dec. 15, 2020). */

			//$nestedQry = "SELECT img FROM products WHERE id = '$id' LIMIT 1";
			//$result = mysqli_query($link, $nestedQry);
			//$row = $result->fetch_array();

			//if(unlink(dirname(__FILE__).$row[0]))
			//	echo "removed";
		
			include "uploadImage.php";

			// if qry does not match initial qry then add comma
			if($qry != $initQry) $qry .= ", ";
			$qry .= "img = '$uploadPath'";
		}
		
		# if query is same as initial query go back to Show Products Page
		if($qry == $initQry)
			header( "refresh:10; url=showProducts.php");

		$qry .= " WHERE id = $id LIMIT 1";
	}

	# Test query string
	#echo "Query: $qry<br>";
	
	$result = mysqli_query($link, $qry);

	echo "Transaction Succesful<br>Redirecting to <a href='showProducts.php'>Show Products</a>";
	header( "refresh:10;url=showProducts.php" );
?>