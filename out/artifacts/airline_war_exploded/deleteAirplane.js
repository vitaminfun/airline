function deleteAirplane(){
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
            let idAirplane = tr.children[1].innerHTML;
            let name = tr.children[2].innerHTML;
            let numbersOfSeats = tr.children[3].innerHTML;

            let airplane = {
                idAirplane : idAirplane,
                name : name,
                numberOfSeats : numbersOfSeats,

            }

            let id = airplane.idAirplane;

            document.getElementById("idAirplaneDelete").value=id;

            let form=document.getElementById("formAirplaneDelete");

            form.submit();

        }

    }
}