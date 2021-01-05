<?php session_start();
		if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { 
	?>
	
<?php
    include "assets/php/dbconnection.php";
	
    #Select unique categories
    $query = "select distinct category from products order by category";
    $results = mysqli_query($conn, $query);
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url('assets/img/menuimage.jpg'); background-size: 100%; background-repeat: no-repeat; background-color: #070A09;">
    <div>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean">
            <div class="container"><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                    id="navcol-1"><img src="assets/img/logo.png">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="#"><strong>ACCOUNT</strong></a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="basket.php"><strong>YOUR TRAY</strong></a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="assets/php/logout.php"><strong>LOG OUT</strong></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="container" id="company-greeting" style="border-radius: 15px; border: solid 2px black; margin-top: 10px; background-color: rgb(255, 255, 255);">
        <div class="row">
            <div class="col">
                <p style="font-size:22px;">Welcome <?php echo $_SESSION['firstName']; ?>, feeling hungry?</p>
            </div>
        </div>
    </div>
    <div class="container" id="company" style="border-radius: 15px; background-color: white;">
        <div class="row">
            <div class="col" id="company-details">
                <img src="assets/img/grads-cafe.jpg" id="img-1" class="center" style="width:289px;">
                <p class="text-center" style="font-size:20px; margin-top: -30px;">UWTSD Waterfront Campus Cafe<br>
				<a href="#" style="font-size: 15px;">Not your location?</a></p>
                <div class="row">
                    <div class="col text-center font-weight-bold">
                        Monday <br>
                        Tuesday <br>
                        Wednesday <br>
                        Thursday <br>
                        Friday <br>
                        Saturday <br>
                        Sunday <br>
                    </div>
                    <div class="col text-center font-italic">
                        8AM - 6PM
                        <br>8AM - 6PM
                        <br>8AM - 6PM
                        <br>8AM - 6PM
                        <br>8AM - 5PM
                        <br>10AM - 4PM
                        <br>Closed
                    </div>
                </div>
                <div class="row font-weight-bold company-tel">
                    <div class="col text-center">
                        <p>Tel. Number:&nbsp;</p>
                    </div>
                    <div class="col">
                        <p class="text-center">01792 481000</p><br>
                    </div>
                </div>
            </div>
            <div class="clearfix center" id="divider"></div>
            <div class="col" id="company-categories">
                <img id="menu" src="assets/img/menu.png" align="center">
                <?php
                    while($row = mysqli_fetch_array($results))
                    {
                        echo "<div class='row'>";
                            echo "<div class='col text-center'>";
	                                echo "<button class='btn btn-outline-dark font-weight-bold categories' data-toggle='collapse' data-target='#row-".strtolower($row[0])."'>".strtoupper($row[0])."</button>"; #prints each table
	                                
									$queryTwo = "SELECT * FROM products WHERE category = '".$row[0]."'";
									$resultsTwo = mysqli_query($conn, $queryTwo);
									
									echo "<div id='row-".strtolower($row[0])."' class='row collapse mt-3'>";
									while($row2 = mysqli_fetch_array($resultsTwo))
									{
										echo "<div id='col-productName'>".$row2['name']."</div>";
										echo "<div id='col-productPrice'>&#163;".$row2['price']."</div>";
										?>
										
										<form action='assets/php/addtobasket.php' method='post'>
											<input type="hidden" name="id" value="<?php echo $row2['id']; ?>"/>
											<input type="submit" value="Add to Tray">
										</form>
										<?php
									}
									echo "</div>";
									echo "<br>";
									
                            echo "</div>";
                        echo "</div>";
                    }
                ?>
				
				<?php
					}
					else header("Location: ../");
				?>
            </div>
        </div>
    </div>
    <div class="footer-basic">
        <footer>
            <div class="social"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="#">Home</a></li>
                <li class="list-inline-item"><a href="#">Services</a></li>
                <li class="list-inline-item"><a href="#">About</a></li>
                <li class="list-inline-item"><a href="#">Terms</a></li>
                <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
            </ul>
            <p class="copyright">FoodTray © 2020</p>
        </footer>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>