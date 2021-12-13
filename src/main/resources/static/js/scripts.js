/*!
* Start Bootstrap - Business Casual v7.0.6 (https://startbootstrap.com/theme/business-casual)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-business-casual/blob/master/LICENSE)
*/
// Highlights current date on contact page
window.addEventListener('DOMContentLoaded', event => {
    const listHoursArray = document.body.querySelectorAll('.list-hours li');
    listHoursArray[new Date().getDay()].classList.add(('today'));
});

function checkPass(){

    //Store the password field objects into variables ...
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field
    //and the confirmation field
    if(pass1.value == pass2.value){
        //The passwords match.
        //Set the color to the good color and inform
        //the user that they have entered the correct password
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Las contraseñas coinciden!"
        $("#registerSubmit").removeClass('disabled');
    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Las contraseñas no coinciden!";
        $("#registerSubmit").addClass('disabled');
    }
}

function email_validate(email, instance) {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

    if (regMail.test(email) == false) {
        if(instance == "reg"){
            document.getElementById("statusReg").innerHTML = "<span class='text-warning'>Este email no es valido.</span>";
        }else{
            document.getElementById("status").innerHTML = "<span class='text-warning'>Este email no es valido.</span>";
        }
    } else {
        if(instance == "reg"){
            document.getElementById("statusReg").innerHTML = "<span class='text-success'>Mail Correcto</span>";
        }else{
            document.getElementById("status").innerHTML = "<span class='text-success'>Mail correcto</span>";
        }

    }
}