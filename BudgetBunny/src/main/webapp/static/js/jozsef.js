/**
 * 
 */


/*************************** Generic JS ***********************************/

let remainingBudget;
      
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
	    	if(destUrl == '/BudgetBunny/home')
	    		transactionSuccess();
	    	else
	    		alert('Ajax success');
	    }
	  });
}


function transactionSuccess(){
	
	//Get the modal
	var modal = document.getElementById('myModal');

	// Get the button that opens the modal
	var btn = document.getElementById("purchase");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal 
	btn.onclick = function() {
	    modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
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
$('.add-systematic').click(function(){addSystematic($(this))});

function addSystematic(table){
	var type = table.attr('id').replace('add', '');
	
	$('#' + type + 'Table').show();
	$('.submission').show();

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
	return newId;
}


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
		
		if(name.val().length == 0 && cost.val().length == 0)
		{
			turnOffHighLight(name);
			turnOffHighLight(cost);
			continue;
		}
		const verifyCost = verifyIncomeValue(cost);
		const verifyName = verifyNonEmpty(name);
		validData = validData && verifyCost && verifyName;
		
		if(validData)
		{
			let costVal = numberPrefix + cost.val();
			remainingBudget += parseFloat(costVal);
			if(numberPrefix == "")
				totalBudget += parseFloat(costVal);
			
			data[dataCount++] = {
					name : name.val(),
					cost : costVal,
					period : period.val(),
					
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

$('#addCategory').click(addCategory);
$('#categoryTable').click(addCategory);
$('#categoryTableBody').click(function(){
	event.stopPropagation();
});

function addCategory(){
	$('#categoryTable').show();
	$('.submission').show();
	
	var hiddenRow = $("#categoryTableRow0").clone(true);
	var newName = 'categoryTableRow' + categoryCount;
	
	$('#categoryTable').show();
	
	hiddenRow.attr('id', newName);
	
	var newInputId = 'name' + categoryCount;
	var newDollarTypeId = 'dollarType' + categoryCount;
	var newCheckBoxId = 'percentage' + categoryCount;
	var newPercentTypeId = 'percentType' + categoryCount;
	var removeButtonId = 'categoryRemoveButton' + categoryCount;
	var amountId = 'amount' + categoryCount++;
	
	hiddenRow.find('#name').attr('id', newInputId);
	hiddenRow.find('#amount').attr('id', amountId);
	hiddenRow.find('#percentType').attr('id', newPercentTypeId);
	hiddenRow.find('#dollarType').attr('id', newDollarTypeId);
	hiddenRow.find('#percentage').attr('id', newCheckBoxId);
	hiddenRow.find('#categoryRemoveButton').attr('id', removeButtonId);
	
	$("#categoryTableBody")[0].append(hiddenRow[0]);
	hiddenRow.show();
	return categoryCount - 1;
}

function createAndFillCategories(name, budget){
	let id = addCategory();
	let row = $('#categoryTableRow' + id);
	row.find('#name' + id).val(name);
	row.find('#amount' + id).val(budget);
}

function createAndFillSysTrans(row, name, cost){
	row.find('#name').val(name);
	row.find('#cost').val(cost);
}

function createAndFillDeposit(name, cost){
	let id = addSystematic($('#adddeposit'));
	let row = $('#depositTableRow' + id);
	createAndFillSysTrans(row, name, cost);
}

function createAndFillWithdraw(name, cost){
	let id = addSystematic($('#addwithdraw'));
	let row = $('#withdrawTableRow' + id);
	createAndFillSysTrans(row, name, cost);
}

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
 * Removes rows from a given table, the type attribute indicates which table.
 */
$('.RemoveButton').click(function()
{
	var type = $(this).attr('type');
	let table = $('#' + type + 'Table');
	
	const id = $(this).attr('id').replace(type + 'RemoveButton', '');
	
	const row = $('#' + type + 'TableRow' + id);

	row.find('[name="name"]').val('');
	row.find('[name="cost"]').val('');
	row.find('[name="Amount"]').val('');
	row.hide();

	hideEmptyTable(table);
});

function hideEmptyTable(table)
{
	let hide = true;
	table.find('tr').each(function(){
	if(!$(this).hasClass('thead-default') && !$(this).hasClass('header-row') && $(this).css('display') != 'none')
	{	
		hide = false;
	}
	});
	if(hide)
	{
		$('.submission').hide();
		table.hide();
	}
}

function verifyPercent(percent)
{
	if(percent.val() > 0 && percent.val() <= 100)
	{
		turnOffHighLight(percent);
		return true;
	}
	turnOnHighLight(percent);
	return false;
}

function submitBudgetCategories(validData)
{
	let count = 1;
	let arrCount = 0;
	let data = {};
	while((nextIncome = $('#categoryTableRow' + count++)).length > 0)
	{
		const category = nextIncome.find('#category');
		const name = nextIncome.find('[name="name"]');
		const percent = nextIncome.find('#percentage' + (count - 1));
		
		
		let amount = nextIncome.find('[name="Amount"]');
		
		if(name.val().length == 0 && amount.val().length == 0)
		{
			turnOffHighLight(name);
			turnOffHighLight(amount);
			continue;
		}
		
		let varifyAmount;
		amount = calculateAmount(percent.is(':checked'), amount);
	
		const verifyName = verifyNonEmpty(name);
		validData = validData && amount > 0 && verifyName;
		
		if(validData)
		{
			remainingBudget += -1 * parseFloat(amount);

			data[arrCount++] = {
					category : category.val(),
					name : name.val(),
					amount : amount,
					percent : percent.val(),
			}
		}
	}
	return displayErrorMessage(validData, data);
}

function calculateAmount(percent, amount){
	let varifyAmount;
	if(percent)
	{
		$('#amount-err').text('0 < percent <= 100')
		varifyAmount = verifyPercent(amount);
		amount = totalBudget * amount.val()/100;
	}
	else
	{
		$('#amount-err').text('0 < Amount < 999999999.99')
		varifyAmount = verifyIncomeValue(amount);
		amount = amount.val();
	}
	if(varifyAmount)
		return amount;
	return -1;
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
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());
	
	const depData = submitSystematicDeposits(true);
	const withData = submitSystematicWithdraws(depData);
	
	const catData = submitBudgetCategories(withData);

	const setupData = {
			depositData : JSON.stringify(depData),
			withdrawData : JSON.stringify(withData),
			categoryData : JSON.stringify(catData)
	}
	
	if(depData && withData && catData)
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/budgetsetuppage');
});

$('.submit-income').click(function(){	
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());

	const depData = submitSystematicDeposits(true);

	const setupData = {
			depositData : JSON.stringify(depData)
	}
	
	if(depData)
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/incomepage');
});

$('.submit-bill').click(function(){
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());

	const withData = submitSystematicWithdraws(true);
	
	const setupData = {
			withdrawData : JSON.stringify(withData)
		}

	if(withData)
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/billpage');
});

