

<%@page import="com.revature.bean.Category" %>
<%@page import="com.revature.bean.TimeUnit" %>


<c:set var="categories" value='<%=new Category[]{new Category(0, "Food", 0, 0, null), new Category(1, "Bills", 0, 0, null), new Category(2, "entertainment", 0, 0, null), new Category(3, "amazon", 0, 0, null)}%>' />

<div id='tableHeader'>

	<h3>Setup Budget Categories</h3>

	<table class="table-hover table table-bordered" id="categoryTable")>
		<tbody id="categoryTableBody">
		  <tr>
		    <th>Name</th>
		    <th class="percentage">Percentage</th>
		    <th>Amount</th> 
		    <th id="close"></th>
		  </tr>
		  <tr>
		    <th hidden class='errorMsg'>Field Cannot Be Empty</th> 
		    <th hidden class='percentage errorMsg'></th>
		    <th hidden class='errorMsg'>0 < Amount < 999999999.99</th>
		    <th hidden class='errorMsg'></th>
		  </tr>
		  <tr id="categoryTableRow0" hidden>
		  	<td><input name='name' placeholder='name' type='text'></input></td>
		    <c:set var="typeValue" value='$'></c:set>
		    <td><input class="percentage" id="percentage" placeholder="" type="checkbox"/></td> 
		    <td>
		    	<label id="dollarType">$</label>
		    	<input id='amount' name="Amount" placeholder="Amount" type="number"/>
		    	<label style='visibility: hidden' id="percentType">%</label>
		    </td>
		   	<td ><button class='btn btn-info removeButton' id="removeButton"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></td>
		  </tr>
		</tbody>
	</table>
	
	<button class="btn btn-info" id="addCategory">Add category</button>
</div>
