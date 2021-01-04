<?php include "dbconnection.php";

	$regUsername = $_POST['registerUsername'];
	$regPassword = $_POST['registerPassword'];
	$regFirstName = $_POST['registerFirst'];
	$regLastName = $_POST['registerLast'];
	$regEmail = $_POST['registerEmail'];
	$regAge = $_POST['registerAge'];
	$regTelPhone = $_POST['registerTel'];
	
	$regPassword = password_hash($regPassword, CRYPT_BLOWFISH);
	
	$query = "SELECT * FROM accounts"; //query
    $i = -1; // index

    $result = mysqli_query($conn, $query); // executes query

    while($row = $result->fetch_array())
        $i = $row['id']; // sets index to id

	$i += 1; // adds one to index
	
	
	$query="INSERT INTO accounts(id,username,password,firstName,lastName,age,email,telephoneNumber,role,picture) VALUES($i,'$regUsername','$regPassword','$regFirstName','$regLastName', $regAge,'$regEmail', '$regTelPhone', 'Student', NULL)";
	
	$result = mysqli_query($conn, $query);
	
	header("Location: ../../");
?>