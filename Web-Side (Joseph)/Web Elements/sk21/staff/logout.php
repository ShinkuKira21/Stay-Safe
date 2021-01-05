<!-- Destroys sessions (Log Out Script) -->

<?php
	session_start();
	
	# Reference:
	# [1]“PHP: session_destroy - Manual.” https://www.php.net/manual/en/function.session-destroy.php (accessed Dec. 16, 2020).

	$_SESSION = array();
	
	
	// Clears session from browsers cookies
	if(ini_get("sesson.use_cookies")) {
		$params = session_get_cookie_params();
		setcookie(session_name(), '', time() - 42000,
			$params["path"], $params["domain"],
			$params["secure"], $params["httponly"]
		);
	}
	
	header("Location: index.php");
	session_destroy();
?>