const url = window.location.href;
const ip = url.split('/')[2].split(':')[0];

window.onload = function () {
    document.getElementById("button").addEventListener("click", uploadProfilePic);
}

function Previous() {
    window.history.back()
}

function uploadProfilePic() {
    let formData = new FormData();
    formData.append("file", fileupload.files[0]);
    try {
        let xhttp = new XMLHttpRequest();
        xhttp.open('Post', `http://`+ip+`:9022/upload`);
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 202) {
                let message = xhttp.responseText;
                alert(message);
                location.assign("http://`+ip+`:9022/html/profilepage.html");
            }
        }
        xhttp.send(formData);
    } catch (error) {
    }
}