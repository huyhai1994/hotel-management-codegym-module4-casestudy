class UserForm {
    constructor(formId, messageId, avatarId) {
        this.form = $(formId);
        this.message = $(messageId);
        this.avatar = $(avatarId);

        // Lấy dữ liệu người dùng khi tài liệu sẵn sàng
        $(document).ready(() => this.getUserData());

        this.form.on('submit', (e) => this.handleSubmit(e));
    }

    getUserData() {
        const userId = $('#id').val() || 1; // Mặc định lấy ID 1 nếu input rỗng
        console.log(userId)
        $.ajax({
            url: `http://localhost:8080/users/get-by-id/${userId}`,
            method: 'GET',
            success: (response) =>{
                console.log(1)
                console.log(response)
                this.fillForm(response)}
                ,
            error: (error) => this.handleError(error)
        });
    }

    fillForm(response) {
        console.log(response.user.avatar)

        $('#id').val(response.user.id);
        $('#email').val(response.user.email);
        $('#name').val(response.user.name);
        $('#birthday').val(response.user.birthday);
        $('#phone').val(response.user.phoneNumber);
        $('#avatar').val(response.user.avatar);
        this.avatar.attr('src', response.user.avatar);
    }

    handleError(error) {
        this.message.text('Failed to show profile user. Please try again.');
        this.message.addClass('text-danger');
    }
}

$(document).ready(function() {
    new UserForm('.form-login', '#message', '#avatarPreview');
});


