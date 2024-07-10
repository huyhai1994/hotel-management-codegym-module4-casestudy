class RoomForm {
    constructor(formId, imageId, messageId) {
        this.form = $(formId);
        this.image = $(imageId);
        this.message = $(messageId);

        this.form.on('submit', (e) => this.handleSubmit(e));
    }

    handleSubmit(e) {
        e.preventDefault();

        const roomData = {
            type: $('#type').val(),
            price: $('#price').val(),
            description: $('#description').val(),
            image: $('#image').val()
        };

        this.sendRequest(roomData);
    }

    sendRequest(roomData) {
        $.ajax({
            url: '',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(roomData),
            success: (response) => this.handleSuccess(response),
            error: (error) => this.handleError(error)
        });
    }

    handleSuccess(response) {
        this.message.text('Room created successfully!');
        this.message.addClass('text-success');
        this.updateImage(response.image);
    }

    handleError(error) {
        this.message.text('Failed to create room. Please try again.');
        this.message.addClass('text-danger');
    }

    updateImage(imageUrl) {
        this.image.attr('src', imageUrl);
    }
}

$(document).ready(function() {
    new RoomForm('#signupForm', '#roomImage', '#message');
});
