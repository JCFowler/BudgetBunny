/*************************** SystematicTransactionForm ***********************************/


/*
 * Creates a new withdraw row to the withdrawTable, with a 
 * unique row name and remove button id.
 */
$('.add-systematic').click(function(){	
	var type = $(this).attr('id').replace('add', '');
	
	$('#' + type + 'Table').show();

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
	let table = $('#' + type + 'Table');
	var type = $(this).attr('type');
	
	const id = $(this).attr('id').replace(type + 'RemoveButton', '');
	
	const row = $('#' + type + 'TableRow' + id);

	row.find('[name="name"]').val('');
	row.find('[name="cost"]').val('');
	row.find('[name="startDate"]').val('');
	row.hide();
	
	let hide = true;
	table.find('tr').each(function(){
		if($(this).find('th') == undefined && !$(this).is(':disabled'))
			hide = false;
	});
	if(hide)
		table.hide();
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

/*
 * Verifies the value contained by element name has a length greater than one, and toggles
 * highlight class accordingly.
 */
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

/*
 * Verifies the value contained by element cost is greater than zero and less than db_max_val, and toggles
 * highlight class accordingly.
 */
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

/*
 * Verifies the date expressed by element startDate is today or in the future, and toggles
 * highlight class accordingly.
 */
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

/*
 * Adds highlight class to element which indicates error.
 */
function turnOnHighLight(element)
{
	if(!element.hasClass('highlight'))
		element.toggleClass('highlight');
}

/*
 * Removes highlight class from element which indicates no error.
 */
function turnOffHighLight(element)
{
	if(element.hasClass('highlight'))
		element.toggleClass('highlight');
}
