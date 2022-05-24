const url = window.location.href;
const ip = url.split('/')[2].split(':')[0];



window.onload = function () {
    document.getElementById("reset").addEventListener("click", changePassword);
}

function Previous() {
    window.history.back()
}

function changePassword() {
    let myPass1 = document.getElementById('password1').value;
    let myPass2 = document.getElementById('password2').value;
    if (myPass1 == myPass2) {
        newPass = {
            "password": myPass1
        }
        console.log(newPass);
        //step 1
        let xhttp = new XMLHttpRequest();
        xhttp.open('Post', `http://`+ip+`/:9022/updateUserDetails`);
        xhttp.setRequestHeader("Accept", "application/json");
        xhttp.setRequestHeader("Content-Type", "application/json");


        //STEP 2: create the callback function for readyState changes

        //this is the response 
        xhttp.onreadystatechange = function () {
            console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4) {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    console.log("readyState is 4!!! AND status is 200!!!");
                    alert("Password updated successfully!!!");
                    location.assign(xhttp.responseURL);
                }
                else {
                    alert("Password failed to update!");
                }
            }
        }
        data = JSON.stringify(newPass);
        xhttp.send(data);
    }
}