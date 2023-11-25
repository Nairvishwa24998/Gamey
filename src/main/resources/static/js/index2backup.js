// Define the URL
const url = 'https://api.rawg.io/api/games?page_size=72&key=0191586e29d04fa09769937e35accbbb&metacritic=80,100&ordering=-rating&publishers=electronic-arts, ubisoft-entertainment,electronic-arts,2k-games,microsoft-studios,valve,rockstar-games';

let maxGameCount = 30;

function generateHtmlIds(number){
    let cardImageId = "cardImage" + number;
    let cardTitleId = "cardTitle" + number;
    let cardRatingId = "cardRating" + number;
    return [cardImageId, cardTitleId, cardRatingId];
}

function getPlatformNames(platformList){
    let platforms = [];
      for (let j = 0; j < platformList.length; j++) {
        platforms.push(platformList[j].platform.name);
      }
    return platforms;
}


const elements = document.querySelectorAll('.card');
  elements.forEach(element => {
    element.innerHTML = '<div style="width:100%;height:0;padding-bottom:100%;position:relative;"><iframe src="https://giphy.com/embed/QCE3HQTLUzWt6q0R6b" width="100%" height="100%" style="position:absolute" frameBorder="0" class="giphy-embed" allowFullScreen></iframe></div><p><a href="https://giphy.com/gifs/fborfw-comic-official-for-better-or-worse-QCE3HQTLUzWt6q0R6b"></a></p>'
  });
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
            const games = data.results;
            const elements = document.querySelectorAll('.card');
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
                    preview: null,
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
                gameInfo.platforms = getPlatformNames(games[i].platforms);
                const backgroundImage = games[i].background_image;
                const nameOriginal = games[i].name;
                const rating = games[i].rating;
                let gameId = games[i].id;
//                <a href = "gamey/gameinfo/${gameId}">
                elements[i].innerHTML = `<a><div class="card-image"><img src="${backgroundImage}"class="card-img-top" alt="" id="${cardImageId}"></div><div class="card-body"><ul class = "no-bullet-list"><li class="gameList"><p class="card-title" id="${cardTitleId}">${nameOriginal}</p></li><li class="gameList"><p class="card-title" id="${cardRatingId}">${rating}</p></li></ul></div></a>`;
                elements[i].addEventListener(
                'click', (event)=>
                {
                    fetch(
                    "/gamey/gameinfo", {  method: 'POST',
                                                headers: {
                                                    'Content-Type': 'application/json',
                                                },
                                                body: JSON.stringify(gameInfo)}
                    ).then(response => {
                                   if (!response.ok) {
                                       throw new Error('Network response was not ok ' + response.statusText);
                                   }
                                   return response.json(); // or response.json() if your server responds with JSON
                               }).then(
                                    data => {data.window.location.href = '/game/gameinfo/';}
                               )
                }
                );
                }
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });});
