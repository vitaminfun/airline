function deleteFlight() {

    let body = document.getElementById("tableBody");

    let rows = body.children;
    for (let i = 0; i < rows.length; i++ ) {
        let tr = rows[i];
        let cell = tr.children;
        let radioCell = cell[0];
        let radio = radioCell.children[0];
        if (radio.checked){
            let b=i+1;
            alert("selected " + b);
            let idFlight = tr.children[1].innerHTML;
            let idAirplane = tr.children[2].innerHTML;
            let from = tr.children[3].innerHTML;
            let to = tr.children[4].innerHTML;
            let date = tr.children[5].innerHTML;

            let flight = {
                idFlight : idFlight,
                idAirplane : idAirplane,
                from : from,
                to : to,
                date :date
            }

            let id = flight.idFlight;

            document.getElementById("idFlightDelete").value=id;

            let form=document.getElementById("formFlightDelete");

            form.submit();

        }

    }
}