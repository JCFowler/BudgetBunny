/**
 * 
 */


/*************************** Generic JS ***********************************/

let totalBudget;
let remainingBudget;
      
/*
 * Send an ajax call.
 * 
 * @param data - Intended to be a Json object.
 * @param url - url for ajax call;
 */
function ajaxCall(data, destUrl, onSuccess)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	    success: function (html) {
	    	if(destUrl == '/BudgetBunny/home')
	    		transactionSuccess(html);
	    	
	    	if(destUrl == '/BudgetBunny/budgetsetuppage')
	    	{	
	    		window.location='/BudgetBunny/home';
	    		return;
	    	}
	    	$('.total-budget').text($(html).find('.total-budget').text());
	    	
	    	submitSuccess();
	    	close_div();
	    }
	  });
}


function transactionSuccess(html){
	
    var div = $('<div>');
    div.html(html);
    var content = div.find('#eggs');
    	
	console.log(content.html());
	$('#eggs').html(content.html());
	readyHtml();
}

function kirtanPopUp(){
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
	let deletedCount = 0;
	let data = {};
	const deletedData = {};
	var nextIncome;
	while((nextIncome = $('#' + type + 'TableRow' + tableRowCount++)).length > 0)
	{
		const name = nextIncome.find('[name="name"]');
		const cost = nextIncome.find('[name="cost"]');
		const period = nextIncome.find('[name="period"]');
		const id = nextIncome.find('#id').val();
		
		if(name.val().length == 0 && cost.val().length == 0)
		{
			if(id != "0" && id != "")
				deletedData[deletedCount++] = {id : id};
				
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
					id : id
			}
		}
	}
	data = {data : data, deletedList : deletedData};
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

function createAndFillCategories(name, budget, id){
	let rowId = addCategory();
	let row = $('#categoryTableRow' + rowId);
	row.find('#name' + rowId).val(name);
	row.find('#amount' + rowId).val(parseFloat(budget).toFixed(2));
	row.find('#id').val(id);
}

function createAndFillSysTrans(row, name, cost, id){
	row.find('#name').val(name);
	row.find('#cost').val(parseFloat(cost).toFixed(2));
	row.find('#id').val(id);	
}

function createAndFillDeposit(name, cost, id){
	let rowId = addSystematic($('#adddeposit'));
	let row = $('#depositTableRow' + rowId);
	createAndFillSysTrans(row, name, cost, id);
}

function createAndFillWithdraw(name, cost, id){
	let rowId = addSystematic($('#addwithdraw'));
	let row = $('#withdrawTableRow' + rowId);
	createAndFillSysTrans(row, name, cost, id);
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
	let deletedCount = 0;
	let deletedData = {};
	let data = {};
	while((nextIncome = $('#categoryTableRow' + count++)).length > 0)
	{
		const category = nextIncome.find('#category');
		const name = nextIncome.find('[name="name"]');
		const percent = nextIncome.find('#percentage' + (count - 1));
		const id = nextIncome.find('#id').val();		
		
		let amount = nextIncome.find('[name="Amount"]');
		
		if(name.val().length == 0 && amount.val().length == 0)
		{
			if(id != "0")
				deletedData[deletedCount++] = {id : id};

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
					id : id
			}
		}
	}
	data = {data : data, deletedList : deletedData};
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
	totalBudget = parseFloat($('#total').val());
	if(typeof totalBudget == undefined || isNaN(totalBudget))
		totalBudget = 0;
	remainingBudget = parseFloat($('#totalBudget').text()) - parseFloat($('#totalSpent').text());
	
	
	const depData = submitSystematicDeposits(true);
	const withData = submitSystematicWithdraws(depData);
	
	const catData = submitBudgetCategories(withData);

	const setupData = {
			depositData : JSON.stringify(depData.data),
			withdrawData : JSON.stringify(withData.data),
			categoryData : JSON.stringify(catData.data)
	}
	
	if(depData && withData && catData  && !$.isEmptyObject(catData.data) && !$.isEmptyObject(depData.data) && !$.isEmptyObject(withData.data))
	{	
		$(this).attr("disabled", true);
		$(this).text("Submitting...");
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/budgetsetuppage');
	}
});



