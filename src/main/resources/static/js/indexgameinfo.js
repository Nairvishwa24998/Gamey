function addToWishlist(id){
    const operation = 1;
    const rawgid = id.substring(8);
         fetch(`/gamey/modifywishlist/${rawgid}/${operation}`, {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json',
                                }
                            }).then(response=>{
                             if (response.ok) {
                                 let container = document.getElementById("messageContainer");
                                 let message = document.getElementById("message");
                                 let messageHolder = document.getElementById("messageHolder");
                                 messageHolder.className = "alert alert-success"
                                 message.innerHTML = "The item was successfully added to Wishlist :)";
                                 container.style.display = "block";
                                 setTimeout(() => {
                                                   let container = document.getElementById("messageContainer");
                                                  container.style.display = "none";
                                                  }, 5000); // 5000 milliseconds = 5 seconds
                                }
                                if (response.status === 400){
                    let container = document.getElementById("messageContainer");
                                                             let message = document.getElementById("message");
                                                             let messageHolder = document.getElementById("messageHolder");
                                                             messageHolder.className = "alert alert-info"
                                                             message.innerHTML = "This item is already in your wishlist :P";
                                                             container.style.display = "block";
                                                             setTimeout(() => {
                                                                               let container = document.getElementById("messageContainer");
                                                                              container.style.display = "none";
                                                                              }, 5000); // 5000 milliseconds = 5 seconds


                                }
                                else{


                                                                // The request was unsuccessful
                                                                 console.error('Request failed with status:', response.status);
                                                                // Handle non-successful response


                            }})
                            .catch(error => {
                             let container = document.getElementById("messageContainer");
                                                             let message = document.getElementById("message");
                                                             let messageHolder = document.getElementById("messageHolder");
                                                             messageHolder.className = "alert alert-danger"
                                                             message.innerHTML = "The item could not be added to wishList :(";
                                                             container.style.display = "block";
                                                             setTimeout(() => {
                                                                               let container = document.getElementById("messageContainer");
                                                                              container.style.display = "none";
                                                                              }, 5000); // 5000 milliseconds = 5 seconds



                                    console.error('Error:', error);
    //                                 Handle errors (e.g., show user-friendly message)
                                });
    }

    function addToCart(id){
    const operation = 1;
    const rawgid = id.substring(4);
         fetch(`/gamey/modifycart/${rawgid}/${operation}`, {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json',
                                }
                            }).then(response=>{
                             if (response.ok) {
                                 let container = document.getElementById("messageContainer");
                                 let message = document.getElementById("message");
                                 let messageHolder = document.getElementById("messageHolder");
                                 messageHolder.className = "alert alert-success"
                                 message.innerHTML = "The item was successfully added to cart :)";
                                 container.style.display = "block";
                                 setTimeout(() => {
                                                   let container = document.getElementById("messageContainer");
                                                  container.style.display = "none";
                                                  }, 5000); // 5000 milliseconds = 5 seconds
                                } else {
                                    // The request was unsuccessful
                                     console.error('Request failed with status:', response.status);
                                    // Handle non-successful response
                                }
                            })
                            .catch(error => {
                             let container = document.getElementById("messageContainer");
                                                             let message = document.getElementById("message");
                                                             let messageHolder = document.getElementById("messageHolder");
                                                             messageHolder.className = "alert alert-danger"
                                                             message.innerHTML = "The item could not be added to cart :(";
                                                             container.style.display = "block";
                                                             setTimeout(() => {
                                                                               let container = document.getElementById("messageContainer");
                                                                              container.style.display = "none";
                                                                              }, 5000); // 5000 milliseconds = 5 seconds



                                    console.error('Error:', error);
    //                                 Handle errors (e.g., show user-friendly message)
                                });

    }


    function buyGame(){

    }






