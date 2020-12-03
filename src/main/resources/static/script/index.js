protocol = $(location).attr('protocol') + '//';
hostUrl = $(location).attr('host');
localServerUrl = protocol+hostUrl+'/';

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

    //price spinner
    var priceFrom = $("#priceFrom");
    var priceTo = $("#priceTo");

    priceTo.attr("min",parseInt(priceFrom.val(),10)+1 );
    priceFrom.on("change", function (event) {
        priceTo.attr("min", parseInt(priceFrom.val(),10)+1);
    })
});


$('#buy').click(function () {
    // $('#error-dates').text("");

    var idFlight = $('#idFlight').val();
    var finalCost = $('#finalCost').val();
    var countSeats =parseInt($('#countSeats').val(), 10);
    var priceOneSeat = $('#price').val();
    var freeSeats = parseInt($('#freeSeats').val(),10);

    if (countSeats > freeSeats ) {
        var error_msg = "Please, enter correct count of seats number.";

        $('#error-dates').text(error_msg);
        return;
    }

    if ( freeSeats==0 ) {
        var error_msg = "No free seats on the flight"
        $('#error-dates').text(error_msg);
        return;
    }
    console.log(
        "idFlight = " + idFlight + '\n' +
        "finalCost = " + finalCost + '\n' +
        "priceOneSeat = " + priceOneSeat + '\n' +
        "countSeats = " + countSeats + '\n'
    );

    var ticketDTO = {
        "idFlight": idFlight,
        "finalCost": finalCost,
        "countSeats": countSeats,
        "priceOneSeat": priceOneSeat,
        "idClient": null,
        "id": null

    };

    console.log(JSON.stringify(ticketDTO));
    console.log(idFlight);
    if(idFlight != null){
    $.ajax({
        url: localServerUrl + "findtrip/orders/checkout",
        contentType: "application/json",
        method: "POST",
        data: JSON.stringify(ticketDTO),
        success: function (order) {
            alert("Ticket(s) was both successfully");
            sendСonfirmPurchaseToEmail(order);
            url = localServerUrl + "findtrip/orders/client";
            window.location.replace(url);
        },
        error: function (error) {
            if (error.status == 409) {
                var error_msg = error.responseJSON.message;
                $('#error-dates').text(error_msg);
            }

        },
        dataType: "json"
    });
    }

});


$('#takeMoreTicketsBtn').click(function () {
    // $('#error-dates').text("");
    var idOrder = $('#idOrder').val();
    var idFlight = $('#idFlight').val();
    var finalCost = $('#finalCost').val();
    var countSeats =parseInt($('#countSeats').val(), 10);
    var priceOneSeat = $('#price').val();
    var freeSeats = parseInt($('#freeSeats').val(),10);

    if (countSeats > freeSeats ) {
        var error_msg = "Please, enter correct count of seats number.";

        $('#error-dates').text(error_msg);
        return;
    }

    if ( freeSeats==0 ) {
        var error_msg = "No free seats on the flight"
        $('#error-dates').text(error_msg);
        return;
    }
    console.log(
        "idFlight = " + idFlight + '\n' +
        "idOrder = " + idOrder + '\n' +
        "finalCost = " + finalCost + '\n' +
        "priceOneSeat = " + priceOneSeat + '\n' +
        "countSeats = " + countSeats + '\n'
    );

    var order = {
        "id": idOrder,
        "idFlight": idFlight,
        "finalCost": finalCost,
        "countSeats": countSeats,
        "priceOneSeat": priceOneSeat,
        "idClient": null
    };
    console.log(JSON.stringify(order));
    $.ajax({
        url: localServerUrl + "findtrip/orders/"+idOrder+"/moreTickets",
        contentType: "application/json",
        method: "POST",
        data: JSON.stringify(order),
        success: function (order) {
            alert("More ticket(s) was(were) both successfully");
            sendСonfirmPurchaseToEmail(order);
            url = localServerUrl + "findtrip/orders/client";
            window.location.replace(url);
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


function sendСonfirmPurchaseToEmail(order) {
    $.ajax({
        url: localServerUrl  + 'findtrip/orders/sendСonfirmPurchaseToEmail',
        contentType: "application/json",
        method: "POST",
        data: JSON.stringify(order),
        success: function (order) {
            console.log("Data: " + order);
        },
        error: function (error) {
            console.log(status);
        },
        dataType: "json"
    });
}