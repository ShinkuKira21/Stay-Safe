var currentSelector = true; // Sets currentSelector as true on Page Load

function SwitchForm()
{
    var loginContainer = document.getElementById("login-container");
    var registerContainer = document.getElementById("register-container");
    
    if (currentSelector)
    {
        loginContainer.style.display = "none";
        registerContainer.style.display = "block";
        currentSelector = false;
    }
    else
    {
        registerContainer.style.display = "none";
        loginContainer.style.display = "block";
        currentSelector = true;
    }
}