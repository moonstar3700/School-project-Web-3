let inputHome = document.getElementById("home")
let inputAway = document.getElementById("away")
let inputDate = document.getElementById("date")
let inputTime = document.getElementById("time")

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
    if (home.length === 0 || home === null) {
        return "No home team given"
    }

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
    error.innerHTML = message
}

function validateAway() {
    let message = getAwayError()
    showAwayValidity(message)
}
function getAwayError() {
    let away = inputAway.value
    if (away.length === 0 || away === null) {
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
    error.innerHTML = message
}

function validateDate() {
    let message = getDateError()
    showDateValidity(message)
}
function getDateError() {
    let date = inputDate.value
    if (date.length === 0 || date === null) {
        return "No date given"
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
    error.innerHTML = message
}

function validateTime() {
    let message = getTimeError()
    showTimeValidity(message)
}
function getTimeError() {
    let time = inputTime.value
    if (time.length === 0 || time === null) {
        return "No time given"
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
    error.innerHTML = message
}

