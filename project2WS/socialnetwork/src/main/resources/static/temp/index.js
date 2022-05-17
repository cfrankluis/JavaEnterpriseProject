window.onload = function(){
    document.getElementById("myLogin").addEventListener("click", login);
}



function login(){
    let myName = document.getElementById("username").value;
    let myPass = document.getElementById("password").value;
      
    let login = {
        username : myName,
        password : myPass
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

            window.location.href = JSON.parse(xhttp.responseText);
        }
    }
    
    
        
  
   // STEP 3: prepare connection/request details
    xhttp.open('post', `http://localhost:9022/login`);
    
    
    
   // STEP 4: send the request, providing any body object the request needs    
   
   console.log(login)
    //into json
    xhttp.setRequestHeader("content-type", "application/json")
    xhttp.send(JSON.stringify(login));


}