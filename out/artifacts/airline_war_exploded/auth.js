function showFormAuth(){
	document.getElementById("auth").hidden=false;
	document.getElementById("linkForAuth").hidden=true;
	document.getElementById("linkForReg").hidden=true;
}
	function sendAuth(){
	let inputLogin=document.getElementById("nameAuth");
	let inputPass=document.getElementById("passAuth");

	let login = inputLogin.value;
	let password = inputPass.value;

	let user={
		login:login,
		password:password
	};
	let json=JSON.stringify(user);

	ajax('POST','sendAuth', json ,true);

}