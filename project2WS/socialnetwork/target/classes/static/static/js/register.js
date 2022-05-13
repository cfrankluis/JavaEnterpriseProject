window.onload = function(){
    loadQuestions();
}

function getAllQuestions(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState==4 && xhttp.status ==200){
            let responseJson = JSON.parse(xhttp.responseText);
            console.log(responseJson);
            //generateOption(responseJson);
        }
    }

    xhttp.open('GET', "http://localhost:9022/allquestions");
    xhttp.send();
}

function generateOption(questions){
    let parentSelect = document.getElementById("securityQuestions");
    for(let index in questions){

    }

}

function loadQuestions(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(xhttp.readyState==4 && xhttp.status ==200){
            let responseJson = JSON.parse(xhttp.responseText);
            console.log(responseJson);
        }
    }

    xhttp.open('GET', "http://localhost:9022/seedquestions");
    xhttp.send();
}