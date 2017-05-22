$(document).ready(function () {
	  $(".nav li").removeClass("active");//this will remove the active class from  
	  var pathname = window.location.pathname
	  switch(pathname ){ 
	  case('/BudgetBunny/home'): $('#home').addClass('active');break;
	  case('/BudgetBunny/transaction'): $('#transaction').addClass('active');break;
	  case('/BudgetBunny/reports'): $('#reports').addClass('active');break;
	  }
});
