function getOptions() {

    let id=document.getElementById("idFlight");
    let idFlight = id.value;
    alert(idFlight);

    let luggage = document.getElementById("luggage");
    if (luggage.checked){
        alert(luggage.checked);
    } else{
        alert("without luggage")
    }
    let vip = document.getElementById("vip");
    if (vip.checked){
        alert(vip.checked);
    } else{
        alert("without vip")
    }
    let option = {
        idFlight: idFlight,
        luggage: luggage.checked,
        vip: vip.checked
    }

    let optionJson =JSON.stringify(option);

    alert(optionJson);

    priceAjax('POST', 'getPrice', optionJson, true);


}
