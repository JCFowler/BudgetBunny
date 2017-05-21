	
	<div class='trans-div' id='myPopup' style="display: none">
		<div class='bar'>
			<span class="glyphicon glyphicon-remove close" onclick="close_div(1)" aria-hidden="true"></span>
		</div>
		<h2 id='name'>name</h2>
		<div id='equation'>
			<h3 class='budget-eq' id='budget'>budget </h3>
			<h3 class='budget-eq'> - </h3>
			<h3 class='budget-eq' id='spent'>spent</h3>
			<h3 class='budget-eq'> = </h3>
			<h3 class='budget-eq' id='total'>total</h3>
			<h3 class="no-top" id='id' hidden>id</h3>
		</div>
		<div class='trans-input' >
			<button class='btn btn-info' id='purchase' >Purchase</button>
			<input id='amount' type='number' placeholder='Amount'/>
		</div>
	</div>
	
<script src="static/js/transaction.js"></script>