window.onload = function () {
    document.getElementById("create").addEventListener("click", registerUser);
}

function Previous() {
    window.history.back()
}

function registerUser() {

    let FirstName = document.getElementById('FirstName').value;
    let LastName = document.getElementById('LastName').value;
    let Email = document.getElementById('Email').value;
    let UserName = document.getElementById('Username').value;
    let Password = document.getElementById('Password').value;

    newUser = {
        "firstName": FirstName,
        "lastName": LastName,
        "email": Email,
        "username": UserName,
        "password": Password
    }

    let xhttp = new XMLHttpRequest();
    xhttp.open('Post', `http://localhost:9022/register1`);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");

    xhttp.onreadystatechange = function () {
        console.log("readyState is changing: ", xhttp.readyState);

        if (xhttp.readyState == 4 && xhttp.status == 201) {
            console.log("readyState is 4!!! AND status is 200!!!");
            let message = xhttp.responseText;
            alert(message);
            if (message === "Field values cannot be blank." || message === "Account Creation failed..." || message === "Please enter a valid email address.") {
                location.reload();
            }
            if (message === "Account Creation Sucessfull!!!") {
                verifyEmail();
                location.assign("http://localhost:9022/html/globalfeedpage.html");
            }
        }
    }
    data = JSON.stringify(newUser);
    xhttp.send(data);
}


function verifyEmail() {
    let xhttp = new XMLHttpRequest();
    xhttp.open('Post', `http://localhost:9022/register`);
        xhttp.onreadystatechange = function () {
            console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4 && xhttp.status == 201) {
                console.log("readyState is 4!!! AND status is 200!!!");
            }
        }
    xhttp.send();
}
