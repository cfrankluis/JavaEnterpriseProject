
window.onload = function () {
    getUser()
    document.getElementById("addButton").addEventListener("click", addNewReim);
    document.getElementById("logoutButton").addEventListener("click", logout);
}



function getUser() {

    // STEP 1: create the XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: create the callback function for readyState changes
    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("readyState is 4!!! AND status is 200!!! getAllReimbur");

            let user = JSON.parse(xhttp.responseText);
            console.log(user);
            printUsername(user);
            // printProfilePic(user);
            // printBio(user);
            // getPosts(user);
        }
    }

    //  STEP 3: prepare connection/request details
    xhttp.open('GET', `http://localhost:9022/curentUser`);
    // STEP 4: send the request, providing any body object the request needs
    xhttp.send();
}//getAllReimbur





function printUsername(user) {
    console.log("in username " + user)
    document.getElementById("name")
    

}//printAll