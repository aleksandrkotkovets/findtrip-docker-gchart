$(document).ready(function () {
//timepicker
    $('#picker1').datetimepicker({
        timepicker: false,
        datepicker: true,
        format: 'Y-m-d',
        // value: '2019-12-26',
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

    // input-spinner
    $("input[type='number']").inputSpinner();
    var countSeats = $("#countSeats");
    var freeSeats = $("#freeSeats");

    countSeats.attr("max", freeSeats.val());

    var price = $('#price');
    var finalCost = $('#finalCost');
    finalCost.val(price.val() * countSeats.val());

    countSeats.on("change", function (event) {
        finalCost.val(price.val() * countSeats.val());
    });



});


$('#buy').click(function () {
    // $('#error-dates').text("");
    var idFlight = $('#idFlight').val();
    var finalCost = $('#finalCost').val();
    var countSeats = $('#countSeats').val();

    console.log(
        "idFlight = " + idFlight + '\n' +
        "finalCost = " + finalCost + '\n' +
        "countSeats = " + countSeats + '\n'
    );

    var localServerUrl = "http://localhost:8080/";

    var ticketDTO = {
        "idFlight": idFlight,
        "finalCost": finalCost,
        "countSeats": countSeats,
        "idClient": null,
        "id": null

    };
    console.log(JSON.stringify(ticketDTO));
    $.ajax({
        url: localServerUrl + "findtrip/tickets/buy",
        contentType: "application/json",
        method: "POST",
        data: JSON.stringify(ticketDTO),
        success: function(ticket){
            alert("Ticket was both successfully")
            /*url = protocol + hostUrl + "/findtrip/home";
            window.location.replace(url) ;*/
        },
        error: function (error) {
            if (error.status == 409) {
                var error_msg = error.responseJSON.message;
                $('#error-dates').text(error_msg);
            }

        },
        dataType: "json"
    });

});



function onAjaxSuccess(data, status) {
    alert(data);
    alert(status);
}

function onAjaxError(error) {
    alert(error)
}