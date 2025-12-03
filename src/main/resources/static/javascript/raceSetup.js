let trackMenuOpened = false; 
let trackMenu;

const textFitMultiplier = 1.1;

$(document).ready(async function () {
    jQuery("#track-name").fitText(textFitMultiplier);
    trackMenu = document.getElementById("popup-menu");
    $('.track-wrap').click(async function () {
        openMenu();
    });
    $('#popup-exit-button').click(async function (){
        closeMenu();
    })
    $('.popup-track-wrap').click(async function () {
        const trackId = $(this).data('track');
        track = await getFullTrackData(trackId);
        updateTrack(track);
        closeMenu();
    });
});

function openMenu(){
    trackMenuOpened = true;
    trackMenu.style.display = "flex";
}

function closeMenu(){
    trackMenuOpened = false;
    trackMenu.style.display = "none";
}

async function getFullTrackData(trackId) {
    return new Promise((resolve, reject) => {
    $.get('/' + trackId)
      .done(function(data) {
        resolve(data);  
      })
      .fail(function(error) {
        reject(error);  
      });
  });
}

function updateTrack(track){
    console.log(track);
    $('#track-image').attr('src', 'images/tracks/' + track.trackImageName + '.webp');
    $('#track-name').text(track.trackName);
    jQuery("#track-name").fitText(textFitMultiplier);   
}