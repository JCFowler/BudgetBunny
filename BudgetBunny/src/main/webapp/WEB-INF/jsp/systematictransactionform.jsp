



<%@page import="com.revature.bean.TimeUnit" %>


<c:set var="timePeriods" value='<%=new TimeUnit[]{new TimeUnit(0, "Monthly"),new TimeUnit(3, "One Time")}%>' scope="application" />
<input hidden value="${type}" id="type">
<div>
	<h3>${headerMsg}</h3>

	<table class="table table-hover" id="${type}Table">
		<tbody id="${type}TableBody">
		  <tr class="thead-default">
		    <th>Name</th>
		    <th>Amount</th> 
   		    <th id="close"></th>
		  </tr>
		  <tr class='header-row'>
		  	<th hidden class='errorMsg'>Field Cannot Be Empty</th>
		    <th hidden class='errorMsg'>0 < Amount < 999999999.99</th> 
		  	<th hidden class='errorMsg'></th>
		  </tr>
		  <tr id="original${type}TableRow0" count='0' style="display: none;">
		    <td><input name="name" id="name" placeholder="name" type="text"/></td>
		    <td><input name="cost" id="cost" placeholder="wage/salary" type="text"/></td> 
		   	<td hidden><input name="id" id="id" value="0"/></td>
		   	<td ><button class='RemoveButton' type="${type}" id="${type}RemoveButton"><span class="glyphicon glyphicon-remove glyph-small" style="font-size: .75em;" aria-hidden="true"></span></button></td>
		  </tr>
		</tbody>
	</table>
</div>
	<button class="btn btn-info add-systematic" id="add${type}">Add ${type}</button>


