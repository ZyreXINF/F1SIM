let intervalId;
let lapTime = 0;
let currentSector = 0;
let timerElement;
// let setSectors = [];
let sectorRating = 0;
let sectorsTime;
let initialPosition;

const drivers = [
  { name: "HAM", teamColor: "--ferrari-color" },
  { name: "LEC", teamColor: "--ferrari-color" },
  { name: "VER", teamColor: "--redbull-color" },
  { name: "TSU", teamColor: "--redbull-color" },
  { name: "RUS", teamColor: "--mercedes-color" },
  { name: "ANT", teamColor: "--mercedes-color" },
  { name: "PIA", teamColor: "--mclaren-color" },
  { name: "NOR", teamColor: "--mclaren-color" },
  { name: "ALO", teamColor: "--astonmartin-color" },
  { name: "STR", teamColor: "--astonmartin-color" },
  { name: "GAS", teamColor: "--alpine-color" },
  { name: "COL", teamColor: "--alpine-color" },
  { name: "SAI", teamColor: "--williams-color" },
  { name: "ALB", teamColor: "--williams-color" },
  { name: "BEA", teamColor: "--haas-color" },
  { name: "OCO", teamColor: "--haas-color" },
  { name: "HAD", teamColor: "--racingbulls-color" },
  { name: "LAW", teamColor: "--racingbulls-color" },
  { name: "HUL", teamColor: "--sauber-color" },
  { name: "BOR", teamColor: "--sauber-color" }
]

const sectorColors = [
    {color: "yellow", var:"--yellow-sector-gradient"},
    {color: "green", var:"--green-sector-gradient"},
    {color: "purple", var:"--purple-sector-gradient"}
]

$(document).ready(async function () {
    console.log("Page Loaded Successfully");
    initializeSectors();
    initalizeDriverData();
    initalizeTimeData();
    intervalId = setInterval(updateTimer, 100);
});

function initializeSectors(){
    const s1 = getRandomNumberInclusive(20000, 45000);
    const s2 = s1 + getRandomNumberInclusive(20000, 30000);
    const s3 = s2 + getRandomNumberInclusive(20000, 30000);
    sectorsTime = [s1, s2, s3]
}
function getRandomNumberInclusive(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function initalizeDriverData(driverDataElement){
    const randomIndex = Math.floor(Math.random() * drivers.length);
    const randomDriver = drivers[randomIndex];

    const positionElement = document.getElementById('position');
    initialPosition = getRandomNumberInclusive(11,20);
    positionElement.textContent = initialPosition;

    const nameElement = document.getElementById('name');
    nameElement.textContent = randomDriver.name;

    const teamColorElement = document.getElementById('team-color');
    const rootStyles = getComputedStyle(document.documentElement);
    const color = rootStyles.getPropertyValue(randomDriver.teamColor).trim();
    teamColorElement.style.background = color;

    
}

function initalizeTimeData(timeDataElement){
    timerElement=document.getElementById('lap-time');
}

function updateTimer(){
    if(currentSector <=2){
        lapTime += 100;
        if(lapTime > sectorsTime[currentSector]){
            setSectorColor(currentSector);
            currentSector++;
        }
        timerElement.textContent = parseTime(lapTime);
    }else{
        clearInterval(intervalId);
        const positionElement = document.getElementById('position');
        if(sectorRating > 0 && sectorRating < 10){
            positionElement.textContent = initialPosition - getRandomNumberInclusive(0,3);
        }
        if(sectorRating >= 10 && sectorRating < 15){
            positionElement.textContent = initialPosition - getRandomNumberInclusive(1,7);
        }
        if(sectorRating >= 15 && sectorRating < 20){
            positionElement.textContent = initialPosition - getRandomNumberInclusive(6,10);
        }
        if(sectorRating >= 20 && sectorRating < 30){
            positionElement.textContent = getRandomNumberInclusive(1,7);
        }
        if(sectorRating === 30){
            positionElement.textContent = 1;
        }
    }
}

function parseTime(ms) {
  const minutes = Math.floor(ms / 60000);
  const seconds = Math.floor((ms % 60000) / 1000);
  const tenths = Math.floor((ms % 1000) / 100);

  const formattedSeconds = seconds < 10 ? "0" + seconds : seconds;

  return `${minutes}.${formattedSeconds}.${tenths}`;
}

function setSectorColor(currentSector){
    const randomIndex = Math.floor(Math.random() * sectorColors.length);
    const randomColor = sectorColors[randomIndex];
    const sectorElement = document.getElementById('sector-' + (currentSector+1));

    // setSectors[0] = randomColor.color;
    // console.log(setSectors);
    if(randomColor.color === "purple"){
        sectorRating += 10;
    }else if(randomColor.color === "green"){
        sectorRating += 7;
    }

    const rootStyles = getComputedStyle(document.documentElement);
    const gradient = rootStyles.getPropertyValue(randomColor.var).trim();
    sectorElement.style.background = gradient;
}