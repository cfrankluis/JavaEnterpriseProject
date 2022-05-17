window.onload = function () {
    // checkIfLogedIn() //come back to if there is time
    getAllReimbur()
    document.getElementById("addButton").addEventListener("click", addNewReim);
    document.getElementById("logoutButton").addEventListener("click", logout);
}





function printAll(reimbur) {
    console.log(reimbur)
    let varOl = document.getElementById("displayedReimbur");
    varOl.innerHTML = "";
    try {
        for (let i = 0; i < reimbur.length; i++) {
            console.log(i)
            let spaceing = document.createTextNode("  -  ");
            let varLi = document.createElement("li");

            //order, spacing, and what varables to print
            varLi.appendChild(document.createTextNode(reimbur[i].reimbursId));
            varLi.appendChild(spaceing.cloneNode());
            
            varLi.appendChild(document.createTextNode(reimbur[i].amount));
            varLi.appendChild(spaceing.cloneNode());
            
            varLi.appendChild(document.createTextNode(reimbur[i].description));
            varLi.appendChild(spaceing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].statusId));
            // varLi.appendChild(spaceing.cloneNode());
            varLi.appendChild(document.createTextNode(reimbur[i].status));
            varLi.appendChild(spaceing.cloneNode());
            
            varLi.appendChild(document.createTextNode(reimbur[i].type));
            
            // for next update
            // //order, spacing, and what varables to print
            // varLi.appendChild(document.createTextNode(reimbur[i].reimbursId));
            // varLi.appendChild(spacing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].amount));
            // varLi.appendChild(spacing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].description));
            // varLi.appendChild(spacing.cloneNode());

            // varLi.appendChild(document.createTextNode(reimbur[i].submitted));
            // varLi.appendChild(spacing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].status));
            // varLi.appendChild(spacing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].type));
            // varLi.appendChild(spacing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].resolver));
            // varLi.appendChild(spacing.cloneNode());
            
            // varLi.appendChild(document.createTextNode(reimbur[i].resulved));



            //each reimburement on its own line
            varOl.appendChild(varLi);
        }//for
    } catch { }//try catch
}//printAll


function getAllReimbur() {
    let textField = document.getElementById('displayedReimbur').value;
    // STEP 1: create the XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: create the callback function for readyState changes
    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("readyState is 4!!! AND status is 200!!! getAllReimbur");

            let reimbur = JSON.parse(xhttp.responseText);
            printAll(reimbur);
        }
    }

    //  STEP 3: prepare connection/request details
    xhttp.open('GET', `http://localhost:8080/reimbursement_project1/Employee/my-reimburs`);
    // STEP 4: send the request, providing any body object the request needs
    xhttp.send();
}//getAllReimbur




function logout() {
    // STEP 1: create the XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: create the callback function for readyState changes
    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log("readyState is 4!!! AND status is 200!!! logout");

            window.location.href = JSON.parse(xhttp.responseText);
        }
    }

    //  STEP 3: prepare connection/request details
    xhttp.open('POST', `http://localhost:8080/reimbursement_project1/login/logout`);
    // STEP 4: send the request, providing any body object the request needs
    xhttp.send();
}


function addNewReim(){
    let myAmount = document.getElementById('amount').value;
    let myDescription = document.getElementById('description').value;

    let myTypeSelected;

    var ele = document.getElementsByName('typeId');
          
    for(i = 0; i < ele.length; i++) {
        if(ele[i].checked){
            myTypeSelected = ele[i].value;
            break;
        }else{
            myTypeSelected = 'no input'
        }
    }

    console.log(myTypeSelected)


    let reimToAdd = {
        amount : myAmount,
        description : myDescription,
        typeId : myTypeSelected
    }


        // STEP 1: create the XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: create the callback function for readyState changes
    xhttp.onreadystatechange = function (){
        if(xhttp.readyState==4 && xhttp.status ==200){
            console.log("readyState is 4!!! AND status is 200!!! addNewReim");
            try{
                let responce = JSON.parse(xhttp.responseText);
                console.log("did not work " + xhttp.responseText)
                let badInput = document.getElementById("didNotWork");
                badInput.innerHTML = 'That your input is not correct pleace try again: make sure to select a reimbursement type'

            }catch{
                let varOl = document.getElementById("displayedReimbur");
                varOl.innerHTML = "Loading";
                getAllReimbur();
            }
        }
    }
    // STEP 3: prepare connection/request details

    xhttp.open('POST', `http://localhost:8080/reimbursement_project1/Employee/create-reimburs`);

    // STEP 4: send the request, providing any body object the request needs
   xhttp.setRequestHeader("content-type", "application/json")
   xhttp.send(JSON.stringify(reimToAdd));
}



// function checkIfLogedIn() {
//     // STEP 1: create the XMLHttpRequest Object
//     let xhttp = new XMLHttpRequest();

//     // STEP 2: create the callback function for readyState changes
//     xhttp.onreadystatechange = function () {

//         if (xhttp.readyState == 4 && xhttp.status == 200) {
//             console.log("readyState is 4!!! AND status is 200!!!");
//             console.log("my response: " + xhttp.responseText);

//             window.location.href = JSON.parse(xhttp.responseText);
//         }
//     }

//     //  STEP 3: prepare connection/request details
//     xhttp.open('put', `http://localhost:8080/reimbursement_project1/login/check`);
//     // STEP 4: send the request, providing any body object the request needs
//     xhttp.send();

// }