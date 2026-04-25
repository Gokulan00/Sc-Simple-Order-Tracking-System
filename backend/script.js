function signup(){

let email = document.getElementById("email").value;
let password = document.getElementById("password").value;

if(!email || !password){
 alert("Fill all fields");
 return;
}

localStorage.setItem("email", email);
localStorage.setItem("password", password);

alert("Signup successful");

}

function login(){

let email = document.getElementById("email").value;
let password = document.getElementById("password").value;

let storedEmail = localStorage.getItem("email");
let storedPassword = localStorage.getItem("password");

if(email == storedEmail && password == storedPassword){
 alert("Login success");
 window.location = "place.html";
}
else{
 alert("Invalid credentials");
}

}