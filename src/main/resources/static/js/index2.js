// Define the URL
const url = 'https://api.rawg.io/api/games?page_size=72&key=0191586e29d04fa09769937e35accbbb&metacritic=80,100&ordering=-rating&publishers=electronic-arts, ubisoft-entertainment,electronic-arts,2k-games,microsoft-studios,valve,rockstar-games';
const urlDescription = 'https://api.rawg.io/api/games/${id}?key=0191586e29d04fa09769937e35accbbb';
const urlPreview = 'https://api.rawg.io/api/games/${id}/movies?key=0191586e29d04fa09769937e35accbbb'

//let maxGameCount = 30;

const platformImageMapper = {
    playstation5:"/images/playstation5.svg",
    pc:"/images/pc.svg",
    playstation4:"/images/playstation4.svg",
    playstation3:"/images/playstation3.svg",
    playstation2:"/images/playstation2.svg",
    "ps-vita":"/images/playstationvita.svg",
    xbox360:"/images/xbox360.svg",
    "xbox-one":"/images/xbox1.svg",
    "xbox-series-x":"/images/xboxseriesx.svg",
    "nintendo-64":"/images/nintendo",
    "nintendo-switch":"/images/nintendo-switch.svg",
    "nintendo-3ds":"/images/nintendo-3ds-ish.svg",
    "nintendo-ds":"/images/nintendo-ds-ish-svg",
    "android":"/images/android.svg",
    ios:"/images/apple-icon.svg",
     mac:"/images/apple-icon.svg",
     macos:"/images/apple-icon.svg",
     macintosh:"/images/apple-icon.svg",
     "apple-appstore":"/images/apple-icon.svg",
     linux:"/images/linux.svg",
     "xbox-old":"/images/xboxold.svg",
     gamecube: "/images/nintendo-gamecube.svg"
}


function generateHtmlIds(number){
    let cardImageId = "cardImage" + number;
    let cardTitleId = "cardTitle" + number;
    let cardRatingId = "cardRating" + number;
    return [cardImageId, cardTitleId, cardRatingId];
}

function getPlatformImages(platformList){
    let platforms = [];
      for (let j = 0; j < platformList.length; j++) {
        if(!platforms.includes(platformImageMapper[platformList[j].platform.slug])){
        let platformSlug = platformList[j].platform.slug;
        platforms.push(platformImageMapper[platformSlug]);}
      }
    platforms = platforms.sort();
    return platforms;
}

function getOtherImages(otherImagesList){
    let images = [];
    for(i = 1; i < otherImagesList.length; i ++){
    images.push(otherImagesList[i].image)
    }
    return images;
}


//const elements = document.querySelectorAll('.card');
let loadScreen = document.querySelector('.row');
loadScreen.innerHTML = '<div style="width:100%;height:0;padding-bottom:100%;position:relative;"><iframe src="https://giphy.com/embed/QCE3HQTLUzWt6q0R6b" width="100%" height="100%" style="position:absolute" frameBorder="0" class="giphy-embed" allowFullScreen></iframe></div><p><a href="https://giphy.com/gifs/fborfw-comic-official-for-better-or-worse-QCE3HQTLUzWt6q0R6b"></a></p>';

//  elements.forEach(element => {
//    element.innerHTML = '<div style="width:100%;height:0;padding-bottom:100%;position:relative;"><iframe src="https://giphy.com/embed/QCE3HQTLUzWt6q0R6b" width="100%" height="100%" style="position:absolute" frameBorder="0" class="giphy-embed" allowFullScreen></iframe></div><p><a href="https://giphy.com/gifs/fborfw-comic-official-for-better-or-worse-QCE3HQTLUzWt6q0R6b"></a></p>'
//  });
let publishers = ["ubisoft-entertainment", "electronic-arts", "2k-games","microsoft-studios", "valve", "rockstar-games"]
// Make the fetch request
document.addEventListener('DOMContentLoaded', (event) => {
fetch(url)
    .then(response => {
        // Check if the request was successful
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
            let loadScreen = document.querySelector('.row');
            loadScreen.innerHTML = '';
            const games = data.results;
            const maxGameCount = games.length;
//            const elements = document.querySelectorAll('.card');
            for (let i = 0; i < maxGameCount; i ++){
            //GetAll GetAll GetAll  GetAll     GetAll            GetAll       GetClips GetId
            //GameId Name,  Rating, Platforms, BackgroundImage,  ReleaseDate  Preview, Description
            //id     name,  rating, platforms,   background_image, released     preview, description
                let htmlIds = generateHtmlIds(i+1);
                let cardImageId = htmlIds[0];
                let cardTitleId = htmlIds[1];
                let cardRatingId = htmlIds[2];
                const gameInfo = {
                    id : null,
                    name: null,
                    rating: null,
                    otherImages: null,
                    background_image: null,
                    released: null,
                    platforms: null,
                    description: null
                };
                gameInfo.id = games[i].id;
                gameInfo.name = games[i].name;
                gameInfo.rating = games[i].rating;
                gameInfo.background_image = games[i].background_image;
                gameInfo.released = games[i].released;
                gameInfo.platforms = getPlatformImages(games[i].platforms);
                gameInfo.otherImages = getOtherImages(games[i].short_screenshots);
                const backgroundImage = games[i].background_image;
                const nameOriginal = games[i].name;
                const rating = games[i].rating;
                let gameId = games[i].id;
//                <a href = "gamey/gameinfo/${gameId}">
                const newDiv = document.createElement('div');
                newDiv.classList.add('col-12', 'col-sm-6', 'col-md-4', 'col-lg-3', 'col-xl-2');
                newDiv.innerHTML = `<div class="card"><a><div class="card-image"><img src="${backgroundImage}"class="card-img-top" alt="" id="${cardImageId}"></div><div class="card-body"><ul class = "no-bullet-list"><li class="gameList"><p class="card-title" id="${cardTitleId}">${nameOriginal}</p></li><li class="gameList"><p class="card-title" id="${cardRatingId}">${rating}</p></li></ul></div></a></div>`;
                loadScreen.appendChild(newDiv);
                console.log(gameInfo);
                newDiv.addEventListener('click',()=>{
                    fetch('/gamey/gameinfo', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(gameInfo)
                    })
                    .then(response => {

                        if (!response.ok) {
                            throw new Error('Network response was not ok ' + response.statusText);
                        }
                       window.location.href = './gameinfo';
                    })

                })
                }
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });});
