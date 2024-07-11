function logout(){
    localStorage.removeItem('token');
    window.location.href = "../hotel/login/index.html";
}