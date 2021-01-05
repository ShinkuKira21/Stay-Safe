<!-- REFERENCE: Navigation E-commerce Navbar Template -->
<link rel="stylesheet" href="css/Navigation-e-commerce.css">
<nav class="navbar navbar-light navbar-expand-md">
	<div class="container"><a class="navbar-brand" href="index.php">Stay Safe</a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
		<div class="collapse navbar-collapse"
			id="navcol-1">
			<form class="form-inline navbar-left" action="search.php" method="post">
				<div class="input-group"><span id="basic-addon1" class="input-group-addon"><i class="fa fa-search"></i></span><input name="search" class="form-control" type="text" placeholder="Search" aria-describedby="basic-addon1"></div>
			</form>
			<ul class="nav navbar-nav ml-auto">
				<li class="nav-item" role="presentation"><a class="nav-link" href="index.php">Add Product</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link" href="showProducts.php">Show Products</a></li>
				<li class="dropdown"><a data-toggle="dropdown" aria-expanded="false" href="#" class="dropdown-toggle nav-link">Extras</a>
					<div role="menu" class="dropdown-menu"><a role="presentation" href="orders.php" class="dropdown-item">Orders</a><a role="presentation" href="../logout.php" class="dropdown-item">Log Out</a>
				</li>
			</ul>
		</div>
	</div>
</nav>