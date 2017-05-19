
$(document).ready(function(){
    $("#myBtn").click(function(){
        $("#myModal").modal();
    });
});

$('body').on('click','#myBtn',function(){
	$('#myModal').load('newTransaction.jsp');
});

$('.datepicker').datepicker({
    format: 'mm/dd/yyyy',
    startDate: '-3d'
});

