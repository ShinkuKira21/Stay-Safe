var currentSelector = true; // Sets currentSelector as true on Page Load

function SwitchForm()
{
    var loginContainer = document.getElementById("loginForm");
    var registerContainer = document.getElementById("registerForm");
	
    if (currentSelector)
    {
        loginContainer.style.display = "none";
		loginContainer.setAttribute("novalidate", "");

        registerContainer.style.display = "block";
		registerContainer.removeAttribute("novalidate");
        currentSelector = false;
		
    }
    else
    {
        registerContainer.style.display = "none";
		registerContainer.setAttribute("novalidate", "");
		
        loginContainer.style.display = "block";
		loginContainer.removeAttribute("novalidate");
        currentSelector = true;
    }
}