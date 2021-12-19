let inputHome = document.getElementById("home")
let inputAway = document.getElementById("away")
let inputDate = document.getElementById("date")
let inputTime = document.getElementById("time")
let errorHome = document.getElementById("error-home")
let errorAway = document.getElementById("error-away")
let errorDate = document.getElementById("error-date")
let errorTime = document.getElementById("error-time")
let button = document.getElementById('addMatch')

// start the validation after the page is fully loaded
window.addEventListener("load", init);

function init() {
    // blur = validate a field after it loses focus
    inputHome.addEventListener("blur", validateHome)
    inputAway.addEventListener("blur", validateAway)
    inputDate.addEventListener("blur", validateDate)
    inputTime.addEventListener("blur", validateTime)
    button.addEventListener("click", validateAll)
}

/**
 * Validates all fields. If any field is invalid,
 * blocks the normal submit action and warns the user.
 *
 * @param event
 */

function validateAll(event) {
    // validate all fields
    validateHome()
    validateAway()
    validateDate()
    validateTime()
    // search for fields which are invalid
    let invalidFields = document.getElementsByClassName("invalid")
    if (invalidFields.length > 0) {
        // prevent the default submit action
        event.preventDefault()
        // alert the user
        window.alert("Niet alle velden zijn correct ingevuld.")
        // focus on the first invalid field
        invalidFields[0].focus()
    }
}

/**
 * Checks for errors in the postcode
 * and updates the visuals.
 */
function validateHome() {
    let message = getHomeError()
    showHomeValidity(message)
}

/**
 * Returns an error message if a requirement
 * for the postcode has NOT been met.
 * Else, returns an empty string.
 * @returns {string}
 */
function getHomeError() {
    let home = inputHome.value
    if (home.length === 0) {
        return "No home team given"
    }
    return ""

}

/**
 * Updates the visuals of the input field
 * and shows the given message if it is not empty.
 * @param message
 */
function showHomeValidity(message) {
    if (message === "") {
        // no errors found
        inputHome.className = "valid"
    } else {
        // errors encountered
        inputHome.className = "invalid"
    }
    errorHome.innerHTML = message
}

function validateAway() {
    let message = getAwayError()
    showAwayValidity(message)
}
function getAwayError() {
    let away = inputAway.value
    if (away.length === 0) {
        return "No away team given"
    }
    return ""

}

function showAwayValidity(message) {
    if (message === "") {
        // no errors found
        inputAway.className = "valid"
    } else {
        // errors encountered
        inputAway.className = "invalid"
    }
    errorAway.innerHTML = message
}

function validateDate() {
    let message = getDateError()
    showDateValidity(message)
}
function getDateError() {
    let date = inputDate.value
    if (date.length === 0) {
        return "No date given"
    }
    let formatDateRegex = /^(\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/
    if (!date.match(formatDateRegex)) {
        return "Wrong date format. Use YYYY-MM-DD. For a better experience use a different browser"
    }
    if (regexDate = date.match(formatDateRegex)) {
        if (!isValidDate(parseInt(regexDate[3]), parseInt(regexDate[2]), parseInt(regexDate[1]))) {
            return "Invalid date. Date format is YYYY-MM-DD. Pay attention to leap years. For a better experience use a different browser"
        }
    }
    let today = new Date()
    if (new Date(date) < today) {
        return "Date must be in the future. Today is: " + today
    }


    return ""

}

function showDateValidity(message) {
    if (message === "") {
        // no errors found
        inputDate.className = "valid"
    } else {
        // errors encountered
        inputDate.className = "invalid"
    }
    errorDate.innerHTML = message
}

function validateTime() {
    let message = getTimeError()
    showTimeValidity(message)
}
function getTimeError() {
    let time = inputTime.value
    if (time.length === 0) {
        return "No time given"
    }
    let timeRegex = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/
    if (!time.match(timeRegex)) {
        return "Wrong time format. Use HH:MM using 24h time with no AM or PM. For a better experience use a different browser"
    }
    return ""

}

function showTimeValidity(message) {
    if (message === "") {
        // no errors found
        inputTime.className = "valid"
    } else {
        // errors encountered
        inputTime.className = "invalid"
    }
    errorTime.innerHTML = message
}

function has30Days(month) {
    return month === 4 || month === 6 || month === 9 || month === 11;
}

function has31Days(month) {
    return month === 1 || month === 3 || month === 5 || month === 7 || month === 8 || month === 10 || month === 12;
}

function isLeapYear(year) {
    return (isDivisibleBy(year, 4) && !isDivisibleBy(year, 100) || isDivisibleBy(year, 400));
}

function has28Days(month, year) {
    return(month===2 && !isLeapYear(year))
}

function has29Days(month, year) {
    return(month===2 && isLeapYear(year))
}

function isValidDate(day, month, year) {
    return (month >= 1 && month <= 12) &&
        (has31Days(month) && day >= 1 && day <= 31) ||
        (has30Days(month) && day >= 1 && day <= 30) ||
        (has28Days(month, year) && day >= 1 && day <= 28) ||
        (has29Days(month, year) && day >= 1 && day <= 29);
}

