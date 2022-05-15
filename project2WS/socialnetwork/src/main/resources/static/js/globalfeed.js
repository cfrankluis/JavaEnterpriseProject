'use strict'
let globalVars = {
    //USER INFO HERE
    user :{
        ID: 0,
        name : "testUser",
        firstname: "firstname",
        lastname: "lastname"
    }
}

window.onload = function(){
    console.log("CONNECTED");
    document.getElementById("postSubmit").addEventListener('click',generatePost);
    //generateTestPost(5);
}

function sendPostToServer(){
    console.log("AJAX request here");
}

function generatePost(){
    let postContainer = document.getElementById("post-container");
    let post = document.getElementById("postTemplate").content.cloneNode(true);
    let inputContent = document.getElementById("postContent").value;
    let postHeader = post.querySelector(".card-header");
    let postBody = post.querySelector(".card-body");
    let postFooter = post.querySelector(".card-footer");
    

    postHeader.innerText = globalVars.user.name;
    postBody.innerText = inputContent;
    postFooter.innerText = "DATE";

    postContainer.appendChild(post);
}

// function generateTestPost(num){
//     for (let index = 0; index < num; index++) {
//         let mainEl = document.getElementsByTagName("main")[0];
//         let post = document.getElementById("postTemplate");
//         let postContent = post.content.cloneNode(true);
//         let postHead = postContent.querySelector(".card-header");
        
//         mainEl.appendChild(postContent);

//         console.log(postContent);
//         console.log(postHead);
//     }
// }