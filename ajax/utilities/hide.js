
function showSignIn(){

}
function hideSignIn (){
    $('#signin').hide();
}
function showSignUp(){

}
function hideSignUp(){
$('#signup').hide();
}
function hideSignOut(){

}
function showLogout(){

}
function hideLogout(){
    $('#logout').hide();
}

function hideAddRoom(){
        let dataUser = JSON.parse(localStorage.getItem('token'));
        if (dataUser && dataUser.authorities && dataUser.authorities[0].authority === "ROLE_USER"){
            $('.btn-primary').hide();
        }

}
