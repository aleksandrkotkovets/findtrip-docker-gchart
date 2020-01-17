/*
$(document).ready(function ($) {
    $('#alertCansFlightSuccessId').hide();

});

$('#canceledId').click(function () {

    var localServerUrl = "http://localhost:8080/";
    var idFlight = $('#idFlight').val();

    $.ajax({
        url: localServerUrl + "findtrip/flights/canceled/"+idFlight,
        method: "GET",
        success: function () {
            $('#alertCansFlightSuccessId').text("Flight was canceled successfully.");
            $('#alertCansFlightSuccessId').show(0, function(){
                setTimeout(function(){
                    $('#alertCansFlightSuccessId').hide(0);
                }, 3000);
            });
            sendСancellationСonfirmToEmail(idFlight);
            /!*url = localServerUrl + "findtrip/flights";
            window.location.replace(url);*!/
        }
    });

});


function sendСancellationСonfirmToEmail(idFlight) {
    $.ajax({
        url:"http://localhost:8080/findtrip"  + '/flights/sendСancellationСonfirmToEmail/'+idFlight,
        method: "GET",
        success: function () {
            console.log("Messages were sent successfully.");
        },
        error: function (error) {
            console.log(status);
        }
    });
}*/
