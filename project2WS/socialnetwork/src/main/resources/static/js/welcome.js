const url = window.location.href;
const ip = url.split('/')[2].split(':')[0];

window.onload = function () {
    document.getElementById("login").addEventListener("click", login);
}




function login() {
    console.count("in login button")
    let myName = document.getElementById('Username').value;
    let myPass = document.getElementById('Password').value;

    login = {
        "username": myName,
        "password": myPass
    }
    //step 1
    let xhttp = new XMLHttpRequest();
    xhttp.open('Post', `http://`+ip+`:9022/login`);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");


    //STEP 2: create the callback function for readyState changes

    //this is the response 
    xhttp.onreadystatechange = function () {
        console.log("readyState is changing: ", xhttp.readyState);

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("readyState is 4!!! AND status is 200!!!");
            console.log("my response: " + xhttp.responseText);
            console.log("my response: " + xhttp.responseURL);
            location.assign(xhttp.responseURL);
        }
    }
    data = JSON.stringify(login);
    xhttp.send(data);
}

