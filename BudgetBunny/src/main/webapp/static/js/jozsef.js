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

/*
 * Returns the calling functions name.
 */
function getFunctionName() {
    var re = /function (.*?)\(/
    var s = getFunctionName.caller.toString();
    var m = re.exec( s )
    return m[1];
}

/*************************** SystematicTransactionForm ***********************************/


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
	
	const row = $('#' + type + 'TableRow' + id);

	row.find('[name="name"]').val('');
	row.find('[name="cost"]').val('');
	row.find('[name="startDate"]').val('');
	row.hide();
});


/*
 * Submits negative values systematic transactions.
 */
function submitSystematicWithdraws(validData)
{	
	return submitSystematicTransactions("-", "withdraw", validData);
}


/*
 * Submits positive value systematic transactions.
 */
function submitSystematicDeposits(validData)
{
	return submitSystematicTransactions("", "deposit", validData);
}


/*
 * Submits systematic transactions, to invert the value submit a negative
 * sign as the numberPrefix value.
 */
function submitSystematicTransactions(numberPrefix, type, validData)
{
	if(numberPrefix !== '-')
		numberPrefix = '';		
	
	let tableRowCount = 1;
	let dataCount = 0;
	const data = {};
	var nextIncome;
	while((nextIncome = $('#' + type + 'TableRow' + tableRowCount++)).length > 0)
	{
		const name = nextIncome.find('[name="name"]');
		const cost = nextIncome.find('[name="cost"]');
		const period = nextIncome.find('[name="period"]');
		const startDate = nextIncome.find('[name="startDate"]');
		
		if(name.val().length == 0 && cost.val().length == 0 && startDate.val().length == 0)
		{
			turnOffHighLight(name);
			turnOffHighLight(cost);
			turnOffHighLight(startDate);			
			continue;
		}
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
	return displayErrorMessage(validData, data);
}


function verifyNonEmpty(name)
{
	if(name.val().length == 0)
	{
		turnOnHighLight(name);
		return false;
	}
	else 
		turnOffHighLight(name);
	return true;
}

function verifyIncomeValue(cost)
{
	if(isNaN(cost.val()) || (cost.val().length == 0 ||cost.val() > 999999999.99 || cost.val() < 0))
	{
		turnOnHighLight(cost);
		return false;
	}
	else 
		turnOffHighLight(cost);
	return true;
}

function verifyFutureDate(startDate)
{
	if(startDate.val().length == 0 || Date.parse(startDate.val())-(Date.parse(new Date()) - 1000*60*60*24*1)<0)
	{
		turnOnHighLight(startDate);
		return false;
	}
	else 
		turnOffHighLight(startDate);
	
	return true;
}

function turnOnHighLight(element)
{
	if(!element.hasClass('highlight'))
		element.toggleClass('highlight');
}

function turnOffHighLight(element)
{
	if(element.hasClass('highlight'))
		element.toggleClass('highlight');
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

function submitBudgetCategories(validData)
{
	let count = 1;
	let arrCount = 0;
	let data = {};
	while((nextIncome = $('#categoryTableRow' + count++)).length > 0)
	{
		const category = nextIncome.find('#category');
		const name = nextIncome.find('[name="name"]');
		const percent = nextIncome.find('#percent');
		const amount = nextIncome.find('[name="Amount"]');
		
		if(name.val().length == 0 && amount.val().length == 0)
		{
			turnOffHighLight(name);
			turnOffHighLight(amount);
			continue;
		}
		
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
	return displayErrorMessage(validData, data);
}

function displayErrorMessage(validData, data)
{
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
	const depData = submitSystematicDeposits(true);
	const withData = submitSystematicWithdraws(depData);
	
	const catData = submitBudgetCategories(withData);
	
	if(depData && withData && catData)
	{
		ajaxCall(catData, '/newBudget');
		ajaxCall(depData, '/processReOcurring');
		ajaxCall(depData, '/processReOcurring');
		//TODO: SwitchPage
	}

});


/*************************** BudgetDisplay ***********************************/

$('.category-display').click(function(){
	const name = $(this).find('#name');
	const budget = $(this).find('#budget');
	const spent = $(this).find('#spent');
	const id = $(this).find('#id');
	const total = (parseFloat(budget.text().replace('$', '')) - parseFloat(spent.text().replace('$', '')));
	
	//$("#home-div > *").addClass("blur-filter");
	$("#home-div").addClass("blur-filter");
	let popUp = $('#myPopup');
	popUp.find('#name').text(name.text());
	popUp.find('#budget').text(budget.text());
	popUp.find('#spent').text(spent.text());
	popUp.find('#total').text('$' + total.toFixed(2));
	popUp.find('#id').text(id.text());
	
	popUp.show(500);
});

function getEggClass(){
	let rand = Math.floor((Math.random() * 8) + 1);
	return 'egg-back-' + rand;
}

$(document).ready(function(){
	$('.category-display').each(function(){
		$(this).addClass(getEggClass());
	
		let budget = $(this).find('#budget');
		budget.text('$' + parseFloat(budget.text().replace('$', '')).toFixed(2));

		let spent = $(this).find('#spent');
		spent.text('$' + parseFloat(spent.text().replace('$', '')).toFixed(2));	
	});
});


$('#purchase').click(function(){
	close_div();
});

function close_div()
{
	$("#home-div").removeClass("blur-filter");
	$('#myPopup').hide();
}








