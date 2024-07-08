document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const email = formData.get('email');
    const password = formData.get('password');

    const apiUrl = 'http://localhost:8080/api/login';

    const xhr = new XMLHttpRequest();
    xhr.open('POST', apiUrl);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function() {
        if (xhr.status === 200) {
            document.getElementById('message').innerHTML = '<p>Login successful!</p>';
        } else {
            document.getElementById('message').innerHTML = '<p>Login failed. Please check your credentials.</p>';
        }
    };

    xhr.onerror = function() {
        console.error('Login error:', xhr.statusText);
        document.getElementById('message').innerHTML = '<p>Login failed. Please check your credentials.</p>';
    };

    const data = JSON.stringify({ email: email, password: password });
    xhr.send(data);
});




