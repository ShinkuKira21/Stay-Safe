<?php session_start();
if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { ?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>
	<?php include "navbar.php" ?>
    <div class="container-fluid" id="info">
        <div class="row" id="labels">
            <div class="col offset-xl-0"><label class="col-form-label float-right" for="staffID" style="x;x;margin-top:0px;">Staff ID</label><label class="col-form-label" for="time" style="padding-left:5px;margin-left:20px;">Date/Time</label></div>
        </div>
        <div class="row" id="information" style="margin-top:-5px;">
            <div class="col" style="margin-top:-10px;"><label class="col-form-label float-right" id="staffID" style="margin:12px;margin-bottom:0px;margin-top:0px;">#0</label><label class="col-form-label" id="time">
    <?php 
        date_default_timezone_set('Europe/London');
        
        $currentTimeDate = date('dS M Y H:i:s');

        echo $currentTimeDate;
    ?>
</label></div>
        </div>
    </div>
    <form action="addProduct.php" method="post" enctype="multipart/form-data">
        <div class="container">
            <div class="form-row">
                <div class="col"><label class="col-form-label">Product Name:</label></div>
                <div class="col"><input class="form-control" type="text" name="name" required="true"></div>
            </div>
        </div>
        <div class="container">
            <div class="form-row">
                <div class="col"><label class="col-form-label" for="categorySelect">Category:</label></div>
                <div class="col"><label class="col-form-label" for="priceInput">Price:</label></div>
                <div class="col"><label class="col-form-label" for="calInput">Calories:</label></div>
                <div class="col"><label class="col-form-label" for="productAvailable">Available:</label></div>
                <div class="col"><label class="col-form-label" for="productInf">Infinite?</label></div>
            </div>
        </div>
        <div class="container">
            <div class="form-row">
                <div class="col"><select class="form-control" id="categorySelect" name="category"><optgroup label="Categories"><option value="Food" selected="">Food</option><option value="Drinks">Drink</option><option value="Snack">Snack</option></optgroup></select></div>
                <div
                    class="col"><input class="form-control" type="number" id="priceInput" name="price" step="0.01" required="true"></div>
            <div class="col"><input class="form-control" type="number" id="calInput" name="calories" min="1" step="1" required="true"></div>
            <div class="col"><input class="form-control" type="number" id="productAvailable" name="availability" min="0" step="1" required="true"></div>
            <div class="col"><input type="checkbox" id="productInf" name="inf" onclick="AvailabilityInf()"></div>
        </div>
        </div>
        <div class="container" id="container-allergyChkbox">
            <div class="form-row">
                <div class="col">
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1" onclick="ShowAllergies()"><label class="form-check-label" for="formCheck-1">Does product contain allergies?</label></div>
                </div>
            </div>
        </div>
        <div class="container" id="container-allergies" style="display: none;">
            <div class="form-row">
                <div class="col">
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-2" name="allergies[]" value="Eggs"><label class="form-check-label" for="formCheck-2">Egg Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-3" name="allergies[]" value="Peanuts"><label class="form-check-label" for="formCheck-3">Peanut Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-4" name="allergies[]" value="Soy"><label class="form-check-label" for="formCheck-4">Soy Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-5" name="allergies[]" value="Wheat"><label class="form-check-label" for="formCheck-5">Wheat, Gluten Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-6" name="allergies[]" value="Dairy"><label class="form-check-label" for="formCheck-6">Dairy Products</label></div>
                </div>
                <div class="col">
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-7" name="allergies[]" value="Mustard"><label class="form-check-label" for="formCheck-7">Mustard Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-8" name="allergies[]" value="Treenut"><label class="form-check-label" for="formCheck-8">Treenut Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-9" name="allergies[]" value="Seafood"><label class="form-check-label" for="formCheck-9">Seafood Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-10" name="allergies[]" value="Seeds"><label class="form-check-label" for="formCheck-10">Sesame and Other Seed Products</label></div>
                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-11" onclick="ShowAllergies()"><label class="form-check-label" for="formCheck-11">No Allergies</label></div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="button"><input type="file" accept="image/*" capture="filesystem" name="img"></div>
        </div>
        <div class="container">
            <div class="button"><input class="btn btn-primary" type="submit" value="Add Product"/></div>
        </div>
    </form>

    <?php } 
    else echo "Unauthorised access. Reporting IP address...";
    ?>

    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/addProducts.js"></script>
</body>

</html>