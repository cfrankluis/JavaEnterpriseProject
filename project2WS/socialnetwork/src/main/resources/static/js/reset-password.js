window.onload = function(){
    console.log("asdf");
    document.getElementById("myPassword").addEventListener("click", newPassword);
}






function newPassword(){
    let myPassword = document.getElementById("newPassword").value;
    let myUsername = document.getElementById("username").value;
      
    let user = {
        password : myPassword,
        username : myUsername
    }
    //step 1
      let xhttp = new XMLHttpRequest();
      
     
      //STEP 2: create the callback function for readyState changes

       //this is the response 
    xhttp.onreadystatechange = function (){
        console.log("readyState is changing: ", xhttp.readyState);
        
        if(xhttp.readyState==4 && xhttp.status ==200){
            console.log("readyState is 4!!! AND status is 200!!!");
            console.log("my response: " + xhttp.responseText);
        }
    }
    
         
  
   // STEP 3: prepare connection/request details
    xhttp.open('post', `http://localhost:9022/reset-password/`);
    
    
    
    
   // STEP 4: send the request, providing any body object the request needs    
   
    //into json
    xhttp.setRequestHeader("content-type", "application/json")
    xhttp.send(JSON.stringify(user));


}