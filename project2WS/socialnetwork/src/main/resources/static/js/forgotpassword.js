const url = window.location.href;
const ip = url.split('/')[2].split(':')[0];

window.onload = function(){
    document.getElementById("resetpw").addEventListener("click", forgotPassword);
}

function Previous() {
    window.history.back()
}


function forgotPassword(){
    let myEmail = document.getElementById("email").value;

      
    let user = {
        "email" : myEmail
    }
    //step 1
      let xhttp = new XMLHttpRequest();
      
     
      //STEP 2: create the callback function for readyState changes

       //this is the response 
    xhttp.onreadystatechange = function (){
        console.log("readyState is changing: ", xhttp.readyState);
        
        if(xhttp.readyState==4 && xhttp.status ==200){
            alert(xhttp.responseText);
            if(xhttp.responseText === "A password reset link has been emailed to you"){
                location.assign("http://`+ip+`:9022/html/welcome.html");
            }
            if(xhttp.responseText === "Email Address could not be found; Try again"){
                location.reload();
            }
        }
    }
    
        
        
  
   // STEP 3: prepare connection/request details
    xhttp.open('post', `http://`+ip+`:9022/forgotPassword`);
    
    
    
   // STEP 4: send the request, providing any body object the request needs    
   
   console.log(user)
    //into json
    xhttp.setRequestHeader("content-type", "application/json")
    xhttp.send(JSON.stringify(user));


}