google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {
    var localServerUrl = "http://localhost:8080/";

    var init = [
        ['Маршрут', 'Кол-во рейсов']
    ];

    $.ajax({
        url: localServerUrl + "findtrip/info/chart/data",
        contentType: "application/json",
        method: "GET",
        success: function (result) {
            console.log(result);
            init = init.concat(func(result));
            console.log(init);
            draw(init);
        },
        dataType: "json"
    });

}

func = (result) => {
    return result.map(elem => {
        return [elem.flightName, elem.countFlight]
    })
};

draw = (init)=>{
    var data = google.visualization.arrayToDataTable(init);

    var options = {
        title: 'Статистика популярности существующих напрявлений полетов',
        chartArea: {width: '70%'},
        hAxis: {
            title: 'Количество рейсов',
            minValue: 0
        },
        vAxis: {
            title: 'Р е й с'
        }
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

    chart.draw(data, options);
};