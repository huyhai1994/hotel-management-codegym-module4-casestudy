// TODO: JSON.stringify de ep kieu tu Object
//      sang JSON vi javascript khong ho tro JSON
function login() {
    // lay du lieu
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let user = {
        email: email, password: password,
    };
    $.ajax({
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        url: 'http://localhost:8080/api/auth/login',
        method: 'POST',
        data: JSON.stringify(user),
        success: function (data) {
            // thay vi ghi vao console ghi vao local storage
            // console.log(data)
            localStorage.setItem('token', JSON.stringify(data));
            //     chuyen trang sang customer
            window.location.href = 'hello.html';
        },
    });
}
