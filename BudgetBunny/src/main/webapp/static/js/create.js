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


    if (x=="" && y=="" && z=="") {
    	document.getElementById('username').style.borderColor = "red";
    	document.getElementById('password').style.borderColor = "red";
    	document.getElementById('name').style.borderColor = "red";
    	document.getElementById('username').style.backgroundColor = "#e2b5b5";
    	document.getElementById('password').style.backgroundColor = "#e2b5b5";
    	document.getElementById('name').style.backgroundColor = "#e2b5b5";
        text = "Please fill in the blanks";}
         if(x==""){
        	document.getElementById('username').style.borderColor = "red";
        	document.getElementById('username').style.backgroundColor = "#e2b5b5";
            text = "Please fill in the blanks";}
         if(y==""){
        	document.getElementById('password').style.borderColor = "red";
        	document.getElementById('password').style.backgroundColor = "#e2b5b5";
            text = "Please fill in the blanks";}
         if(z==""){
        	document.getElementById('name').style.borderColor = "red";
        	document.getElementById('name').style.backgroundColor = "#e2b5b5";
            text = "Please fill in the blanks";}
        else {
        text = "Invalid input. User is already in the system";
    }
    document.getElementById("valid").innerHTML = text;
}

var test = document.getElementById("create");

test.addEventListener("click", validateCreate);