'use strict'
// let globalVars = {
//     //USER INFO HERE
//     user :{
//         ID: 0,
//         name : "testUser",
//         firstname: "firstname",
//         lastname: "lastname"
//     }
// }

window.onload = function(){
    console.log("CONNECTED");
    getAllPost();
    document.getElementById("postSubmit").addEventListener('click',sendPost);
    //generateTestPost(1);
}

function sendPost(){
    let postContent = document.getElementById("postContent").value;
    if(postContent ){
        console.log("AJAX request here");
         let xhttp = new XMLHttpRequest();
         xhttp.onreadystatechange = function(){
             if(xhttp.readyState==4 && xhttp.status ==200){
                 let responseJson = JSON.parse(xhttp.responseText);
                 generatePost(responseJson);        
            }
        }
        xhttp.open('POST', "http://localhost:9022/post");
        xhttp.send();
    }
}

function getAllPost(){
    console.log("AJAX request here");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState==4 && xhttp.status ==200){
            let responseJson = JSON.parse(xhttp.responseText);
            if(responseJson){
                for (const post of responseJson) {
                    generatePost(post);
                };
            }          
        }
    }
    xhttp.open('GET', "http://localhost:9022/global");
    xhttp.send();
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
    xhttp.open('GET', `http://localhost:9022/commentbypost?id=${id}`);

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
    let postBody = post.querySelector(".card-body");
    let postCard = post.querySelector(".card");
    let postFooter = post.querySelector("p");
    let commentCheck = post.querySelector(".commentCheck");
    let likeBtn = post.querySelector(".likebtn");
    let numOfLikes = post.querySelector("span");
    let addCommentForm = post.querySelector(".addcomment");
    
    postHeader.innerText = postObject.author.username;
    postBody.innerText = postObject.content;
    postFooter.innerText = postObject.dateCreated;

    numOfLikes.setAttribute("id",postObject.postId+"_numOfLikes");
    commentCheck.setAttribute("id",postObject.postId+"_showCom");
    likeBtn.setAttribute("id",postObject.postId+"_like");
    addCommentForm.setAttribute("id",postObject.postId+"_form");

    addCommentForm.hidden = true;
    commentCheck.addEventListener('change',function(){
        showComments(postObject.postId);
    })

    likeBtn.addEventListener('click',function(){
        likePost(postObject.postId);
    });

    let commentContainer = document.createElement("ul");
    commentContainer.setAttribute("class","list-group list-group-flush");
    commentContainer.setAttribute("id", postObject.postId+"_comments");
    postCard.appendChild(commentContainer);

    postContainer.prepend(post);
}

function likePost(id){
    let likeBtn = document.getElementById(id+"_like");
    let numOfLikes = document.getElementById(id+"_numOfLikes");
    likeBtn.innerText = "Liked";
    likeBtn.disabled = true;
    numOfLikes.innerText = 1;
}