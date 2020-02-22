function flightAjax(method, url, request, async) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, async);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let flightArray =JSON.parse(this.responseText);
            fillTable(flightArray);

        }

    };
    xhr.send(request);
}
function fillTable(flightArray){
    let tableBody = document.getElementById("tableBody");

    for(let i = 0; i < flightArray.length; i++){
        let flight = flightArray[i];

        let row = document.createElement("TR");
        tableBody.appendChild(row);

        let radio  = document.createElement("input");
        radio.type="radio";
        radio.id="radio" + i;
        radio.name = "choise";
        let tdRadio = document.createElement("TD");
        let tdIdFlight = document.createElement("TD");
        let tdIdAirplane = document.createElement("TD");
        let tdFrom = document.createElement("TD");
        let tdTo = document.createElement("TD");
        let tdDate = document.createElement("TD");

        tdRadio.appendChild(radio);

        row.appendChild(tdRadio);
        row.appendChild(tdIdFlight);
        row.appendChild(tdIdAirplane);
        row.appendChild(tdFrom);+
        row.appendChild(tdTo);
        row.appendChild(tdDate);

        tdIdFlight.innerHTML = flight["idFlight"];
        tdIdAirplane.innerHTML = flight["idAirplane"];
        tdFrom.innerHTML = flight["from"];
        tdTo.innerHTML = flight["to"];
        tdDate.innerHTML = flight["date"];

    }

}