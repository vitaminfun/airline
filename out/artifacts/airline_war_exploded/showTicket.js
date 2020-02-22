function showTicket() {

        let id=document.getElementById("idFlight");
        let idFlight = id.value;
        alert(idFlight);
        let luggage = document.getElementById("luggage").checked;
        alert(luggage);
        let vip = document.getElementById("vip").checked;
        alert(vip);

    let form=document.getElementById("formTicket");
    form.submit();



}