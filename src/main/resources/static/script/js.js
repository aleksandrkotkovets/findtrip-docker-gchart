/**
 *
 */

/* $(document).ready(function () {
     $('.table .eBtn').on('click', function (event) {
         event.preventDefault();
         var href = $(this).attr('href');

         $.get(href, function (country, status) {
             $('.myForm #id').val(country.id);
             $('.myForm #country').val(country.name);

         });

         $('.myForm #exampleModal').modal();
         $('.myForm #save').click(function () {
         });
     });
 });*/
$(document).ready(function () {

    var localUrl = "http://localhost:8080/findtrip";
    // input-spinner
    $("input[type='number']").inputSpinner();
    var $countTickets = $("#countTickets");
    var $returnTickets = $("#returnTickets");
    var $returnMoney = $('#returnMoney');
    var $priceOneSeat = $('#priceOneSeat');

    $returnTickets.attr("max", $countTickets.val());

    $returnTickets.on("change", function (event) {
        $returnMoney.val($returnTickets.val() * $priceOneSeat.val());
    });

    $('#returnBtn').on('click', function () {
        var returnTickets = $('#returnTickets').val();

        if (returnTickets == 0) {
            var va = "Enter count return tickets >0";
            $('#error_text').text(va);
            return;
        }
        var id = $('#order_id').val();

        var orderDTO = {
            "id": id,
            "returnTickets": returnTickets
        };

        console.log(JSON.stringify(orderDTO));
        $.ajax({
            url: localUrl + "/orders/return",
            contentType: "application/json",
            method: "POST",
            data: JSON.stringify(orderDTO),
            success: function () {
                alert("You have successfully abandoned places. The money was returned.\n The money was returned.");
                url = localUrl + "/orders/client";
                window.location.replace(url);
            },
            error: function (error) {
                if (error.status == 409) {
                    var error_msg = error.responseJSON.message;
                    $('#error_text').text(error_msg);
                }

            },
            dataType: "json"
        });

    });
});