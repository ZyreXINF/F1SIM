let drivers, oldDriversPositions;

let gapToLeader = false;

let raceStatus;
let currentLap;

let intervalId;

$(document).ready(async function () {
    await requestSessionData();

    switch (raceStatus) {
        case "RACING":
            intervalId = setInterval(raceLogic, 1500);
            enableGapButtton();
            disableRaceButton();
            disableSettingsButton();
            break;
        case "FINISHED":
            await requestSessionData();
            enableGapButtton();
            enableSettingsButton();
            updatePositions();
            updateLapCounter();
            resetButton();
            break;
    }

    $('#race-button').click(async function () {
        switch (raceStatus) {
            case "READY":
                enableGapButtton();
                disableRaceButton();
                disableSettingsButton();
                await startRace();
                intervalId = setInterval(raceLogic, 1500);
                break;
            case "FINISHED":
                disableRaceButton();
                disableSettingsButton();
                await restartRace();
                await requestSessionData();
                clearDNFEffect();
                clearFastestLapEffect();
                updatePositions();
                updateLapCounter();
                intervalId = setInterval(raceLogic, 1500);
                break;
        }
    });
    $('#gap-button').click(async function() {
        switch(raceStatus){
            case "READY":
                console.error("Gap view cannot be changed before race begins")
                break;
            case "RACING":
            case "FINISHED":
                gapToLeader = !gapToLeader;
                updatePositions();
                break;
        }
    });
    $('#settings-button').click(async function(){
        window.location.href = "/raceSetup"; 
    });
});

$(window).on('beforeunload', function(event){
    if(raceStatus != "READY"){
        event.preventDefault();
        event.returnValue = ''; 
        return '';  
    }
});

async function raceLogic(){
    await requestSessionData();
    checkRaceStatus();
    if(raceStatus === "RACING") {
        updatePositions();
    }
    updateLapCounter();
}
function checkRaceStatus(){
    if(raceStatus === "FINISHED"){
        clearInterval(intervalId);
        resetButton();
        enableSettingsButton();
    }
}

//UI Updates
function updatePositions() {
    for(var i = 0; i < drivers.length; i++){
        const driver = drivers[i];
        const newPosition = i;
        const oldPosition = oldDriversPositions.indexOf(driver.fullName);
        let positionDifference = oldPosition - newPosition;
        updateDriverCard(driver, i, positionDifference);
    }
}
function updateLapCounter(){
    let lapElement = document.getElementById("lap-counter");
    if(raceStatus === "RACING"){
        let lapNumber = drivers[0].currentLap;
        lapElement.textContent = "Lap: " + lapNumber + "/" + lapAmount;
    }else if(raceStatus === "FINISHED"){
        lapElement.textContent = "Race Finished";
    }

}
function updateDriverCard(driver, index, positionDifference) {
    const listElement = document.getElementById(`Driver${index}`);
    const elements = listElement.getElementsByTagName("div");

    updateFastestLapState(elements[0], driver);
    updateDriverName(elements[2], driver);
    updateTeamLogo(elements[3], driver);
    updateDriverGapOrStatus(elements, driver, index);
    // updateCardMovement(elements[0], positionDifference);
}
function updateTeamLogo(teamLogoElement, driver){
    teamLogoElement.children[0].src = 'images/logos/' + driver.team.teamName + '.webp'; 
}
function updateDriverName(nameElement, driver) {
    nameElement.textContent = driver.fullName;
}
function updateFastestLapState(teamStripeElement, driver) {
    if (driver.setFastestLap) {
        clearFastestLapEffect();
        teamStripeElement.classList.add("fastest");
    }
}
function updateDriverGapOrStatus(elements, driver, index) {
    const gapElement = elements[4];
    const driverCard = elements[0];

    if (driver.status === "ReliabilityDNF" || driver.status === "CrashDNF") {
        gapElement.textContent = "DNF";
        driverCard.classList.add("dnf");
    } else if (index !== 0) {
        if(gapToLeader){
            gapElement.textContent = "+" + formatTime(driver.raceTime - drivers[0].raceTime);
        }else{
            gapElement.textContent = "+" + formatTime(driver.raceTime - drivers[index - 1].raceTime);
        }
    }
}
function formatTime(time) {
    return (time / 1000).toFixed(3);
}
//Clearing Special Visuals
function clearDNFEffect(){
    for(let i = 0; i < drivers.length; i++){
        let driverCard = document.getElementById(`Driver${i}`);
        let driverCardElements = driverCard.getElementsByTagName("div");
        driverCardElements[0].classList.remove("dnf");
    }
}
function clearFastestLapEffect(){
    for(let i = 0; i < drivers.length; i++){
        let driverCard = document.getElementById(`Driver${i}`);
        let driverCardElements = driverCard.getElementsByTagName("div");
        driverCardElements[0].classList.remove("fastest");
    }
}
//Button updates
function disableRaceButton(){
    let button = document.getElementById("race-button");
    button.disabled = true;
    button.textContent = "Race Started";
}
function disableSettingsButton(){
    let button = document.getElementById("settings-button");
    button.disabled = true;
}
function enableSettingsButton(){
    let button = document.getElementById("settings-button");
    button.disabled = false;
}
function enableGapButtton(){
    let button = document.getElementById("gap-button");
    button.disabled = false;
}
function resetButton(){
    let button = document.getElementById("race-button");
    button.disabled = false;
    button.textContent = "Restart";
}

//API Communication
async function requestSessionData() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "GET",
            url: "/getSessionData",
            dataType: "json",
            success: function (sessionDataJSON) {
                if (!drivers) {
                    oldDriversPositions = sessionDataJSON.driversList.map(d => d.fullName);
                } else {
                    oldDriversPositions = drivers.map(d => d.fullName);
                }
                drivers = sessionDataJSON.driversList;
                raceStatus = sessionDataJSON.raceStatus;
                currentLap = sessionDataJSON.currentLap;
                lapAmount = sessionDataJSON.lapAmount;
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}
//Race control
async function startRace() {
    return new Promise((resolve, reject) => {
        $.post("/startRace")
            .done(() => resolve())
            .fail(error => reject(error));
    });
}
async function restartRace() {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: "POST",
            url: "/restartRace",
            success: function () {
                resolve();
            },
            error: function (error) {
                console.error("Error occurred:", error);
                reject(error);
            },
        });
    });
}