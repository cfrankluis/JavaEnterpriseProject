const url = window.location.href;
const ip = url.split('/')[2].split(':')[0];

window.onload = function () {
    friendOrMe();
}

function friendOrMe(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const user = urlParams.get('user')
    console.log(user);
    if (user == null){
        getUser();
    }
    else{
        getFriend(user);
    }
}

function getUser() {
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', `http://`+ip+`:9022/currentUser`);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.onreadystatechange = function () {
        console.log(xhttp.readyState);
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("readyState is 4!!! AND status is 200!!! getAllReimbur");

            let user = JSON.parse(xhttp.responseText);

            printUsername(user);
            printProfilePic(user);
            printBio(user);
            for (const posts of user.posts){
                generatePost(posts);
            }
        }
    }
    xhttp.send();
}

function getFriend(user) {
    friend = {
        "username": user
    }
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', `http://`+ip+`:9022/currentFriend`);
    xhttp.setRequestHeader("Accept", "application/json");
    xhttp.setRequestHeader("Content-Type", "application/json");
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
    xhttp.open('GET', `http://`+ip+`:9022/currentUser`);
    // STEP 4: send the request, providing any body object the request needs
     xhttp.send(JSON.stringify(friend));
}//getgetUser


   


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

    console.log("in getPosts " + user);
    console.log(user);
    console.log(user.posts);

    let post = document.getElementById("posts");

    let postsAuthor = document.getElementById("postsAuthor");
    let postsContent = document.getElementById("postsContent");
    // let postsImg = document.getElementById("postsImg");


    // post.innerHTML =""
    postsAuthor.innerHTML = "";
    postsContent.innerHTML = "";
    // postsImg.innerHTML = "";


    for (let i = 0; i < user.posts.length; i++) {
        console.log("i = " + i);
        postsAuthor = document.createElement("h5");
        // .posts[i].author
        author =  document.createTextNode(user.username);
        postsAuthor.appendChild(author);
        post.appendChild(postsAuthor);

        postsContent = document.createElement("p");
        content =  document.createTextNode(user.posts[i].content);
        postsContent.appendChild(content);
        post.appendChild(postsContent);

        // postsImg = document.createElement("img");
        // img =  document.createTextNode(user.posts[i].img);
        // postsImg.appendChild(img);
        // post.appendChild(postsImg);

    }//for
}//getPosts

function sendComment(id){
    let commentInput = document.getElementById(id+"_comField").value;
    if(commentInput){
        console.log(commentInput);
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(xhttp.readyState==4 && xhttp.status ==200){
                let responseJson = JSON.parse(xhttp.responseText);
                generateComment(responseJson, id);     
           }
       }

       xhttp.open('POST', `http://`+ip+`:9022/comment`);
       let commentToSend = {
           "content" : commentInput,
           "post" : {"postId":id}
       }
       xhttp.setRequestHeader("content-type", "application/json");
       xhttp.send(JSON.stringify(commentToSend));
    }
}

function getComments(id){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState===4 && xhttp.status ===200){
            let responseJson = JSON.parse(xhttp.responseText);
            for (const comment of responseJson) {
                generateComment(comment,id);
            }
        }
    }
    xhttp.open('GET', `http://`+ip+`:9022/commentbypost?id=${id}`);

    xhttp.send();
}

function showComments(id){
    let commentCheck = document.getElementById(id+"_showCom");
    let commentForm = document.getElementById(id+"_form");
    if(commentCheck.checked){
        commentForm.hidden = false;
        getComments(id);
    } else {
        commentForm.hidden = true;
        commentForm.querySelector("input").value = '';
        hideComments(id);
    }
}

function hideComments(id){
    let parent = document.getElementById(id+"_comments");
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function generateComment(comment,id){
    let commentContainer = document.getElementById(id+"_comments");
    let commentTemplate = document.getElementById("commentTemplate");
    let commentContent = commentTemplate.content.cloneNode(true);
    let commentWrapper = commentContent.querySelector("li");
    let commentUsername = commentContent.querySelector("a");

    commentUsername.innerText = comment.author.username;
    commentUsername.setAttribute("href","#");

    let content = document.createTextNode(comment.content);
    commentWrapper.appendChild(content);

    commentContainer.appendChild(commentContent);
}

function generatePost(postObject){
    console.log(postObject);
    let postContainer = document.getElementById("post-container");
    let post = document.getElementById("postTemplate").content.cloneNode(true);
    
    let postHeader = post.querySelector(".card-header a");
    let postImg = post.querySelector(".card-img-top");
    let postBody = post.querySelector(".card-body");
    let postCard = post.querySelector(".card");
    let postFooter = post.querySelector("p");
    let commentCheck = post.querySelector(".commentCheck");
    let likeBtn = post.querySelector(".likebtn");
    let numOfLikes = post.querySelector("span");
    let addCommentForm = post.querySelector(".addcomment");
    let commentBtn = addCommentForm.querySelector("button");
    let commentField = addCommentForm.querySelector("input");

    postHeader.innerText = postObject.author.username;
    postHeader.href = `http://`+ip+`:9022/profilepage/?user=` + postObject.author.username;
    if(postObject.img != null){
        postImg.src = postObject.img;
        postImg.sizes = "(max-width: 500px)";
        postImg.hidden = false;
    }
    postBody.innerText = postObject.content;

    postFooter.innerText = (new Date(postObject.dateCreated)).toDateString();
    numOfLikes.innerText = postObject.numOfLikes;

    numOfLikes.setAttribute("id",postObject.postId+"_numOfLikes");
    commentCheck.setAttribute("id",postObject.postId+"_showCom");
    likeBtn.setAttribute("id",postObject.postId+"_like");
    addCommentForm.setAttribute("id",postObject.postId+"_form");
    commentField.setAttribute("id",postObject.postId+"_comField");

    addCommentForm.hidden = true;

    commentCheck.addEventListener('change',function(){
        showComments(postObject.postId);
    })

    likeBtn.addEventListener('click',function(){
        sendLike(postObject.postId);
    });

    commentBtn.addEventListener('click',function(){
        sendComment(postObject.postId);
    })

    let commentContainer = document.createElement("ul");
    commentContainer.setAttribute("class","list-group list-group-flush");
    commentContainer.setAttribute("id", postObject.postId+"_comments");
    postCard.appendChild(commentContainer);

    postContainer.prepend(post);
}

function sendLike(id){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState===4 && xhttp.status ===200){
            let likedPost = JSON.parse(xhttp.responseText);
            likePost(likedPost,id);
        }
    }
    xhttp.open('POST', `http://`+ip+`:9022/postlike`);
    xhttp.setRequestHeader("content-type", "application/json");
    let postToLike = {
        "postId":id
    };
    xhttp.send(JSON.stringify(postToLike));
}

function likePost(post,id){
    let likeBtn = document.getElementById(id+"_like");
    let numOfLikes = document.getElementById(id+"_numOfLikes");
    likeBtn.innerText = "Unlike";
    //likeBtn.disabled = true;
    numOfLikes.innerText = post.numOfLikes;
}


    