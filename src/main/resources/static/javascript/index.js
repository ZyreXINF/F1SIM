let intervalId;
let lapTime = 0;
let currentSector = 0;
let timerElement;

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
    "--yellow-sector-gradient",
    "--green-sector-gradient",
    "--purple-sector-gradient"
]

const sectorsTime = [
    23275, //23275
    58705, //35430
    89420  //30715
];

$(document).ready(async function () {
    console.log("Page Loaded Successfully");
    initalizeDriverData();
    initalizeTimeData();
    intervalId = setInterval(updateTimer, 100);
});

function initalizeDriverData(driverDataElement){
    const randomIndex = Math.floor(Math.random() * drivers.length);
    const randomDriver = drivers[randomIndex];

    const positionElement = document.getElementById('position');
    positionElement.textContent = Math.floor(Math.random() * drivers.length+1);

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

        lapTime = lapTime + Math.floor(Math.random() * 430);
        timerElement.textContent = parseTime(lapTime);

        const positionElement = document.getElementById('position');
        positionElement.textContent = 1;
    }
}

function setSectorColor(currentSector){
    const randomIndex = Math.floor(Math.random() * sectorColors.length);
    const randomColor = sectorColors[randomIndex];
    const sectorElement = document.getElementById('sector-' + (currentSector+1));

    const rootStyles = getComputedStyle(document.documentElement);
    const gradient = rootStyles.getPropertyValue(randomColor).trim();

    sectorElement.style.background = gradient;
}

function parseTime(ms) {
  const minutes = Math.floor(ms / 60000);
  const seconds = Math.floor((ms % 60000) / 1000);
  const tenths = Math.floor((ms % 1000) / 100); // one digit for ms

  const formattedSeconds = seconds < 10 ? "0" + seconds : seconds;

  return `${minutes}.${formattedSeconds}.${tenths}`;
}