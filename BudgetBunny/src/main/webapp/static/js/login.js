/**
 * 
 */

//onClick="validateLogin();"


function validateLogin() {
    var x,y, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("username").value;
    y = document.getElementById("password").value;


    // If x is Not a Number or less than one or greater than 10
    if (x=="" && y=="") {
    	document.getElementById('username').style.borderColor = "red";
    	document.getElementById('password').style.borderColor = "red";
        text = "Please fill in the blanks.";
    }
    document.getElementById("valid").innerHTML = text;
}

var test = document.getElementById("login");

test.addEventListener("click", validateLogin);