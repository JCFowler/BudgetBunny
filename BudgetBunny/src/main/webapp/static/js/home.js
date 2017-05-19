/*************************** BudgetDisplay ***********************************/

/*
 * Displays transaction popup, propagated with budget information, when user clicks on a displayed category.
 */
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

/*
 * Chosses a random egg-background class.
 * TODO: choose based on budget state.
 */
function getEggClass(){
	let rand = Math.floor((Math.random() * 8) + 1);
	return 'egg-back-' + rand;
}

/*
 * Ensures double point precision is displayed on budget values.
 */
$(document).ready(function(){
	$('.category-display').each(function(){
		$(this).addClass(getEggClass());
	
		let budget = $(this).find('#budget');
		budget.text('$' + parseFloat(budget.text().replace('$', '')).toFixed(2));

		let spent = $(this).find('#spent');
		spent.text('$' + parseFloat(spent.text().replace('$', '')).toFixed(2));	
	});
});


/*
 * TODO: attach to functionality.
 */
$('#purchase').click(function(){
	close_div();
});

/*
 * Closes popup and removes dark filter from page.
 */
function close_div()
{
	$("#home-div").removeClass("blur-filter");
	$('#myPopup').hide();
}
