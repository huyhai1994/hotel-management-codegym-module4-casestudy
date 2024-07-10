// class UserForm {
//     constructor(formId, messageId, avatarId) {
//         this.form = $(formId);
//         this.message = $(messageId);
//         this.avatar = $(avatarId);
//         $(document).ready(() => this.getUserData());
//         this.form.on('submit', (e) => this.handleSubmit(e));
//     }
//
//     getUserData() {
//         const userId = $('#id').val() || 1; // Mặc định lấy ID 1 nếu input rỗng
//         console.log(userId)
//         $.ajax({
//             url: `http://localhost:8080/users/get-by-id/${userId}`,
//             method: 'GET',
//             success: (response) =>{
//                 console.log(response.user.email)
//                 this.fillForm(response)}
//             ,
//             error: (error) => this.handleError(error)
//         });
//     }
//
//     fillForm(response) {
//         console.log(response.user.avatar)
//         $('#email').val(response.user.email);
//     }
// }
// $(document).ready(function() {
//     new UserForm('.form-login', '#message', '#avatarPreview');
// });
//
//
//
//
// class UserForm {
//     constructor(formId, messageId) {
//         this.form = $(formId);
//         this.message = $(messageId);
//         this.form.on('submit', (e) => this.handleSubmit(e));
//     }
//
//     handleSubmit(e) {
//         e.preventDefault();
//
//         const email = $('#email').val();
//         const newPassword = $('#password').val();
//         const confirmPassword = $('#newpassword').val();
//
//         if (newPassword !== confirmPassword) {
//             this.showMessage("Mật khẩu mới không khớp");
//             return;
//         }
//
//         const data = {
//             email: email,
//             newPassword: newPassword,
//             confirmPassword: confirmPassword
//         };
//
//         $.ajax({
//             type: 'POST',
//             url: 'http://localhost:8080/users/update-password',
//             contentType: 'application/json',
//             data: JSON.stringify(data),
//             success: (response) => {
//                 this.showMessage(response.message);
//                 this.form[0].reset();
//             },
//             error: (error) => {
//                 this.showMessage(error.responseJSON.message);
//             }
//         });
//     }
//
//     showMessage(message) {
//         this.message.text(message);
//     }
// }
//
// $(document).ready(function() {
//     new UserForm('.form-login', '#message');
// });




class UserForm {
    constructor(formId, messageId, avatarId) {
        this.form = $(formId);
        this.message = $(messageId);
        this.avatar = $(avatarId);
        $(document).ready(() => {
            this.getUserData();
            this.form.on('submit', (e) => this.handleSubmit(e));
        });
    }

    getUserData() {
        const userId = $('#id').val() || 1; // Mặc định lấy ID 1 nếu input rỗng
        console.log(userId);
        $.ajax({
            url: `http://localhost:8080/users/get-by-id/${userId}`,
            method: 'GET',
            success: (response) => {
                console.log(response.user.email);
                this.fillForm(response);
            },
            error: (error) => this.handleError(error)
        });
    }

    fillForm(response) {
        console.log(response.user.avatar);
        $('#email').val(response.user.email);
    }

    handleSubmit(e) {
        e.preventDefault();

        const email = $('#email').val();
        const newPassword = $('#password').val();
        const confirmPassword = $('#newpassword').val();

        if (newPassword !== confirmPassword) {
            this.showMessage("Mật khẩu mới không khớp");
            return;
        }

        const data = {
            email: email,
            newPassword: newPassword,
            confirmPassword: confirmPassword
        };

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/users/update-password/${userId}',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: (response) => {
                this.showMessage(response.message);
                this.form[0].reset();
            },
            error: (error) => {
                this.showMessage(error.responseJSON.message);
            }
        });
    }

    showMessage(message) {
        this.message.text(message);
    }
}

$(document).ready(function() {
    new UserForm('.form-login', '#message', '#avatarPreview');
});
