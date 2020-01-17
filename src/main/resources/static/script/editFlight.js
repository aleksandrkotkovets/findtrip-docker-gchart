$(document).ready(function () {
    protocol = $(location).attr('protocol') + '//';
    hostUrl = $(location).attr('host');


    //City from
    const selectCity_fr = document.getElementById('select_city_fr');
    const selectButton_fr = document.getElementById('select_country_fr');
    selectButton_fr.addEventListener("change", function () {
        $('#error-dates').text("");
        const select = document.getElementById("select_country_fr");
        let id = select.value;
        console.log(id);
        fetch(protocol + hostUrl + "/findtrip/flights/countries/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (selectCity_fr.length > 0) {
                        selectCity_fr.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        selectCity_fr.appendChild(option);
                    });
                }));

    });

    //City to
    const selectCity_to = document.getElementById('select_city_to');
    const selectButton_to = document.getElementById('select_country_to');
    selectButton_to.addEventListener("change", function () {
        $('#error-dates').text("");
        const select = document.getElementById("select_country_to");
        let id = select.value;
        console.log(id);
        fetch(protocol + hostUrl + "/findtrip/flights/countries/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (selectCity_to.length > 0) {
                        selectCity_to.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        selectCity_to.appendChild(option);
                    });
                }));

    });

