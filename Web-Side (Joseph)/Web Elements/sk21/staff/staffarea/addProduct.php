<?php include "../conn.php" ?>

<?php
    /* Fetch Data HTML FORM */
	$productName = ucwords(strtolower($_POST["name"]));
	$productCategory = $_POST["category"];
	$productPrice = $_POST["price"];
	$productCalories = $_POST["calories"];

    // If infinite set availability to -1 else,
    //set Availability to availability
	if(isset($_POST["inf"])) $productAvailability = -1;
	else $productAvailability = $_POST["availability"];

    //Add Allergies to allergies string variable
    if(isset($_POST["allergies"]))
    {
        $productAllergies = $_POST["allergies"];

        $allergies = "";
        $count = 0;

        foreach($productAllergies as $productAllergy)
        {
            if($count != 0)
                $allergies .= ", ";

            $allergies .= $productAllergy;

            $count++;
        }
    }

    //Check if img is set
    if(isset($_FILES['img']))
        include "uploadImage.php"; //launch uploadImage.php

    /* DATABASE UPLINK */

    // UNIQUE IDS \\

    /* MY PYTHON CODE TO COMPARE UNIQUE ID LOGIC
     * # Makes sure to generate a new ID for each account
     *       cursor.execute('SELECT * FROM products')
     *       index = 0
     *       for row in cursor :
     *           index = row[0]
     *
     *       # If cursor.rowcount is more than 0, 0+ ID's available
     *       if (cursor.rowcount > 0) :
     *           index += 1 # Adds +1 to existing ID
    */

    $qry = "SELECT * FROM products"; //query
    $i = -1; // index

    $result = mysqli_query($link, $qry); // executes query

    while($row = $result->fetch_array())
        $i = $row['id']; // sets index to id

   $i += 1; // adds one to index

   // index is a UNIQUE ID (even when table is empty)
   # echo "<strong>$i</strong>";

    $qry = $link->prepare("INSERT INTO products(id, name, category, price, calories, allergies, availability, img) VALUES(?,?,?,?,?,?,?,?)");

    //Binds the following to VALUES
    //id, name, category, price, calories, allergies, availability, img
    $qry->bind_param("issdisis", $i, $productName, $productCategory, $productPrice, $productCalories, $allergies, $productAvailability, $uploadPath);

    $qry->execute();
    $qry->close();

    header( "refresh:5;url=index.php" );
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StaffArea</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col"><p>Product Name: <?php echo $productName ?></p></div>
        </div>
        <div class="row">
            <div class="col"><p>Product Category: <?php echo $productCategory ?></p></div>
        </div>
        <div class="row">
            <div class="col"><p>Product Price: <?php echo $productPrice ?></p></div>
        </div>
        <div class="row">
            <div class="col"><p>Product Calories: <?php echo $productCalories ?></p></div>
        </div>
        <div class="row">
            <div class="col"><p>Product Availability: <?php echo $productAvailability ?></p></div>
        </div>
        <div class="row">
            <div class="col">
                <p>
                    <?php
                    if(isset($_POST['allergies']))
                    echo "Product Allergies: <br>$allergies";
                    ?>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>Product Image:</p>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <?php if($uploaded) { ?>
                <img src="<?php echo $uploadPath; ?>" height="250px"></img>
                <?php } else { echo $fileStatus; } ?>
            </div>
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/addProducts.js"></script>
</body>

</html>