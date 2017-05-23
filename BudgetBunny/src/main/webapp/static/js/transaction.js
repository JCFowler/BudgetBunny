
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});

$('body').on('click','#myBtn',function(){
	$('#myModal').load('newTransaction.jsp');
});


$(document).ready(function(){
	$
});

$('.tranRemove').click(function(){
			var id = $(this).attr('id');
			$('#row' + id).hide();
			let x = {transactionId : id}
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
