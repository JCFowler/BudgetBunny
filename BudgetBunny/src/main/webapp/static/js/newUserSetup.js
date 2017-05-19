/*************************** NewUserSetup ***********************************/

/*
 * Retrieves json objects built from depositTable, withdrawTable, and categoryTable and combines them
 * into one object to be transmitted via ajax call;
 */
$('#submitSetup').click(function()
{
	const depData = submitSystematicDeposits(true);
	const withData = submitSystematicWithdraws(depData);
	
	const catData = submitBudgetCategories(withData);
	
	const setupData = {
			depositData : JSON.stringify(depData),
			withdrawData : JSON.stringify(withData),
			categoryData : JSON.stringify(catData)
	}
	
	if(depData && withData && catData)
	{
		ajaxCall(setupData, '/BudgetBunny/budgetsetuppage');
	}

});
