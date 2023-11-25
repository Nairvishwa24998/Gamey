let images = ['/images/BackgroundImage - Copy.png', '/images/BackgroundImage2 - Copy.png', '/images/BackgroundImage3 - Copy.png', '/images/BackgroundImage4 - Copy.png']
let index = 0;


setInterval(()=>{
    document.body.style.backgroundImage = `url('${images[index]}')`;
    index = (index + 1) % images.length
}, 2000)