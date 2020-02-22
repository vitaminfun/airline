function showFormReg() {
	document.getElementById("reg").hidden = false;
	document.getElementById("linkForAuth").hidden=true;
	document.getElementById("linkForReg").hidden=true;
}

function sendReg(){
    var inputRegLogin=document.getElementById("nameReg");
    let inputFirstNmae=document.getElementById("firstName");
    let inputLastName=document.getElementById("lastName");
    var inputRegEmail=document.getElementById("emailReg");
    var inputRegPass=document.getElementById("passReg");

    var loginReg = inputRegLogin.value;
    let firstNameReg = inputFirstNmae.value;
    let lastNameReg = inputLastName.value;
    var emailReg= inputRegEmail.value;
    var passwordReg = inputRegPass.value;

    var user={
        login:loginReg,
        firstName:firstNameReg,
        lastName:lastNameReg,
        email: emailReg,
        password:passwordReg
    };

    var jsonReg=JSON.stringify(user);

    ajax('POST','sendReg', jsonReg ,true);
    
}
