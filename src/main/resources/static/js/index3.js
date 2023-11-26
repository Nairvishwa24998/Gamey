function toggleLoginSignup() {
    let loginHeading = document.getElementById('loginHeading');
    let signUpHeading = document.getElementById('signUpHeading');

    let login = document.getElementById('login');
    let signUp = document.getElementById('signUp');

    let loginButton = document.getElementById('loginButton');
    let signUpButton = document.getElementById('signUpButton');

    // Check the current display style and toggle it
    if (loginHeading.style.display === 'none') {
        signUpHeading.style.display = 'none';
        loginHeading.style.display = 'block';
        login.setAttribute('style', 'display: block !important;');
        signUp.setAttribute('style', 'display: none !important;');
        loginButton.setAttribute('style', 'display: block !important;');
        signUpButton.setAttribute('style', 'display: none !important;');
    } else {
        signUpHeading.style.display = 'block';
        loginHeading.style.display = 'none';
        login.setAttribute('style', 'display: none !important;');
        signUp.setAttribute('style', 'display: block !important;');
        loginButton.setAttribute('style', 'display: none !important;');
        signUpButton.setAttribute('style', 'display: block !important;');
    }
}


function logInUser(){
    document.getElementById('loginButton').type = "submit";

}
function signUpUser(){
    document.getElementById('loginButton').type = "button";
    document.getElementById('signUpButton').type = "button";
      const userInfo = {
                   username: null,
                   password: null
                };
      let username = document.getElementById('username').value;
      let password = document.getElementById('password').value;
      userInfo.username = username;
      userInfo.password = password;

     fetch('/gamey/createuser', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(userInfo)
                        }).then(response=>{
                            if (!response.ok) {
                                   window.location.href = './login';
                            }
                            document.getElementById('signUpButton').type = "submit";
                        })
                        .catch(error => {
                                console.error('Error:', error);
                                // Handle errors (e.g., show user-friendly message)
                            });

}

