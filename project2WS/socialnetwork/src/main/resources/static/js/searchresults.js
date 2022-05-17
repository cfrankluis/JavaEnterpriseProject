window.onload = function () {
    document.getElementById("usersubmit").addEventListener('click', getAllUsers);
}



function getAllUsers() {
    let username = document.getElementById("userid").value;

    data = {
        "username": username
    }

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let responseJson = JSON.parse(xhttp.responseText)
            generateUsers(responseJson);
        }
    }


xhttp.open('Post', "http://localhost:9022/friends");
xhttp.send(JSON.stringify(data));
}


function generateUsers(userObject) {
    console.log(userObject);
    let userContainer = document.getElementById("user-container");
    let user = document.getElementById("usersTemplate").content.cloneNode(true);

    let userHeader = user.querySelector(".username a");
    let userBody = user.querySelector(".ProfilePic");
    let userCard = user.querySelector(".bio");
    

    userHeader.innerText = <a href="'http://localhost:9022/profilepage.html/' + 'userObject.userId'">"userObject.firstName + " " + userObject.lastName"</a>;
    userBody.innerText = <img src="'https://buckylebucket.s3.us-east-2.amazonaws.com/ProfilePics/' + userObject.userId +'/ProfilePic.jpg'"/>

    userCard.innerText = userObject.bio;
    

    numOfLikes.setAttribute("id", postObject.postId + "_numOfLikes");
    commentCheck.setAttribute("id", postObject.postId + "_showCom");
    likeBtn.setAttribute("id", postObject.postId + "_like");

    
    userCard.appendChild(userContainer);

    userContainer.prepend(user);
}