$(document).ready(function () {
//timepicker
    $('#picker1').datetimepicker({
        timepicker: false,
        datepicker: true,
        format: 'Y-m-d',
        value: '2019-12-26',
        weeks: true,
        yearStart: 2019,
        yearEnd: 2021
    });

    tail.select('#select1', {
        search: true,
        deselect: true
    });

    tail.select('#select2', {
        search: true,
        deselect: true
    });

    $('#show').click(function () {
        // $('#error-dates').text("");
        var cityFromId = $('#select1').val();
        var cityToId = $("#select2").val();
        var dateDeparture = $("#picker1").val() + "";

        var dateD = Date.parse(dateDeparture) + "";

        var currentDate = new Date();
        var currD = Date.parse(currentDate);

        if (dateD < currD) {
            // $('#error-dates').text("Incorrect dates");
            return;
        }

    });
});