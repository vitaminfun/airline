function airplaneAjax(method, url, request, async) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, async);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let airplaneArray =JSON.parse(this.responseText);
            fillTable(airplaneArray);

        }

    };
    xhr.send(request);
}
function fillTable(airplaneArray){
    let tableBody = document.getElementById("tableBody");

    for(let i = 0; i < airplaneArray.length; i++){
        let airplane = airplaneArray[i];

        let row = document.createElement("TR");
        tableBody.appendChild(row);

        let radio  = document.createElement("input");
        radio.type="radio";
        radio.id="radio" + i;
        radio.name = "choice";
        let tdRadio = document.createElement("TD");
        let tdIdAirplane = document.createElement("TD");
        let tdName = document.createElement("TD");
        let tdNumberOfSeats = document.createElement("TD");

        tdRadio.appendChild(radio);

        row.appendChild(tdRadio);
        row.appendChild(tdIdAirplane);
        row.appendChild(tdName);
        row.appendChild(tdNumberOfSeats);

        tdIdAirplane.innerHTML = airplane["id"];
        tdName.innerHTML = airplane["name"];
        tdNumberOfSeats.innerHTML = airplane["numbersOfSeats"];


    }

}