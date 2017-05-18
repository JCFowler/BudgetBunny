/**
 * 
 */

function validateLogin() {
    var x, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("username").value;

    // If x is Not a Number or less than one or greater than 10
    if (x=="no") {
        text = "Input not valid";
    } else {
        text = "Input OK";
    }
    document.getElementById("valid").innerHTML = text;
}