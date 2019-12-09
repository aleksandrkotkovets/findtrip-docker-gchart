$(document).ready(function() {

    const selectCity = document.getElementById('selectCity');
    console.log(selectCity.target);
    const selectButton = document.getElementById('select_');
    selectButton.addEventListener("change", function () {
        const select = document.getElementById("select_");
        const obj = { "id": select.value}
        fetch( "http://localhost:8080/api/airports/add/getCities",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(obj)
        })
            .then(resp => resp.json()
                .then(json => {
                    console.log(json);
                    while (selectCity.length > 0) {
                        selectCity.remove(0);
                    }

                    json.map(item => {
                        const option = document.createElement("option");
                        option.setAttribute("value", item.id.toString());
                        option.innerHTML = item.name.toString();
                        selectCity.appendChild(option);
                    });
                }));
    });



});