$('.submit-budget').click(function(){
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());
	
	const catData = submitBudgetCategories(true);
	
	const setupData = {
			categoryData : JSON.stringify(catData)
	}

	if(catData)
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/budgetpage');
});

function submitAjaxBudgetCheck(data, url){
		if(remainingBudget < 0)
		{
			alert("Your over budget by " + remainingBudget);
		}
		ajaxCall(data, url);

}


/*************************** BudgetDisplay ***********************************/

$('#home-div').click(function()
{
	if($("#home-div").hasClass("blur-filter"))
		close_div()	
});

$('.category-display').click(function(){
	if(!$("#home-div").hasClass("blur-filter"))
	{
		event.stopPropagation();
		const name = $(this).find('#name');
		const budget = $(this).find('#budget');
		const spent = $(this).find('#spent');
		const id = $(this).find('#id');
		const total = (parseFloat(budget.text().replace('$', '')) - parseFloat(spent.text().replace('$', '')));

		$("#home-div").addClass("blur-filter");
		let popUp = $('#myPopup');
		popUp.find('#name').text(name.text());
		popUp.find('#budget').text(budget.text());
		popUp.find('#spent').text(spent.text());
		popUp.find('#total').text('$' + total.toFixed(2));
		popUp.find('#id').text(id.text());
		
		popUp.show(500);
	}
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

function close_div()
{
	$("#home-div").removeClass("blur-filter");
	$('#myPopup').hide();
	let input = $('#myPopup').find('#amount');
	turnOffHighLight(input);
	input.val("");
}




