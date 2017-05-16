/**
 * 
 */


/*************************** Generic JS ***********************************/

/*
 * Send an ajax call.
 * 
 * @param data - Intended to be a Json object.
 * @param url - url for ajax call;
 */
function ajaxCall(data, destUrl)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	    success: function (html) {
	    	
	      //TODO: finish ajax;
	    	alert("Ajax Success: \n\t" + html);
	    }
	  });
}

/*************************** SystematicTransactionForm ***********************************/

var withdrawRowCount = 1;
var depositRowCount = 1;


/*
 * Creates a new withdraw row to the withdrawTable, with a 
 * unique row name and remove button id.
 */
$('.add-systematic').click(function(){
	var type = $(this).attr('id').replace('add', '');

	var hiddenRow = $("#original" + type + "TableRow0");
	var newId = parseInt(hiddenRow.attr('count')) + 1;

	hiddenRow.attr('count', newId);
	var newRow = hiddenRow.clone(true);
	
	var newName = type + 'TableRow' + newId;
	var newRemoveId = type + 'RemoveButton' + newId;

	newRow.find('#' + type + 'RemoveButton').attr('id', newRemoveId);
	newRow.attr('id', newName);
	$("#" + type + "TableBody")[0].append(newRow[0])
	$('#' + newName).show();
});


/*
 * Removes rows from a given table, the type attribute indicates which table.
 */
$('.RemoveButton').click(function()
{
	var type = $(this).attr('type');
	
	const id = $(this).attr('id').replace(type + 'RemoveButton', '');
	alert(type);
	alert(id);
	
	const row = $('#' + type + 'TableRow' + id);

	row.find('[name="name"]').val('');
	row.find('[name="cost"]').val('');
	row.find('[name="startDate"]').val('');
	row.hide();
});


/*
 * Submits negative values systematic transactions.
 */
function submitSystematicWithdraws()
{
	return submitSystematicTransactions("-", "withdraw")
}


/*
 * Submits positive value systematic transactions.
 */
function submitSystematicDeposits()
{
	return submitSystematicTransactions("", "deposit");
}


/*
 * Submits systematic transactions, to invert the value submit a negative
 * sign as the numberPrefix value.
 */
function submitSystematicTransactions(numberPrefix, type)
{
	if(numberPrefix !== '-')
		numberPrefix = '';		
	
	let tableRowCount = 1;
	let dataCount = 0;
	let data = {};
	let validData = true;
	while((nextIncome = $('#' + type + 'TableRow' + tableRowCount++)).length > 0)
	{
		const name = nextIncome.find('[name="name"]');
		const cost = nextIncome.find('[name="cost"]');
		const period = nextIncome.find('[name="period"]');
		const startDate = nextIncome.find('[name="startDate"]');
		
		if(name.val().length == 0 && cost.val().length == 0 && startDate.val().length == 0)
			continue;
		
		const verifyStartDate = verifyNonEmpty(name);
		const verifyCost = verifyIncomeValue(cost);
		const verifyName = verifyFutureDate(startDate);
		validData = validData && verifyStartDate && verifyCost && verifyName;
		
		if(validData)
		{
			data[dataCount++] = {
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
	
	var newDollarTypeId = 'dollarType' + categoryCount;
	var newCheckBoxId = 'percentage' + categoryCount;
	var newPercentTypeId = 'percentType' + categoryCount;
	var removeButtonId = 'removeButton' + categoryCount;
	var amountId = 'amount' + categoryCount++;
	
	hiddenRow.find('#amount').attr('id', amountId);
	hiddenRow.find('#percentType').attr('id', newPercentTypeId);
	hiddenRow.find('#dollarType').attr('id', newDollarTypeId);
	hiddenRow.find('#percentage').attr('id', newCheckBoxId);
	hiddenRow.find('#removeButton').attr('id', removeButtonId);
	
	$("#categoryTableBody")[0].append(hiddenRow[0]);
	$('#' + newName).show();
});

$('.percentage').click(function(){
	const id = $(this).attr('id').replace('percentage', '');
	if($(this).is(':checked'))
	{
		$('#percentType' + id).attr('style', 'visibility: visible');
		$('#dollarType' + id).attr('style', 'visibility: hidden');
		$('#amount' + id).attr('placeholder', 'Percent');
	}
	else
	{
		$('#percentType' + id).attr('style', 'visibility: hidden');
		$('#dollarType' + id).attr('style', 'visibility: visible');
		$('#amount' + id).attr('placeholder', 'Amount');
	}

});

$('.removeButton').click(function()
{
	const id = $(this).attr('id').replace('removeButton', '');
	const row = $('#categoryTableRow' + id);
	
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
		const category = nextIncome.find('#category');
		const name = nextIncome.find('[name="name"]');
		const percent = nextIncome.find('#percent');
		const amount = nextIncome.find('[name="Amount"]');
		
		if(name.val().length == 0 && amount.val().length == 0)
			continue;
		
		const verifyName = verifyNonEmpty(name);
		const verifyCost = verifyIncomeValue(amount);
		validData = validData && verifyCost && verifyName;
		
		if(validData)
		{
			data[arrCount++] = {
					category : category.val(),
					name : name.val(),
					amount : amount.val(),
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
	const depData = submitSystematicDeposits();
	const withData = submitSystematicWithdraws();
	
	const catData = submitBudgetCategories();
	
	if(depData && withData && catData)
	{
		ajaxCall(catData, '/newBudget');
		ajaxCall(depData, '/processReOcurring');
		ajaxCall(depData, '/processReOcurring');
		//TODO: SwitchPage
	}

});


