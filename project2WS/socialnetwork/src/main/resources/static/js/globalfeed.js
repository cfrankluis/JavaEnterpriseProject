'use strict'

window.onload = function(){
    console.log("CONNECTED");
    getAllPost();
    document.getElementById("postSubmit").addEventListener('click',sendPost);
    //generateTestPost(1);
}
function reload(){
    location.reload();
}

function getAllPost(){
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

function sendPost(){
    let postContent = document.getElementById("postContent").value;
    if(postContent ){
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

       xhttp.open('POST', "http://localhost:9022/comment");
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
    xhttp.open('POST', 'http://localhost:9022/postlike');
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