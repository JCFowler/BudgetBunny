
	<nav class="navbar navbar-default">
		<div class="container-fluid">
		<h4 class="total-budget">TotalBudget $${user.getBudget().getBudgetString()}</h4>
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
					<a class="navbar-brand white-bunny" href="home"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">
					<li class="active"><a href="home">Home <span class="sr-only">(current)</span></a></li>
					<li><a href="transaction">Transactions</a></li>
					<li><a href="reports">Reports</a></li>
				</ul>
			
				
			
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">${user.getName() } <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="budgetpage">Edit Budget</a></li>
							<li><a href="incomepage">Edit Income</a></li>
							<li><a href="billpage">Edit Bills</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>