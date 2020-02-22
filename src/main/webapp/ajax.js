function ajax(method, url, request, async) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, async);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let serverResponsetext = xhr.responseText;
            if (serverResponsetext==="The username or password you entered is incorrect"|| serverResponsetext==="Successful Registration"){
                alert(serverResponsetext);
                document.location.href="index.html";
            }else {
                if (serverResponsetext==="admin"){
                    document.location.href="indexAdmin.html";
                }else {
                    document.location.href = "indexFlight.html";
                }
            }
        }
    };
    xhr.send(request);

}