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
// When the user scrolls the page, execute myFunction
window.onscroll = function() {myFunction()};

// Get the navbar
var navbar = document.getElementById("mainNav");

// Get the offset position of the navbar
var sticky = navbar.offsetTop;

// Add the sticky class to the navbar when you reach its scroll position. Remove "sticky" when you leave the scroll position
function myFunction() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
}


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

function facePass(){
    $("#iconLog").removeClass('fa-laugh');
    $("#iconLog").addClass('bi-emoji-sunglasses-fill');
}
function emailPass() {
    $("#iconLog").removeClass('bi-emoji-sunglasses-fill');
    $("#iconLog").addClass('fa-laugh');
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

const Countdown = (() => {

    //let nextMidnight = new Date();
    //nextMidnight.setHours(24,0,0,0);
    let descontar = 10000;
    let nextTime = new Date(new Date().getTime() + descontar);

    const getRemainingTime = () => {
        let now = new Date();

        let time = (nextTime.getTime() - now.getTime())/1000;//(nextMidnight.getTime() - now.getTime())/1000;

        if(time <= 0) {
            //nextMidnight = new Date();
            //nextMidnight.setHours(24,0,0,0);
            time = 0;
            location.reload();
        }

        return time;
    }

    const parseTime = (time) => {
        const hours = Math.floor(time/3600);
        let rest = time-(hours*3600);
        const minutes = Math.floor(rest/60);
        rest = rest-(minutes*60);
        const seconds = Math.floor(rest);
        const milliseconds = (rest-seconds)*1000;

        return [hours, minutes, seconds, milliseconds];
    };

    const formatTime = (parsedTime) => {
        return '<span class="hours">' + parsedTime[0] + '</span><span class="hSep">:</span><span class="minutes">' + ("0" + parsedTime[1]).slice(-2) + '</span><span class="mSep">:</span><span class="seconds">' + ("0" + parsedTime[2]).slice(-2) + '</span>';
    };

    const els = [];
    let timeout;

    return (el) => {
        els.push(el);

        if(!timeout) {

            const refresh = () => {
                const parsedTime = parseTime(getRemainingTime());
                const formattedTimes = formatTime(parsedTime);

                for(let i = 0, iend = els.length; i < iend; i++) {
                    els[i].innerHTML = formattedTimes;
                }

                setTimeout(() => {
                    refresh();
                }, parsedTime[3]);
            };
            refresh();

        }
        else el.innerHTML = formatTime(parseTime(getRemainingTime()));
    };

})();

//Countdown(document.getElementById('countdown'));
Countdown(document.getElementById('countdown-two'));
