function deleteUser() {

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
            let idUser = tr.children[1].innerHTML;
            let login = tr.children[2].innerHTML;
            let firstName = tr.children[3].innerHTML;
            let lastName = tr.children[4].innerHTML;
            let email = tr.children[5].innerHTML;
            let password = tr.children[6].innerHTML;
            let admin = tr.children[7].innerHTML;

            let airplane = {
                idUser : idUser,
                login : login,
                firstNames : firstName,
                lastName : lastName,
                email : email,
                password : password,
                admin : admin,

            }

            let id = airplane.idUser;
            alert(id);
            document.getElementById("idUserDelete").value=id;

            let form=document.getElementById("formUserDelete");

            form.submit();

        }

    }

}