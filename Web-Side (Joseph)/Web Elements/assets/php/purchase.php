<?php session_start();
	if(@$_SESSION['username'] != "" && isset($_SESSION['username'])) { 
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>basket</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../fonts/ionicons.min.css">
    <link rel="stylesheet" href="../css/basket.css">
    <link rel="stylesheet" href="../css/Footer-Basic.css">
    <link rel="stylesheet" href="../css/Navigation-Clean.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-md navigation-clean">
        <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1"><img src="../img/logo.png">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link" href="#"><strong>ACCOUNT</strong></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="../../menu.php"><strong>OUR MENU</strong></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="logout.php"><strong>LOG OUT</strong></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div></div>
    <div class="container">
        <div class="row">
            <div class="col" style="height: 256px;">
                <h1 class="text-center" style="width: 323px;; margin-top: 20%;margin-left: 65%;">Order Accepted&nbsp;</h1>
            </div>
            <div class="col">
                <div><img src="../img/Green-Tick.jpg" style="height: 90px; margin-top: 17%; margin-left:20%;"></div>
            </div>
        </div>
    </div>
	<?php
		}
		else header("Location: ../../");
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
    <script src="../js/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
</body>

</html>