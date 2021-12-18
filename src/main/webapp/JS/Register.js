var strength = {
    0: "Zeer slecht ☹",
    1: "Slecht ☹",
    2: "Matig ☹",
    3: "Goed ☺",
    4: "Sterk ☻"
}

let pass = document.getElementById('password');
let meter = document.getElementById('password-meter');
let text = document.getElementById('password-text');
let lowerCaseLetters = /[a-z]/g;
let upperCaseLetters = /[A-Z]/g;
let numbers = /[0-9]/g;
let other = /.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/;


pass.addEventListener("input", function()
{
    var val = pass.value;
    var result = calculateResult(val);

    meter.value = result;

    if(val !== "") {
        text.innerHTML = "Wachtwoord sterkte: " + "<strong>" + strength[result] + "</strong>"
        meter.value = result;
    }
    else {
        text.innerHTML = "";
    }
});

pass.addEventListener("input", function(){
    var val = pass.value;

    if(val.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");
    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
    }
    var upperCaseLetters = /[A-Z]/g;
    if(val.match(upperCaseLetters)) {
        capital.classList.remove("invalid");
        capital.classList.add("valid");
    } else {
        capital.classList.remove("valid");
        capital.classList.add("invalid");
    }

    var numbers = /[0-9]/g;
    if(val.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }

    if(val.length >= 8) {
        len.classList.remove("invalid");
        len.classList.add("valid");
    } else {
        len.classList.remove("valid");
        len.classList.add("invalid");
    }

    if(val.match(other)){
        char.classList.remove("invalid");
        char.classList.add("valid");
    } else {
        char.classList.remove("valid");
        char.classList.add("invalid");
    }
});

function calculateResult(str){
    var score = 0;
    if(str.match(lowerCaseLetters)){
        score++
    }
    if(str.match(upperCaseLetters)){
        score++
    }
    if(str.match(numbers)){
        score++
    }
    if(str.length >= 8){
        score++
    }
    if(str.match(other)){
        score++
    }
    return score;
}

