



<%@page import="com.revature.bean.TimeUnit" %>


<c:set var="timePeriods" value='<%=new TimeUnit[]{new TimeUnit(0, "Daily"), new TimeUnit(1, "Weekly"), new TimeUnit(2, "Monthly"), new TimeUnit(3, "Annually")}%>' scope="application" />
<input hidden value="${type}" id="type">

<div id='tableHeader'>
	
	<h3>${headerMsg}</h3>

	<table class="table-hover table table-bordered" id="${type}Table")>
		<tbody id="${type}TableBody">
		  <tr>
		    <th>Name</th>
		    <th>Amount</th> 
		    <th class='period'>Period</th>
		    <th>Start Date</th>
   		    <th id="close"></th>
		  </tr>
		  <tr>
		  	<th hidden class='errorMsg'>Field Cannot Be Empty</th>
		    <th hidden class='errorMsg'>0 < Amount < 999999999.99</th> 
		    <th hidden class='errorMsg period'></th>
		    <th hidden class='errorMsg'>Today or in the future</th>
		  </tr>
		  <tr id="${type}TableRow0" hidden>
		    <td><input name="name" placeholder="name" type="text"/></td>
		    <td><input name="cost" placeholder="wage/salary" type="text"/></td> 
		    <td>	
			    <select name='period' class='period'>
					<c:forEach items="${timePeriods}" var="period">
						<option value="${period.unitId}">${period.unit}</option>
					</c:forEach>
				</select>
			</td>
			<td><input name="startDate" type="date"	></td>
		   	<td ><button class='btn btn-info ${type}RemoveButton' id="${type}RemoveButton"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></td>
		  </tr>
		</tbody>
	</table>
	
	<button class="btn btn-info" id="add${type}">Add ${type}</button>

</div>


