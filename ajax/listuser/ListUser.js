$(document).ready(function() {
    $('#fetchUsersBtn').click(function() {
        $.ajax({
            url: 'http://localhost:8080/users/all',
            type: 'GET',
            success: function(response) {
                if (response.statusCode === 200) {
                    populateUserTable(response.userList);
                } else {
                    alert('Error fetching users: ' + response.message);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('Error: ' + textStatus + ' - ' + errorThrown);
            }
        });
    });

    function populateUserTable(users) {
        let userTableBody = $('#userTableBody');
        userTableBody.empty();
        users.forEach(user => {
            let row = `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.phoneNumber}</td>
                    <td><img src="${user.avatar}" alt="Avatar" width="50" height="50"></td>
                    <td>${user.birthday}</td>
                    <td>${user.roles}</td>
                    <td>${formatBookings(user.bookings)}</td>
                </tr>
            `;
            userTableBody.append(row);
        });
    }

    function formatBookings(bookings) {
        if (!bookings || bookings.length === 0) {
            return 'No bookings';
        }
        return bookings.map(booking => `Booking ID: ${booking.id}`).join(', ');
    }
});
