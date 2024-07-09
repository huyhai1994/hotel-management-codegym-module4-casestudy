$(document).ready(function() {
    $('#updateUserForm').submit(function(event) {
        event.preventDefault();
        let formData = {
            name: $('#name').val(),
            birthday: $('#birthday').val(),
            phoneNumber: $('#phoneNumber').val(),
            avatar: $('#avatar').val()
        };

        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: 'http://localhost:8080/',
            data: JSON.stringify(formData),
            success: function(response) {
                $('#message').text('User information updated successfully!');
                console.log('User updated:', response);
             },
            error: function(error) {
                $('#message').text('Error updating user information!');
                console.error('Error updating user:', error);
            }
        });
    });
});
