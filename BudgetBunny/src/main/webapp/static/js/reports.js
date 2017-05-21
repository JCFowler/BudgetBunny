
window.onload = function () {

}

function renderTieredBarGraph(){
	var chart = new CanvasJS.Chart("chartContainer", {
		title: {
			text: "Per-Month Budget Expenditurs"
		},
		axisY: {
			title: "Amount Spent",
			stripLines: [{
				value: 1950,
				label: "Avg",
				showOnTop: true
			},{
				value: 1900,
				label: "Min",
				showOnTop: true
			},{
				value: 2000,
				label: "Max",
				showOnTop: true,
				lineColor: "red"
			}
			]	
		},
		data: [{
			type: "stackedColumn",
			legendText: "Food",
			showInLegend: "true",
			dataPoints: [
				{ y: 405.35, label: "August 2016" },
				{ y: 355.44, label: "September 2016" },
				{ y: 534.96, label: "October 2016" },
				{ y: 436.44, label: "November 2016" },
			]
		}, {
			type: "stackedColumn",
			legendText: "Rent",
			showInLegend: "true",
			indexLabelPlacement: "outside",
			dataPoints: [
				{ y: 1500.00, label: "August 2016" },
				{ y: 1500.00, label: "September 2016" },
				{ y: 1500.00, label: "October 2016" },
				{ y: 1500.00, label: "November 2016" },
			]
	}, {
		type: "stackedColumn",
		legendText: "Gaming",
		showInLegend: "true",
		indexLabelPlacement: "outside",
		dataPoints: [
			{ y: 103.11, label: "August 2016" },
			{ y: 24.84, label: "September 2016" },
			{ y: 0.00, label: "October 2016" },
			{ y: 15.48, label: "November 2016" },
		]
	}, {
		type: "stackedColumn",
		legendText: "movies",
		showInLegend: "true",
		indexLabel: "#total bn",
		indexLabelPlacement: "outside",
		dataPoints: [
			{ y: 15.55, label: "August 2016" },
			{ y: 20.00, label: "September 2016" },
			{ y: 0.00, label: "October 2016" },
			{ y: 34.18, label: "November 2016" },
		]
	}
	]
	});
	chart.render();
}

$('#chart').click(renderTieredBarGraph);