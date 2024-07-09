$(document).ready(function() {
    $('#signupForm').submit(function(e) {
        e.preventDefault();

        let name = $('#name').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        let password = $('#password').val();
        let dateOfBirth = $('#dateOfBirth').val();

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/',
            data: {
                email: email,
                name: name,
                phone: phone,
                password: password,
                dateOfBirth: dateOfBirth

            },
            success: function(response) {
                 if (response.success) {
                    $('#message').text(response.message);
                 } else {
                    $('#message').text(response.message);
                }
            },
            error: function(xhr, status, error) {
                console.error('AJAX Error: ' + status, error);
                $('#message').text('Error occurred, please try again.');
            }
        });
    });
});
