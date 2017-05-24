/**
 * 
 */



const name = document.querySelector('#name');
const username = document.querySelector('#username');
const password = document.querySelector('#password');

function validateCreate() {
    var x,y,z;
    var text = '';

    // Get the value of the input field with id="numb"
    x = document.getElementById("username").value;
    y = document.getElementById("password").value;
    z = document.getElementById("name").value;

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
    if(z=="") {
    	document.getElementById('name').style.borderColor = "red";
    	document.getElementById('name').style.backgroundColor = "#e2b5b5";
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

var test = document.getElementById("create");

test.addEventListener("click", (event) => {
	event.preventDefault();
	var result = validateCreate();
	
	if (!result) {
		return;
	}
	$('#error-msg').hide();
	$('success-msg').hide();
	var btn = document.querySelector('#create');
	btn.disabled = true;
	btn.innerHTML = "Creating...";
	
	$.ajax ({
		type: 'post',
		url: '/BudgetBunny/create',
		data: { name: name.value, username: username.value, password: password.value },
		success: () => {
			$('#success-msg').html('You have successfully created a new User!')
			$('#success-msg').show();
			btn.innerHTML = "Created Account!";
		},
		error: () => {
			document.getElementById('username').style.borderColor = "red";
	    	document.getElementById('username').style.backgroundColor = "#e2b5b5";
			$('#error-msg').html('Username is already taken.');
			$('#error-msg').show();
			btn.disabled = false;
			btn.innerHTML = "Create Account";
		}
	});
});