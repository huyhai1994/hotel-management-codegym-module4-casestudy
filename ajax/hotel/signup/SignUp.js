$(document).ready(function () {
    $('#signupForm').submit(function (e) {
        e.preventDefault();

        let name = document.getElementById(
            'name').value;
        let phone = document.getElementById('phone').value;
        let email = document.getElementById('email').value;
        let password = document.getElementById('password').value;
        let birthday = document.getElementById('birthday').value;
        let user = {
            name: name,
            email: email,
            phoneNumber: phone,
            password: password,
            birthday: birthday,
            avatar: '124.jpg'
        }

        $.ajax({
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            url: 'http://localhost:8080/api/auth/register',
            method: 'POST',
            data: JSON.stringify(user),
            success: function (response) {
                if (response.success) {
                    $('#message').text(response.message);
                } else {
                    $('#message').text(response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('AJAX Error: ' + status, error);
                $('#message').text('Error occurred, please try again.');
            }
        });
    });
});