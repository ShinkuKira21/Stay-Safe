<!-- Search Results for Products -->
<!-- Content:
		Use page selector (mainly) and the search
-->

<?php session_start();
if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { ?>

<?php include "../conn.php";
    $search = $_POST['search'];

    //Page logic
    $limit = 4;
    @$cPage = $_GET['s'];

    //CONCAT allows wild cards in prepared statement
    //Reference:
    //https://stackoverflow.com/questions/18527659/php-mysqli-prepared-statement-like

    $qry = "SELECT * FROM products WHERE name like CONCAT('%',?,'%') OR category like CONCAT('%',?,'%') OR allergies like CONCAT('%',?,'%')";

    $qry = $link->prepare($qry);
    $qry->bind_param("sss", $search, $search, $search);
    $qry->execute();
    $results = $qry->get_result();
    $qry->close();
?>

<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body
    <?php include "navbar.php"; ?>
    <div class="container text-center">
    <?php if(mysqli_num_rows($results)) { 
        ?>
        <div id="result-headings" class="row">
            <div id="productName" class="col">Name:</div>
            <div id="productCategory" class="col">Category:</div>
            <div id="productCalories" class="col">Calories:</div>
            <div id="productCalories" class="col">Allergies:</div>
            <div id="productPrice" class="col">Price:</div>
        </div>
        <?php
    
        while($row = $results->fetch_array()) { ?>
        <div id="results" class="row border" style="margin-top: 2.0%;">
            <div class="col"><?php echo $row['name']; ?></div>
            <div class="col"><?php echo $row['category']; ?></div>
            <div class="col"><?php echo $row['calories']; ?></div>
            <div class="col"><?php echo $row['allergies']; ?></div>
            <div class="col">&#163;<?php echo $row['price']; ?></div>
        </div>
    <?php }}
        else {
	    ?>
        <div id="results" class="row border" style="margin-top: 2.0%;">
            <div class="col">
                No Results
            </div>
        </div>
        <?php
        }
    ?>
    </div>

	<?php }
		else echo "Unauthorised access. Reporting IP address..."; 
	?>
	
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>

</html>