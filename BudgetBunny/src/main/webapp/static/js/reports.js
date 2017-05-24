
window.onload = function () {

}

function ajaxCall(data, destUrl, type)
{
	$.ajax({
	    type:"POST",
	    cache:false,
	    url: destUrl,
	    data: data,    // multiple data sent using ajax
	    success: function (html, data) {
	    	
	        var div = $('<div>');
	        div.html(html);
	    	var content = div.find('#container');

	        let json = div.find('#json').text();
	    	let jsonObj = $.parseJSON(json).types
	    	
	    	$('#container').html(content.html());
	    	createGraph(type, jsonObj);
	    }
	  });
}

function createGraph(graphName, jsonObj)
{
	switch(graphName)
	{
	case 'tieredBarGraph': 
		renderTieredBarGraph(jsonObj);
		break;
	case 'pieGraph':
		renderPieGraph(jsonObj);
		break;
	}
}

function renderTieredBarGraph(jsonObj){
	data = [];
	for(category in jsonObj)
	{
	    dataPoints = [];
	    for(month in jsonObj[category])
	    {
	    	dataPoints.push({
	    			y : jsonObj[category][month], 
	    			label : month
	    	});
	    }
	    
	    data.push({	
	   		type: "stackedColumn",
			legendText: category,
			showInLegend: "true", 
			dataPoints: dataPoints
		});
	}
	var chart = new CanvasJS.Chart("chartContainer", {
		title: {
			text: "Per-Month Budget Expenditurs"
		},
		data: data
	});
	chart.render();
}


function renderPieGraph(jsonObj){
	data = [];
	dataPoints = [];
	for(category in jsonObj)
	{
	    let total = 0;
	    for(month in jsonObj[category])
	    {
	    	total = total + parseFloat(jsonObj[category][month]);
	    }
	    dataPoints.push({
	    	y: total,
	    	indexLabel: category
	    });
	}
	
    data.push({	
		type: "pie",
		showInLegend: true,
		toolTipContent: "{y} - #percent %",
		yValueFormatString: "#0.#,,. Million",
		legendText: "{indexLabel}",
		dataPoints: dataPoints
	});
    
	var chart = new CanvasJS.Chart("chartContainer",{
				theme: "theme2",
				title:{
					text: "Gaming Consoles Sold in 2012"
				},
				data: data
			});
			chart.render();
}

$('#chart').click(function(){
	ajaxCall({}, '/BudgetBunny/reports', 'tieredBarGraph')
});

$('#pie').click(function(){
	ajaxCall({}, '/BudgetBunny/reports', 'pieGraph')
});


