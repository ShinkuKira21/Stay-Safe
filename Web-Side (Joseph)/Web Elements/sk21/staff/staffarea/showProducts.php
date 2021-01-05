<?php session_start();
if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { ?>

<?php include "../conn.php";
    /* FIND UNIQUE CATEGORIES IN PRODUCTS TABLE */
    $qry = "SELECT DISTINCT category FROM products ORDER BY category";
    $result = mysqli_query($link, $qry);

    $availableCategories = []; # array holds unique categories

    while($row = $result->fetch_array())
        array_push($availableCategories, $row["category"]); # append unique categories


    # Uncomment to Test (Theory Above)
    # echo "Count: ".count($availableCategories)."<br>";
    # echo "All Categories: ".print_r($availableCategories)."<br>";
    # echo "1 Category: ".$availableCategories[1];
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Products</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>

<!-- Calls the JS ShowCategory function to hide all containers on initial load -->
<body onload="LoadCategories()">
    <?php include "navbar.php"; ?>
    <?php
        # Loop through the available categories
        for($i = 0; $i < count($availableCategories); $i++)
        {
            //QUERY all WHERE category is equal to a Available Category
            $qry = "SELECT * FROM products WHERE category = '$availableCategories[$i]'";
            $result = mysqli_query($link, $qry);
     ?>
        <!-- Creates a unique ID and calls ShowCategory(elementID) if clicked -->
        <div id="con-<?php echo strtolower($availableCategories[$i]) ?>" class="container border text-center categories" onclick="ShowCategory('con-<?php echo strtolower($availableCategories[$i]) ?>')">
        <p><strong><?php echo $availableCategories[$i] ?></strong></p>
     <?php
            //HEADER ROWS
            echo "<div id='row-titles' class='row'>";
                echo "<div id='col-titles-name' class='col'>Name</div>";
                echo "<div id='col-titles-calories' class='col'>Calories</div>";
                echo "<div id='col-titles-allergies' class='col'>Allergies</div>";
                echo "<div id='col-titles-availability' class='col'>Availability</div>";
                echo "<div id='col-titles-price' class='col'>Price</div>";
                echo "<div id='col-titles-img' class='col'>Image</div>";
            echo "</div>";
            
            while($row = $result->fetch_array())
            {
                //COLUMN HOUSE STYLE: col-0-Flat
                //EXPLODE ALLOWS TO SELECTION BEFORE A CERTAIN CHARACTER
                echo "<div id='row-product-".$row['id']."' class='row'>";
                
                    //DISPLAY NAME ROWS
                    echo "<div id='col-".$row['id']."-productname' class='col'>";
                    echo $row['name'];
                    echo "</div>";

                    //DISPLAY CALORIES ROWS
                    echo "<div id='col-".$row['id']."-calories' class='col'>";
                    echo $row['calories']." KCAL";
                    echo "</div>";

                    //DISPLAY ALLERGIES ROWS
                    echo "<div id='col-".$row['id']."-allergies' class='col'>";
                    

                    //Seperates a string of allergies into an array.
                    $seperationAllergies = explode(', ', $row['allergies']);

                    #Allergy Debugging
                    #echo $seperationAllergies[0]; // Works (even with blank fields)

                    //The fix: I reused var i, changed index to var j.
                    for($j = 0; $j < count($seperationAllergies); $j++)
                    {
                        // iterates 
                        echo $seperationAllergies[$j];

                        //only adds a new line if it's not the last item
                        if($j + 1 != count($seperationAllergies))
                            echo "<br>";
                    }
                    echo "</div>";

                    //DISPLAY AVAILABILITY ROWS
                    echo "<div id='col-".$row['id']."-availability' class='col'>";
                    echo $row['availability'];
                    echo "</div>";

                    //DISPLAY PRICE ROWS
                    echo "<div id='col-".$row['id']."-price' class='col'>";
                    echo "&#163;".$row['price'];
                    echo "</div>";

                    //DISPLAY IMAGE ROWS
                    echo "<div id='col-".$row['id']."-img' class='col'>";

                    // checks if image is set
                    if($row['img'] != "N/A")
                        echo "<img src='".$row['img']."' width='65px' height='65px'/>";

                    echo "</div>";
                    
                echo "</div>";

                 echo "<div id='row-cTitle-".$row['id']."' class='row'>";
                    echo "<div id='col-cTitle-".$row['id']."' class='col text-center'>";
                        echo "Product Control:";
                    echo "</div>";
                echo "</div>";

                echo "<div id='row-control-".$row['id']."' class='row' style='padding-top: 15px; padding-bottom: 15px;'>";
                ?>
                            <!-- SECURITY RESEARCH -->
                    <div id="col-edit-<?php echo $row['id'] ?>" class="col" align="center">
                        <form action="<?php echo htmlentities($_SERVER['PHP_SELF']); ?>" method="post">
                            <input type="hidden" name="id" value="<?php echo $row['id'] ?>"/>
                            <input type="submit" name="sub" value="Edit"/>
                        </form>
                    </div>
                    <div id="col-delete-<?php echo $row['id'] ?>" class="col" align="center">
                        <form action='<?php echo htmlentities($_SERVER['PHP_SELF']); ?>' method='post'>
                            <input type="hidden" name="id" value="<?php echo $row['id'] ?>" />
                            <input type="submit" name="sub" value="Delete" />
                        </form>
                    </div>     
                <?php
                echo "</div>";
            }
            echo "</div>";
        }
    ?>

    <?php 
        //Display Edit Form on same page :D
        if(isset($_POST['sub']))
        {
            //Connect to database, query products that match ID
            $qry = $link->prepare("SELECT * FROM products WHERE id = ? LIMIT 1");
            $qry->bind_param("i", $_POST['id']); //stops sql injection
            $qry->execute();
            $result = $qry->get_result();
            $qry->close();
            $row = $result->fetch_array();

            if($_POST['sub'] == "Edit")
            {
                ?>
                    <div id="edit-form" class="container">
                        <form action="editProduct.php" method="post" enctype="multipart/form-data">
                            <label>Type -1 on Product Availability for infinite or type null Product Allergies to clear.</label>
                            <label>Any fields empty will stay as same value</label>
                            <br><br>
                            <label>Product ID: <?php echo $row['id'] ?><input type="hidden" name="id" value="<?php echo $row['id'] ?>"/></label>
                            <br>
                            <label>Product Name: <input name="name" placeholder="<?php echo $row['name'] ?>" /></label>
                            <br>
                            <label>Product Calories: <input type="number" name="calories" min="1" step="1" placeholder="<?php echo $row['calories'] ?>" /></label>
                            <br>
                            <label>Product Availability: <input type="number" name="availability" placeholder="<?php echo $row['availability'] ?>" /></label>
                            <br>
                            <label>Product Allergies: <input name="allergies" placeholder="<?php echo $row['allergies'] ?>" /></label>
                            <br>
                            <label>Product Price: <input id="editPrice" name="price" step="0.01" placeholder="<?php echo $row['price'] ?>" onkeyup="InputDiffValue('editPrice', 'editPriceDifference', <?php echo $row['price'] ?>)"/></label>
                            <br>
                            <!-- Shows Price Difference (Dynamic Span Container)-->
                            <label>Price Difference: &#163;<span id="editPriceDifference">0.00</span></input>
                            <br>
                            <?php if($row['img'] != "N/A") { ?>
                            <label>Image:</label>
                            <br> 
                            <img src="<?php echo $row['img'] ?>" width="100px"/>
                            <br><br>
                            <?php } ?>
                            <input type="file" accept="image/*" capture="filesystem" name="img">
                            <br>
                            <br>
                            <button onclick="HideContainer('edit-form')">Cancel</button>
                            <input type="submit" value="Make Changes">
                        </form>
                    </div>
                <?php
            }
            else
            {
                ?>
                    <div id="delete-form" class="container">
                        <form action="deleteProduct.php" method="post">
                            <label>Product ID: <?php echo $row['id'] ?></label><br>
                            <label>Product Name: <?php echo $row['name'] ?></label>
                            <br>
                            <input type="hidden" name="id" value="<?php echo $row['id']; ?>"
                            <button onclick="HideContainer('delete-form')">Cancel</button>
                            <input type="submit" value="Confirm Delete">
                        </form>
                    </div>
                <?php
            }
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
