$('.tranRemove').click(function(){
			var id = $(this).attr('id');
			$('#row' + id).remove();
			const x = {transactionId : id};
			ajaxCall(x , '/BudgetBunny/transaction')
});


function ajaxCall(data, destUrl)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	  });
}

$('select').change(function(){
	var textselect = $(this).val();
	   $('#filterrow').find('tr').each(function(){
		  const catname = $(this).find('#catname').text();
		  if(catname != textselect){
			  $(this).hide();
		if(textselect == "All"){
			$('#filtertable tr').show();
		}
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
	$('#filtertable').find('tr').each(function() {
		const date = $(this).find('#trandate').text();
		if (date1 <= date && date <= date2) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}
);