//Airport from
    const selectAirport_fr = document.getElementById('select_airport_fr');
    const selectButtonCity_fr = document.getElementById('select_city_fr');
    selectButtonCity_fr.addEventListener("change", function () {
        $('#error-dates').text("");
        const select = document.getElementById("select_city_fr");
        let id = select.value;
        console.log(id);
        fetch(protocol + hostUrl + "/findtrip/flights/cities/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (selectAirport_fr.length > 0) {
                        selectAirport_fr.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString() + '(' + item.code.toString() + ')';
                        selectAirport_fr.appendChild(option);
                    });
                }));

    });

    //Airport to
    const selectAirport_to = document.getElementById('select_airport_to');
    const selectButtonCity_to = document.getElementById('select_city_to');
    selectButtonCity_to.addEventListener("change", function () {
        $('#error-dates').text("");
        const select = document.getElementById("select_city_to");
        let id = select.value;
        console.log(id);
        fetch(protocol + hostUrl + "/findtrip/flights/cities/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (selectAirport_to.length > 0) {
                        selectAirport_to.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString() + '(' + item.code.toString() + ')';
                        selectAirport_to.appendChild(option);
                    });
                }));

    });

    //Plane
    const selectPlane = document.getElementById('select_plane');
    const selectButtonCompany = document.getElementById('select_company');
    selectButtonCompany.addEventListener("change", function () {
        $('#error-dates').text("");
        const select = document.getElementById("select_company");
        let id = select.value;
        console.log(id);
        fetch(protocol + hostUrl + "/findtrip/flights/companies/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (selectPlane.length > 0) {
                        selectPlane.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.sideNumber.toString();
                        selectPlane.appendChild(option);
                    });
                }));

    });

    //timepicker
    $('#picker1').datetimepicker({
        timepicker: true,
        datepicker: true,
        format: 'Y-m-d H:i',
        step: 5,
        yearStart: 2019,
        yearEnd: 2021,
        onShow: function (ct) {
            this.setOptions({
                maxDateTime: $('#picker2').val() ? $('#picker2').val() : false
            })
        }
    });

    $('#picker2').datetimepicker({
        timepicker: true,
        datepicker: true,
        format: 'Y-m-d H:i',
        step: 5,
        yearStart: 2019,
        yearEnd: 2021,
        onShow: function (ct) {
            this.setOptions({
                minDateTime: $('#picker1').val() ? $('#picker1').val() : false
            })
        }
    });


    // input-spinner
    $("input[type='number']").inputSpinner();
    var $allSeats = $("#allSeats");
    var $freeSeats = $("#freeSeats");

    $allSeats.on("change", function (event) {
        $freeSeats.attr("max", $allSeats.val());
        $freeSeats.val($allSeats.val());
    });


    //save button
    $('#save').click(function () {
        $('#error-dates').text("");
        var cityFromId = $('#select_city_fr').val();
        var cityToId = $("#select_city_to").val();
        var airportFromId = $("#select_airport_fr").val();
        var airportToId = $("#select_airport_to").val();
        var dateDeparture = $("#picker1").val() + "";
        var dateArrival = $("#picker2").val() + "";
        var companyId = $("#select_company").val();
        var planeId = $("#select_plane").val();
        var allSeats = $('#allSeats').val();
        var freeSeats = $('#freeSeats').val();
        var ticketPrice = $('#price').val();
        console.log(
            "city_fr = " + cityFromId + '\n' +
            "city_to = " + cityToId + '\n' +
            "airport_fr = " + airportFromId + '\n' +
            "airport_to = " + airportToId + '\n' +
            "dateDeparture = " + dateDeparture + '\n' +
            "dateArrival = " + dateArrival + '\n' +
            "planeId = " + planeId + '\n' +
            "companyId = " + companyId + '\n' +
            "allSeats = " + allSeats + '\n' +
            "freeSeats = " + freeSeats + '\n' +
            "ticketPrice = " + ticketPrice + '\n'
        );

        if (cityFromId == "" || cityFromId == null || cityToId == "" || cityToId == null || airportFromId == "" ||
            airportFromId == null || airportToId == "" || airportToId == null ||
            dateDeparture == "" || dateArrival == "" || planeId == "" || companyId == "" || planeId == null ||
            companyId == null || allSeats == "" || freeSeats == "" || ticketPrice == "") {

            $('#error-dates').text("Please, fill in all fields!");
            return;
        }
        if (cityToId === cityFromId) {
            $('#error-dates').text("Please, Enter different cities!");
            return;
        }

        var dateD = Date.parse(dateDeparture) + "";
        var dateA = Date.parse(dateArrival) + "";

        var currentDate = new Date();
        var currD = Date.parse(currentDate);

        if (dateD < currD) {
            $('#error-dates').text("Incorrect dates");
            return;
        }

        var diffHours = dateA - dateD;
        if (diffHours <= 0) {
            $('#error-dates').text("Incorrect dates");
            return;
        }

        flightLocalServerUrl = protocol + hostUrl;
        var flightDTO = {
            "cityFromId": cityFromId,
            "cityToId": cityToId,
            "airportFromId": airportFromId,
            "airportToId": airportToId,
            "dateDeparture": dateD,
            "dateArrival": dateA,
            "planeId": planeId,
            "companyId": companyId,
            "allSeats": allSeats,
            "freeSeats": freeSeats,
            "ticketPrice": ticketPrice,
            "id": null

        };
        console.log(JSON.stringify(flightDTO));
        $.ajax({
            url: flightLocalServerUrl + "/findtrip/flights",
            contentType: "application/json",
            method: "POST",
            data: JSON.stringify(flightDTO),
            success: function(flight){
                alert("Flight was created successfully");
                url = protocol + hostUrl + "/findtrip/flights";
                window.location.replace(url) ;
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

    //click edit country_from button
    const country_fr = document.getElementById('select_country_fr');
    $("#edt_country_fr").click(function () {

        const a = document.getElementById('select_city_fr');
        const b = document.getElementById('select_airport_fr');
        //Edit
        while (a.length > 0) {
            a.remove(0);
        }

        while (b.length > 0) {
            b.remove(0);
        }

        fetch(protocol + hostUrl + "/findtrip/flights/countries", {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (country_fr.length > 0) {
                        country_fr.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        country_fr.appendChild(option);
                    });
                }));
    });

    //click edit country_to button
    const country_to = document.getElementById('select_country_to');
    $("#edt_country_to").click(function () {

        const a = document.getElementById('select_city_to');
        const b = document.getElementById('select_airport_to');
        //Edit
        while (a.length > 0) {
            a.remove(0);
        }

        while (b.length > 0) {
            b.remove(0);
        }


        fetch(protocol + hostUrl + "/findtrip/flights/countries", {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (country_to.length > 0) {
                        country_to.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        country_to.appendChild(option);
                    });
                }));
    });

    //click edit city_from button
    const city_fr = document.getElementById('select_city_fr');
    $("#edt_city_fr").click(function () {

        const b = document.getElementById('select_airport_fr');
        //Edit
        while (b.length > 0) {
            b.remove(0);
        }

        let id = $('#select_country_fr').val();
        fetch(protocol + hostUrl + "/findtrip/flights/countries/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (city_fr.length > 0) {
                        city_fr.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        city_fr.appendChild(option);
                    });
                }));
    });

    //click edit city_to button
    const city_to = document.getElementById('select_city_to');
    $("#edt_city_to").click(function () {

        const b = document.getElementById('select_airport_to');
        //Edit
        while (b.length > 0) {
            b.remove(0);
        }

        let id = $('#select_country_to').val();
        fetch(protocol + hostUrl + "/findtrip/flights/countries/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (city_to.length > 0) {
                        city_to.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        city_to.appendChild(option);
                    });
                }));
    });

    //click edit airport_to button
    const airport_fr = document.getElementById('select_airport_fr');
    $("#edt_airport_from").click(function () {

        let id = $('#select_city_fr').val();
        fetch(protocol + hostUrl + "/findtrip/flights/cities/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (airport_fr.length > 0) {
                        airport_fr.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString() + '(' + item.code.toString() + ')';
                        airport_fr.appendChild(option);
                    });
                }));
    });

    //click edit airport_to button
    const airport_to = document.getElementById('select_airport_to');
    $("#edt_airport_to").click(function () {

        let id = $('#select_city_to').val();
        fetch(protocol + hostUrl + "/findtrip/flights/cities/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (airport_to.length > 0) {
                        airport_to.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString() + '(' + item.code.toString() + ')';
                        airport_to.appendChild(option);
                    });
                }));
    });


    //Plane


    $("#edt_company").click(function () {
        const select = document.getElementById("select_company");

        fetch(protocol + hostUrl + "/findtrip/flights/companies", {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (select.length > 0) {
                        select.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        select.appendChild(option);
                    });
                }));

    });

    //Plane
    const plane = document.getElementById('select_plane');
    $('#edt_plane').click(function () {


        let id = $('#select_company').val();
        console.log(id);
        fetch(protocol + hostUrl + "/findtrip/flights/companies/" + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
        })

            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (plane.length > 0) {
                        plane.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.sideNumber.toString();
                        plane.appendChild(option);
                    });
                }));

    });

    // input-spinner

    var $allSeatsEdit = $("#allSeatsEdit");
    var $freeSeatsEdit = $("#freeSeatsEdit");
    var $soldTickets = $('#soldTickets');

    $allSeatsEdit.attr("min", $soldTickets.val());

    $allSeatsEdit.on("change", function (event) {
        $freeSeatsEdit.attr("max", $allSeatsEdit.val()-$soldTickets.val());
        $freeSeatsEdit.val($allSeatsEdit.val());
    });


    //edit button
    $('#edit').click(function () {
        $('#error-dates').text("");
        var id = $("#id").val();
        var cityFromId = $('#select_city_fr').val();
        var cityToId = $("#select_city_to").val();
        var airportFromId = $("#select_airport_fr").val();
        var airportToId = $("#select_airport_to").val();
        var dateDeparture = $("#picker1").val() + "";
        var dateArrival = $("#picker2").val() + "";
        var companyId = $("#select_company").val();
        var planeId = $("#select_plane").val();
        var allSeats = $('#allSeatsEdit').val();
        var freeSeats = $('#freeSeatsEdit').val();
        var ticketPrice = $('#price').val();
        console.log(
            "id=" + id + '\n',
            "city_fr = " + cityFromId + '\n' +
            "city_to = " + cityToId + '\n' +
            "airport_fr = " + airportFromId + '\n' +
            "airport_to = " + airportToId + '\n' +
            "dateDeparture = " + dateDeparture + '\n' +
            "dateArrival = " + dateArrival + '\n' +
            "planeId = " + planeId + '\n' +
            "companyId = " + companyId + '\n' +
            "allSeats = " + allSeats + '\n' +
            "freeSeats = " + freeSeats + '\n' +
            "ticketPrice = " + ticketPrice + '\n'
        );

        if (cityFromId == "" || cityFromId == null || cityToId == "" || cityToId == null || airportFromId == "" ||
            airportFromId == null || airportToId == "" || airportToId == null ||
            dateDeparture == "" || dateArrival == "" || planeId == "" || companyId == "" || planeId == null ||
            companyId == null || allSeats == "" || freeSeats == "" || ticketPrice == "") {

            $('#error-dates').text("Please, fill in all fields!");
            return;
        }
        if (cityToId === cityFromId) {
            $('#error-dates').text("Please, Enter different cities!");
            return;
        }

        var dateD = Date.parse(dateDeparture) + "";
        var dateA = Date.parse(dateArrival) + "";

        var currentDate = new Date();
        var currD = Date.parse(currentDate);

        if (dateD < currD) {
            $('#error-dates').text("Incorrect dates");
            return;
        }

        var diffHours = dateA - dateD;
        if (diffHours <= 0) {
            $('#error-dates').text("Incorrect dates");
            return;
        }

        flightLocalServerUrl = protocol + hostUrl;
        var flightDTO = {

            "cityFromId": cityFromId,
            "cityToId": cityToId,
            "airportFromId": airportFromId,
            "airportToId": airportToId,
            "dateDeparture": dateD,
            "dateArrival": dateA,
            "planeId": planeId,
            "companyId": companyId,
            "allSeats": allSeats,
            "freeSeats": freeSeats,
            "ticketPrice": ticketPrice,
            "id": id
        };
        console.log(JSON.stringify(flightDTO));
        $.ajax({
            url: flightLocalServerUrl + "/findtrip/flights/"+id,
            contentType: "application/json",
            method: "POST",
            data: JSON.stringify(flightDTO),
            success:function(flight){
                alert("Flight was edited successfully")
                url = protocol + hostUrl + "/findtrip/flights";
                window.location.replace(url) ;
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
});