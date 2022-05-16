window.onload = function () {
    console.log("onload");
    getUser();
}



function getUser() {

    // STEP 1: create the XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: create the callback function for readyState changes
    xhttp.onreadystatechange = function () {
        console.log(xhttp.readyState);

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("readyState is 4!!! AND status is 200!!! getAllReimbur");

            let user = JSON.parse(xhttp.responseText);

            printUsername(user);
            printProfilePic(user);
            printBio(user);
            getPosts(user);
        }
    }

    //  STEP 3: prepare connection/request details
    xhttp.open('GET', `http://localhost:9022/currentUser`);
    // STEP 4: send the request, providing any body object the request needs
    xhttp.send();
}//getgetUser




// function getRequests(){
//     let xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function (){
//         if(xhttp.readyState==4 && xhttp.status ==200){
//             console.log("readyState is 4!!! AND status is 200!!!");
//             let requestObj = JSON.parse(xhttp.responseText);
//             console.log(requestObj);
//             ourDOMManipulation(requestObj);
//         }
//     }
//     xhttp.open('POST', http://localhost:9001/ExpenseReimbursementSystem/json/pastrequests);
//     xhttp.send();
// }



function printUsername(user) {

    console.log("in username " + user)
    let username = document.getElementById("name")
    username.innerHTML = "";
    username.innerHTML = user.username;
}//printUsername

function printProfilePic(user) {

    console.log("in printProfilePic " + user)
    let profilePic = document.getElementById("ProfilePic")
    profilePic.setAttribute("src", "https://buckylebucket.s3.us-east-2.amazonaws.com/ProfilePics/" + user.userId + "/ProfilePic.jpg");
}//printProfilePic

function printBio(user) {

    console.log("in printBio " + user)
    let bio = document.getElementById("bio")
    bio.innerHTML = "";
    bio.innerHTML = user.bio;
}//printBio

function getPosts(user) {

    console.log("in getPosts " + user)
    let posts = document.getElementById("bio")
    posts.innerHTML = "";
    posts.innerHTML = user.posts;

    user.posts.author
    user.posts.img
    user.posts.content
    
    
    let varOl = document.getElementById("displayedPost");
    varOl.innerHTML = "";
        for (let i = 0; i < user.length; i++) {
            console.log(i)
            let spaceing = document.createTextNode("  -  ");
            let varLi = document.createElement("li");
            
            //order, spacing, and what varables to print
            varLi.appendChild(document.createTextNode(user.posts.author));
            varLi.appendChild(spaceing.cloneNode());
            
            varLi.appendChild(document.createTextNode(user[i].amount));
            varLi.appendChild(spaceing.cloneNode());
            
            varLi.appendChild(document.createTextNode(user[i].description));
            varLi.appendChild(spaceing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].statusId));
            // varLi.appendChild(spaceing.cloneNode());
            varLi.appendChild(document.createTextNode(user[i].status));
            varLi.appendChild(spaceing.cloneNode());
            
            varLi.appendChild(document.createTextNode(user[i].type));
            //each reimburement on its own line
            varOl.appendChild(varLi);
        }//for
}//getPosts