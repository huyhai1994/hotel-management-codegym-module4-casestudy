$(document).ready(function() {
    $('#signupForm').submit(function(e) {
        e.preventDefault();

        let name = $('#name').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        let password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: '',
            data: {
                name: name,
                email: email,
                phone: phone,
                password: password
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
