<?php session_start();
		if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { 
	?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Your Tray</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/basket.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Navigation-Clean.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-md navigation-clean">
        <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1"><img src="assets/img/logo.png">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link" href="#"><strong>ACCOUNT</strong></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="menu.php"><strong>OUR MENU</strong></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="assets/php/logout.php"><strong>LOG OUT</strong></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container text-center basket" style="padding-bottom: 10px;">
        <div class="row">
            <div class="col" style="margin-bottom: 2%;">
                <h1 style="margin-top: 10px;font-size: 32px;">Your Tray</h1>
            </div>
            <div class="col" style="margin-bottom:2%;">
                <h1 class="text-center" style="margin-top: 10px;font-size: 32px;">Choose Pickup Time:&nbsp;</h1>
            </div>
        </div>
        <div class="row">
            <div class="col"><div class="row">
    <div class="col text-center font-weight-bold" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
        <?php
			if(count($_SESSION['trayID']) == 0) echo "No Items<br>";

			for($i = 0; $i < count($_SESSION['trayID']); $i++)
				echo $_SESSION['trayName'][$i]."<br><br>";
			
			echo "Total:<br>";
		?>
    </div>
    <div class="col text-center font-weight-bold">
		<?php 
			$total = 0;
			if(count($_SESSION['trayID']) == 0) echo "<br>";
			for($i = 0; $i < count($_SESSION['trayID']); $i++)
			{
				echo "&#163;".$_SESSION['trayPrice'][$i]."<br><br>";
				$total = number_format($total + $_SESSION['trayPrice'][$i], 2);
			}
			
			echo "&#163;".$total."<br>";
		?>
    </div>
</div></div>
	
            <div class="col">
				<form action="assets/php/purchase.php" method="post">
					<input id="timeinput" type="time" name="pickuptime" class="form-control"/>
					<input type="submit" class="btn btn-primary" id="purchase" value="Place Order"/>
				</form>
			</div>
        </div>
    </div>

	<?php
		}
		else header("Location: ../");
	?>
	
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
    <script src="assets/js/jquery.min.js"/>
    <script src="assets/bootstrap/js/bootstrap.min.js"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"/>

</body>

</html>