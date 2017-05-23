
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});

$('body').on('click','#myBtn',function(){
	$('#myModal').load('newTransaction.jsp');
});


/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
	
$('#sort').click(function(){
	let popUp = $('#myPopup');
	var transamount = popUp.find("#amount");

var item = transamount.val();

	
	if(verifyIncomeValue(transamount))
	{
		let data = {
				name: popUp.find('#name').text(),
				budget: popUp.find('#budget').text(),
				spent: popUp.find('#spent').text(),
				amount: item,
				id: popUp.find('#id').text(),
		};
		ajaxCall(data, '/BudgetBunny/home');
		close_div();
	}
	
});

$(document).ready(function(){
	$
});

$('.tranRemove').click(function(){
			var id = $(this).attr('id');
			$('#row' + id).hide();
			let x = {transactionId : id};
			ajaxCall(x , '/BudgetBunny/transaction')
});


function ajaxCall(data, destUrl, onSuccess)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	    
	    success: function (html) {
	    	
	    }
	  });
}



$('a').click(function(){
	var textselect = $(this).text();
	    
	   $('#filtertable').find('tr').each(function(){
		  let catname = $(this).find('#catname').text();
		  
		  if(catname != textselect){
			  $(this).hide();
		  }
		  else{
			  $(this).show();
		  }
			  
		  
	   });
	  }

);
