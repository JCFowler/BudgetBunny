/**
 * 
 */


/*************************** Generic JS ***********************************/


function ajaxCall(data, destUrl)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	    success: function (html) {
	    	
	      //TODO: finish ajax;
	    }
	  });
}

/*************************** SystematicTransactionForm ***********************************/

var withdrawRowCount = 1;
var depositRowCount = 1;

$('#addwithdraw').click(function(){
	var hiddenRow = $("#withdrawTableRow0").clone(true);
	var newName = 'withdrawTableRow' + withdrawRowCount;
	var newRemoveId = 'withdrawRemoveButton' + withdrawRowCount++;

	hiddenRow.find('#withdrawRemoveButton').attr('id', newRemoveId);
	hiddenRow.attr('id', newName);
	$("#withdrawTableBody")[0].append(hiddenRow[0])
	$('#' + newName).show();
});

$('#adddeposit').click(function(){
	var hiddenRow = $("#depositTableRow0").clone(true);
	var newName = 'depositTableRow' + depositRowCount;
	var newRemoveId = 'depositRemoveButton' + depositRowCount++;
	
	hiddenRow.find('#depositRemoveButton').attr('id', newRemoveId);
	hiddenRow.attr('id', newName);
	$("#depositTableBody")[0].append(hiddenRow[0])
	$('#' + newName).show();
});

$('.withdrawRemoveButton').click(function()
{
	let id = $(this).attr('id').replace('withdrawRemoveButton', '');
	let row = $('#withdrawTableRow' + id);

	//TODO
	row.find('[name="name"]').val('');
	row.find('[name="cost"]').val('');
	row.find('[name="startDate"]').val('');
	row.hide();
});

$('.depositRemoveButton').click(function()
{
	let id = $(this).attr('id').replace('depositRemoveButton', '');
	let row = $('#depositTableRow' + id);
	alert(row.attr('id'));
	
	//TODO
	row.find('[name="name"]').val('');
	row.find('[name="cost"]').val('');
	row.find('[name="startDate"]').val('');
	row.hide();
});

function submitSystematicWithdraws()
{
	return submitSystematicTransactions("-", "withdraw")
}
function submitSystematicDeposits()
{
	return submitSystematicTransactions("", "deposit");
}


function submitSystematicTransactions(numberPrefix, type)
{
	count = 1;
	arrCount = 0;
	data = {};
	validData = true;
	while((nextIncome = $('#' + type + 'TableRow' + count++)).length > 0)
	{
		let name = nextIncome.find('[name="name"]');
		let cost = nextIncome.find('[name="cost"]');
		let period = nextIncome.find('[name="period"]');
		let startDate = nextIncome.find('[name="startDate"]');
		
		if(name.val().length == 0 && cost.val().length == 0 && startDate.val().length == 0)
			continue;
		
		validData = validData & verifyFutureDate(startDate) & verifyIncomeValue(cost) & verifyNonEmpty(name);
		
		if(validData)
		{
			data[arrCount++] = {
					name : name.val(),
					cost : numberPrefix + cost.val(),
					period : period.val(),
					startDate : startDate.val()
					
			}
		}
	}
	if(validData)
	{
		$('.errorMsg').hide();
		return data;
	}
	else
	{
		$('.errorMsg').show();
		return null;
	}
}

function verifyNonEmpty(name)
{
	if(name.val().length == 0)
	{
		if(!name.hasClass('highlight'))
			name.toggleClass('highlight');
		return false;
	}
	else if(name.hasClass('highlight'))
		name.toggleClass('highlight');
	return true;
}

function verifyIncomeValue(cost)
{
	if(isNaN(cost.val()) || (cost.val().length == 0 ||cost.val() > 999999999.99 || cost.val() < 0))
	{
		if(!cost.hasClass('highlight'))
			cost.toggleClass('highlight');
		return false;
	}
	else if(cost.hasClass('highlight'))
		cost.toggleClass('highlight');
	return true;
}

function verifyFutureDate(startDate)
{
	if(startDate.val().length == 0 || Date.parse(startDate.val())-(Date.parse(new Date()) - 1000*60*60*24*1)<0)
	{
		if(!startDate.hasClass('highlight'))
			startDate.toggleClass('highlight');
		return false;
	}
	else if(startDate.hasClass('highlight'))
		startDate.toggleClass('highlight');
	
	return true;
}




/*************************** BudgetForm ***********************************/

var categoryCount = 1;

$('#addCategory').click(function(){
	var hiddenRow = $("#categoryTableRow0").clone(true);
	var newName = 'categoryTableRow' + categoryCount;
	hiddenRow.attr('id', newName);
	var newDollarTypeId = hiddenRow.find('#dollarType').attr('id') + categoryCount;
	var newCheckBoxId = hiddenRow.find('#percentage').attr('id') + categoryCount;
	var newPercentTypeId = hiddenRow.find('#percentType').attr('id') + categoryCount;
	var removeButtonId = hiddenRow.find('#removeButton').attr('id') + categoryCount++;
	
	
	hiddenRow.find('#percentType').attr('id', newPercentTypeId);
	hiddenRow.find('#dollarType').attr('id', newDollarTypeId);
	hiddenRow.find('#percentage').attr('id', newCheckBoxId);
	hiddenRow.find('#removeButton').attr('id', removeButtonId);
	
	$("#categoryTableBody")[0].append(hiddenRow[0]);
	$('#' + newName).show();
});

$('.percentage').click(function(){
	let id = $(this).attr('id').replace('percentage', '');
	$('#amountType' + id).text('%');
	if($(this).is(':checked'))
	{
		$('#percentType' + id).attr('style', 'visibility: visible');
		$('#dollarType' + id).attr('style', 'visibility: hidden')
	}
	else
	{
		$('#percentType' + id).attr('style', 'visibility: hidden');
		$('#dollarType' + id).attr('style', 'visibility: visible');
	}

});

$('.removeButton').click(function()
{
	let id = $(this).attr('id').replace('removeButton', '');
	let row = $('#categoryTableRow' + id);
	alert(row.attr('id'));
	
	row.find('[name="name"]').val('');
	row.find('[name="Amount"]').val('');
	row.hide();
});

function submitBudgetCategories()
{
	count = 1;
	arrCount = 0;
	data = {};
	validData = true;
	while((nextIncome = $('#categoryTableRow' + count++)).length > 0)
	{
		let category = nextIncome.find('#category');
		let name = nextIncome.find('[name="name"]');
		let percent = nextIncome.find('#percent');
		let amount = nextIncome.find('[name="Amount"]');
		
		if(name.val().length == 0 && amount.val().length == 0)
			continue;
		
		validData = validData & verifyIncomeValue(amount) & verifyNonEmpty(name);
		
		if(validData)
		{
			data[arrCount++] = {
					category : category.val(),
					name : name.val(),
					amount : cost.val(),
					percent : percent.val(),
			}
		}
	}
	if(validData)
	{
		$('.errorMsg').hide();
		return data;
	}
	else
	{
		$('.errorMsg').show();
		return null;
	}
}


/*************************** NewUserSetup ***********************************/

$('#submitSetup').click(function()
{
	catData = submitBudgetCategories();
	depData = submitSystematicDeposits();
	withData = submitSystematicWithdraws();
	
	if(depData && withData && catData)
	{
		ajaxCall(catData, '/newBudget');
		ajaxCall(depData, '/processReOcurring');
		ajaxCall(depData, '/processReOcurring');
		//TODO: SwitchPage
	}

});