function submitSuccess(){
	$('.submission').attr("disabled", false);
	$('.submission').text("Submit");
}

$('.submit-income').click(function(){	
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());

	const depData = submitSystematicDeposits(true);
	if($.isEmptyObject(depData.data))
		return;

	const setupData = {
			depositData : JSON.stringify(depData.data),
			deletedList : JSON.stringify(depData.deletedList)
	}
	alert(JSON.stringify(setupData.depositData));

	if(depData)
	{
		$(this).attr("disabled", true);
		$(this).text("Submitting...");
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/incomepage');
	}

});

$('.submit-bill').click(function(){
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());

	const withData = submitSystematicWithdraws(true);
	if($.isEmptyObject(withData.data))
		return;
	
	const setupData = {
			withdrawData : JSON.stringify(withData.data),
			deletedList : JSON.stringify(withData.deletedList)
	}

	$(this).attr("disabled", true);
	$(this).text("Submitting...");
	
	if(withData && !$.isEmptyObject(setupData.withdrawData))
	{	
		$(this).attr("disabled", true);
		$(this).text("Submitting...");
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/billpage');
	}	
});

$('.submit-budget').click(function(){
	totalBudget = parseFloat($('#totalSpent').text());
	remainingBudget = parseFloat($('#totalBudget').text());
	
	const catData = submitBudgetCategories(true);
	if($.isEmptyObject(catData.data))
		return;
	
	const setupData = {
			categoryData : JSON.stringify(catData.data),
			deletedList : JSON.stringify(catData.deletedList)
	}

	if(catData && !$.isEmptyObject(setupData.categoryData))
	{
		$(this).attr("disabled", true);
		$(this).text("Submitting...");
		submitAjaxBudgetCheck(setupData, '/BudgetBunny/budgetpage');
	}
});

function submitAjaxBudgetCheck(data, url){
		if(remainingBudget < 0)
		{
		}
		ajaxCall(data, url);

}


/*************************** BudgetDisplay ***********************************/

$('#home-div').click(function()
{
	if($("#home-div").hasClass("blur-filter"))
		close_div()	
});

function getEggClass(spent, budget){
	let ratio = spent/budget;
	let color;
	if(ratio > 1)
		color = 'red';
	else if(ratio > .75)
		color = 'yellow';
	else if(ratio > .25)
		color = 'blue';
	else
		color = 'green';
	
	return 'egg-' + color;
}

$(document).ready(function(){
	readyHtml();
});

function readyHtml()
{
	$('.category-display').each(function(){
		let spent = $(this).find('#spent');
		let budget = $(this).find('#budget');
		spentVal = parseFloat(spent.text().replace('$', ''));
		budgetVal = parseFloat(budget.text().replace('$', ''));
		
		budget.text('$' + budgetVal.toFixed(2));
		spent.text('$' + spentVal.toFixed(2));	

		$(this).addClass(getEggClass(spentVal, budgetVal));
	
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
}

$('#purchase').click(function(){
	let popUp = $('#myPopup');
	var transamount = popUp.find("#amount");
	var item = transamount.val();

	
	if(verifyIncomeValue(transamount))
	{
		$(this).text('Processing');

		let data = {
				name: popUp.find('#name').text(),
				budget: popUp.find('#budget').text(),
				spent: popUp.find('#spent').text(),
				amount: item,
				id: popUp.find('#id').text(),
		};
		ajaxCall(data, '/BudgetBunny/home');
		
	}
	
});

function close_div()
{
	$("#home-div").removeClass("blur-filter");
	$('#myPopup').hide();
	$('#purchase').text('Purchase');
	let input = $('#myPopup').find('#amount');
	turnOffHighLight(input);
	input.val("");
}


var selection = $("#category option:selected").text();


