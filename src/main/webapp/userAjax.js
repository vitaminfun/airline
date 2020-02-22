function userAjax(method, url, request, async) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, async);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let userArray =JSON.parse(this.responseText);
            fillTable(userArray);

        }

    };
    xhr.send(request);
}
function fillTable(userArray){
    let tableBody = document.getElementById("tableBody");

    for(let i = 0; i < userArray.length; i++){
        let user = userArray[i];

        let row = document.createElement("TR");
        tableBody.appendChild(row);

        let radio  = document.createElement("input");
        radio.type="radio";
        radio.id="radio" + i;
        radio.name = "choise";
        let tdRadio = document.createElement("TD");
        let tdIdUser = document.createElement("TD");
        let tdLogin = document.createElement("TD");
        let tdFirstName = document.createElement("TD");
        let tdLastName = document.createElement("TD");
        let tdEmail = document.createElement("TD");
        let tdPassword = document.createElement("TD");
        let tdIsAdmin = document.createElement("TD");

        tdRadio.appendChild(radio);

        row.appendChild(tdRadio);
        row.appendChild(tdIdUser);
        row.appendChild(tdLogin);
        row.appendChild(tdFirstName);
        row.appendChild(tdLastName);
        row.appendChild(tdEmail);
        row.appendChild(tdPassword);
        row.appendChild(tdIsAdmin);

        tdIdUser.innerHTML = user["id"];
        tdLogin.innerHTML = user["login"];
        tdFirstName.innerHTML = user["firstName"];
        tdLastName.innerHTML = user["lastName"];
        tdEmail.innerHTML = user["email"];
        tdPassword.innerHTML = user["password"];
        tdIsAdmin.innerHTML = user["admin"];

    }

}