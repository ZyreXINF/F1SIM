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
        let track = await getFullTrackData(trackId);
        updateTrack(track, trackId);
        closeMenu();
    });
    $('#submit-button').click(async function() {
        const trackName = $('#track-wrap').data('track');
        const racemode = document.querySelector('input[name="racemode"]:checked').value;
        const weather = document.querySelector('input[name="condition"]:checked').value;
        let equalPerformance = false;
        if(document.getElementById('extra-equal-car-performance').checked){
            equalPerformance = true;
        }

        const data = {
            trackName: trackName,
            raceMode: racemode,
            weather: weather,
            equalPerformance: equalPerformance
        };

        let settingsSet = await submitData(JSON.stringify(data));
        if(settingsSet){
            window.location.href = "/race"; 
        }
    });
});

//menu logic
function openMenu(){
    trackMenuOpened = true;
    trackMenu.style.display = "flex";
}
function closeMenu(){
    trackMenuOpened = false;
    trackMenu.style.display = "none";
}
function updateTrack(track, trackId){
    console.log(track);
    $('#track-wrap').data('track', trackId);
    $('#track-image').attr('src', 'images/tracks/' + track.trackImageName + '.webp');
    $('#track-name').text(track.trackName);
    jQuery("#track-name").fitText(textFitMultiplier);   
}

//API requests
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
function submitData(data){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "/setupSettings",
            type: "POST",
            contentType: "application/json",
            data: data,
            success: function (success) {
                resolve(success);
            },
            error: function (err) {
                console.error(err);
            }
        });
    });
}