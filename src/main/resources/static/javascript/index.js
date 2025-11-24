let intervalId;
let lapTime = 0;
let currentSector = 0;
let timerElement;
let sectorRating = 0;
let sectorsTime;
let initialPosition;
let frozenDuration = 0;
let frozen = false;
let randomColor;

const freezeDuration = 2000;
const rootStyles = getComputedStyle(document.documentElement);

const drivers = [
  { name: "HAMILTON", teamColor: "--ferrari-color" },
  { name: "LECLERC", teamColor: "--ferrari-color" },
  { name: "VERSTAPPEN", teamColor: "--redbull-color" },
  { name: "TSUNODA", teamColor: "--redbull-color" },
  { name: "RUSSEL", teamColor: "--mercedes-color" },
  { name: "ANTONELLI", teamColor: "--mercedes-color" },
  { name: "PIASTRI", teamColor: "--mclaren-color" },
  { name: "NORRIS", teamColor: "--mclaren-color" },
  { name: "ALONSO", teamColor: "--astonmartin-color" },
  { name: "STROLL", teamColor: "--astonmartin-color" },
  { name: "GASLY", teamColor: "--alpine-color" },
  { name: "COLAPINTO", teamColor: "--alpine-color" },
  { name: "SAINZ", teamColor: "--williams-color" },
  { name: "ALBON", teamColor: "--williams-color" },
  { name: "BEARMAN", teamColor: "--haas-color" },
  { name: "OCON", teamColor: "--haas-color" },
  { name: "HADJAR", teamColor: "--racingbulls-color" },
  { name: "LAWSON", teamColor: "--racingbulls-color" },
  { name: "HULKENERG", teamColor: "--sauber-color" },
  { name: "BORTOLETO", teamColor: "--sauber-color" }
]

const sectorColors = [
    {color: "yellow", colorVar: "--worse-time-color", gradientVar:"--yellow-sector-gradient"},
    {color: "green", colorVar: "--better-time-color", gradientVar:"--green-sector-gradient"},
    {color: "purple", colorVar: "--better-time-color", gradientVar:"--purple-sector-gradient"}
]

$(document).ready(async function () {
    console.log("Page Loaded Successfully");
    initializeSectors();
    initalizeDriverData();
    initalizeTimeData();
    intervalId = setInterval(updateTimer, 100);
});

function initializeSectors(){
    // const s1 = getRandomNumberInclusive(5000, 10000);
    // const s2 = s1 + getRandomNumberInclusive(5000, 10000);
    // const s3 = s2 + getRandomNumberInclusive(5000, 10000);
    const s1 = getRandomNumberInclusive(20000, 45000);
    const s2 = s1 + getRandomNumberInclusive(20000, 30000);
    const s3 = s2 + getRandomNumberInclusive(20000, 30000);
    sectorsTime = [s1, s2, s3]
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
    if(frozen){
        freezeTimer();
    }
    if(currentSector <=2 && !frozen){
        lapTime += 100;
        if(lapTime > sectorsTime[currentSector]){
            setSectorColor(currentSector);
            currentSector++;
            if(currentSector <3){
                frozen = true;
            }
            frozenDuration = 0;
        }
        timerElement.textContent = parseTime(lapTime);
    }else if(currentSector > 2){
        clearInterval(intervalId);
        timerElement.textContent = parseFinalTime(lapTime);
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
            positionElement.textContent = getRandomNumberInclusive(1,5);
        }
        if(sectorRating === 30){
            positionElement.textContent = 1;
        }
    }
}
function updateFrozenTimer(){
    frozenDuration += 100;
    if(frozenDuration >= freezeDuration){
        frozen = false;
        lapTime += frozenDuration;
        timerElement.style.color = rootStyles.getPropertyValue('--secondary-white-color');
        clearInterval(intervalId);
        intervalId = setInterval(updateTimer, 100)
    }
}
function freezeTimer(){
    timerElement.textContent = parseFinalTime(lapTime);
    const color = rootStyles.getPropertyValue(randomColor.colorVar).trim()
    timerElement.style.color = color;
    clearInterval(intervalId);
    intervalId = setInterval(updateFrozenTimer, 100);
}
function setSectorColor(currentSector){
    const randomIndex = Math.floor(Math.random() * sectorColors.length);
    randomColor = sectorColors[randomIndex];
    const sectorElement = document.getElementById('sector-' + (currentSector+1));

    if(randomColor.color === "purple"){
        sectorRating += 10;
    }else if(randomColor.color === "green"){
        sectorRating += 7;
    }

    const gradient = rootStyles.getPropertyValue(randomColor.gradientVar).trim();
    sectorElement.style.setProperty('--sector-before-width', '0')
    sectorElement.style.setProperty('--sector-color', gradient);
    void sectorElement.offsetWidth;
    sectorElement.style.setProperty('--sector-before-width', '100%');
}

function parseTime(ms) {
  const minutes = Math.floor(ms / 60000);
  const seconds = Math.floor((ms % 60000) / 1000);
  const tenths = Math.floor((ms % 1000) / 100);

  const formattedSeconds = seconds < 10 ? "0" + seconds : seconds;

  return `${minutes}.${formattedSeconds}.${tenths}`;
}
function parseFinalTime(ms) {
    ms += Math.floor(Math.random() * 999);
    const minutes = Math.floor(ms / 60000);
    const seconds = Math.floor((ms % 60000) / 1000);
    const milliseconds = ms % 1000;

    const formattedSeconds = seconds < 10 ? "0" + seconds : seconds;
    const formattedMilliseconds = milliseconds.toString().padStart(3, "0");

    return `${minutes}.${formattedSeconds}.${formattedMilliseconds}`;
}
function getRandomNumberInclusive(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}