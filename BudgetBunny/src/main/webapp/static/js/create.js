/**
 * 
 */

//onClick="validateLogin();"


function validateCreate() {
    var x,y,z, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("username").value;
    y = document.getElementById("password").value;
    z = document.getElementById("name").value;


    // If x is Not a Number or less than one or greater than 10
    if (x=="" && y=="" && z=="") {
    	document.getElementById('username').style.borderColor = "red";
    	document.getElementById('password').style.borderColor = "red";
    	document.getElementById('name').style.borderColor = "red";
        text = "Please fill in the blanks.";
    } else {
        text = "Please fill in the blanks.";
    }
    document.getElementById("valid").innerHTML = text;
}

var test = document.getElementById("create");

test.addEventListener("click", validateCreate);