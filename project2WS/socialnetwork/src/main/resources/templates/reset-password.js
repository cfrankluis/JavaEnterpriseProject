window.onload = function(){
    document.getElementById("myPassword").addEventListener("click", newPassword);
}






function newPassword(){
    let myPassword = document.getElementById("newPassword").value;
    let token = window.location.href;
    token = token.substring(token.lastIndexOf(':'))
    console.log(token)
      
    let user = {
        password : myPassword
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
    
        let url = 'http://localhost:9022/reset-password/' + token
         
  
   // STEP 3: prepare connection/request details
    xhttp.open('post', url);
    
    
    
    
   // STEP 4: send the request, providing any body object the request needs    
   
   console.log(user)
    //into json
    xhttp.setRequestHeader("content-type", "application/json")
    xhttp.send(JSON.stringify(user));


}