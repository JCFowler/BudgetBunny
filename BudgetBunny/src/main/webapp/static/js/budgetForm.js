/*************************** BudgetForm ***********************************/

var categoryCount = 1;

/*
 * Adds Table row to categoryTable using row with id categoryTableRow0 as a template.
 */
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

/*
 * Swaps dollar sign and percent sign also changes placeholder to correspond to value.
 */
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

/*
 * Removes a table row from CategoryTable.
 */
$('.removeButton').click(function()
{
	const id = $(this).attr('id').replace('removeButton', '');
	const row = $('#categoryTableRow' + id);
	
	row.find('[name="name"]').val('');
	row.find('[name="Amount"]').val('');
	row.hide();
});

/*
 * Verifies collects and if verification is valid returns json data object.
 */
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

/*
 * Turns hides and shows error message dicatated by validData (true=off/false=on).
 */
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
