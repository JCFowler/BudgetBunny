/**
 * 
 */

//onClick="validateLogin();"

const username = document.querySelector('#username');
const password = document.querySelector('#password');

function validateLogin() {
    var x,y;
    var text = '';

    // Get the value of the input field with id="numb"
    x = document.getElementById("username").value;
    y = document.getElementById("password").value;


    // If x is Not a Number or less than one or greater than 10
    if (x=="") {
    	document.getElementById('username').style.borderColor = "red";
    	document.getElementById('username').style.backgroundColor = "#e2b5b5";
        text = "Please fill in the blanks";
        document.getElementById("error-msg").innerHTML = text;
        $('#error-msg').show();
    } 
    if(y=="") {
    	document.getElementById('password').style.borderColor = "red";
    	document.getElementById('password').style.backgroundColor = "#e2b5b5";
    	text = "Please fill in the blanks";
        document.getElementById("error-msg").innerHTML = text;
        $('#error-msg').show();
    }
    
    if (text === '') {
    	return true;
    } else {
    	return false;
    }
}

var test = document.getElementById("login");

test.addEventListener("click", (event) => {
	event.preventDefault();
	const inputIsValid = validateLogin();
	
	if (!inputIsValid) {
		return;
	}
	$('#error-msg').hide();
	var loginBtn = document.querySelector('#login');
	loginBtn.disabled = true;
	loginBtn.innerHTML = "Logging in...";
	$.ajax({
		type: 'post',
		url: '/BudgetBunny/login',
		data: { username: username.value, password: password.value },
		success: () => {
			window.location = '/BudgetBunny/home';
		},
		error: () => {
			$('#error-msg').html('Username or Password was incorrect.');
			$('#error-msg').show();
			loginBtn.disabled = false;
			loginBtn.innerHTML = "Log In";
		}
	});
});