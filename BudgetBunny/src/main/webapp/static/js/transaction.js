
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});

$('body').on('click','#myBtn',function(){
	$('#myModal').load('newTransaction.jsp');
});


	
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



$('select').change(function(){
	var textselect = $(this).val();
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

$('input').change(function() {
	var date1 = $('#startDate').val();
	var date2 = $('#endDate').val();
	if (date2 == ""){
		date2 = "3017-05-22"
	}
	if (date1 == ""){
		date2 = "1017-05-22"
	}
	/*var date1 = $('filtertable1').find('#startDate').text();
	var date2 = $('filtertable1').find('#endDate').text();*/
	$('#filtertable').find('tr').each(function() {
		let date = $(this).find('#trandate').text();
		if (date1 < date && date < date2) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}

);