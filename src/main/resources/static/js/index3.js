function toggleLoginSignup() {
    let loginHeading = document.getElementById('loginHeading');
    let signUpHeading = document.getElementById('signUpHeading');

    let login = document.getElementById('login');
    let signUp = document.getElementById('signUp');

    // Check the current display style and toggle it
    if (loginHeading.style.display === 'none') {
        signUpHeading.style.display = 'none';
        loginHeading.style.display = 'block';
        login.setAttribute('style', 'display: block !important;');
        signUp.setAttribute('style', 'display: none !important;');
    } else {
        signUpHeading.style.display = 'block';
        loginHeading.style.display = 'none';
        login.setAttribute('style', 'display: none !important;');
        signUp.setAttribute('style', 'display: block !important;');
    }
}