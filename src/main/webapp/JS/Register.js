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
        letter.classList.remove("invalid2");
        letter.classList.add("valid2");
    } else {
        letter.classList.remove("valid2");
        letter.classList.add("invalid2");
    }
    var upperCaseLetters = /[A-Z]/g;
    if(val.match(upperCaseLetters)) {
        capital.classList.remove("invalid2");
        capital.classList.add("valid2");
    } else {
        capital.classList.remove("valid2");
        capital.classList.add("invalid2");
    }

    var numbers = /[0-9]/g;
    if(val.match(numbers)) {
        number.classList.remove("invalid2");
        number.classList.add("valid2");
    } else {
        number.classList.remove("valid2");
        number.classList.add("invalid2");
    }

    if(val.length >= 8) {
        len.classList.remove("invalid2");
        len.classList.add("valid2");
    } else {
        len.classList.remove("valid2");
        len.classList.add("invalid2");
    }

    if(val.match(other)){
        char.classList.remove("invalid2");
        char.classList.add("valid2");
    } else {
        char.classList.remove("valid2");
        char.classList.add("invalid2");
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

