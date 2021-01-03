<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>basket</title>
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
    <div class="container text-center basket" style="height: 325px;">
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
    <div class="col text-center font-weight-bold">
        Flat White (Primo) <br>
                        <br>
        Small Breakfast <br>
                        <br>
        Walkers Crisps <br>
                        <br>
        Small Water <br>
                    <br>
        TOTAL       <br>
    </div>
    <div class="col text-center font-weight-bold">
            £2.40
        <br>
        <br>£1.50
        <br>
        <br>£1.69
        <br>
        <br>£0.80
        <br>
        <br>£6.39
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
            <p class="copyright">Company Name © 2017</p>
        </footer>
    </div>
    <script src="assets/js/jquery.min.js"/>
    <script src="assets/bootstrap/js/bootstrap.min.js"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"/>

</body>

</html>