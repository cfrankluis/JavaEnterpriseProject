window.onload = function () {
    document.getElementById("update").addEventListener("click", updateBio);
}

function Previous() {
    window.history.back()
}

function updateBio() {

let updatedBio = document.getElementById('Bio').value;
    newBio = {
        "bio": updatedBio
    }
   
    let xhttp = new XMLHttpRequest();
    xhttp.open('Post', `http://54.226.130.109:9022/updateUserDetails`);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");


    //STEP 2: create the callback function for readyState changes

    //this is the response 
    xhttp.onreadystatechange = function () {
        console.log("readyState is changing: ", xhttp.readyState);
        if (xhttp.readyState == 4) {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                console.log("readyState is 4!!! AND status is 200!!!");
                alert("Bio updated successfully!!!");
                location.assign(xhttp.responseURL);
            }
            else {
                alert("Bio failed to update!");
            }
        }
    }
    data = JSON.stringify(newBio);
    xhttp.send(data);
}
